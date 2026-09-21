/**
 * Exercício 8 — três versões de Fibonacci.
 */
public class Fibonacci {
    public static long fibonacciConstante(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n deve ser >= 0");
        }
        double raiz5 = Math.sqrt(5.0);
        double phi = (1.0 + raiz5) / 2.0;
        return Math.round(Math.pow(phi, n) / raiz5);
    }

    public static long fibonacciLinear(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n deve ser >= 0");
        }
        if (n == 0 || n == 1) {
            return n;
        }
        long a = 0;
        long b = 1;
        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static long fibonacciQuadratico(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n deve ser >= 0");
        }
        if (n == 0 || n == 1) {
            return n;
        }
        long f = 0;
        for (int i = 2; i <= n; i++) {
            f = 0;
            for (int j = 1; j <= i; j++) {
                f += fibonacciLinear(j);
            }
        }
        return f;
    }

    public static String comparar(int n) {
        long inicioConst = System.nanoTime();
        long c = fibonacciConstante(n);
        long fimConst = System.nanoTime();

        long inicioLinear = System.nanoTime();
        long l = fibonacciLinear(n);
        long fimLinear = System.nanoTime();

        long inicioQuadratico = System.nanoTime();
        long q = fibonacciQuadratico(n);
        long fimQuadratico = System.nanoTime();

        return "n=" + n + " | constante=" + c + " (" + ((fimConst - inicioConst) / 1_000_000.0) + " ms)"
                + " | linear=" + l + " (" + ((fimLinear - inicioLinear) / 1_000_000.0) + " ms)"
                + " | quadratico=" + q + " (" + ((fimQuadratico - inicioQuadratico) / 1_000_000.0) + " ms)";
    }
}
