import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Questão 1 — Fila (Queue) para entrada de veículos.
 *
 * A ordem de chegada deve ser mantida (FIFO): o primeiro veículo que entra
 * é o primeiro a ser montado. Enqueue/dequeue são O(1) com ArrayDeque.
 */
public class FilaVeiculos {
    public static void demonstrar(int[] veiculos) {
        Queue<Integer> fila = new ArrayDeque<>();
        for (int id : veiculos) {
            fila.offer(id);
            System.out.println("enqueue(veiculo " + id + ") -> fila: " + fila);
        }
        while (!fila.isEmpty()) {
            int proximo = fila.poll();
            System.out.println("dequeue() -> monta veiculo " + proximo + " | fila restante: " + fila);
        }
    }
}
