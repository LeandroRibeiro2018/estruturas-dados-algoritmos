/**
 * Questão 2 — Soma dos pesos 2^i.
 *
 * Soma pelo laço vs. fórmula fechada 2^n - 1, provada por indução:
 * (2^n - 1) + 2^n = 2^(n+1) - 1.
 */
public class SomaPesosGeometrica {
    public static long somaPeloLoop(int n) {
        long soma = 0;
        for (int i = 0; i <= n - 1; i++) {
            soma += (1L << i);
        }
        return soma;
    }

    public static long formula(int n) {
        return (1L << n) - 1;
    }
}
