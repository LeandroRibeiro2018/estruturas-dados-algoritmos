import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Classificação de árvores binárias (estritamente binária, completa, quase
 * completa e degenerada) e verificação do teorema
 * floor(lg n) <= h <= n - 1.
 */
public class TiposArvore {

    // Exemplos com o mesmo formato das figuras dos slides.
    public static NoArvore estritamenteBinaria() {
        return no(1, no(2), no(3, no(4, no(6), no(7)), no(5)));
    }

    public static NoArvore quaseCompleta() {
        return no(1,
                no(2, no(4), no(5)),
                no(3, no(6, no(8), no(9)), no(7)));
    }

    public static NoArvore completa() {
        return no(1,
                no(2, no(4, no(8), no(9)), no(5, no(10), no(11))),
                no(3, no(6, no(12), no(13)), no(7, no(14), no(15))));
    }

    public static NoArvore degenerada() { // zigue-zague
        return no(1, null, no(2, no(3, null, no(4)), null));
    }

    /** Cada nó tem 0 ou 2 filhos. */
    public static boolean estritamenteBinaria(NoArvore r) {
        if (r == null) {
            return true;
        }
        if ((r.esq == null) != (r.dir == null)) {
            return false;
        }
        return estritamenteBinaria(r.esq) && estritamenteBinaria(r.dir);
    }

    /** Todos os níveis completamente preenchidos: n = 2^(h+1) - 1. */
    public static boolean completa(NoArvore r) {
        int h = ArvoreBinariaBusca.altura(r);
        return ArvoreBinariaBusca.tamanho(r) == (1 << (h + 1)) - 1;
    }

    /** Até o penúltimo nível a árvore está completamente preenchida. */
    public static boolean quaseCompleta(NoArvore r) {
        int h = ArvoreBinariaBusca.altura(r);
        if (h <= 0) {
            return true;
        }
        return nosNoNivel(r, h - 1) == (1 << (h - 1));
    }

    /** Todo nó, exceto a folha, tem exatamente 1 filho. */
    public static boolean degenerada(NoArvore r) {
        while (r != null) {
            if (r.esq != null && r.dir != null) {
                return false;
            }
            r = r.esq != null ? r.esq : r.dir;
        }
        return true;
    }

    public static int nosNoNivel(NoArvore r, int nivel) {
        if (r == null) {
            return 0;
        }
        if (nivel == 0) {
            return 1;
        }
        return nosNoNivel(r.esq, nivel - 1) + nosNoNivel(r.dir, nivel - 1);
    }

    public static String resumo(String nome, NoArvore r) {
        int n = ArvoreBinariaBusca.tamanho(r);
        int h = ArvoreBinariaBusca.altura(r);
        int lgPiso = 31 - Integer.numberOfLeadingZeros(n); // floor(lg n)
        return String.format("%-22s n=%2d h=%2d | floor(lg n)=%d <= h=%d <= n-1=%d : %b%n"
                        + "%-22s estritamente binaria=%b, quase completa=%b, completa=%b, degenerada=%b",
                nome, n, h, lgPiso, h, n - 1, lgPiso <= h && h <= n - 1,
                "", estritamenteBinaria(r), quaseCompleta(r), completa(r), degenerada(r));
    }

    /** Ordem de inserção que gera uma BST perfeitamente balanceada para 1..n. */
    public static int[] ordemBalanceada(int n) {
        int[] ordem = new int[n];
        int k = 0;
        Deque<int[]> fila = new ArrayDeque<>();
        fila.addLast(new int[]{1, n});
        while (!fila.isEmpty()) {
            int[] intervalo = fila.removeFirst();
            if (intervalo[0] > intervalo[1]) {
                continue;
            }
            int meio = (intervalo[0] + intervalo[1]) / 2;
            ordem[k++] = meio;
            fila.addLast(new int[]{intervalo[0], meio - 1});
            fila.addLast(new int[]{meio + 1, intervalo[1]});
        }
        return ordem;
    }

    private static NoArvore no(int chave, NoArvore esq, NoArvore dir) {
        return new NoArvore(chave, esq, dir);
    }

    private static NoArvore no(int chave) {
        return new NoArvore(chave);
    }
}
