import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Questão 10 — DFS para investigação retroativa de falha.
 *
 * A investigação segue um único caminho (o histórico do veículo) até achar
 * a origem — não precisa explorar por camadas, precisa ir fundo em um
 * caminho. Custo: O(V+E) no pior caso.
 */
public class DFSInvestigacao {
    public static List<String> investigar(GrafoSetores grafo, String inicio) {
        List<String> ordem = new ArrayList<>();
        Set<String> visitados = new LinkedHashSet<>();
        dfs(inicio, grafo, visitados, ordem);
        return ordem;
    }

    private static void dfs(String atual, GrafoSetores grafo, Set<String> visitados, List<String> ordem) {
        if (visitados.contains(atual)) {
            return;
        }
        visitados.add(atual);
        ordem.add(atual);
        for (String proximo : grafo.vizinhos(atual)) {
            dfs(proximo, grafo, visitados, ordem);
        }
    }
}
