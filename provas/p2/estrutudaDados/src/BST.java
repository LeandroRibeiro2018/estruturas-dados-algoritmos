/**
 * Questão 6 — Árvore binária de busca (BST) para peças por código.
 *
 * A BST mantém os códigos ordenados e permite busca eficiente mesmo com o
 * inventário mudando. Busca, inserção e remoção são O(log n) se
 * balanceada, ou O(n) se degenerar (ver ArvoreAVL).
 */
public class BST {
    public static class No {
        final int codigo;
        No esquerda;
        No direita;

        No(int codigo) {
            this.codigo = codigo;
        }
    }

    public static int comparacoes = 0;

    public static No inserir(No raiz, int codigo) {
        if (raiz == null) {
            return new No(codigo);
        }
        if (codigo < raiz.codigo) {
            raiz.esquerda = inserir(raiz.esquerda, codigo);
        } else if (codigo > raiz.codigo) {
            raiz.direita = inserir(raiz.direita, codigo);
        }
        return raiz;
    }

    public static boolean buscar(No raiz, int codigo) {
        comparacoes = 0;
        No atual = raiz;
        while (atual != null) {
            comparacoes++;
            if (codigo == atual.codigo) {
                return true;
            }
            atual = codigo < atual.codigo ? atual.esquerda : atual.direita;
        }
        return false;
    }

    public static int altura(No raiz) {
        if (raiz == null) {
            return 0;
        }
        return 1 + Math.max(altura(raiz.esquerda), altura(raiz.direita));
    }
}
