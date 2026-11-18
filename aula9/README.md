# Aula 9 — Algoritmos em Grafos

Projeto que implementa os algoritmos dos slides da Aula 09.

## Como executar

```bash
cd "aula9/estrutudaDados"
javac -d out src/*.java
java -cp out Main
```

## Exercícios implementados

1. G = (V, E), adjacência e grau (não orientado e orientado)
2. Matriz de adjacência (não orientado, ponderado e orientado ponderado)
3. Lista de adjacência para os mesmos grafos
4. Matriz x lista: grafo denso x esparso
5. Busca em profundidade (DFS) com timestamps d/f — O(|V| + |E|)
6. Busca em largura (BFS), distâncias e caminho mínimo — O(|V| + |E|)
7. Grafos eulerianos: pontes de Königsberg (Teorema de Euler + Hierholzer)
8. Grafos hamiltonianos: dodecaedro x grafo de Petersen (backtracking)

A DFS reproduz os timestamps do exemplo dos slides (s 1/12, r 2/7, v 3/4,
w 5/6, t 8/11, x 9/10, u 13/16, y 14/15 — floresta com 2 árvores) e a BFS
reproduz a sequência da fila (s; w r; r t x; t x v; ...).
