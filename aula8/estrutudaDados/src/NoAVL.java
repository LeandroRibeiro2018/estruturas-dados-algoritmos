/**
 * Nó de árvore AVL: além da chave e dos filhos, guarda a altura h do nó
 * (folha tem h = 0), usada para calcular o fator de balanceamento em O(1).
 */
public class NoAVL {
    int chave;
    int h;
    NoAVL esq;
    NoAVL dir;

    NoAVL(int chave) {
        this.chave = chave;
    }
}
