/**
 * Merge Sort e Quick Sort recursivos dos slides, instrumentados para contar
 * comparações entre elementos e chamadas recursivas.
 */
public class Ordenacao {
    public static long comparacoes;
    public static long chamadas;
    public static int profundidadeMaxima;

    public static void zerarContadores() {
        comparacoes = 0;
        chamadas = 0;
        profundidadeMaxima = 0;
    }

    // mergeSort (A[p..r], p, r): T(n) = T(ceil(n/2)) + T(floor(n/2)) + Theta(n)
    public static void mergeSort(int[] a) {
        zerarContadores();
        mergeSort(a, new int[a.length], 0, a.length - 1, 1);
    }

    private static void mergeSort(int[] a, int[] aux, int p, int r, int prof) {
        chamadas++;
        profundidadeMaxima = Math.max(profundidadeMaxima, prof);
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(a, aux, p, q, prof + 1);
            mergeSort(a, aux, q + 1, r, prof + 1);
            merge(a, aux, p, q, r);
        }
    }

    private static void merge(int[] a, int[] aux, int p, int q, int r) {
        System.arraycopy(a, p, aux, p, r - p + 1);
        int i = p;
        int j = q + 1;
        int k = p;
        while (i <= q && j <= r) {
            comparacoes++;
            a[k++] = aux[i] <= aux[j] ? aux[i++] : aux[j++];
        }
        while (i <= q) {
            a[k++] = aux[i++];
        }
        while (j <= r) {
            a[k++] = aux[j++];
        }
    }

    /**
     * quickSort (A[p..r], p, r). Os slides recursam em [p..q] e [q+1..r], que
     * é a divisão do particionamento de Hoare. Com o particiona dos slides
     * (Lomuto, pivô A[r]), o pivô já fica na posição final q; recursar em
     * [p..q] faria quickSort(A, p, r) se repetir para sempre quando o pivô
     * é o maior elemento (q = r). Por isso usamos [p..q-1] e [q+1..r].
     */
    public static void quickSort(int[] a) {
        zerarContadores();
        quickSort(a, 0, a.length - 1, 1);
    }

    private static void quickSort(int[] a, int p, int r, int prof) {
        chamadas++;
        profundidadeMaxima = Math.max(profundidadeMaxima, prof);
        if (p < r) {
            int q = particiona(a, p, r);
            quickSort(a, p, q - 1, prof + 1);
            quickSort(a, q + 1, r, prof + 1);
        }
    }

    // particiona (A[p..r], p, r) — Theta(n)
    public static int particiona(int[] a, int p, int r) {
        int x = a[r]; // pivô
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            comparacoes++;
            if (a[j] <= x) {
                i++;
                troca(a, i, j);
            }
        }
        troca(a, i + 1, r);
        return i + 1;
    }

    private static void troca(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean ordenado(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Executa a tarefa em uma thread com pilha grande: o pior caso do Quick
     * Sort chega a n chamadas recursivas aninhadas.
     */
    public static void comPilhaGrande(Runnable tarefa) {
        Thread t = new Thread(null, tarefa, "pilha-grande", 1L << 29);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
