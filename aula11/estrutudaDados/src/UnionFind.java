/**
 * Estrutura para conjuntos disjuntos (Union-Find / Disjoint-Set).
 * Cada conjunto é uma árvore cujo representante é a raiz.
 *
 * Make-Set(x): cria {x}; Find-Set(x): devolve o representante do conjunto
 * de x (com compressão de caminho); Union(x, y): une os conjuntos de x e y
 * (pendurando a árvore de menor rank na de maior rank).
 * Com as duas otimizações, cada operação custa quase O(1) amortizado.
 */
public class UnionFind {
    private final int[] pai;
    private final int[] rank;

    public UnionFind(int n) {
        pai = new int[n];
        rank = new int[n];
        for (int x = 0; x < n; x++) { // Make-Set(x) para cada elemento
            pai[x] = x;
        }
    }

    public void makeSet(int x) {
        pai[x] = x;
        rank[x] = 0;
    }

    public int findSet(int x) {
        if (pai[x] != x) {
            pai[x] = findSet(pai[x]); // compressão de caminho
        }
        return pai[x];
    }

    public void union(int x, int y) {
        int rx = findSet(x);
        int ry = findSet(y);
        if (rx == ry) {
            return;
        }
        if (rank[rx] < rank[ry]) {
            pai[rx] = ry;
        } else if (rank[rx] > rank[ry]) {
            pai[ry] = rx;
        } else {
            pai[ry] = rx;
            rank[rx]++;
        }
    }
}
