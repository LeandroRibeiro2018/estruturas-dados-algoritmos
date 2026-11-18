import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Busca em largura (BFS) a partir de um vértice s: uma fila guarda os
 * vértices cinza, e cada vértice é descoberto pelo caminho com o menor
 * número de arestas. d[v] é essa distância e pi[v] o pai na árvore BFS.
 *
 * Com lista de adjacência a complexidade é O(|V| + |E|).
 */
public class BuscaLargura {
    static final int INFINITO = Integer.MAX_VALUE;

    enum Cor { BRANCO, CINZA, PRETO }

    private final Grafo g;
    final int[] d;
    final int[] pi; // -1 representa NULL

    public BuscaLargura(Grafo g) {
        this.g = g;
        d = new int[g.n()];
        pi = new int[g.n()];
    }

    // BFS (G, s)
    public void executar(int s, boolean rastrear) {
        Cor[] cor = new Cor[g.n()];
        cor[s] = Cor.CINZA; // s foi descoberto
        d[s] = 0; // distância de s até s
        pi[s] = -1; // NULL é pai de s
        for (int u = 0; u < g.n(); u++) {
            if (u != s) {
                cor[u] = Cor.BRANCO; // u ainda não descoberto
                d[u] = INFINITO; // distância de s até u
                pi[u] = -1; // NULL é pai de u
            }
        }
        Deque<Integer> q = new ArrayDeque<>(); // fila vazia
        q.addLast(s);
        if (rastrear) {
            System.out.println("Q: " + nomes(q) + " (origem " + g.nome(s) + " cinza, d=0)");
        }
        while (!q.isEmpty()) {
            int u = q.removeFirst(); // remove primeiro da fila
            List<String> descobertos = new ArrayList<>();
            for (Grafo.Aresta a : g.adj(u)) {
                int v = a.destino();
                if (cor[v] == Cor.BRANCO) { // v ainda não descoberto
                    cor[v] = Cor.CINZA; // v foi descoberto
                    d[v] = d[u] + 1; // distância de s até v
                    pi[v] = u; // u é pai de v
                    q.addLast(v); // insere v no final da fila
                    descobertos.add(g.nome(v) + "(d=" + d[v] + ")");
                }
            }
            cor[u] = Cor.PRETO; // visitou todos os vizinhos de u
            if (rastrear) {
                System.out.println("dequeue " + g.nome(u) + " -> preto | descobre "
                        + (descobertos.isEmpty() ? "nenhum" : String.join(", ", descobertos))
                        + " | Q: " + nomes(q));
            }
        }
    }

    /** Caminho mínimo (em número de arestas) de s até v, seguindo pi. */
    public List<String> caminho(int v) {
        if (d[v] == INFINITO) {
            return List.of();
        }
        List<String> caminho = new ArrayList<>();
        for (int x = v; x != -1; x = pi[x]) {
            caminho.add(g.nome(x));
        }
        Collections.reverse(caminho);
        return caminho;
    }

    /** Fracamente conexo: trocando as arestas por não orientadas, o grafo fica conexo. */
    public static boolean fracamenteConexo(Grafo g) {
        Grafo naoOrientado = new Grafo(false, nomesDe(g));
        for (int u = 0; u < g.n(); u++) {
            for (Grafo.Aresta a : g.adj(u)) {
                if (g.orientado() || u <= a.destino()) {
                    naoOrientado.adicionarAresta(g.nome(u), g.nome(a.destino()));
                }
            }
        }
        return alcancaTodos(naoOrientado, 0);
    }

    /** Fortemente conexo: existe caminho de qualquer vértice para qualquer outro. */
    public static boolean fortementeConexo(Grafo g) {
        for (int s = 0; s < g.n(); s++) {
            if (!alcancaTodos(g, s)) {
                return false;
            }
        }
        return true;
    }

    private static boolean alcancaTodos(Grafo g, int s) {
        BuscaLargura bfs = new BuscaLargura(g);
        bfs.executar(s, false);
        for (int dist : bfs.d) {
            if (dist == INFINITO) {
                return false;
            }
        }
        return true;
    }

    private static String[] nomesDe(Grafo g) {
        String[] nomes = new String[g.n()];
        for (int i = 0; i < g.n(); i++) {
            nomes[i] = g.nome(i);
        }
        return nomes;
    }

    private List<String> nomes(Deque<Integer> q) {
        List<String> lista = new ArrayList<>();
        for (int v : q) {
            lista.add(g.nome(v));
        }
        return lista;
    }
}
