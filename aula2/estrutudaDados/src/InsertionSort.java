/**
 * Exercício 5 — Insertion Sort.
 */
public class InsertionSort {
    public static void ordenar(int[] vetor) {
        for (int j = 1; j < vetor.length; j++) {
            int chave = vetor[j];
            int i = j - 1;
            while (i >= 0 && vetor[i] > chave) {
                vetor[i + 1] = vetor[i];
                i--;
            }
            vetor[i + 1] = chave;
        }
    }
}
