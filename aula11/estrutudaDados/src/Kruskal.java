import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Algoritmo de Kruskal: árvore geradora mínima de um grafo conexo não
 * orientado. Guloso: percorre as arestas da mais barata para a mais cara e
 * aceita uma aresta só se ela ligar dois componentes diferentes (Union-Find),
 * isto é, se não formar ciclo. Complexidade O(|E| log |E|), dominada pela
 * ordenação das arestas.
 */
public class Kruskal {

    public record Resultado(List<GrafoPonderado.Aresta> arvore, int pesoTotal) {
    }

    // Kruskal (G)
    public static Resultado executar(GrafoPonderado g, boolean rastrear) {
        List<GrafoPonderado.Aresta> t = new ArrayList<>(); // T <- vazio
        // Q <- E[G] em ordem crescente de peso. A ordenação é estável, então
        // arestas de mesmo peso saem na ordem em que foram cadastradas.
        List<GrafoPonderado.Aresta> q = new ArrayList<>(g.arestas());
        q.sort(Comparator.comparingInt(GrafoPonderado.Aresta::peso));
        UnionFind uf = new UnionFind(g.n()); // Make-Set(v) para cada vértice
        int peso = 0;
        for (GrafoPonderado.Aresta e : q) { // Extrai-Min(Q)
            String nome = "(" + g.nome(e.u()) + "," + g.nome(e.v()) + ") peso " + e.peso();
            if (uf.findSet(e.u()) != uf.findSet(e.v())) { // S_x e S_y disjuntos
                t.add(e); // T <- T U {(x, y)}
                uf.union(e.u(), e.v()); // une S_x e S_y
                peso += e.peso();
                if (rastrear) {
                    System.out.printf("ACEITA    %-18s | conjuntos: %s%n", nome, conjuntos(g, uf));
                }
            } else if (rastrear) {
                System.out.printf("REJEITA   %-18s | %s e %s ja estao no mesmo conjunto (formaria ciclo)%n",
                        nome, g.nome(e.u()), g.nome(e.v()));
            }
        }
        return new Resultado(t, peso);
    }

    /** Conjuntos disjuntos atuais, agrupados pelo representante. */
    private static String conjuntos(GrafoPonderado g, UnionFind uf) {
        Map<Integer, TreeSet<String>> grupos = new LinkedHashMap<>();
        for (int v = 0; v < g.n(); v++) {
            grupos.computeIfAbsent(uf.findSet(v), k -> new TreeSet<>()).add(g.nome(v));
        }
        StringBuilder sb = new StringBuilder();
        for (TreeSet<String> grupo : grupos.values()) {
            sb.append(grupo.toString().replace("[", "{").replace("]", "}").replace(" ", "")).append(' ');
        }
        return sb.toString().trim();
    }
}
