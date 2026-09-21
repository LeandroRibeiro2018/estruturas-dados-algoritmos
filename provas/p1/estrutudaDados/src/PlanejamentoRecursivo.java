/**
 * Questão 4 — Quanto custa o planejamento recursivo?
 *
 * A cada nível da recursão o total de área inspecionada é n; existem
 * log2(n) níveis, portanto o custo total é Θ(n log n) — mesma recorrência
 * T(n) = 2T(n/2) + n do Teorema Mestre.
 */
public class PlanejamentoRecursivo {
    public static long inspecoes = 0;

    public static void planejar(int inicio, int fim) {
        if (inicio > fim) {
            return;
        }
        for (int i = inicio; i <= fim; i++) {
            inspecoes++;
        }
        if (inicio == fim) {
            return;
        }
        int meio = (inicio + fim) / 2;
        planejar(inicio, meio);
        planejar(meio + 1, fim);
    }

    public static double nLogN(int n) {
        return n * (Math.log(n) / Math.log(2));
    }
}
