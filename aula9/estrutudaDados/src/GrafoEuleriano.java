import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Grafos eulerianos. Teorema de Euler (1736): um grafo conexo não orientado
 * tem ciclo euleriano se e somente se todo vértice tem grau par. Se
 * exatamente 2 vértices têm grau ímpar, existe um caminho euleriano entre
 * eles (mas não um ciclo).
 *
 * O ciclo/caminho é construído com o algoritmo de Hierholzer, O(|V| + |E|).
 */
public class GrafoEuleriano {

    /** Vértices de grau ímpar. */
    public static List<Integer> verticesImpares(Grafo g) {
        List<Integer> impares = new ArrayList<>();
        for (int v = 0; v < g.n(); v++) {
            if (g.grau(v) % 2 != 0) {
                impares.add(v);
            }
        }
        return impares;
    }

    /**
     * Algoritmo de Hierholzer para multigrafo não orientado. Retorna a
     * sequência de vértices do ciclo (ou caminho) euleriano, ou uma lista
     * vazia se ele não existir.
     */
    public static List<String> hierholzer(Grafo g) {
        List<Integer> impares = verticesImpares(g);
        if (!BuscaLargura.fracamenteConexo(g) || (impares.size() != 0 && impares.size() != 2)) {
            return List.of();
        }
        // Cada aresta (u, v) aparece nas duas listas; identificamos as cópias
        // para marcar a aresta como usada uma única vez.
        List<List<int[]>> incidencias = new ArrayList<>(); // {vizinho, idAresta}
        for (int v = 0; v < g.n(); v++) {
            incidencias.add(new ArrayList<>());
        }
        int id = 0;
        for (int u = 0; u < g.n(); u++) {
            for (Grafo.Aresta a : g.adj(u)) {
                int v = a.destino();
                if (u < v) { // cada cópia (u, v) com u < v é uma aresta distinta
                    incidencias.get(u).add(new int[]{v, id});
                    incidencias.get(v).add(new int[]{u, id});
                    id++;
                }
            }
        }
        boolean[] usada = new boolean[id];
        int[] proximo = new int[g.n()];

        int inicio = impares.isEmpty() ? 0 : impares.get(0);
        Deque<Integer> pilha = new ArrayDeque<>();
        List<String> circuito = new ArrayList<>();
        pilha.push(inicio);
        while (!pilha.isEmpty()) {
            int u = pilha.peek();
            List<int[]> lista = incidencias.get(u);
            while (proximo[u] < lista.size() && usada[lista.get(proximo[u])[1]]) {
                proximo[u]++;
            }
            if (proximo[u] == lista.size()) { // sem arestas livres: entra no circuito
                circuito.add(g.nome(pilha.pop()));
            } else { // segue por uma aresta ainda não usada
                int[] aresta = lista.get(proximo[u]);
                usada[aresta[1]] = true;
                pilha.push(aresta[0]);
            }
        }
        Collections.reverse(circuito);
        return circuito;
    }
}
