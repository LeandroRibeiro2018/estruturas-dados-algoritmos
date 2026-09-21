import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Questão 2 — Pilha (Stack) para retrabalho.
 *
 * O retrabalho retoma exatamente do último defeito tratado (LIFO), como o
 * botão "desfazer" de um editor. Push/pop são O(1) com ArrayDeque.
 */
public class PilhaRetrabalho {
    public static void demonstrar(int[] etapas) {
        Deque<String> pilha = new ArrayDeque<>();
        for (int etapa : etapas) {
            pilha.push("etapa " + etapa);
            System.out.println("push(etapa " + etapa + ") -> pilha: " + pilha);
        }
        while (!pilha.isEmpty()) {
            String topo = pilha.pop();
            System.out.println("pop() -> retoma " + topo + " | pilha restante: " + pilha);
        }
    }
}
