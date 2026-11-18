/**
 * Fila (FIFO) dinâmica: lista ligada com referências para o nó do início e
 * para o nó do final. Enqueue e dequeue são O(1).
 */
public class FilaDinamica {
    private No inicio;
    private No fim;

    // enqueue (fila, x) — x é um nó cuja chave já foi definida
    public void enqueue(No x) {
        if (fim == null) { // fila vazia
            x.prox = null;
            inicio = x;
            fim = x;
        } else { // fila não vazia
            x.prox = null;
            fim.prox = x;
            fim = x;
        }
    }

    public void enqueue(int chave) {
        enqueue(new No(chave));
    }

    /** dequeue (fila, i). Retorna null quando a fila está vazia ("retorna false"). */
    public Integer dequeue() {
        if (inicio == null) { // fila vazia
            return null;
        }
        int i = inicio.chave;
        inicio = inicio.prox;
        if (inicio == null) { // fila ficou vazia
            fim = null;
        }
        return i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("inicio");
        for (No p = inicio; p != null; p = p.prox) {
            sb.append(" -> ").append(p.chave);
        }
        sb.append(" -> NULL | final=").append(fim == null ? "NULL" : fim.chave);
        return sb.toString();
    }
}
