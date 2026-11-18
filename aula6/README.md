# Aula 6 — Lista Ligada, Pilha e Fila

Projeto que implementa os algoritmos dos slides da Aula 06, seguindo o
pseudocódigo apresentado em aula (estruturas próprias, sem `java.util`).

## Como executar

```bash
cd "aula6/estrutudaDados"
javac -d out src/*.java
java -cp out Main
```

## Exercícios implementados

1. Lista simplesmente ligada sem cabeça — inserção O(1), busca e remoção O(n) (3 casos)
2. Lista simplesmente ligada com cabeça — mesma complexidade, remoção com 2 casos
3. Pilha estática (vetor de tamanho MAX) — push/pop O(1)
4. Pilha dinâmica (lista ligada) — push/pop O(1)
5. Simulação de recursão com pilha — fatorial recursivo x iterativo
6. Fila estática circular (operação mod) — enqueue/dequeue O(1)
7. Fila dinâmica (lista ligada com início e final) — enqueue/dequeue O(1)
8. Fila de prioridade com lista não ordenada x ordenada (com medição real)

Observação: os slides iniciam a fila estática com `inicio = -1`, mas com
esse valor o enqueue calcularia a posição -1; a implementação usa
`inicio = 0` e identifica a fila vazia por `qtdElementos = 0`.
