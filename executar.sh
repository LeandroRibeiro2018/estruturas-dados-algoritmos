#!/usr/bin/env bash
# Escolhe uma aula/prova, compila o módulo e abre o menu interativo.
#
#   ./executar.sh            # mostra a lista de aulas para escolher
#   ./executar.sh aula4      # compila e abre o menu da aula 4
#   ./executar.sh aula4 5    # executa direto o exercício 5 da aula 4
#
# Modo passo a passo (padrão 300 ms por linha + pausas de ENTER):
#   RITMO_MS=800 ./executar.sh 4    # mais devagar
#   RITMO_MS=0   ./executar.sh 4    # velocidade normal
set -euo pipefail

ritmo="${RITMO_MS:-300}"

raiz="$(cd "$(dirname "$0")" && pwd)"

chaves=(aula1 aula2 aula3 aula4 aula6 aula7 aula8 aula9 aula11 aula12 p1 p2)
pastas=(aula1 aula2 aula3 aula4 aula6 aula7 aula8 aula9 aula11 aula12 provas/p1 provas/p2)
titulos=(
    "Funções, Somatórios e Indução Matemática"
    "Corretude de Algoritmos Iterativos e Recursivos"
    "Análise Assintótica e Algoritmos Iterativos"
    "Análise Assintótica e Algoritmos Recursivos"
    "Lista Ligada, Pilha e Fila"
    "Árvores Binárias e seus Percursos"
    "Árvores Balanceadas (AVL) e Filas de Prioridade (Heap)"
    "Algoritmos em Grafos"
    "Algoritmos Gulosos (troco, mochila, Dijkstra, Kruskal)"
    "Programação Dinâmica (Fibonacci, mochila 0-1, Bellman-Ford)"
    "Prova P1 — Reflorestamento Sustentável com Drones"
    "Prova P2 — Estruturas de Dados na Linha de Produção"
)

if ! command -v javac >/dev/null 2>&1; then
    echo "JDK nao encontrado: instale o Java 21 e coloque javac/java no PATH." >&2
    exit 1
fi

modulo="${1:-}"
opcao="${2:-}"

if [[ -z "$modulo" ]]; then
    echo
    echo "=== Estruturas de Dados e Análise de Algoritmos ==="
    for i in "${!chaves[@]}"; do
        printf "%3s - %s\n" "${chaves[$i]#aula}" "${titulos[$i]}"
    done
    echo "  0 - Sair"
    read -rp "Escolha a aula para testar (ex.: 4, 9, p1): " escolha
    [[ -z "$escolha" || "$escolha" == "0" ]] && exit 0
    modulo="$escolha"
fi

modulo="${modulo,,}"
[[ "$modulo" =~ ^[0-9]+$ ]] && modulo="aula$modulo"
indice=-1
for i in "${!chaves[@]}"; do
    [[ "${chaves[$i]}" == "$modulo" ]] && indice=$i
done
if (( indice < 0 )); then
    echo "Aula desconhecida: '$modulo'. Opcoes: ${chaves[*]}" >&2
    exit 1
fi

dir="$raiz/${pastas[$indice]}/estrutudaDados"
echo
echo "Compilando $modulo — ${titulos[$indice]}..."
javac -encoding UTF-8 -d "$dir/out" "$dir"/src/*.java

if [[ -n "$opcao" ]]; then
    java -Dstdout.encoding=UTF-8 -Dritmo.ms="$ritmo" -cp "$dir/out" Main "$opcao"
else
    java -Dstdout.encoding=UTF-8 -Dritmo.ms="$ritmo" -cp "$dir/out" Main
fi
