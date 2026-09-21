/**
 * Questão 7 — Árvore AVL para inventário em grande escala.
 *
 * Inserir códigos em ordem crescente numa BST comum degenera a árvore em
 * uma lista (busca vira O(n)). A AVL rebalanceia automaticamente após cada
 * inserção (rotações), mantendo a altura sempre próxima de log2(n).
 */
public class ArvoreAVL {
    public static class No {
        final int codigo;
        No esquerda;
        No direita;
        int altura = 1;

        No(int codigo) {
            this.codigo = codigo;
        }
    }

    public static No inserir(No no, int codigo) {
        if (no == null) {
            return new No(codigo);
        }
        if (codigo < no.codigo) {
            no.esquerda = inserir(no.esquerda, codigo);
        } else if (codigo > no.codigo) {
            no.direita = inserir(no.direita, codigo);
        } else {
            return no;
        }

        atualizarAltura(no);
        int balanceamento = fatorBalanceamento(no);

        if (balanceamento > 1 && codigo < no.esquerda.codigo) {
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && codigo > no.direita.codigo) {
            return rotacaoEsquerda(no);
        }
        if (balanceamento > 1 && codigo > no.esquerda.codigo) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && codigo < no.direita.codigo) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    public static int altura(No no) {
        return no == null ? 0 : no.altura;
    }

    private static void atualizarAltura(No no) {
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    private static int fatorBalanceamento(No no) {
        return no == null ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private static No rotacaoDireita(No y) {
        No x = y.esquerda;
        No t2 = x.direita;
        x.direita = y;
        y.esquerda = t2;
        atualizarAltura(y);
        atualizarAltura(x);
        return x;
    }

    private static No rotacaoEsquerda(No x) {
        No y = x.direita;
        No t2 = y.esquerda;
        y.esquerda = x;
        x.direita = t2;
        atualizarAltura(x);
        atualizarAltura(y);
        return y;
    }
}
