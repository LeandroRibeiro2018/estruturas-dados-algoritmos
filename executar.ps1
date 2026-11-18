<#
.SYNOPSIS
  Escolhe uma aula/prova, compila o módulo e abre o menu interativo.

.EXAMPLE
  .\executar.ps1            # mostra a lista de aulas para escolher
  .\executar.ps1 aula4      # compila e abre o menu da aula 4
  .\executar.ps1 aula4 5    # executa direto o exercício 5 da aula 4

  Modo passo a passo (padrão 300 ms por linha + pausas de ENTER):
  .\executar.ps1 4 -Ritmo 800   # mais devagar
  .\executar.ps1 4 -Ritmo 0     # velocidade normal
#>
param(
    [string]$Modulo,
    [string]$Opcao,
    # Atraso por linha em ms (modo passo a passo); 0 desliga.
    [int]$Ritmo = 300
)

$ErrorActionPreference = 'Stop'
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

$modulos = [ordered]@{
    'aula1' = @{ Pasta = 'aula1';     Titulo = 'Funções, Somatórios e Indução Matemática' }
    'aula2' = @{ Pasta = 'aula2';     Titulo = 'Corretude de Algoritmos Iterativos e Recursivos' }
    'aula3' = @{ Pasta = 'aula3';     Titulo = 'Análise Assintótica e Algoritmos Iterativos' }
    'aula4' = @{ Pasta = 'aula4';     Titulo = 'Análise Assintótica e Algoritmos Recursivos' }
    'aula6' = @{ Pasta = 'aula6';     Titulo = 'Lista Ligada, Pilha e Fila' }
    'aula7' = @{ Pasta = 'aula7';     Titulo = 'Árvores Binárias e seus Percursos' }
    'aula8' = @{ Pasta = 'aula8';     Titulo = 'Árvores Balanceadas (AVL) e Filas de Prioridade (Heap)' }
    'aula9' = @{ Pasta = 'aula9';     Titulo = 'Algoritmos em Grafos' }
    'aula11' = @{ Pasta = 'aula11';   Titulo = 'Algoritmos Gulosos (troco, mochila, Dijkstra, Kruskal)' }
    'p1'    = @{ Pasta = 'provas\p1'; Titulo = 'Prova P1 — Reflorestamento Sustentável com Drones' }
    'p2'    = @{ Pasta = 'provas\p2'; Titulo = 'Prova P2 — Estruturas de Dados na Linha de Produção' }
}

if (-not (Get-Command javac -ErrorAction SilentlyContinue)) {
    Write-Host 'JDK nao encontrado: instale o Java 21 e coloque javac/java no PATH.' -ForegroundColor Red
    exit 1
}

if (-not $Modulo) {
    Write-Host "`n=== Estruturas de Dados e Análise de Algoritmos ===" -ForegroundColor Cyan
    foreach ($chave in $modulos.Keys) {
        $atalho = $chave -replace '^aula', ''
        Write-Host ("{0,3} - {1}" -f $atalho, $modulos[$chave].Titulo)
    }
    Write-Host '  0 - Sair'
    $escolha = Read-Host 'Escolha a aula para testar (ex.: 4, 9, p1)'
    if ($escolha -eq '0' -or [string]::IsNullOrWhiteSpace($escolha)) { exit 0 }
    $Modulo = $escolha.Trim()
}

if ($Modulo -match '^\d+$') { $Modulo = "aula$Modulo" }

$Modulo = $Modulo.ToLower()
if (-not $modulos.Contains($Modulo)) {
    Write-Host "Aula desconhecida: '$Modulo'. Opcoes: $($modulos.Keys -join ', ')" -ForegroundColor Red
    exit 1
}

$dir = Join-Path $PSScriptRoot (Join-Path $modulos[$Modulo].Pasta 'estrutudaDados')
$out = Join-Path $dir 'out'
$fontes = Get-ChildItem (Join-Path $dir 'src') -Filter *.java | ForEach-Object { $_.FullName }

Write-Host "`nCompilando $Modulo — $($modulos[$Modulo].Titulo)..." -ForegroundColor Cyan
& javac -encoding UTF-8 -d $out @fontes
if ($LASTEXITCODE -ne 0) {
    Write-Host 'Falha na compilacao.' -ForegroundColor Red
    exit $LASTEXITCODE
}

$argsJava = @('-Dstdout.encoding=UTF-8', '-Dstderr.encoding=UTF-8', "-Dritmo.ms=$Ritmo", '-cp', $out, 'Main')
if ($Opcao) { $argsJava += $Opcao }
& java @argsJava
