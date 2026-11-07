/**
 * Lista simplesmente ligada "sem cabeça": o primeiro nó é tão relevante
 * quanto os demais, e L (inicio) é o endereço do primeiro nó.
 *
 * Inserção O(1), busca O(n), remoção O(n). A remoção precisa tratar 3 casos:
 * (1) k no primeiro nó, (2) k em outra posição e (3) k ausente.
 */
public class ListaSemCabeca {
    private No inicio; // L

    // insere (L, x) — no início da lista
    public void insere(No x) {
        if (inicio == null) { // lista vazia
            x.prox = null;
            inicio = x;
        } else { // lista não vazia
            x.prox = inicio;
            inicio = x;
        }
    }

    // busca (L, k) — retorna o nó com a chave k ou null
    public No busca(int k) {
        No p = inicio;
        while (p != null && p.chave != k) {
            p = p.prox;
        }
        return p;
    }

    /**
     * remove (L, k). Retorna o caso que ocorreu (1, 2 ou 3), apenas para a
     * demonstração deixar visível qual ramo do pseudocódigo foi executado.
     */
    public int remove(int k) {
        No p = null;
        No q = inicio;
        while (q != null && q.chave != k) {
            p = q;
            q = q.prox;
        }
        if (q != null) { // achou k
            if (p == null) { // k está no 1º nó (Caso 1)
                inicio = q.prox;
                return 1;
            }
            p.prox = q.prox; // k não está no 1º nó (Caso 2)
            return 2;
        }
        return 3; // k não pertence à lista (Caso 3)
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("L");
        for (No p = inicio; p != null; p = p.prox) {
            sb.append(" -> ").append(p.chave);
        }
        return sb.append(" -> NULL").toString();
    }
}
