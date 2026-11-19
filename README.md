# Estruturas de Dados e Análise de Algoritmos

Projetos Java desenvolvidos para o Mestrado Profissional em Computação
Aplicada, disciplina de Estruturas de Dados e Análise de Algoritmos.

Cada pasta é um módulo Java independente (menu interativo pelo terminal),
com as resoluções comentadas dos exercícios e provas.

## Estrutura

```
bloco1/
├── aula1/estrutudaDados/   # Funções, Somatórios e Indução Matemática
├── aula2/estrutudaDados/   # Corretude de Algoritmos Iterativos e Recursivos
├── aula3/estrutudaDados/   # Análise Assintótica e Algoritmos Iterativos
├── aula4/estrutudaDados/   # Análise Assintótica e Algoritmos Recursivos
├── aula6/estrutudaDados/   # Lista Ligada, Pilha e Fila
├── aula7/estrutudaDados/   # Árvores Binárias e seus Percursos
├── aula8/estrutudaDados/   # Árvores Binárias Balanceadas e Filas de Prioridade
├── aula9/estrutudaDados/   # Algoritmos em Grafos
├── aula11/estrutudaDados/  # Algoritmos Gulosos
├── aula12/estrutudaDados/  # Programação Dinâmica
├── provas/
│   ├── p1/estrutudaDados/  # P1 — Plano de Reflorestamento Sustentável com Drones
│   └── p2/estrutudaDados/  # P2 — Estruturas de Dados na Linha de Produção (BigO Motors)
├── executar.ps1            # escolhe a aula, compila e roda (Windows)
└── executar.sh             # idem para Git Bash / Linux / macOS
```

## Conteúdo por aula

| Aula | Tema | Destaques |
|---|---|---|
| [aula1](aula1/README.md) | Funções, Somatórios e Indução Matemática | piso/teto, backoff exponencial, busca binária |
| [aula2](aula2/README.md) | Corretude de Algoritmos Iterativos e Recursivos | buscas, Bubble/Insertion/Merge Sort |
| [aula3](aula3/README.md) | Análise Assintótica e Algoritmos Iterativos | crescimento assintótico, Selection Sort, Fibonacci |
| [aula4](aula4/README.md) | Análise Assintótica e Algoritmos Recursivos | recorrências, Teorema Mestre, MergeSort x QuickSort |
| [aula6](aula6/README.md) | Lista Ligada, Pilha e Fila | listas com/sem cabeça, fila circular, fila de prioridade |
| [aula7](aula7/README.md) | Árvores Binárias e seus Percursos | BST, remoção, pré/in/pós-ordem e largura |
| [aula8](aula8/README.md) | Árvores Balanceadas e Filas de Prioridade | AVL (RD, RE, RED, RDE), heap, Heap Sort |
| [aula9](aula9/README.md) | Algoritmos em Grafos | matriz/lista de adjacência, DFS, BFS, Euler, Hamilton |
| [aula11](aula11/README.md) | Algoritmos Gulosos | troco, mochila fracionária x 0-1, Dijkstra, Kruskal + Union-Find |
| [aula12](aula12/README.md) | Programação Dinâmica | Fibonacci e mochila 0-1 (recursivo x memoization x tabulation), Bellman-Ford |
| [p1](provas/p1/README.md) | Prova P1 | Plano de Reflorestamento Sustentável com Drones |
| [p2](provas/p2/README.md) | Prova P2 | Estruturas de Dados na Linha de Produção (BigO Motors) |

## Como executar

### Escolhendo a aula pelo script (recomendado)

Na raiz do repositório, o script lista as aulas, compila a escolhida e abre
o menu dela:

```powershell
# Windows (PowerShell)
.\executar.ps1          # mostra a lista e pergunta qual aula testar
.\executar.ps1 4        # abre direto o menu da aula 4
.\executar.ps1 9 5      # executa direto o exercício 5 da aula 9
.\executar.ps1 p2       # abre o menu da prova P2
```

```bash
# Git Bash, Linux ou macOS
./executar.sh
./executar.sh 4
./executar.sh 9 5
./executar.sh p2
```

#### Modo passo a passo

Pelo script, a execução é **passo a passo** por padrão: cada linha aparece
com 300 ms de intervalo, e o programa espera **ENTER** depois da explicação
(antes de executar) e depois do resultado (antes de voltar ao menu).

```powershell
.\executar.ps1 7 -Ritmo 800   # mais devagar (800 ms por linha)
.\executar.ps1 7 -Ritmo 0     # velocidade normal, sem pausas
```

```bash
RITMO_MS=800 ./executar.sh 7
RITMO_MS=0   ./executar.sh 7
```

O atraso só acontece na impressão, fora dos trechos cronometrados — os
tempos medidos pelos exercícios não são afetados. Rodando `java` direto, o
modo é ativado com `-Dritmo.ms=300` (ou a variável `RITMO_MS`).

> Se o PowerShell bloquear o script, rode uma vez
> `Set-ExecutionPolicy -Scope CurrentUser RemoteSigned` ou use
> `powershell -ExecutionPolicy Bypass -File .\executar.ps1`.

### Manualmente, em qualquer módulo

```bash
cd "<pasta>/estrutudaDados"
javac -encoding UTF-8 -d out src/*.java
java -cp out Main        # menu interativo
java -cp out Main 3      # executa direto a opção 3
```

Cada `Main` exibe um menu interativo; ao escolher uma opção, o programa
mostra primeiro uma breve explicação do exercício e, em seguida, executa a
resolução (com saída real, sem valores fabricados). Algumas opções das
aulas 1 e 2 e da P1 pedem valores pelo teclado (ex.: quantidade, SKU,
valor procurado).

## Requisitos

- Java 21 (JDK) com `javac` e `java` no PATH

## Abrir no IntelliJ IDEA

Abra a pasta raiz deste repositório (`bloco1`) — cada subprojeto já está
configurado como um módulo Java independente.
