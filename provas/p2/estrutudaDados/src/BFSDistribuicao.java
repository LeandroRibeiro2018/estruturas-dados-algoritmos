import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Questão 9 — BFS para distribuição de peças por proximidade.
 *
 * BFS visita primeiro tudo que está a 1 rota de distância do depósito,
 * depois a 2 rotas, e assim por diante — a ordem certa para entregar por
 * proximidade. Custo: O(V+E).
 */
public class BFSDistribuicao {
    public static Map<String, Integer> distanciasAPartirDe(GrafoSetores grafo, String origem) {
        Map<String, Integer> distancia = new LinkedHashMap<>();
        Queue<String> fila = new ArrayDeque<>();
        fila.add(origem);
        distancia.put(origem, 0);

        while (!fila.isEmpty()) {
            String atual = fila.poll();
            for (String vizinho : grafo.vizinhos(atual)) {
                if (!distancia.containsKey(vizinho)) {
                    distancia.put(vizinho, distancia.get(atual) + 1);
                    fila.add(vizinho);
                }
            }
        }
        return distancia;
    }
}
