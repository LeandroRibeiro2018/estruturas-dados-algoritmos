# P2 — Estruturas de Dados na Linha de Produção (BigO Motors)

Projeto Java que reproduz as 10 questões da prova P2, baseado no material
`Prova_P2_Resolvida.pdf` desta mesma pasta.

## Como executar

```bash
cd "provas/p2/estrutudaDados"
javac -d out src/*.java
java -cp out Main
```

## Questões implementadas

1. Fila (Queue) — entrada de veículos (FIFO), O(1)
2. Pilha (Stack) — retrabalho (LIFO), O(1)
3. Lista ligada (LinkedList) — componentes do veículo
4. Árvore (Tree) — decomposição sistema/subsistema/peça
5. Árvore binária — checkpoint pass/fail
6. Árvore binária de busca (BST) — peça por código
7. Árvore AVL — BST degenerada (altura 8) x AVL rebalanceada (altura 4)
8. Grafo — setores da fábrica interligados por rotas
9. BFS — distribuição de peças por proximidade (por nível)
10. DFS — investigação retroativa de falha (até a causa raiz)

Cada classe reproduz o mesmo raciocínio e código do PDF resolvido; as saídas
foram validadas e batem com os valores documentados (ex.: BST comum com
altura 8 vs. AVL com altura 4 para os mesmos códigos inseridos em ordem
crescente).
