import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Grafo ponderado com lista de adjacência. Em grafo não orientado cada
 * aresta aparece nas duas listas e também é guardada uma única vez em
 * {@link #arestas()}, que é a entrada do algoritmo de Kruskal.
 */
public class GrafoPonderado {
    public record Adj(int destino, int peso) {
    }

    public record Aresta(int u, int v, int peso) {
    }

    private final String[] nomes;
    private final boolean orientado;
    private final List<List<Adj>> adj = new ArrayList<>();
    private final List<Aresta> arestas = new ArrayList<>();

    public GrafoPonderado(boolean orientado, String... nomes) {
        this.orientado = orientado;
        this.nomes = nomes;
        for (int i = 0; i < nomes.length; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void aresta(String u, String v, int peso) {
        aresta(indice(u), indice(v), peso);
    }

    public void aresta(int u, int v, int peso) {
        adj.get(u).add(new Adj(v, peso));
        if (!orientado) {
            adj.get(v).add(new Adj(u, peso));
        }
        arestas.add(new Aresta(u, v, peso));
    }

    /** Grafo orientado aleatório com n vértices e ~grauMedio arestas por vértice (pesos 1..100). */
    public static GrafoPonderado aleatorio(int n, int grauMedio, long semente) {
        String[] nomes = new String[n];
        for (int i = 0; i < n; i++) {
            nomes[i] = "v" + i;
        }
        GrafoPonderado g = new GrafoPonderado(true, nomes);
        Random r = new Random(semente);
        for (int u = 0; u < n; u++) {
            g.aresta(u, (u + 1) % n, 1 + r.nextInt(100)); // garante que tudo é alcançável
            for (int k = 1; k < grauMedio; k++) {
                g.aresta(u, r.nextInt(n), 1 + r.nextInt(100));
            }
        }
        return g;
    }

    public int indice(String nome) {
        for (int i = 0; i < nomes.length; i++) {
            if (nomes[i].equals(nome)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Vertice inexistente: " + nome);
    }

    public int n() {
        return nomes.length;
    }

    public String nome(int v) {
        return nomes[v];
    }

    public List<Adj> adj(int u) {
        return adj.get(u);
    }

    public List<Aresta> arestas() {
        return arestas;
    }
}
