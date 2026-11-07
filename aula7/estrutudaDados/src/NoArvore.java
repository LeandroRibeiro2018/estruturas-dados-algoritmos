/**
 * Nó de árvore binária (implementação dinâmica): chave + referências para
 * as subárvores esquerda (esq) e direita (dir). O endereço da árvore é o
 * endereço do nó raiz.
 */
public class NoArvore {
    int chave;
    NoArvore esq;
    NoArvore dir;

    NoArvore(int chave) {
        this.chave = chave;
    }

    NoArvore(int chave, NoArvore esq, NoArvore dir) {
        this.chave = chave;
        this.esq = esq;
        this.dir = dir;
    }
}
