# Aula 12 — Programação Dinâmica

Projeto que implementa os algoritmos dos slides da Aula 12, seguindo o
pseudocódigo apresentado em aula.

## Como executar

```bash
cd "aula12/estrutudaDados"
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

Ou, pela raiz do repositório: `.\executar.ps1 12` (passo a passo).

## Exercícios implementados

1. Fibonacci — recursivo O(2ⁿ) x memoization x tabulation (contagem real de chamadas)
2. Mochila 0-1 — recursiva x memoization x tabulation, O(n·W) pseudopolinomial
3. Mochila 0-1 — tabela passo a passo do exemplo dos slides (n=4, W=5, ótimo = 15)
4. Mochila 0-1 — recuperação dos objetos escolhidos (objetos 3 e 4)
5. Bellman-Ford passo a passo — d = {s=0, t=2, y=7, x=4, z=-2}
6. Bellman-Ford — detecção de ciclo negativo (aresta (y,x) com peso -9)

## Observação sobre o Bellman-Ford

A ordem de relaxamento das arestas muda o estado intermediário de cada
passada (os slides mostram um relaxamento por linha), mas não o resultado
final, que é o mesmo dos slides.
