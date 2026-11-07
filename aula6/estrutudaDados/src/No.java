/**
 * Nó de uma lista simplesmente ligada: chave + referência para o sucessor.
 * O último nó aponta para NULL (null), pois não tem sucessor.
 */
public class No {
    int chave;
    No prox;

    No(int chave) {
        this.chave = chave;
    }
}
