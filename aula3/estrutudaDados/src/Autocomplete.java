import java.util.Map;
import java.util.TreeMap;

/**
 * Exercício 7 — tomada de decisão sobre autocomplete.
 */
public class Autocomplete {
    public static Map<String, Integer> montarMatriz() {
        Map<String, Integer> notas = new TreeMap<>();
        notas.put("A — O(1)", 5);
        notas.put("B — O(n)", 3);
        notas.put("C — O(n²)", 1);
        return notas;
    }

    public static String escolherSolucao() {
        return "Escolha recomendada: A (índice pré-computado / O(1)), porque a latência deve ficar abaixo de ~100 ms sob alta frequência de consultas.";
    }
}
