# Aula 8 — Árvores Binárias Balanceadas e Filas de Prioridade

Projeto que implementa os algoritmos dos slides da Aula 08 (árvore AVL e heap).

## Como executar

```bash
cd "aula8/estrutudaDados"
javac -d out src/*.java
java -cp out Main
```

## Exercícios implementados

1. Fator de balanceamento: árvore AVL x não AVL
2. Inserção na AVL com a sequência 70, 50, 40, 80, 90, 15, 25, 45, 42 (RD, RE, RED, RDE)
3. BST comum x AVL com chaves crescentes (altura n - 1 x O(log n))
4. Remoção na AVL com rebalanceamento
5. Heap de máximo em vetor (pai `k/2`, filhos `2k` e `2k + 1`)
6. Inserção no heap (subir / heapify up) — O(log n)
7. Remoção no heap (descer / heapify down) — O(log n)
8. Heap Sort — construção subindo x descendo e ordenação O(n log n)

As rotações e os heaps resultantes batem com os slides (ex.: árvore final
`50(25(15,42(40,45)),80(70,90))`; inserir 50 gera
`[50, 16, 10, 8, 14, 9, 3, 2, 4, 1, 7]`).
