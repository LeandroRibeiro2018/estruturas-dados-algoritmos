import java.util.Arrays;

/** Fibonacci: recursivo puro O(2^n), top-down (memoization) e bottom-up (tabulation), ambos O(n). */
public class Fibonacci {
    private long chamadas;

    public long chamadas() {
        return chamadas;
    }

    public void zerar() {
        chamadas = 0;
    }

    // fibonacci(n)
    public long recursivo(int n) {
        chamadas++;
        if (n == 0 || n == 1) {
            return n;
        }
        return recursivo(n - 1) + recursivo(n - 2);
    }

    // fibonacci-Memoization(n): mem[] inicializada com -1
    public long memoization(int n) {
        long[] mem = new long[Math.max(n + 1, 2)];
        Arrays.fill(mem, -1);
        return memo(n, mem);
    }

    private long memo(int n, long[] mem) {
        chamadas++;
        if (n == 0 || n == 1) {
            return n;
        }
        if (mem[n] != -1) { // valor calculado antes
            return mem[n];
        }
        mem[n] = memo(n - 1, mem) + memo(n - 2, mem);
        return mem[n];
    }

    // fibonacci-Tabulation(n)
    public long tabulation(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        long[] tab = new long[n + 1];
        tab[0] = 0;
        tab[1] = 1;
        for (int i = 2; i <= n; i++) {
            chamadas++; // uma atualização de tabela por posição
            tab[i] = tab[i - 1] + tab[i - 2];
        }
        return tab[n];
    }
}
