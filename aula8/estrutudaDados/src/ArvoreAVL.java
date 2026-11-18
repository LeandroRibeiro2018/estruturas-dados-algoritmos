import java.util.ArrayList;
import java.util.List;

/**
 * Árvore AVL (Adelson-Velsky e Landis): árvore binária de busca em que,
 * para todo nó, |hE - hD| <= 1. O fator de balanceamento FB = hE - hD vale
 * -1, 0 ou 1; quando uma inserção/remoção o leva a +2 ou -2, uma rotação
 * (simples ou dupla, cada uma O(1)) restaura a propriedade. A altura fica
 * O(log n), logo inserção, busca e remoção são O(log n).
 *
 * As operações devolvem a nova raiz da subárvore (equivalente ao "r <- ...").
 * Cada rotação aplicada é registrada em {@link #log} para a demonstração.
 */
public class ArvoreAVL {
    public static final List<String> log = new ArrayList<>();

    public static int altura(NoAVL p) {
        return p == null ? -1 : p.h;
    }

    public static int fator(NoAVL p) {
        return altura(p.esq) - altura(p.dir);
    }

    private static void atualizaAltura(NoAVL p) {
        p.h = Math.max(altura(p.esq), altura(p.dir)) + 1;
    }

    // rotacaoDireita (p)
    public static NoAVL rotacaoDireita(NoAVL p) {
        NoAVL u = p.esq; // u é filho esquerdo de p
        p.esq = u.dir; // filho direito de u torna-se filho esquerdo de p
        u.dir = p; // p torna-se filho direito de u
        p.h = Math.max(altura(p.esq), altura(p.dir)) + 1;
        u.h = Math.max(altura(u.esq), p.h) + 1;
        return u;
    }

    // rotacaoEsquerda (p)
    public static NoAVL rotacaoEsquerda(NoAVL p) {
        NoAVL y = p.dir; // y é filho direito de p
        p.dir = y.esq; // filho esquerdo de y torna-se filho direito de p
        y.esq = p; // p torna-se filho esquerdo de y
        p.h = Math.max(altura(p.esq), altura(p.dir)) + 1;
        y.h = Math.max(p.h, altura(y.dir)) + 1;
        return y;
    }

    // rotacaoEsquerdaDireita (p) — rotação dupla à direita
    public static NoAVL rotacaoEsquerdaDireita(NoAVL p) {
        NoAVL u = p.esq; // u é filho esquerdo de p
        p.esq = rotacaoEsquerda(u);
        return rotacaoDireita(p);
    }

    // rotacaoDireitaEsquerda (p) — rotação dupla à esquerda
    public static NoAVL rotacaoDireitaEsquerda(NoAVL p) {
        NoAVL y = p.dir; // y é filho direito de p
        p.dir = rotacaoDireita(y);
        return rotacaoEsquerda(p);
    }

    // insere (r, x)
    public static NoAVL insere(NoAVL r, NoAVL x) {
        if (r == null) { // insere x
            return x;
        }
        if (x.chave < r.chave) { // insere na subárvore esquerda
            r.esq = insere(r.esq, x);
            if (altura(r.esq) - altura(r.dir) == 2) { // desbalanceou
                if (x.chave < r.esq.chave) { // esq + esq = "reta"
                    log.add("RD em " + r.chave + " (inseriu " + x.chave + ")");
                    r = rotacaoDireita(r);
                } else { // esq + dir = "curva"
                    log.add("RED em " + r.chave + " (inseriu " + x.chave + ")");
                    r = rotacaoEsquerdaDireita(r);
                }
            }
        } else { // insere na subárvore direita
            r.dir = insere(r.dir, x);
            if (altura(r.esq) - altura(r.dir) == -2) { // desbalanceou
                if (x.chave > r.dir.chave) { // dir + dir = "reta"
                    log.add("RE em " + r.chave + " (inseriu " + x.chave + ")");
                    r = rotacaoEsquerda(r);
                } else { // dir + esq = "curva"
                    log.add("RDE em " + r.chave + " (inseriu " + x.chave + ")");
                    r = rotacaoDireitaEsquerda(r);
                }
            }
        }
        atualizaAltura(r);
        return r;
    }

    public static NoAVL insere(NoAVL r, int chave) {
        return insere(r, new NoAVL(chave));
    }

    /**
     * Remoção: igual à da árvore binária de busca (casos 0/1 filho e 2 filhos
     * com o sucessor lógico), seguida de rotação no caminho de volta. Como não
     * há nó inserido para indicar "reta" ou "curva", a escolha usa o FB do
     * filho do lado mais alto.
     */
    public static NoAVL remove(NoAVL r, int k) {
        if (r == null) { // k não pertence à árvore
            return null;
        }
        if (k < r.chave) {
            r.esq = remove(r.esq, k);
        } else if (k > r.chave) {
            r.dir = remove(r.dir, k);
        } else if (r.esq == null || r.dir == null) { // 0 ou 1 filho
            return r.esq == null ? r.dir : r.esq;
        } else { // 2 filhos: copia o sucessor e o remove da subárvore direita
            NoAVL p = r.dir;
            while (p.esq != null) {
                p = p.esq;
            }
            r.chave = p.chave;
            r.dir = remove(r.dir, p.chave);
        }
        return rebalanceia(r, "removeu " + k);
    }

    private static NoAVL rebalanceia(NoAVL r, String motivo) {
        atualizaAltura(r);
        int fb = fator(r);
        if (fb == 2) {
            if (fator(r.esq) >= 0) {
                log.add("RD em " + r.chave + " (" + motivo + ")");
                return rotacaoDireita(r);
            }
            log.add("RED em " + r.chave + " (" + motivo + ")");
            return rotacaoEsquerdaDireita(r);
        }
        if (fb == -2) {
            if (fator(r.dir) <= 0) {
                log.add("RE em " + r.chave + " (" + motivo + ")");
                return rotacaoEsquerda(r);
            }
            log.add("RDE em " + r.chave + " (" + motivo + ")");
            return rotacaoDireitaEsquerda(r);
        }
        return r;
    }

    /** Inserção de BST comum (sem rotações), mantendo h atualizado — para comparação. */
    public static NoAVL insereSemBalancear(NoAVL r, int chave) {
        if (r == null) {
            return new NoAVL(chave);
        }
        if (chave < r.chave) {
            r.esq = insereSemBalancear(r.esq, chave);
        } else {
            r.dir = insereSemBalancear(r.dir, chave);
        }
        atualizaAltura(r);
        return r;
    }

    /** Verifica a propriedade AVL em todos os nós. */
    public static boolean ehAVL(NoAVL r) {
        if (r == null) {
            return true;
        }
        return Math.abs(fator(r)) <= 1 && ehAVL(r.esq) && ehAVL(r.dir);
    }

    /** Parênteses aninhados em pré-ordem, com o FB de cada nó. */
    public static String parenteses(NoAVL r) {
        if (r == null) {
            return "()";
        }
        return "( " + r.chave + "[FB=" + fator(r) + "] " + parenteses(r.esq) + " " + parenteses(r.dir) + " )";
    }

    /** Visualização "deitada" (direita, raiz, esquerda) mostrando o FB. */
    public static void lateral(NoAVL r, int b, StringBuilder saida) {
        if (r == null) {
            return;
        }
        lateral(r.dir, b + 1, saida);
        saida.append("-----".repeat(b)).append(r.chave)
                .append(" (FB=").append(fator(r)).append(")\n");
        lateral(r.esq, b + 1, saida);
    }

    public static void inOrdem(NoAVL r, List<Integer> visita) {
        if (r != null) {
            inOrdem(r.esq, visita);
            visita.add(r.chave);
            inOrdem(r.dir, visita);
        }
    }
}
