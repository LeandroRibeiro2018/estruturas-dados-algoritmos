import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Mochila binária (0-1) por programação dinâmica. w[] e v[] usam índice
 * a partir de 1 (posição 0 ignorada), como nos slides.
 */
public class Mochila {
    public static final int[] W_SLIDES = {0, 3, 2, 4, 1};
    public static final int[] V_SLIDES = {0, 8, 3, 9, 6};

    private long chamadas;

    public long chamadas() {
        return chamadas;
    }

    // mochila-Recursiva(w, v, n, W): Omega(2^n)
    public int recursiva(int[] w, int[] v, int n, int cap) {
        chamadas++;
        if (n == 0 || cap == 0) {
            return 0;
        }
        if (w[n] > cap) {
            return recursiva(w, v, n - 1, cap);
        }
        int a = recursiva(w, v, n - 1, cap);
        int b = recursiva(w, v, n - 1, cap - w[n]) + v[n];
        return Math.max(a, b);
    }

    // mochila-Memoization(w, v, n, W): tabela M[0..n][0..W] iniciada com -1
    public int memoization(int[] w, int[] v, int n, int cap) {
        int[][] m = new int[n + 1][cap + 1];
        for (int[] linha : m) {
            Arrays.fill(linha, -1);
        }
        return memo(w, v, n, cap, m);
    }

    private int memo(int[] w, int[] v, int n, int cap, int[][] m) {
        chamadas++;
        if (n == 0 || cap == 0) {
            return 0;
        }
        if (m[n][cap] != -1) {
            return m[n][cap];
        }
        if (w[n] > cap) {
            m[n][cap] = memo(w, v, n - 1, cap, m);
        } else {
            int a = memo(w, v, n - 1, cap, m);
            int b = memo(w, v, n - 1, cap - w[n], m) + v[n];
            m[n][cap] = Math.max(a, b);
        }
        return m[n][cap];
    }

    // mochila-Tabulation(w, v, n, W): devolve a tabela inteira M[0..n][0..W]
    public int[][] tabulation(int[] w, int[] v, int n, int cap) {
        int[][] m = new int[n + 1][cap + 1]; // linha 0 e coluna 0 já são 0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= cap; j++) {
                chamadas++;
                if (w[i] > j) {
                    m[i][j] = m[i - 1][j];
                } else {
                    m[i][j] = Math.max(m[i - 1][j], m[i - 1][j - w[i]] + v[i]);
                }
            }
        }
        return m;
    }

    /** Descrição de como M[i][j] foi calculada (igual aos slides 23-42). */
    public static String explicar(int[] w, int[] v, int[][] m, int i, int j) {
        String inicio = "objeto " + i + " (w=" + w[i] + ", v=" + v[i] + ") na capacidade " + j + ": ";
        if (w[i] > j) {
            return inicio + "w[" + i + "] = " + w[i] + " > " + j + " -> NAO cabe; M[" + i + "," + j + "] = M["
                    + (i - 1) + "," + j + "] = " + m[i][j];
        }
        int a = m[i - 1][j];
        int b = m[i - 1][j - w[i]] + v[i];
        return inicio + "w[" + i + "] = " + w[i] + " <= " + j + " -> SIM; M[" + i + "," + j + "] = max{M[" + (i - 1)
                + "," + j + "]=" + a + ", M[" + (i - 1) + "," + (j - w[i]) + "]+" + v[i] + "=" + b + "} = " + m[i][j];
    }

    // mochila(w, v, n, W, M): recupera quais objetos entraram, O(n)
    public static List<Integer> obter(int[] w, int[][] m, int n, int cap) {
        List<Integer> escolhidos = new ArrayList<>();
        int j = cap;
        for (int i = n; i >= 1; i--) {
            if (m[i][j] != m[i - 1][j]) { // objeto i está na mochila
                escolhidos.add(0, i);
                j -= w[i];
            }
        }
        return escolhidos;
    }
}
