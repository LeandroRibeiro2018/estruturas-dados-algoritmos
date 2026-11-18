import java.util.ArrayList;
import java.util.List;

/**
 * Busca em profundidade (DFS) com timestamps: d[v] é o momento em que v foi
 * descoberto (fica CINZA) e f[v] o momento em que seus vizinhos terminaram
 * de ser explorados (fica PRETO). A pilha é a própria recursão.
 *
 * Com lista de adjacência a complexidade é O(|V| + |E|).
 */
public class BuscaProfundidade {
    enum Cor { BRANCO, CINZA, PRETO }

    private final Grafo g;
    private final Cor[] cor;
    final int[] d;
    final int[] f;
    final int[] pi; // -1 representa NULL
    private int tempo;
    private final boolean rastrear;

    public BuscaProfundidade(Grafo g, boolean rastrear) {
        this.g = g;
        this.rastrear = rastrear;
        int n = g.n();
        cor = new Cor[n];
        d = new int[n];
        f = new int[n];
        pi = new int[n];
    }

    // DFS (G)
    public void executar() {
        for (int u = 0; u < g.n(); u++) {
            cor[u] = Cor.BRANCO; // u ainda não descoberto
            pi[u] = -1; // NULL é pai de u
        }
        tempo = 0; // inicialização do timestamp
        for (int u = 0; u < g.n(); u++) {
            if (cor[u] == Cor.BRANCO) { // u ainda não descoberto
                if (rastrear) {
                    System.out.println("Nova arvore com raiz (vertice origem) " + g.nome(u));
                }
                visitaDFS(u, 1); // visita u em profundidade
            }
        }
    }

    // visitaDFS (u)
    private void visitaDFS(int u, int profundidade) {
        cor[u] = Cor.CINZA; // u foi descoberto
        tempo = tempo + 1;
        d[u] = tempo; // momento da descoberta de u
        log(profundidade, "descobre " + g.nome(u) + " (d=" + d[u] + ", cinza)");
        for (Grafo.Aresta a : g.adj(u)) {
            int v = a.destino();
            if (cor[v] == Cor.BRANCO) { // v ainda não descoberto
                pi[v] = u; // u é pai de v
                visitaDFS(v, profundidade + 1);
            }
        }
        cor[u] = Cor.PRETO; // visitou todos os vizinhos de u
        tempo = tempo + 1;
        f[u] = tempo; // momento de finalização de u
        log(profundidade, "finaliza " + g.nome(u) + " (f=" + f[u] + ", preto)");
    }

    /** Árvores da floresta de busca em profundidade (raízes têm pi = NULL). */
    public List<List<String>> floresta() {
        List<List<String>> arvores = new ArrayList<>();
        for (int raiz = 0; raiz < g.n(); raiz++) {
            if (pi[raiz] == -1) {
                List<String> membros = new ArrayList<>();
                for (int v = 0; v < g.n(); v++) {
                    if (raizDe(v) == raiz) {
                        membros.add(g.nome(v));
                    }
                }
                arvores.add(membros);
            }
        }
        return arvores;
    }

    private int raizDe(int v) {
        while (pi[v] != -1) {
            v = pi[v];
        }
        return v;
    }

    private void log(int profundidade, String msg) {
        if (rastrear) {
            System.out.println("  ".repeat(profundidade) + msg);
        }
    }
}
