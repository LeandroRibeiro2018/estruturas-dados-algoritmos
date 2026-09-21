# Aula 1 — Funções, Somatórios e Indução (Estruturas de Dados e Análise de Algoritmos)

Este projeto reúne exercícios práticos em Java que mapeiam os conceitos teóricos do
**Guia Unificado de Estudo & Resoluções** (Funções, Somatórios e Indução Matemática —
Mestrado Profissional em Computação Aplicada, Profa. Dra. Lucy Mari Tabuti) para código
de engenharia de software real.

## Estrutura do projeto

```
aula1/
└── estrutudaDados/
    ├── estrutudaDados.iml     # Módulo Java do IntelliJ (associa a pasta src)
    └── src/
        ├── Main.java              # Menu interativo para rodar todos os exercícios
        ├── CalculaDescontos.java  # Exercício 1 — Funções e validação de domínio
        ├── BackoffExponencial.java# Exercício 2 — Função exponencial (backoff)
        ├── PisoTeto.java          # Exercício 3 — Funções piso e teto (paginação)
        ├── BuscaBinaria.java      # Exercício 4 — Função log / monotonicidade (busca binária)
        ├── ComplexidadeDo.java    # Exercício 5 — Somatórios (custo total de pedido)
        └── Inducao.java           # Exercício 6 — Indução forte (contagem em árvore)
```

## Como executar

### Opção 1 — Pelo IntelliJ IDEA (recomendado)

1. Abra o projeto no IntelliJ IDEA.
2. Certifique-se de que há um JDK configurado em **File → Project Structure → Project**
   (o projeto foi criado com JDK 21+).
3. Abra o arquivo `src/Main.java`.
4. Clique no ícone ▶ ao lado do método `main` (ou use `Run → Run 'Main'`).
5. O terminal de execução do IntelliJ abrirá o menu interativo — digite a opção
   desejada e pressione Enter.

> **Dica:** se aparecer erro `ClassNotFoundException: Main` ou `cannot find symbol`,
> feche o projeto e reabra (**File → Reload All from Disk**) e rode
> **Build → Rebuild Project** antes de executar novamente. Verifique também se o
> campo **Program arguments** da configuração de execução (`Run → Edit Configurations`)
> está vazio ou contém apenas um número de 0 a 6.

### Opção 2 — Pela linha de comando / terminal (fora do IntelliJ)

Esta opção não depende do IntelliJ: funciona em qualquer terminal (Linux, macOS ou
Windows), desde que o **JDK (Java Development Kit)** esteja instalado.

#### Passo 1 — Verifique se o Java está instalado

```bash
java -version
javac -version
```

Se aparecer algo como `openjdk version "21..."` para os dois comandos, pode pular
para o Passo 2. Se aparecer `comando não encontrado` (ou `'java' não é reconhecido`
no Windows), instale um JDK antes de continuar:

- **Linux (Ubuntu/Debian):**
  ```bash
  sudo apt update && sudo apt install openjdk-21-jdk
  ```
- **Linux via SDKMAN (o mesmo gerenciador já usado neste projeto):**
  ```bash
  curl -s "https://get.sdkman.io" | bash
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  sdk install java 21.0.2-tem
  ```
- **macOS (Homebrew):**
  ```bash
  brew install openjdk@21
  ```
- **Windows:** baixe o instalador do
  [Eclipse Temurin JDK 21](https://adoptium.net/) e siga o assistente. Depois,
  confirme com `java -version` em um novo terminal (cmd ou PowerShell).

#### Passo 2 — Acesse a pasta do módulo

```bash
cd "aula1/estrutudaDados"
```

Ajuste o caminho acima de acordo com o local onde você salvou a pasta `aula1` na
sua máquina.

#### Passo 3 — Compile os arquivos `.java`

```bash
javac -d out src/*.java
```

Isso gera os arquivos `.class` compilados dentro da pasta `out/`. Se não houver
nenhum erro impresso no terminal, a compilação foi bem-sucedida.

#### Passo 4 — Execute o programa

```bash
java -cp out Main
```

O menu interativo será exibido no próprio terminal:

```
=== Exercicios de Estruturas de Dados ===
1 - Calcular desconto
2 - Calcular piso/teto de paginas
3 - Backoff exponencial
4 - Busca binaria
5 - Custo total do pedido
6 - Contar nos por inducao
0 - Sair
Escolha um exercicio:
```

Digite o número do exercício desejado e pressione Enter; em seguida, informe os
dados pedidos (também pelo teclado). Para encerrar o programa, escolha a opção `0`.

Você também pode pular direto para um exercício passando o número da opção como
argumento (os dados específicos do exercício continuam sendo pedidos depois,
interativamente):

```bash
java -cp out Main 4
```

#### Problemas comuns

| Sintoma | Causa provável | Solução |
|---|---|---|
| `javac: comando não encontrado` | JDK não instalado ou não está no `PATH` | Instale o JDK (Passo 1) e abra um novo terminal |
| `error: package X does not exist` | Compilou um arquivo isolado, sem os demais `.java` | Sempre compile com `javac -d out src/*.java` (todos os arquivos juntos) |
| `Erro: Não foi possível localizar nem carregar a classe principal Main` | Esqueceu de indicar o classpath da pasta `out`, ou a compilação falhou | Rode novamente o Passo 3 e confira se `out/Main.class` foi criado; use sempre `-cp out` no Passo 4 |
| Acentos/caracteres corrompidos no console | Terminal usando codificação diferente de UTF-8 | Rode com `java -Dfile.encoding=UTF-8 -cp out Main` |

## Exercícios disponíveis

| # | Classe / Função testada | Título | Exercício pertinente no guia |
|---|---|---|---|
| 1 | `CalculaDescontos.calcularDesconto(int)` | Funções e Validação de Domínio (Defensive Programming) | Parte I, seção 1.3 — relacionado ao **Exercício 1**: "Quanto Realmente Custa uma Hora do Seu Trabalho?" (Função Afim, Domínio e Imagem) |
| 2 | `PisoTeto.calcularPaginas(int, int)` | Função Piso e Função Teto — Paginação de APIs REST | Parte I, seção 3 — relacionado ao **Exercício 3**: "Quantas Pessoas Realmente Cabem em um Orçamento?" (Função Piso ⌊x⌋, Função Teto ⌈x⌉ e Dimensionamento Operacional) |
| 3 | `BackoffExponencial.delayMs(int)` | Função Exponencial — Backoff Exponencial com Teto Limitador | Parte I, seção 2.1 — corresponde ao **Exercício 2**: "Seu Celular Está Consumindo Sua Produtividade?" (Função Exponencial, Taxa de Crescimento, Limites Físicos e Cap) |
| 4 | `BuscaBinaria.buscaBinaria(int[], int)` | Função Logarítmica e Monotonicidade — Busca Binária O(log n) | Parte I, seção 2.2 — relacionado ao **Exercício 4**: "O Investimento em uma Habilidade Está Crescendo ou Diminuindo?" (Exponencial, Resolução Logarítmica, Monotonicidade e B-Trees) |
| 5 | `ComplexidadeDo.calcularCustoTotal(List)` | Somatório ∑ — Custo Total de Pedido | Parte I, seção 5.2 — relacionado ao **Exercício 5**: "Vale a Pena Automatizar? Análise de Retorno sobre Investimento (ROI)" (Somatório ∑, Análise Financeira de Engenharia e Ponto de Equilíbrio) |
| 6 | `Inducao.contarNos(Categoria)` | Indução Forte — Contagem de Nós em uma Árvore de Categorias | Parte I, seção 6.2 — relacionado ao **Exercício 8**: "Prove Que Seu Algoritmo Sempre Funciona" (Indução Matemática, Corretude de Algoritmos e Ataque Defensivo) |

Cada classe de exercício contém, em seu Javadoc, o título, a função testada e o
exercício correspondente do guia teórico, além de instruções de como executá-la
isoladamente pelo menu do `Main`.

## Referência

Material teórico de apoio: `Guia_Unificado_Funcoes_Somatorios_Inducao.pdf`
(Programa de Pós-Graduação em Computação Aplicada — Setembro de 2026).
