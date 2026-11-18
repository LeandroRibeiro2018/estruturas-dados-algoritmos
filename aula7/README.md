# Aula 7 — Árvores Binárias e seus Percursos

Projeto que implementa os algoritmos dos slides da Aula 07.

## Como executar

```bash
cd "aula7/estrutudaDados"
javac -d out src/*.java
java -cp out Main
```

## Exercícios implementados

1. Terminologia: raiz, folha, nó interno, nível e altura
2. Árvore de expressão `(a * b) + (c / (d + e))` — notações prefixa, infixa e pós-fixa
3. Tipos de árvores binárias e o limite `floor(lg n) <= h <= n - 1`
4. Árvore binária de busca: inserção recursiva x iterativa e busca — O(h)
5. Árvore binária de busca: remoção (folha, 1 filho, 2 filhos com sucessor)
6. Percursos pré-ordem, in-ordem, pós-ordem e em largura — O(n)
7. Pré-ordem iterativo com pilha explícita
8. Impressão da árvore (parênteses aninhados e visualização lateral)

As saídas reproduzem os exemplos dos slides (ex.: in-ordem
35, 40, 45, 50, 55, 60, 65 e largura 50, 40, 60, 35, 45, 55, 65).
