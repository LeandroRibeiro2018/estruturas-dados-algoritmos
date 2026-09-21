/**
 * Questão 5 — Laço de monitoramento escalonado.
 *
 * Para cada posição i, o laço interno divide i por 2 até chegar a zero
 * (piso(log2 i) + 1 vezes); somando para todo i de 1 até n, o total se
 * aproxima de n·log2(n).
 */
public class MonitoramentoEscalonado {
    public static long totalIteracoes(int n) {
        long total = 0;
        for (int i = 1; i <= n; i++) {
            int nivel = i;
            while (nivel >= 1) {
                total++;
                nivel /= 2;
            }
        }
        return total;
    }

    public static double nLogN(int n) {
        return n * (Math.log(n) / Math.log(2));
    }
}
