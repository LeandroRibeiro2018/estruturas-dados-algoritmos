/**
 * Teorema Mestre (versão da aula) para T(n) = a T(n/b) + f(n), com
 * f(n) = n^k (lg n)^p.
 *
 * (1) f = O(n^(log_b a - eps))            -> T = Theta(n^(log_b a))
 * (2) f = Theta(n^(log_b a))              -> T = Theta(n^(log_b a) lg n)
 * (3) f = Omega(n^(log_b a + eps)) e a f(n/b) <= c f(n), c < 1
 *                                         -> T = Theta(f(n))
 * Se k = log_b a e p > 0, nenhum dos 3 casos da aula se aplica.
 */
public class MetodoMestre {
    private static final double EPS = 1e-9;

    public static String resolver(double a, double b, double k, int p) {
        if (a < 1 || b <= 1) {
            return "Nao se aplica: exige a >= 1 e b > 1.";
        }
        double expoente = Math.log(a) / Math.log(b); // log_b a
        String f = descreverF(k, p);
        String nLogBA = potencia(expoente);
        String base = String.format("a=%s, b=%s, f(n)=%s | n^(log_b a) = %s%n  ", fmt(a), fmt(b), f, nLogBA);
        if (k < expoente - EPS) {
            return base + String.format(java.util.Locale.ROOT,
                    "f(n) e dominada (eps=%.3f) -> Caso 1 -> T(n) = Theta(%s)", expoente - k, nLogBA);
        }
        if (Math.abs(k - expoente) < EPS) {
            if (p == 0) {
                String t = nLogBA.equals("1") ? "lg n" : nLogBA + " lg n";
                return base + "f(n) = Theta(" + nLogBA + ") -> Caso 2 -> T(n) = Theta(" + t + ")";
            }
            return base + "f(n) difere de n^(log_b a) apenas por fator lg n: nenhum caso da aula se aplica.";
        }
        double c = regularidade(a, b, k, p);
        if (c < 1) {
            return base + String.format(java.util.Locale.ROOT, "f(n) domina (eps=%.3f); regularidade a f(n/b) <= %.3f f(n) "
                    + "-> Caso 3 -> T(n) = Theta(%s)", k - expoente, c, f);
        }
        return base + "f(n) domina, mas a condicao de regularidade falhou: nao se aplica.";
    }

    /** Maior razão a f(n/b) / f(n) observada para n em [b^4, 10^9] (deve ficar < 1). */
    private static double regularidade(double a, double b, double k, int p) {
        double max = 0;
        for (double n = Math.pow(b, 4); n <= 1e9; n *= 1.5) {
            max = Math.max(max, a * f(n / b, k, p) / f(n, k, p));
        }
        return max;
    }

    static double f(double n, double k, int p) {
        return Math.pow(n, k) * Math.pow(Math.log(n) / Math.log(2), p);
    }

    private static String descreverF(double k, int p) {
        String parteN = k == 0 ? (p == 0 ? "1" : "") : (k == 1 ? "n" : "n^" + fmt(k));
        String parteLog = p == 0 ? "" : (p == 1 ? "lg n" : "(lg n)^" + p);
        return (parteN + (parteN.isEmpty() || parteLog.isEmpty() ? "" : " ") + parteLog);
    }

    /** n^e legível: "1", "n", "n^2" ou "n^0.792". */
    private static String potencia(double e) {
        if (Math.abs(e) < EPS) {
            return "1";
        }
        if (Math.abs(e - 1) < EPS) {
            return "n";
        }
        if (Math.abs(e - Math.rint(e)) < EPS) {
            return "n^" + (long) Math.rint(e);
        }
        return String.format(java.util.Locale.ROOT, "n^%.3f", e);
    }

    private static String fmt(double v) {
        return v == Math.rint(v) ? String.valueOf((long) v) : String.valueOf(v);
    }
}
