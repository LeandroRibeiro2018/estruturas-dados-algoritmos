import java.util.Arrays;

/**
 * Pilha (LIFO) com implementação estática: vetor de tamanho MAX e o índice
 * do topo (topo = -1 indica pilha vazia). Push e pop são O(1).
 */
public class PilhaEstatica {
    private final int[] a;
    private int topo = -1;

    public PilhaEstatica(int max) {
        this.a = new int[max];
    }

    // push (pilha, x)
    public boolean push(int x) {
        if (topo == a.length - 1) { // pilha cheia
            return false;
        }
        topo = topo + 1;
        a[topo] = x;
        return true;
    }

    /** pop (pilha, t). Retorna null quando a pilha está vazia ("retorna false"). */
    public Integer pop() {
        if (topo == -1) { // pilha vazia
            return null;
        }
        int t = a[topo];
        topo = topo - 1;
        return t;
    }

    public boolean vazia() {
        return topo == -1;
    }

    @Override
    public String toString() {
        return "A=" + Arrays.toString(Arrays.copyOf(a, topo + 1)) + " topo=" + topo;
    }
}
