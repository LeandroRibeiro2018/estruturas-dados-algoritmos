import java.util.ArrayList;
import java.util.List;

/**
 * Grafos hamiltonianos: um ciclo hamiltoniano passa por cada VÉRTICE
 * exatamente uma vez e volta ao início. Decidir se ele existe é um problema
 * NP-completo — não se conhece algoritmo polinomial —, então aqui usamos
 * backtracking (tentativa e erro), que é exponencial no pior caso.
 */
public class GrafoHamiltoniano {
    private final Grafo g;
    private final int[] caminho;
    private final boolean[] noCaminho;
    private long chamadas;

    public GrafoHamiltoniano(Grafo g) {
        this.g = g;
        this.caminho = new int[g.n()];
        this.noCaminho = new boolean[g.n()];
    }

    /** Retorna o ciclo hamiltoniano (fechando no vértice 0) ou lista vazia. */
    public List<String> cicloHamiltoniano() {
        chamadas = 0;
        caminho[0] = 0;
        noCaminho[0] = true;
        if (!estender(1)) {
            return List.of();
        }
        List<String> ciclo = new ArrayList<>();
        for (int v : caminho) {
            ciclo.add(g.nome(v));
        }
        ciclo.add(g.nome(0));
        return ciclo;
    }

    private boolean estender(int posicao) {
        chamadas++;
        int ultimo = caminho[posicao - 1];
        if (posicao == g.n()) { // todos os vértices usados: precisa voltar à origem
            return temAresta(ultimo, caminho[0]);
        }
        for (Grafo.Aresta a : g.adj(ultimo)) {
            int v = a.destino();
            if (!noCaminho[v]) {
                caminho[posicao] = v;
                noCaminho[v] = true;
                if (estender(posicao + 1)) {
                    return true;
                }
                noCaminho[v] = false; // desfaz a escolha (backtracking)
            }
        }
        return false;
    }

    private boolean temAresta(int u, int v) {
        for (Grafo.Aresta a : g.adj(u)) {
            if (a.destino() == v) {
                return true;
            }
        }
        return false;
    }

    public long chamadas() {
        return chamadas;
    }

    /** Dodecaedro: 20 vértices, 30 arestas, todo vértice com grau 3 (jogo de Hamilton). */
    public static Grafo dodecaedro() {
        Grafo g = Grafo.numerado(false, 20);
        int[][] arestas = {
                {0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0}, // pentágono externo
                {0, 5}, {1, 7}, {2, 9}, {3, 11}, {4, 13}, // ligações externo -> anel
                {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, // anel de 10 vértices
                {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 5},
                {6, 15}, {8, 16}, {10, 17}, {12, 18}, {14, 19}, // ligações anel -> interno
                {15, 16}, {16, 17}, {17, 18}, {18, 19}, {19, 15} // pentágono interno
        };
        for (int[] a : arestas) {
            g.adicionarAresta(String.valueOf(a[0]), String.valueOf(a[1]));
        }
        return g;
    }

    /** Grafo de Petersen: 10 vértices, grau 3, conexo e NÃO hamiltoniano. */
    public static Grafo petersen() {
        Grafo g = Grafo.numerado(false, 10);
        int[][] arestas = {
                {0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0}, // pentágono externo
                {0, 5}, {1, 6}, {2, 7}, {3, 8}, {4, 9}, // raios
                {5, 7}, {7, 9}, {9, 6}, {6, 8}, {8, 5} // pentagrama interno
        };
        for (int[] a : arestas) {
            g.adicionarAresta(String.valueOf(a[0]), String.valueOf(a[1]));
        }
        return g;
    }
}
