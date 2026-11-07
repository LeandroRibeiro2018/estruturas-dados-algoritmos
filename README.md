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
└── provas/
    ├── p1/estrutudaDados/  # P1 — Plano de Reflorestamento Sustentável com Drones
    └── p2/estrutudaDados/  # P2 — Estruturas de Dados na Linha de Produção (BigO Motors)
```

## Como executar qualquer módulo

```bash
cd "<pasta>/estrutudaDados"
javac -d out src/*.java
java -cp out Main
```

Cada `Main` exibe um menu interativo; ao escolher uma opção, o programa
mostra primeiro uma breve explicação do exercício e, em seguida, executa a
resolução (com saída real, sem valores fabricados).

## Requisitos

- Java 21 (JDK)

## Abrir no IntelliJ IDEA

Abra a pasta raiz deste repositório (`bloco1`) — cada subprojeto já está
configurado como um módulo Java independente.
