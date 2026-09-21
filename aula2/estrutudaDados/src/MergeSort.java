/**
 * Exercício 6 — Merge Sort.
 */
public class MergeSort {
    public static void ordenar(int[] vetor) {
        if (vetor == null || vetor.length < 2) {
            return;
        }
        int[] auxiliar = new int[vetor.length];
        mergeSort(vetor, auxiliar, 0, vetor.length - 1);
    }

    private static void mergeSort(int[] vetor, int[] auxiliar, int inicio, int fim) {
        if (inicio >= fim) {
            return;
        }

        int meio = inicio + (fim - inicio) / 2;
        mergeSort(vetor, auxiliar, inicio, meio);
        mergeSort(vetor, auxiliar, meio + 1, fim);
        mesclar(vetor, auxiliar, inicio, meio, fim);
    }

    private static void mesclar(int[] vetor, int[] auxiliar, int inicio, int meio, int fim) {
        for (int i = inicio; i <= fim; i++) {
            auxiliar[i] = vetor[i];
        }

        int i = inicio;
        int j = meio + 1;
        int k = inicio;

        while (i <= meio && j <= fim) {
            if (auxiliar[i] <= auxiliar[j]) {
                vetor[k++] = auxiliar[i++];
            } else {
                vetor[k++] = auxiliar[j++];
            }
        }

        while (i <= meio) {
            vetor[k++] = auxiliar[i++];
        }

        while (j <= fim) {
            vetor[k++] = auxiliar[j++];
        }
    }
}
