import java.util.Arrays;

/**
 * Fila (FIFO) estática circular: vetor de tamanho MAX, posição do início e
 * quantidade de elementos (o final é inferido). O operador mod faz o índice
 * "dar a volta", evitando o falso "fila cheia" depois de remoções.
 *
 * Observação: os slides mostram inicio = -1 na fila vazia, mas com esse
 * valor o enqueue calcularia (-1 + 0) mod MAX = -1. Por isso iniciamos com
 * inicio = 0 — a fila vazia é identificada por qtdElementos = 0.
 */
public class FilaEstatica {
    private final Integer[] a;
    private int inicio = 0;
    private int qtdElementos = 0;

    public FilaEstatica(int max) {
        this.a = new Integer[max];
    }

    // enqueue (fila, x)
    public boolean enqueue(int x) {
        if (qtdElementos == a.length) { // fila cheia
            return false;
        }
        int posicao = (inicio + qtdElementos) % a.length;
        a[posicao] = x;
        qtdElementos = qtdElementos + 1;
        return true;
    }

    /** dequeue (fila, i). Retorna null quando a fila está vazia ("retorna false"). */
    public Integer dequeue() {
        if (qtdElementos == 0) { // fila vazia
            return null;
        }
        int i = a[inicio];
        a[inicio] = null; // apenas para a visualização mostrar a posição livre
        inicio = (inicio + 1) % a.length;
        qtdElementos = qtdElementos - 1;
        return i;
    }

    @Override
    public String toString() {
        int fim = (inicio + qtdElementos - 1 + a.length) % a.length;
        return "A=" + Arrays.toString(a) + " inicio=" + inicio
                + " final=" + (qtdElementos == 0 ? "-" : fim) + " qtd=" + qtdElementos;
    }
}
