# P1 — Plano de Reflorestamento Sustentável com Drones

Projeto Java que reproduz e valida numericamente as 6 questões da prova P1,
baseado no material `Prova_P1_Resolvida.pdf` desta mesma pasta.

## Como executar

```bash
cd "provas/p1/estrutudaDados"
javac -d out src/*.java
java -cp out Main
```

## Questões implementadas

1. Comparações do Selection Sort — contagem real vs. fórmula `n(n-1)/2`
2. Soma dos pesos `2^i` — soma pelo laço vs. fórmula fechada `2^n - 1`
3. Busca binária e invariante de laço — iterações reais vs. limite teórico `⌈log2 n⌉ + 1`
4. Planejamento recursivo (divisão ao meio) — Θ(n log n), Teorema Mestre
5. Monitoramento escalonado — Θ(n log n)
6. Complexidade total do algoritmo — a etapa mais pesada domina; comparação
   real de tempo entre Selection Sort O(n²) e `Arrays.sort` O(n log n)

Cada classe reproduz o mesmo código Java mostrado no PDF resolvido, permitindo
executar e conferir as mesmas medições reais (Java 21, `System.nanoTime()`).
