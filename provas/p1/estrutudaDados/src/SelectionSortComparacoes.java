/**
 * Questão 1 — Quantas comparações o Selection Sort faz?
 *
 * Contagem real de comparações vs. fórmula fechada n(n-1)/2, provada por
 * indução simples: somando (n-1)+(n-2)+...+1+0.
 */
public class SelectionSortComparacoes {
    public static long comparacoesReais(double[] a) {
        long comparacoes = 0;
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                comparacoes++;
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            double aux = a[i];
            a[i] = a[min];
            a[min] = aux;
        }
        return comparacoes;
    }

    public static long formula(int n) {
        return (long) n * (n - 1) / 2;
    }

    public static double[] gerarVetorAleatorio(int n) {
        double[] vetor = new double[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = Math.random() * n;
        }
        return vetor;
    }
}
