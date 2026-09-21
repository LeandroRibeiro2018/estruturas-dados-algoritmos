import java.util.Arrays;

/**
 * Questão 6 — Complexidade total e troca do algoritmo.
 *
 * A etapa mais pesada domina a complexidade total: Θ(n²) do Selection Sort
 * torna o algoritmo inteiro Θ(n²), mesmo com as demais etapas em Θ(n log n).
 * Compara o tempo real de Selection Sort O(n²) vs. Arrays.sort O(n log n).
 */
public class ComparacaoOrdenacao {
    public static String compararTempos(int n) {
        double[] a1 = SelectionSortComparacoes.gerarVetorAleatorio(n);
        double[] a2 = Arrays.copyOf(a1, a1.length);

        long t0 = System.nanoTime();
        selectionSort(a1);
        long t1 = System.nanoTime();

        Arrays.sort(a2);
        long t2 = System.nanoTime();

        double msSelection = (t1 - t0) / 1_000_000.0;
        double msArrays = (t2 - t1) / 1_000_000.0;
        double ganho = msArrays == 0 ? 0 : msSelection / msArrays;

        return String.format("n=%d | SelectionSort=%.4f ms | Arrays.sort=%.4f ms | ganho=%.1fx",
                n, msSelection, msArrays, ganho);
    }

    private static void selectionSort(double[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            double aux = a[i];
            a[i] = a[min];
            a[min] = aux;
        }
    }
}
