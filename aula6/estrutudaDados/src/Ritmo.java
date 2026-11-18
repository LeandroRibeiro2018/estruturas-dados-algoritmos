import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Modo passo a passo: quando ativado, cada linha impressa aparece com um
 * pequeno atraso, e o Main espera ENTER antes de executar o exercício e
 * antes de voltar ao menu.
 *
 * Ative com -Dritmo.ms=300 (java) ou com a variável de ambiente RITMO_MS.
 * Sem configuração (ou com 0) o programa roda na velocidade normal.
 * O atraso acontece só ao imprimir, fora dos trechos cronometrados.
 */
public final class Ritmo {
    private static int atrasoMs;

    private Ritmo() {
    }

    public static void instalar() {
        atrasoMs = lerAtraso();
        if (atrasoMs <= 0) {
            return;
        }
        PrintStream original = System.out;
        OutputStream lento = new OutputStream() {
            @Override
            public void write(int b) {
                original.write(b);
                if (b == '\n') {
                    original.flush();
                    dormir();
                }
            }

            @Override
            public void write(byte[] b, int off, int len) {
                for (int i = off; i < off + len; i++) {
                    write(b[i]);
                }
            }

            @Override
            public void flush() {
                original.flush();
            }
        };
        System.setOut(new PrintStream(lento, true, original.charset()));
    }

    public static boolean ativo() {
        return atrasoMs > 0;
    }

    private static int lerAtraso() {
        String valor = System.getProperty("ritmo.ms", System.getenv("RITMO_MS"));
        if (valor == null || valor.isBlank()) {
            return 0;
        }
        try {
            return Math.max(0, Integer.parseInt(valor.trim()));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static void dormir() {
        try {
            Thread.sleep(atrasoMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
