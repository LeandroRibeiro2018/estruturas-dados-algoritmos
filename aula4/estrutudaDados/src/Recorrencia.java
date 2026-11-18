import java.util.HashMap;
import java.util.Map;

/**
 * Valores exatos das recorrências do Merge Sort, para conferir as soluções
 * fechadas obtidas pelos métodos iterativo, substituição e árvore de recursão.
 */
public class Recorrencia {
    private static final Map<Long, Long> memo = new HashMap<>();

    /** T(1) = 1; T(n) = T(ceil(n/2)) + T(floor(n/2)) + n. */
    public static long tMergeSort(long n) {
        if (n == 1) {
            return 1;
        }
        Long salvo = memo.get(n);
        if (salvo != null) {
            return salvo;
        }
        long t = tMergeSort((n + 1) / 2) + tMergeSort(n / 2) + n;
        memo.put(n, t);
        return t;
    }

    public static double lg(double n) {
        return Math.log(n) / Math.log(2);
    }

    /** Menor c tal que T(n) <= c n lg n para todo 2 <= n <= limite. */
    public static double menorConstante(long limite) {
        double c = 0;
        for (long n = 2; n <= limite; n++) {
            c = Math.max(c, tMergeSort(n) / (n * lg(n)));
        }
        return c;
    }

    /** Primeiro n em [2, limite] em que T(n) <= c n lg n falha, ou -1. */
    public static long primeiraFalha(double c, long limite) {
        for (long n = 2; n <= limite; n++) {
            if (tMergeSort(n) > c * n * lg(n) + 1e-9) {
                return n;
            }
        }
        return -1;
    }
}
