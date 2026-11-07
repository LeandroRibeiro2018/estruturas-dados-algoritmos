/**
 * Fila de prioridade com lista ligada com cabeça NÃO ordenada:
 * inserção O(1) no início e extração do máximo O(n), pois é preciso
 * percorrer a lista para encontrar a maior chave.
 */
public class FilaPrioridadeNaoOrdenada {
    private final No f = new No(0); // F (nó cabeça)

    // insere (F, x)
    public void insere(int chave) {
        No x = new No(chave);
        x.prox = f.prox;
        f.prox = x;
    }

    /** extrai (F). Retorna null quando a fila está vazia. */
    public Integer extrai() {
        No p = f;
        No ant = f;
        No q = f.prox;
        No max = f.prox;
        while (q != null) {
            if (q.chave > max.chave) { // atualiza max
                ant = p;
                max = q;
                p = q;
                q = q.prox;
            } else { // apenas vai para o próximo
                p = q;
                q = q.prox;
            }
        }
        if (max != null) { // fila não está vazia
            ant.prox = max.prox;
            return max.chave;
        }
        return null;
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
