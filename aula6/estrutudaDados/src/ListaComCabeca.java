/**
 * Lista simplesmente ligada "com cabeça": o primeiro nó (head/dummy node)
 * serve apenas para indicar o início da lista. Aqui ele guarda uma
 * informação útil: a quantidade de elementos.
 *
 * Mesma complexidade da lista sem cabeça, mas os casos "lista vazia" e
 * "k no primeiro nó" deixam de ser especiais — a implementação fica mais
 * simples.
 */
public class ListaComCabeca {
    private final No cabeca = new No(0); // L; cabeca.chave = qtd de elementos

    // insere (L, x)
    public void insere(No x) {
        x.prox = cabeca.prox;
        cabeca.prox = x;
        cabeca.chave++;
    }

    // busca (L, k)
    public No busca(int k) {
        No p = cabeca.prox; // p <- L se fosse sem cabeça
        while (p != null && p.chave != k) {
            p = p.prox;
        }
        return p;
    }

    // remove (L, k) — apenas 2 casos: achou ou não achou
    public boolean remove(int k) {
        No p = cabeca;
        No q = cabeca.prox;
        while (q != null && q.chave != k) {
            p = q;
            q = q.prox;
        }
        if (q != null) { // achou k
            p.prox = q.prox;
            cabeca.chave--;
            return true;
        }
        return false;
    }

    public int tamanho() {
        return cabeca.chave;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("L -> [Cabeca qtd=" + cabeca.chave + "]");
        for (No p = cabeca.prox; p != null; p = p.prox) {
            sb.append(" -> ").append(p.chave);
        }
        return sb.append(" -> NULL").toString();
    }
}
