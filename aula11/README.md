# Aula 11 — Algoritmos Gulosos

Projeto que implementa os algoritmos dos slides da Aula 11, seguindo o
pseudocódigo apresentado em aula.

## Como executar

```bash
cd "aula11/estrutudaDados"
javac -d out src/*.java
java -cp out Main
```

Ou, pela raiz do repositório: `.\executar.ps1 11` (passo a passo).

## Exercícios implementados

1. Problema do troco — os três exemplos dos slides (ótimo, não ótimo e sem solução)
2. Quando o troco guloso é ótimo? — busca automática de contraexemplos
3. Mochila fracionária — ordenação por valor/peso, resultado $240
4. Mochila binária (0-1) — o mesmo critério guloso fica em $160; o ótimo é $220
5. Dijkstra passo a passo — exemplo dos slides (s=0, y=5, z=7, t=8, x=9)
6. Dijkstra com vetor O(V²) x heap O((V+E) log V) — medição real
7. Dijkstra com peso negativo — contraexemplo comparado com Bellman-Ford
8. Kruskal + Union-Find — árvore geradora mínima com w(T) = 37

A solução ótima do troco e da mochila 0-1 é conferida por programação
dinâmica, que não é gulosa.

## Observação sobre os slides

A complexidade do troco guloso aparece como O(n), n = |C|. O laço, porém,
roda uma vez por moeda devolvida além de uma vez por moeda descartada:
com C = {1} e valor 1000 são 1000 iterações. O custo é O(|C| + k), em que
k é o número de moedas do troco (depende do valor, não só de |C|).
