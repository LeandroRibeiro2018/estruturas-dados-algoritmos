import java.util.Arrays;

/**
 * Exercício 5 e 6 — Selection Sort, com análise assintótica e medida real.
 */
public class SelectionSort {
    public static void ordenar(double[] valores) {
        int n = valores.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (valores[j] < valores[min]) {
                    min = j;
                }
            }
            double aux = valores[i];
            valores[i] = valores[min];
            valores[min] = aux;
        }
    }

    public static boolean estaOrdenado(double[] valores) {
        for (int i = 1; i < valores.length; i++) {
            if (valores[i - 1] > valores[i]) {
                return false;
            }
        }
        return true;
    }

    public static String medirPorTamanho(int tamanho) {
        double[] valores = new double[tamanho];
        for (int i = 0; i < tamanho; i++) {
            valores[i] = Math.random() * tamanho;
        }
        long inicio = System.nanoTime();
        ordenar(valores);
        long fim = System.nanoTime();
        return "n=" + tamanho + ", ordenado=" + estaOrdenado(valores) + ", tempo=" + ((fim - inicio) / 1_000_000.0) + " ms";
    }

    public static String[] medirVarios() {
        int[] tamanhos = {100, 500, 1_000, 5_000, 10_000, 20_000};
        String[] saida = new String[tamanhos.length];
        for (int i = 0; i < tamanhos.length; i++) {
            saida[i] = medirPorTamanho(tamanhos[i]);
        }
        return saida;
    }

    public static void mostrarExemplo() {
        double[] valores = {9.0, 4.0, 7.0, 2.0, 1.0};
        System.out.println("Antes: " + Arrays.toString(valores));
        ordenar(valores);
        System.out.println("Depois: " + Arrays.toString(valores));
    }
}
