import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Exercício 2 — comparação de crescimento assintótico.
 */
public class CrescimentoAssintotico {
    public static Map<String, String> estimarTempos() {
        Map<String, String> tabela = new LinkedHashMap<>();
        tabela.put("O(1)", "1 ms em 1.000, 1 ms em 10.000, 1 ms em 100.000, 1 ms em 1.000.000");
        tabela.put("O(n)", "1 ms em 1.000, 10 ms em 10.000, 100 ms em 100.000, 1.000 ms em 1.000.000");
        tabela.put("O(n²)", "1 ms em 1.000, 100 ms em 10.000, 10.000 ms em 100.000, 1.000.000 ms em 1.000.000");
        tabela.put("O(2^n)", "impraticável já em n moderado; cresce muito mais rápido do que qualquer polinômio");
        return tabela;
    }
}
