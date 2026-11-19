import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Bellman-Ford (PD bottom-up): caminhos mínimos com pesos negativos e detecção de ciclo negativo. */
public class BellmanFord {
    public static final long INF = Long.MAX_VALUE / 4;

    public record Aresta(int u, int v, int w) {
    }

    public final String[] nomes;
    public final List<Aresta> arestas = new ArrayList<>();
    public long[] d;
    public int[] pi;

    public BellmanFord(String... nomes) {
        this.nomes = nomes;
    }

    public void aresta(String u, String v, int w) {
        arestas.add(new Aresta(indice(u), indice(v), w));
    }

    public int indice(String nome) {
        return Arrays.asList(nomes).indexOf(nome);
    }

    /** Devolve false se há ciclo negativo alcançável a partir de s. Com verbose, imprime cada passada. */
    public boolean executar(int s, boolean verbose) {
        int n = nomes.length;
        d = new long[n];
        pi = new int[n];
        Arrays.fill(d, INF); // inicializa(G, s)
        Arrays.fill(pi, -1);
        d[s] = 0;
        if (verbose) {
            System.out.println("Inicializa: " + estado());
        }
        for (int i = 1; i <= n - 1; i++) {
            for (Aresta e : arestas) {
                if (relaxa(e) && verbose) {
                    System.out.println("  relaxa(" + nomes[e.u] + "," + nomes[e.v] + ") w=" + e.w + " -> d["
                            + nomes[e.v] + "] = " + d[e.v]);
                }
            }
            if (verbose) {
                System.out.println("Passada " + i + ": " + estado());
            }
        }
        for (Aresta e : arestas) { // detecção de ciclo negativo
            if (d[e.u] != INF && d[e.v] > d[e.u] + e.w) {
                return false;
            }
        }
        return true;
    }

    // relaxa(u, v, w)
    private boolean relaxa(Aresta e) {
        if (d[e.u] != INF && d[e.v] > d[e.u] + e.w) {
            d[e.v] = d[e.u] + e.w;
            pi[e.v] = e.u;
            return true;
        }
        return false;
    }

    public String caminho(int v) {
        if (d[v] == INF) {
            return "inalcancavel";
        }
        List<String> p = new ArrayList<>();
        for (int x = v; x != -1; x = pi[x]) {
            p.add(0, nomes[x]);
        }
        return String.join(" -> ", p);
    }

    public String estado() {
        StringBuilder sb = new StringBuilder("d = {");
        for (int i = 0; i < nomes.length; i++) {
            sb.append(i == 0 ? "" : ", ").append(nomes[i]).append('=').append(d[i] == INF ? "inf" : d[i]);
        }
        return sb.append('}').toString();
    }
}
