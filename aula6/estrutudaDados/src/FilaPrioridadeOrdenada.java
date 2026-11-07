/**
 * Fila de prioridade com lista ligada com cabeça ordenada de forma
 * DECRESCENTE: inserção O(n) (procura a posição) e extração O(1)
 * (o máximo é sempre o primeiro elemento).
 */
public class FilaPrioridadeOrdenada {
    private final No f = new No(0); // F (nó cabeça)

    // insere (F, x)
    public void insere(int chave) {
        No x = new No(chave);
        No p = f;
        No q = f.prox;
        while (q != null && q.chave > x.chave) {
            p = q;
            q = q.prox;
        }
        x.prox = q;
        p.prox = x;
    }

    /**
     * extrai (F). O pseudocódigo dos slides não testa fila vazia; aqui
     * retornamos null nesse caso para não acessar uma referência nula.
     */
    public Integer extrai() {
        No p = f.prox;
        if (p == null) {
            return null;
        }
        f.prox = p.prox;
        return p.chave;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("F -> [Cabeca]");
        for (No p = f.prox; p != null; p = p.prox) {
            sb.append(" -> ").append(p.chave);
        }
        return sb.append(" -> NULL").toString();
    }
}
