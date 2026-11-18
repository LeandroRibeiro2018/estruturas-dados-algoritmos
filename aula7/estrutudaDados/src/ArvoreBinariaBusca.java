import java.util.ArrayList;
import java.util.List;

/**
 * Árvore binária de busca: para todo nó p, as chaves da subárvore esquerda
 * são menores que p e as da subárvore direita são maiores.
 *
 * Inserção, busca e remoção custam O(h): O(n) no pior caso (árvore
 * degenerada) e O(lg n) no caso médio. Como em Java não há passagem por
 * referência, as operações que alteram a árvore devolvem a nova raiz
 * (equivalente ao "r <- ..." do pseudocódigo).
 */
public class ArvoreBinariaBusca {

    // altura (r) — árvore vazia tem altura -1
    public static int altura(NoArvore r) {
        if (r == null) { // árvore vazia
            return -1;
        }
        int he = altura(r.esq); // altura da subárvore esquerda
        int hd = altura(r.dir); // altura da subárvore direita
        if (he < hd) { // subárvore direita é maior
            return hd + 1;
        }
        return he + 1; // subárvore esquerda é maior
    }

    public static int tamanho(NoArvore r) {
        return r == null ? 0 : 1 + tamanho(r.esq) + tamanho(r.dir);
    }

    // insere (r, x) — versão recursiva
    public static NoArvore insereRecursivo(NoArvore r, NoArvore x) {
        if (r == null) { // árvore vazia
            return x;
        }
        if (x.chave < r.chave) { // insere na subárvore esquerda
            r.esq = insereRecursivo(r.esq, x);
        } else { // insere na subárvore direita
            r.dir = insereRecursivo(r.dir, x);
        }
        return r;
    }

    // insere (r, x) — versão iterativa
    public static NoArvore insereIterativo(NoArvore r, NoArvore x) {
        if (r == null) { // árvore vazia
            return x;
        }
        NoArvore q = r;
        NoArvore p = null;
        while (q != null) {
            p = q;
            if (x.chave < q.chave) {
                q = q.esq;
            } else {
                q = q.dir;
            }
        }
        if (x.chave < p.chave) { // insere no filho esquerdo
            p.esq = x;
        } else { // insere no filho direito
            p.dir = x;
        }
        return r;
    }

    public static NoArvore construir(int... chaves) {
        NoArvore raiz = null;
        for (int c : chaves) {
            raiz = insereRecursivo(raiz, new NoArvore(c));
        }
        return raiz;
    }

    // busca (r, k) — versão recursiva
    public static NoArvore busca(NoArvore r, int k) {
        if (r == null || r.chave == k) { // árvore vazia ou achou k
            return r;
        }
        if (k < r.chave) { // pesquisa na subárvore esquerda
            return busca(r.esq, k);
        }
        return busca(r.dir, k); // pesquisa na subárvore direita
    }

    /** Mesmo percurso da busca, registrando as chaves visitadas. */
    public static List<Integer> caminhoBusca(NoArvore r, int k) {
        List<Integer> caminho = new ArrayList<>();
        while (r != null) {
            caminho.add(r.chave);
            if (r.chave == k) {
                break;
            }
            r = k < r.chave ? r.esq : r.dir;
        }
        return caminho;
    }

    /** Descreve qual situação da remoção se aplica à chave k (para a demonstração). */
    public static String casoRemocao(NoArvore r, int k) {
        NoArvore x = busca(r, k);
        if (x == null) {
            return "k nao pertence a arvore";
        }
        if (x.esq == null && x.dir == null) {
            return "caso 1: no folha (sem filhos)";
        }
        if (x.esq == null || x.dir == null) {
            return "caso 2: no com 1 filho";
        }
        return "caso 3: no com 2 filhos (usa o sucessor " + minimo(x.dir).chave + ")";
    }

    // remove (r, k)
    public static NoArvore remove(NoArvore r, int k) {
        if (r == null) { // árvore vazia: k não pertence à árvore
            return null;
        }
        if (r.chave == k) { // achou k
            if (r.esq == null || r.dir == null) { // caso 1 ou caso 2
                return removeCaso1ou2(r);
            }
            return removeCaso3(r); // caso 3
        }
        if (k < r.chave) { // remove na subárvore esquerda
            r.esq = remove(r.esq, k);
        } else { // remove na subárvore direita
            r.dir = remove(r.dir, k);
        }
        return r;
    }

    // removeCaso1ou2 (r) — r tem 0 ou 1 filho; devolve quem ocupa o lugar de r
    private static NoArvore removeCaso1ou2(NoArvore r) {
        if (r.esq == null) { // subárvore esquerda vazia
            return r.dir; // caso 1 (r.dir = NULL) ou caso 2 (r.dir != NULL)
        }
        return r.esq; // caso 2 (r.esq != NULL e r.dir = NULL)
    }

    // removeCaso3 (r) — r tem 2 filhos; usa o sucessor lógico Y (menor da direita)
    private static NoArvore removeCaso3(NoArvore r) {
        NoArvore p = minimo(r.dir); // descendente mais à esquerda na subárvore direita
        r.chave = p.chave; // X <- Y
        // Y tem no máximo 1 filho (direito): removê-lo cai no caso 1 ou caso 2.
        r.dir = remove(r.dir, p.chave);
        return r;
    }

    private static NoArvore minimo(NoArvore p) {
        while (p.esq != null) {
            p = p.esq;
        }
        return p;
    }
}
