import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Exercício 2 — reprocessamento de fila com limite de tentativas.
 *
 * O objetivo aqui é demonstrar a ideia de terminação: cada item que falha recebe
 * uma contagem de tentativas; quando a contagem cruza o limite, o item sai da fila.
 */
public class FilaReprocessamento {
    public static void processarFila(Deque<Integer> fila, int maxTentativas) {
        Map<Integer, Integer> tentativas = new HashMap<>();

        while (!fila.isEmpty()) {
            int item = fila.poll();
            int tentativasAtual = tentativas.getOrDefault(item, 0);

            boolean sucesso = tentarReenviar(item);
            if (!sucesso) {
                tentativasAtual++;
                tentativas.put(item, tentativasAtual);
                if (tentativasAtual < maxTentativas) {
                    fila.offer(item);
                }
            }
        }
    }

    private static boolean tentarReenviar(int item) {
        return item % 5 == 0;
    }

    public static Deque<Integer> criarFilaExemplo() {
        Deque<Integer> fila = new ArrayDeque<>();
        fila.offer(10);
        fila.offer(11);
        fila.offer(12);
        fila.offer(13);
        return fila;
    }
}
