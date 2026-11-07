import java.util.Arrays;

/**
 * Heap binário de máximo em vetor (implementação estática, índices 1..n):
 * árvore quase completa em que cada pai tem prioridade >= à dos filhos.
 * O pai do nó k é floor(k/2); os filhos são 2k e 2k + 1.
 *
 * Inserção (subir) e remoção da raiz (descer) custam O(log n), pois a
 * altura de uma árvore quase completa é floor(lg n).
 */
public class HeapMaximo {
    private int[] heap; // heap[0] não é usado
    private int n;
    private long trocas; // contador de trocas, para comparar as construções

    public HeapMaximo(int capacidade) {
        this.heap = new int[capacidade + 1];
    }

    /** Copia o vetor para as posições 1..n sem organizá-lo como heap. */
    public static HeapMaximo deVetor(int[] vetor) {
        HeapMaximo h = new HeapMaximo(vetor.length);
        System.arraycopy(vetor, 0, h.heap, 1, vetor.length);
        h.n = vetor.length;
        return h;
    }

    // insere (heap[1..n], elemento)
    public void insere(int elemento) {
        if (n + 1 == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        heap[n + 1] = elemento;
        n = n + 1;
        subir(n);
    }

    // subir (filho) — heapify up
    private void subir(int filho) {
        int pai = filho / 2;
        if (pai >= 1) { // pai está dentro do heap
            if (heap[filho] > heap[pai]) { // troca pai e filho
                troca(filho, pai);
                subir(pai);
            }
        }
    }

    /**
     * remove (heap[1..n]) — remove sempre a raiz (o máximo). Retorna o valor
     * removido, ou null se o heap estiver vazio.
     */
    public Integer remove() {
        if (n == 0) {
            return null;
        }
        int maximo = heap[1];
        heap[1] = heap[n];
        n = n - 1;
        descer(1);
        return maximo;
    }

    // descer (pai) — heapify down
    private void descer(int pai) {
        int filho = 2 * pai; // filho esquerdo
        if (filho <= n) { // filho está dentro do heap
            if (filho < n) { // tem irmão à direita
                if (heap[filho + 1] > heap[filho]) { // filho direito maior que filho esquerdo
                    filho = filho + 1;
                }
            }
            if (heap[pai] < heap[filho]) { // troca pai e filho
                troca(pai, filho);
                descer(filho);
            }
        }
    }

    // construirHeapSubindo (vetor[1..n]) — O(n log n)
    public void construirHeapSubindo() {
        for (int i = 2; i <= n; i++) {
            subir(i);
        }
    }

    /**
     * construirHeapDescendo (vetor[1..n]). Os slides indicam O(n log n), que é
     * um limite válido; a análise mais justa (Cormen) mostra que é O(n), pois
     * a maioria dos nós está perto das folhas e desce pouco.
     */
    public void construirHeapDescendo() {
        int j = n / 2; // último pai no heap
        for (int i = j; i >= 1; i--) {
            descer(i);
        }
    }

    /** heapSort (vetor[1..n]) — O(n log n). Devolve o vetor ordenado (crescente). */
    public static int[] heapSort(int[] vetor) {
        HeapMaximo h = deVetor(vetor);
        h.construirHeapDescendo();
        int tamanhoOriginal = h.n;
        for (int i = h.n; i > 1; i--) {
            h.troca(1, i); // troca a raiz com o último elemento (posição i)
            h.n = h.n - 1; // diminui o tamanho do vetor/heap
            h.descer(1); // desce a raiz no novo heap de tamanho n - 1
        }
        return Arrays.copyOfRange(h.heap, 1, tamanhoOriginal + 1);
    }

    public boolean ehHeap() {
        for (int k = 2; k <= n; k++) {
            if (heap[k / 2] < heap[k]) {
                return false;
            }
        }
        return true;
    }

    // imprimeHeap (p) — parênteses aninhados com percurso pré-ordem
    public String parenteses(int p) {
        if (p > n) {
            return "()";
        }
        return "( " + heap[p] + " " + parenteses(2 * p) + " " + parenteses(2 * p + 1) + " )";
    }

    // imprimeHeap (p, b) — visualização bidimensional com percurso in-ordem
    public void lateral(int p, int b, StringBuilder saida) {
        if (p > n) { // índice p fora do heap
            saida.append("-----".repeat(b)).append("NULL\n");
            return;
        }
        lateral(2 * p + 1, b + 1, saida); // imprime subárvore direita
        saida.append("-----".repeat(b)).append(heap[p]).append('\n'); // imprime raiz
        lateral(2 * p, b + 1, saida); // imprime subárvore esquerda
    }

    public int tamanho() {
        return n;
    }

    public int valor(int k) {
        return heap[k];
    }

    public long trocas() {
        return trocas;
    }

    private void troca(int i, int j) {
        int aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
        trocas++;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(heap, 1, n + 1));
    }
}
