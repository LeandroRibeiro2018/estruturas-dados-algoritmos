/**
 * Exercício 7 — maior latência em uma janela de monitoramento.
 */
public class MaiorLatencia {
    public static long maiorLatencia(long[] latencias) {
        long maior = latencias[0];
        for (int i = 1; i < latencias.length; i++) {
            if (latencias[i] > maior) {
                maior = latencias[i];
            }
        }
        return maior;
    }
}
