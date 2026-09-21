import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Questão 8 — Grafo para setores da fábrica interligados.
 *
 * Os setores se conectam de forma arbitrária (não hierárquica) e podem
 * formar ciclos. Percorrer o grafo inteiro (BFS/DFS) custa O(V+E).
 */
public class GrafoSetores {
    private final Map<String, Set<String>> adjacencia = new LinkedHashMap<>();

    public void addRota(String origem, String destino) {
        adjacencia.computeIfAbsent(origem, k -> new LinkedHashSet<>()).add(destino);
        adjacencia.computeIfAbsent(destino, k -> new LinkedHashSet<>()).add(origem);
    }

    public Set<String> vizinhos(String setor) {
        return adjacencia.getOrDefault(setor, Set.of());
    }

    public Set<String> setores() {
        return adjacencia.keySet();
    }

    public int totalVertices() {
        return adjacencia.size();
    }

    public int totalArestas() {
        int soma = 0;
        for (Set<String> vizinhos : adjacencia.values()) {
            soma += vizinhos.size();
        }
        return soma / 2;
    }

    public static GrafoSetores montarExemplo() {
        GrafoSetores grafo = new GrafoSetores();
        grafo.addRota("Recebimento", "Estamparia");
        grafo.addRota("Estamparia", "Solda");
        grafo.addRota("Solda", "Pintura");
        grafo.addRota("Pintura", "Montagem");
        grafo.addRota("Montagem", "Inspecao");
        grafo.addRota("Inspecao", "Recebimento");
        grafo.addRota("Montagem", "Expedicao");
        grafo.addRota("Pintura", "Expedicao");
        return grafo;
    }
}
