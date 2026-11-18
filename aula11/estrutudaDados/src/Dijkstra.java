import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Algoritmo de Dijkstra: caminhos mínimos a partir de s em grafos com pesos
 * NÃO negativos. É guloso: a cada passo extrai o vértice de menor estimativa
 * d[u] (Extrai-Min) e relaxa as arestas que saem dele.
 *
 * Duas implementações da fila de prioridade Q, como nos slides:
 * - vetor: Extrai-Min percorre todos os vértices -> O(|V|^2);
 * - heap binário: Extrai-Min e atualizações em O(log |V|) -> O((|V| + |E|) log |V|).
 */
public class Dijkstra {
    public static final long INFINITO = Long.MAX_VALUE / 4;

    final long[] d;
    final int[] pi; // -1 representa NULL
    final List<Integer> ordemExtracao = new ArrayList<>();
    private final GrafoPonderado g;

    public Dijkstra(GrafoPonderado g) {
        this.g = g;
        d = new long[g.n()];
        pi = new int[g.n()];
    }

    // inicializa (G, s)
    private void inicializa(int s) {
        Arrays.fill(d, INFINITO); // d[v] <- infinito
        Arrays.fill(pi, -1); // pi[v] <- NULL
        d[s] = 0;
        ordemExtracao.clear();
    }

    // relaxa (u, v, w)
    private boolean relaxa(int u, int v, int w) {
        if (d[v] > d[u] + w) {
            d[v] = d[u] + w;
            pi[v] = u;
            return true;
        }
        return false;
    }

    /** Dijkstra (G, s) com Q em vetor (versão O(|V|^2)); rastrear imprime cada passo. */
    public void comVetor(int s, boolean rastrear) {
        inicializa(s);
        boolean[] emS = new boolean[g.n()]; // S <- vazio; Q = vértices fora de S
        for (int passo = 0; passo < g.n(); passo++) {
            int u = -1;
            for (int v = 0; v < g.n(); v++) { // Extrai-Min(Q): varre o vetor
                if (!emS[v] && (u == -1 || d[v] < d[u])) {
                    u = v;
                }
            }
            if (d[u] == INFINITO) {
                break; // o restante não é alcançável a partir de s
            }
            emS[u] = true; // S <- S U {u}
            ordemExtracao.add(u);
            List<String> relaxadas = new ArrayList<>();
            for (GrafoPonderado.Adj a : g.adj(u)) {
                long antes = d[a.destino()];
                if (relaxa(u, a.destino(), a.peso())) {
                    relaxadas.add(g.nome(a.destino()) + ": " + (antes == INFINITO ? "inf" : antes)
                            + " -> " + d[a.destino()]);
                }
            }
            if (rastrear) {
                System.out.printf("Extrai-Min = %s (d=%d) | relaxa: %s%n   %s%n", g.nome(u), d[u],
                        relaxadas.isEmpty() ? "nenhuma melhora" : String.join(", ", relaxadas), estado(emS));
            }
        }
    }

    /** Dijkstra (G, s) com Q em heap binário (PriorityQueue, com entradas obsoletas ignoradas). */
    public void comHeap(int s) {
        inicializa(s);
        boolean[] emS = new boolean[g.n()];
        PriorityQueue<long[]> q = new PriorityQueue<>((x, y) -> Long.compare(x[0], y[0]));
        q.add(new long[]{0, s});
        while (!q.isEmpty()) {
            long[] topo = q.poll(); // Extrai-Min(Q): O(log |V|)
            int u = (int) topo[1];
            if (emS[u]) {
                continue; // entrada antiga: u já foi finalizado com distância menor
            }
            emS[u] = true;
            ordemExtracao.add(u);
            for (GrafoPonderado.Adj a : g.adj(u)) {
                if (relaxa(u, a.destino(), a.peso())) {
                    q.add(new long[]{d[a.destino()], a.destino()}); // "diminui a chave"
                }
            }
        }
    }

    public List<String> caminho(int v) {
        if (d[v] == INFINITO) {
            return List.of();
        }
        List<String> c = new ArrayList<>();
        for (int x = v; x != -1; x = pi[x]) {
            c.add(g.nome(x));
        }
        Collections.reverse(c);
        return c;
    }

    /** Bellman-Ford: caminhos mínimos corretos também com pesos negativos (sem ciclo negativo). */
    public static long[] bellmanFord(GrafoPonderado g, int s) {
        long[] dist = new long[g.n()];
        Arrays.fill(dist, INFINITO);
        dist[s] = 0;
        for (int i = 1; i < g.n(); i++) {
            for (GrafoPonderado.Aresta e : g.arestas()) {
                if (dist[e.u()] != INFINITO && dist[e.u()] + e.peso() < dist[e.v()]) {
                    dist[e.v()] = dist[e.u()] + e.peso();
                }
            }
        }
        return dist;
    }

    private String estado(boolean[] emS) {
        StringBuilder sb = new StringBuilder("d = {");
        for (int v = 0; v < g.n(); v++) {
            sb.append(v == 0 ? "" : ", ").append(g.nome(v)).append('=')
                    .append(d[v] == INFINITO ? "inf" : String.valueOf(d[v])).append(emS[v] ? "*" : "");
        }
        return sb.append("}  (* = em S, finalizado)").toString();
    }
}
