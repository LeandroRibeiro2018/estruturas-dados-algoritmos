import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length > 0) {
            executarOpcaoUnica(args[0]);
            return;
        }

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha um exercicio: ");
            try {
                executar(opcao);
            } catch (IllegalArgumentException e) {
                System.out.println("Entrada invalida: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void executarOpcaoUnica(String argumento) {
        try {
            int opcao = Integer.parseInt(argumento.trim());
            executar(opcao);
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida: '" + argumento + "'. Informe um numero de 0 a 8.");
        } catch (IllegalArgumentException e) {
            System.out.println("Entrada invalida: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Aula 09 — Algoritmos em Grafos ===");
        System.out.println("1 - G = (V, E), adjacência e grau");
        System.out.println("2 - Matriz de adjacência");
        System.out.println("3 - Lista de adjacência");
        System.out.println("4 - Matriz x lista: grafo denso x esparso");
        System.out.println("5 - Busca em profundidade (DFS) com timestamps");
        System.out.println("6 - Busca em largura (BFS) e caminho mínimo");
        System.out.println("7 - Grafos eulerianos (pontes de Königsberg)");
        System.out.println("8 - Grafos hamiltonianos (dodecaedro x Petersen)");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao) {
        if (opcao != 0) {
            exibirDescricao(opcao);
        }
        switch (opcao) {
            case 1 -> exercicio1();
            case 2 -> exercicio2();
            case 3 -> exercicio3();
            case 4 -> exercicio4();
            case 5 -> exercicio5();
            case 6 -> exercicio6();
            case 7 -> exercicio7();
            case 8 -> exercicio8();
            case 0 -> System.out.println("Programa encerrado.");
            default -> System.out.println("Opcao inexistente.");
        }
    }

    private static void exibirDescricao(int opcao) {
        switch (opcao) {
            case 1 -> System.out.println("""

                    ---------------------------------------------------------
                    1) Grafo, adjacencia e grau
                    ---------------------------------------------------------
                    O que e: o grafo dos slides com V = {v1..v5} e 6 arestas,
                    nas versoes nao orientada e orientada.
                    O que observar: no nao orientado o grau e o numero de
                    arestas incidentes (soma dos graus = 2|E|); no orientado
                    grau = grau de saida + grau de entrada, e a adjacencia
                    deixa de ser simetrica.
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Matriz de adjacencia
                    ---------------------------------------------------------
                    O que e: A[i][j] = 1 (ou o peso) se ha aresta de i para
                    j e 0 caso contrario, para os tres grafos dos slides.
                    O que observar: a matriz e simetrica nos grafos nao
                    orientados (ponderado ou nao) e nao simetrica no
                    orientado.
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Lista de adjacencia
                    ---------------------------------------------------------
                    O que e: um vetor de n listas ligadas; a lista de u
                    contem todos os vizinhos de u (e os pesos, quando o
                    grafo e ponderado).
                    O que observar: no grafo orientado cada aresta aparece
                    em uma unica lista.
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Quando usar matriz e quando usar lista?
                    ---------------------------------------------------------
                    O que e: espaco ocupado (n^2 celulas x n + 2|E| nos) e
                    custo das operacoes tipicas em cada representacao.
                    Licao: grafo denso -> matriz de adjacencia; grafo
                    esparso -> lista de adjacencia (DFS e BFS ficam
                    O(|V| + |E|) em vez de O(|V|^2)).
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Busca em profundidade (DFS)
                    ---------------------------------------------------------
                    O que e: o exemplo dos slides (s, t, u, r, x, v, w, y)
                    com os rotulos d (descoberta, fica cinza) e f
                    (finalizacao, fica preto).
                    O que observar: o resultado e uma floresta com 2
                    arvores (6 e 2 vertices), pois o grafo orientado e so
                    fracamente conexo. Complexidade O(|V| + |E|).
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Busca em largura (BFS)
                    ---------------------------------------------------------
                    O que e: o exemplo dos slides a partir de s, com o
                    estado da fila Q a cada vertice retirado.
                    O que observar: so entram na fila vertices brancos (que
                    viram cinza na hora); d[v] e o menor numero de arestas
                    de s ate v e a arvore BFS da o caminho mais curto.
                    Complexidade O(|V| + |E|).
                    ---------------------------------------------------------""");
            case 7 -> System.out.println("""

                    ---------------------------------------------------------
                    7) Grafos eulerianos
                    ---------------------------------------------------------
                    O que e: as sete pontes de Konigsberg (A = ilha, B, C,
                    D) e duas variacoes com pontes extras.
                    Teorema de Euler: um grafo conexo nao orientado tem
                    ciclo euleriano sse todo vertice tem grau par. Com
                    exatamente 2 vertices impares ha apenas caminho
                    euleriano. O percurso e montado por Hierholzer.
                    ---------------------------------------------------------""");
            case 8 -> System.out.println("""

                    ---------------------------------------------------------
                    8) Grafos hamiltonianos
                    ---------------------------------------------------------
                    O que e: busca por backtracking de um ciclo que passa
                    por todos os vertices uma unica vez, no dodecaedro do
                    jogo "Around the World" de Hamilton e no grafo de
                    Petersen.
                    O que observar: nao ha teorema simples como o de Euler;
                    o problema e NP-completo (Caixeiro Viajante).
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void exercicio1() {
        Grafo g = grafoNaoOrientado();
        System.out.println("Nao orientado: V = " + g.vertices());
        System.out.println("               E = " + g.arestas());
        int soma = 0;
        for (int v = 0; v < g.n(); v++) {
            soma += g.grau(v);
            System.out.println("  grau(" + g.nome(v) + ") = " + g.grau(v));
        }
        System.out.println("  soma dos graus = " + soma + " = 2|E| = " + 2 * g.numArestas());

        Grafo o = grafoOrientadoPonderado();
        System.out.println("\nOrientado: E = " + o.arestas());
        for (int v = 0; v < o.n(); v++) {
            System.out.printf("  %s: grau de saida=%d, grau de entrada=%d, grau=%d%n",
                    o.nome(v), o.grau(v), o.grauEntrada(v), o.grau(v) + o.grauEntrada(v));
        }
        System.out.println("  (v1,v2) e aresta, mas (v2,v1) nao: a adjacencia nao e simetrica.");
    }

    private static void exercicio2() {
        System.out.println("Grafo nao orientado:");
        System.out.print(grafoNaoOrientado().matrizFormatada());
        System.out.println("Simetrica: " + simetrica(grafoNaoOrientado().matriz()));
        System.out.println("\nGrafo nao orientado ponderado:");
        System.out.print(grafoNaoOrientadoPonderado().matrizFormatada());
        System.out.println("Simetrica: " + simetrica(grafoNaoOrientadoPonderado().matriz()));
        System.out.println("\nGrafo orientado ponderado:");
        System.out.print(grafoOrientadoPonderado().matrizFormatada());
        System.out.println("Simetrica: " + simetrica(grafoOrientadoPonderado().matriz()));
    }

    private static void exercicio3() {
        System.out.println("Grafo nao orientado:");
        System.out.print(grafoNaoOrientado().listaFormatada(false));
        System.out.println("\nGrafo nao orientado ponderado:");
        System.out.print(grafoNaoOrientadoPonderado().listaFormatada(true));
        System.out.println("\nGrafo orientado ponderado:");
        System.out.print(grafoOrientadoPonderado().listaFormatada(true));
    }

    private static void exercicio4() {
        System.out.println("Espaco (grafo nao orientado): matriz = n^2 celulas | lista = n + 2|E| nos,");
        System.out.println("cada no da lista ocupa 2 celulas (vizinho + ponteiro prox) -> n + 4|E| celulas");
        long[][] casos = {
                {5, 6}, // grafo dos slides
                {10_000, 30_000}, // esparso: grau medio 6
                {10_000, 40_000_000} // denso: 80% de todos os pares possiveis
        };
        String[] nomes = {"Slides (n=5)", "Esparso", "Denso"};
        for (int i = 0; i < casos.length; i++) {
            long n = casos[i][0];
            long e = casos[i][1];
            double densidade = e / (n * (n - 1) / 2.0);
            long matriz = n * n;
            long lista = n + 4 * e;
            System.out.printf("%-13s n=%6d |E|=%10d densidade=%5.1f%% | matriz=%,13d | lista=%,12d -> %s%n",
                    nomes[i], n, e, densidade * 100, matriz, lista,
                    lista < matriz ? "lista" : "matriz");
        }
        System.out.println("\nOperacao                      | Matriz    | Lista");
        System.out.println("Existe aresta (u,v)?          | O(1)      | O(grau(u))");
        System.out.println("Percorrer vizinhos de u       | O(|V|)    | O(grau(u))");
        System.out.println("Percorrer o grafo (DFS/BFS)   | O(|V|^2)  | O(|V| + |E|)");
        System.out.println("Espaco                        | O(|V|^2)  | O(|V| + |E|)");
    }

    private static void exercicio5() {
        Grafo g = grafoDFS();
        BuscaProfundidade dfs = new BuscaProfundidade(g, true);
        dfs.executar();
        System.out.println("\nVertice | d  | f  | pai");
        for (int v = 0; v < g.n(); v++) {
            System.out.printf("   %s    | %2d | %2d | %s%n", g.nome(v), dfs.d[v], dfs.f[v],
                    dfs.pi[v] == -1 ? "NULL" : g.nome(dfs.pi[v]));
        }
        List<List<String>> floresta = dfs.floresta();
        System.out.println("\nFloresta com " + floresta.size() + " arvores:");
        for (List<String> arvore : floresta) {
            System.out.println("  " + arvore + " (" + arvore.size() + " vertices)");
        }
        System.out.println("Fracamente conexo: " + BuscaLargura.fracamenteConexo(g));
        System.out.println("Fortemente conexo: " + BuscaLargura.fortementeConexo(g));
    }

    private static void exercicio6() {
        Grafo g = grafoBFS();
        BuscaLargura bfs = new BuscaLargura(g);
        int s = g.indice("s");
        bfs.executar(s, true);
        System.out.println("\nVertice | d | caminho mais curto a partir de s");
        for (int v = 0; v < g.n(); v++) {
            System.out.printf("   %s    | %d | %s%n", g.nome(v), bfs.d[v], String.join(" -> ", bfs.caminho(v)));
        }
    }

    private static void exercicio7() {
        Grafo konigsberg = konigsberg();
        mostrarEuleriano("Konigsberg (7 pontes)", konigsberg);

        Grafo umaPonte = konigsberg();
        umaPonte.adicionarAresta("B", "C");
        mostrarEuleriano("Konigsberg + ponte B-C", umaPonte);

        Grafo duasPontes = konigsberg();
        duasPontes.adicionarAresta("B", "C");
        duasPontes.adicionarAresta("A", "D");
        mostrarEuleriano("Konigsberg + pontes B-C e A-D", duasPontes);
    }

    private static void exercicio8() {
        Grafo dodecaedro = GrafoHamiltoniano.dodecaedro();
        GrafoHamiltoniano h1 = new GrafoHamiltoniano(dodecaedro);
        List<String> ciclo = h1.cicloHamiltoniano();
        System.out.println("Dodecaedro: " + dodecaedro.n() + " vertices, " + dodecaedro.numArestas() + " arestas");
        System.out.println("  ciclo hamiltoniano: " + String.join(" -> ", ciclo));
        System.out.println("  chamadas do backtracking: " + h1.chamadas());
        System.out.println("  valido: " + cicloValido(dodecaedro, ciclo));

        Grafo petersen = GrafoHamiltoniano.petersen();
        GrafoHamiltoniano h2 = new GrafoHamiltoniano(petersen);
        List<String> ciclo2 = h2.cicloHamiltoniano();
        System.out.println("\nPetersen: " + petersen.n() + " vertices, " + petersen.numArestas()
                + " arestas, conexo=" + BuscaLargura.fracamenteConexo(petersen));
        System.out.println("  ciclo hamiltoniano: " + (ciclo2.isEmpty() ? "nao existe" : String.join(" -> ", ciclo2)));
        System.out.println("  chamadas do backtracking ate esgotar as opcoes: " + h2.chamadas());
        System.out.println("  Euleriano? " + GrafoEuleriano.verticesImpares(petersen).isEmpty()
                + " (todos os 10 vertices tem grau 3, impar)");
    }

    private static void mostrarEuleriano(String titulo, Grafo g) {
        StringBuilder graus = new StringBuilder();
        for (int v = 0; v < g.n(); v++) {
            graus.append(g.nome(v)).append('=').append(g.grau(v)).append(' ');
        }
        List<Integer> impares = GrafoEuleriano.verticesImpares(g);
        List<String> percurso = GrafoEuleriano.hierholzer(g);
        String conclusao;
        if (impares.isEmpty()) {
            conclusao = "EULERIANO - ciclo: " + String.join(" -> ", percurso);
        } else if (impares.size() == 2) {
            conclusao = "nao euleriano, mas tem caminho euleriano: " + String.join(" -> ", percurso);
        } else {
            conclusao = "nao euleriano e sem caminho euleriano (" + impares.size() + " vertices de grau impar)";
        }
        System.out.println(titulo + " | " + g.numArestas() + " arestas | graus: " + graus.toString().trim());
        System.out.println("  " + conclusao + "\n");
    }

    private static boolean cicloValido(Grafo g, List<String> ciclo) {
        if (ciclo.size() != g.n() + 1 || !ciclo.get(0).equals(ciclo.get(ciclo.size() - 1))) {
            return false;
        }
        if (ciclo.subList(0, g.n()).stream().distinct().count() != g.n()) {
            return false;
        }
        for (int i = 0; i + 1 < ciclo.size(); i++) {
            int u = g.indice(ciclo.get(i));
            int v = g.indice(ciclo.get(i + 1));
            if (g.adj(u).stream().noneMatch(a -> a.destino() == v)) {
                return false;
            }
        }
        return true;
    }

    private static boolean simetrica(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i][j] != a[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Grafos dos slides de representação.
    private static Grafo grafoNaoOrientado() {
        Grafo g = new Grafo(false, "v1", "v2", "v3", "v4", "v5");
        g.adicionarAresta("v1", "v2");
        g.adicionarAresta("v1", "v3");
        g.adicionarAresta("v2", "v3");
        g.adicionarAresta("v2", "v4");
        g.adicionarAresta("v3", "v5");
        g.adicionarAresta("v4", "v5");
        return g;
    }

    private static Grafo grafoNaoOrientadoPonderado() {
        Grafo g = new Grafo(false, "v1", "v2", "v3", "v4", "v5");
        g.adicionarAresta("v1", "v2", 2);
        g.adicionarAresta("v1", "v3", 12);
        g.adicionarAresta("v2", "v3", 4);
        g.adicionarAresta("v2", "v4", 3);
        g.adicionarAresta("v3", "v5", 40);
        g.adicionarAresta("v4", "v5", 8);
        return g;
    }

    private static Grafo grafoOrientadoPonderado() {
        Grafo g = new Grafo(true, "v1", "v2", "v3", "v4", "v5");
        g.adicionarAresta("v1", "v2", 2);
        g.adicionarAresta("v2", "v3", 4);
        g.adicionarAresta("v3", "v1", 12);
        g.adicionarAresta("v3", "v5", 40);
        g.adicionarAresta("v4", "v2", 3);
        g.adicionarAresta("v5", "v4", 8);
        return g;
    }

    /** Dígrafo do exemplo de DFS dos slides (a ordem das listas reproduz os timestamps). */
    private static Grafo grafoDFS() {
        Grafo g = new Grafo(true, "s", "r", "t", "u", "v", "w", "x", "y");
        g.adicionarAresta("s", "r");
        g.adicionarAresta("s", "t");
        g.adicionarAresta("s", "w");
        g.adicionarAresta("r", "v");
        g.adicionarAresta("r", "w");
        g.adicionarAresta("t", "x");
        g.adicionarAresta("t", "w");
        g.adicionarAresta("u", "t");
        g.adicionarAresta("u", "x");
        g.adicionarAresta("u", "y");
        g.adicionarAresta("v", "s");
        g.adicionarAresta("w", "v");
        g.adicionarAresta("x", "w");
        g.adicionarAresta("y", "w");
        return g;
    }

    /** Grafo não orientado do exemplo de BFS dos slides. */
    private static Grafo grafoBFS() {
        Grafo g = new Grafo(false, "r", "s", "t", "u", "v", "w", "x", "y");
        g.adicionarAresta("s", "w");
        g.adicionarAresta("s", "r");
        g.adicionarAresta("r", "v");
        g.adicionarAresta("w", "t");
        g.adicionarAresta("w", "x");
        g.adicionarAresta("t", "x");
        g.adicionarAresta("t", "u");
        g.adicionarAresta("x", "y");
        g.adicionarAresta("u", "y");
        return g;
    }

    /** Multigrafo das sete pontes: A = ilha, B e C = margens, D = região leste. */
    private static Grafo konigsberg() {
        Grafo g = new Grafo(false, "A", "B", "C", "D");
        g.adicionarAresta("A", "B");
        g.adicionarAresta("A", "B");
        g.adicionarAresta("A", "C");
        g.adicionarAresta("A", "C");
        g.adicionarAresta("A", "D");
        g.adicionarAresta("B", "D");
        g.adicionarAresta("C", "D");
        return g;
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        String valor = scanner.nextLine().trim();
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor numerico invalido: '" + valor + "'.");
        }
    }
}
