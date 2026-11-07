/**
 * Pilha (LIFO) com implementação dinâmica: lista ligada em que só se
 * controla o endereço do nó do topo. Push e pop são O(1) e não há limite MAX.
 */
public class PilhaDinamica {
    private No topo;

    // push (pilha, x) — x é um nó cuja chave já foi definida
    public void push(No x) {
        x.prox = topo;
        topo = x;
    }

    public void push(int chave) {
        push(new No(chave));
    }

    /** pop (pilha, t). Retorna null quando a pilha está vazia ("retorna false"). */
    public Integer pop() {
        if (topo == null) { // pilha vazia
            return null;
        }
        int t = topo.chave;
        topo = topo.prox;
        return t;
    }

    public boolean vazia() {
        return topo == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("topo");
        for (No p = topo; p != null; p = p.prox) {
            sb.append(" -> ").append(p.chave);
        }
        return sb.append(" -> NULL").toString();
    }
}
