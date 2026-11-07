import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Grafo G = (V, E) como Tipo Abstrato de Dados, representado por lista de
 * adjacência (vetor de n listas, uma por vértice). A matriz de adjacência é
 * gerada a partir dela para comparação.
 *
 * Em grafo não orientado cada aresta (u, v) aparece nas listas de u e de v;
 * em grafo orientado (dígrafo) aparece só na lista de u. Arestas paralelas
 * (multigrafo, como as pontes de Königsberg) são permitidas.
 */
public class Grafo {
    public record Aresta(int destino, int peso) {
    }

    private final String[] nomes;
    private final boolean orientado;
    private final List<List<Aresta>> adj = new ArrayList<>();

    public Grafo(boolean orientado, String... nomes) {
        this.orientado = orientado;
        this.nomes = nomes;
        for (int i = 0; i < nomes.length; i++) {
            adj.add(new ArrayList<>());
        }
    }

    /** Grafo com vértices nomeados "0", "1", ..., "n-1". */
    public static Grafo numerado(boolean orientado, int n) {
        String[] nomes = new String[n];
        for (int i = 0; i < n; i++) {
            nomes[i] = String.valueOf(i);
        }
        return new Grafo(orientado, nomes);
    }

    public void adicionarAresta(String u, String v) {
        adicionarAresta(u, v, 1);
    }

    public void adicionarAresta(String u, String v, int peso) {
        int iu = indice(u);
        int iv = indice(v);
        adj.get(iu).add(new Aresta(iv, peso));
        if (!orientado) {
            adj.get(iv).add(new Aresta(iu, peso));
        }
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

    public boolean orientado() {
        return orientado;
    }

    public List<Aresta> adj(int u) {
        return adj.get(u);
    }

    public int numArestas() {
        int soma = 0;
        for (List<Aresta> lista : adj) {
            soma += lista.size();
        }
        return orientado ? soma : soma / 2;
    }

    /** Não orientado: arestas que incidem no vértice. Orientado: grau de saída. */
    public int grau(int v) {
        return adj.get(v).size();
    }

    public int grauEntrada(int v) {
        int g = 0;
        for (List<Aresta> lista : adj) {
            for (Aresta a : lista) {
                if (a.destino() == v) {
                    g++;
                }
            }
        }
        return g;
    }

    /** Matriz n x n: peso da aresta (1 se não ponderado), 0 se não há aresta. */
    public int[][] matriz() {
        int[][] a = new int[n()][n()];
        for (int u = 0; u < n(); u++) {
            for (Aresta e : adj.get(u)) {
                a[u][e.destino()] += e.peso();
            }
        }
        return a;
    }

    public String matrizFormatada() {
        int[][] a = matriz();
        StringBuilder sb = new StringBuilder("      ");
        for (String nome : nomes) {
            sb.append(String.format("%4s", nome));
        }
        sb.append('\n');
        for (int i = 0; i < n(); i++) {
            sb.append(String.format("%4s |", nomes[i]));
            for (int j = 0; j < n(); j++) {
                sb.append(String.format("%4d", a[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public String listaFormatada(boolean ponderado) {
        StringBuilder sb = new StringBuilder();
        for (int u = 0; u < n(); u++) {
            sb.append(String.format("%4s", nomes[u]));
            for (Aresta e : adj.get(u)) {
                sb.append(" -> ").append(nomes[e.destino()]);
                if (ponderado) {
                    sb.append(", ").append(e.peso());
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public String vertices() {
        return "{" + String.join(", ", nomes) + "}";
    }

    /** Conjunto de arestas E, sem repetir (u, v) e (v, u) em grafo não orientado. */
    public String arestas() {
        List<String> lista = new ArrayList<>();
        for (int u = 0; u < n(); u++) {
            for (Aresta e : adj.get(u)) {
                if (orientado || u <= e.destino()) {
                    lista.add("(" + nomes[u] + "," + nomes[e.destino()] + ")");
                }
            }
        }
        return "{" + String.join(", ", lista) + "}";
    }

    @Override
    public String toString() {
        return Arrays.toString(nomes);
    }
}
