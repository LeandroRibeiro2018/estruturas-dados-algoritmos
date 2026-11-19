import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Ritmo.instalar();
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
                if (opcao != 0) {
                    pausar("\n[ENTER] para voltar ao menu...");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Entrada invalida: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void executarOpcaoUnica(String argumento) {
        try {
            executar(Integer.parseInt(argumento.trim()));
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida: '" + argumento + "'. Informe um numero de 0 a 6.");
        } catch (IllegalArgumentException e) {
            System.out.println("Entrada invalida: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Aula 12 — Programação Dinâmica ===");
        System.out.println("1 - Fibonacci: recursivo x memoization x tabulation");
        System.out.println("2 - Mochila 0-1: recursiva x memoization x tabulation");
        System.out.println("3 - Mochila 0-1: tabela passo a passo (n=4, W=5)");
        System.out.println("4 - Mochila 0-1: recuperando os objetos da mochila");
        System.out.println("5 - Bellman-Ford passo a passo (exemplo dos slides)");
        System.out.println("6 - Bellman-Ford: detectando ciclo negativo");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao) {
        if (opcao != 0) {
            exibirDescricao(opcao);
            pausar("\n[ENTER] para executar o exercicio...");
        }
        switch (opcao) {
            case 1 -> exercicio1();
            case 2 -> exercicio2();
            case 3 -> exercicio3();
            case 4 -> exercicio4();
            case 5 -> exercicio5();
            case 6 -> exercicio6();
            case 0 -> System.out.println("Programa encerrado.");
            default -> System.out.println("Opcao inexistente.");
        }
    }

    private static void exibirDescricao(int opcao) {
        switch (opcao) {
            case 1 -> System.out.println("""

                    ---------------------------------------------------------
                    1) Fibonacci e sobreposicao de subproblemas
                    ---------------------------------------------------------
                    O que e: F(n) = F(n-1) + F(n-2) calculado de tres formas:
                    recursivo puro O(2^n), top-down com tabela mem[] (memoization)
                    e bottom-up iterativo com tab[] (tabulation), ambos O(n).
                    O que observar: o numero de chamadas explode no recursivo
                    (fib(2) e recalculado varias vezes) e e linear nos outros
                    dois. Os resultados sao iguais.
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Mochila binaria: o ganho da tabela
                    ---------------------------------------------------------
                    O que e: mochila-Recursiva (Omega(2^n)), mochila-Memoization
                    (top-down) e mochila-Tabulation (bottom-up), O(n.W), com
                    objetos aleatorios.
                    O que observar: mesmo valor otimo nas tres; o numero de
                    chamadas da recursiva cresce exponencialmente com n.
                    n.W e pseudopolinomial: polinomial no valor de W, mas
                    exponencial no tamanho (numero de bits) de W.
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Tabela M[0..n][0..W] passo a passo
                    ---------------------------------------------------------
                    O que e: exemplo dos slides: w = {3,2,4,1}, v = {8,3,9,6},
                    W = 5. Para cada objeto i e capacidade j: se w[i] > j, copia
                    M[i-1][j]; senao M[i][j] = max{M[i-1][j], M[i-1][j-w[i]]+v[i]}.
                    O que observar: a solucao otima M[4][5] = 15.
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Obtencao da mochila
                    ---------------------------------------------------------
                    O que e: a tabela so guarda valores. Percorrendo de i = n
                    ate 1: se M[i][j] = M[i-1][j] o objeto i nao entrou; senao
                    entrou e j diminui de w[i]. Custo O(n).
                    O que observar: objetos 3 e 4 (peso 4+1 = 5, valor 9+6 = 15).
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Algoritmo de Bellman-Ford
                    ---------------------------------------------------------
                    O que e: o grafo dos slides (s, t, y, x, z) com pesos
                    negativos, a partir de s. |V|-1 passadas relaxando todas
                    as arestas; depois uma varredura extra detecta ciclo negativo.
                    O que observar: distancias finais s=0, t=2, y=7, x=4, z=-2.
                    Complexidade O(|V|.|E|). A tabela de subproblemas e implicita:
                    apos a passada k, d[v] e o melhor caminho com ate k arestas.
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Ciclo negativo
                    ---------------------------------------------------------
                    O que e: o mesmo grafo com o peso da aresta (y,x) trocado
                    por -9, o que cria o ciclo x -> t -> y -> x de custo -3.
                    O que observar: a varredura final encontra uma aresta que
                    ainda pode ser relaxada e o algoritmo devolve FALSO.
                    ---------------------------------------------------------""");
            default -> { }
        }
    }

    private static void exercicio1() {
        Fibonacci f = new Fibonacci();
        System.out.println("  n | F(n)     | recursivo: chamadas | memoization: chamadas | tabulation: atualizacoes");
        for (int n : new int[]{5, 10, 20, 30, 35}) {
            f.zerar();
            long r = f.recursivo(n);
            long cr = f.chamadas();
            f.zerar();
            long m = f.memoization(n);
            long cm = f.chamadas();
            f.zerar();
            long t = f.tabulation(n);
            long ct = f.chamadas();
            System.out.printf("%3d | %8d | %19d | %21d | %d  (iguais: %b)%n", n, r, cr, cm, ct, r == m && m == t);
        }
        long t0 = System.nanoTime();
        new Fibonacci().recursivo(40);
        double ms = (System.nanoTime() - t0) / 1e6;
        long t1 = System.nanoTime();
        new Fibonacci().tabulation(40);
        double ms2 = (System.nanoTime() - t1) / 1e6;
        System.out.printf("%nfib(40): recursivo %.1f ms | tabulation %.4f ms%n", ms, ms2);
    }

    private static int[][] gerar(int n, long semente) {
        Random r = new Random(semente);
        int[] w = new int[n + 1];
        int[] v = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            w[i] = 1 + r.nextInt(20);
            v[i] = 1 + r.nextInt(100);
        }
        return new int[][]{w, v};
    }

    private static void exercicio2() {
        int cap = 100;
        System.out.println("W = " + cap + ", objetos aleatorios (peso 1..20, valor 1..100)");
        System.out.println(" n | otimo | recursiva: chamadas | memoization: chamadas | tabulation: celulas");
        for (int n : new int[]{10, 15, 20, 24}) {
            int[][] wv = gerar(n, 7);
            Mochila a = new Mochila();
            Mochila b = new Mochila();
            Mochila c = new Mochila();
            int ra = a.recursiva(wv[0], wv[1], n, cap);
            int rb = b.memoization(wv[0], wv[1], n, cap);
            int rc = c.tabulation(wv[0], wv[1], n, cap)[n][cap];
            System.out.printf("%2d | %5d | %19d | %21d | %d  (iguais: %b)%n", n, ra, a.chamadas(), b.chamadas(),
                    c.chamadas(), ra == rb && rb == rc);
        }
    }

    private static void exercicio3() {
        int[] w = Mochila.W_SLIDES;
        int[] v = Mochila.V_SLIDES;
        int n = 4;
        int cap = 5;
        System.out.println("w = [3, 2, 4, 1]   v = [8, 3, 9, 6]   W = 5\n");
        int[][] m = new Mochila().tabulation(w, v, n, cap);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= cap; j++) {
                System.out.println(Mochila.explicar(w, v, m, i, j));
            }
            System.out.println();
        }
        StringBuilder cab = new StringBuilder("i\\j ");
        for (int j = 0; j <= cap; j++) {
            cab.append(String.format("%4d", j));
        }
        System.out.println(cab);
        for (int i = 0; i <= n; i++) {
            StringBuilder l = new StringBuilder(String.format("%3d ", i));
            for (int x : m[i]) {
                l.append(String.format("%4d", x));
            }
            System.out.println(l);
        }
        System.out.println("\nSolucao otima: M[" + n + "][" + cap + "] = " + m[n][cap]);
    }

    private static void exercicio4() {
        int[] w = Mochila.W_SLIDES;
        int[] v = Mochila.V_SLIDES;
        int[][] m = new Mochila().tabulation(w, v, 4, 5);
        int j = 5;
        for (int i = 4; i >= 1; i--) {
            if (m[i][j] == m[i - 1][j]) {
                System.out.println("i=" + i + ", j=" + j + ": M[i][j] = M[i-1][j] = " + m[i][j]
                        + " -> objeto " + i + " NAO esta na mochila");
            } else {
                System.out.println("i=" + i + ", j=" + j + ": M[i][j] = " + m[i][j] + " != M[i-1][j] = " + m[i - 1][j]
                        + " -> objeto " + i + " ESTA na mochila; j = " + j + " - " + w[i] + " = " + (j - w[i]));
                j -= w[i];
            }
        }
        List<Integer> escolhidos = Mochila.obter(w, m, 4, 5);
        int peso = 0;
        int valor = 0;
        for (int i : escolhidos) {
            peso += w[i];
            valor += v[i];
        }
        System.out.println("\nObjetos escolhidos: " + escolhidos + " | peso " + peso + " <= 5 | valor " + valor);
    }

    private static BellmanFord grafoSlides(int pesoYX) {
        BellmanFord g = new BellmanFord("s", "t", "y", "x", "z");
        g.aresta("t", "x", 5);
        g.aresta("t", "y", 8);
        g.aresta("t", "z", -4);
        g.aresta("x", "t", -2);
        g.aresta("y", "x", pesoYX);
        g.aresta("y", "z", 9);
        g.aresta("z", "x", 7);
        g.aresta("z", "s", 2);
        g.aresta("s", "t", 6);
        g.aresta("s", "y", 7);
        return g;
    }

    private static void exercicio5() {
        BellmanFord g = grafoSlides(-3);
        System.out.println("Arestas (ordem de relaxamento): (t,x)5 (t,y)8 (t,z)-4 (x,t)-2 (y,x)-3 (y,z)9 (z,x)7 (z,s)2 (s,t)6 (s,y)7\n");
        boolean ok = g.executar(g.indice("s"), true);
        System.out.println("\nRetorno: " + (ok ? "VERDADE (sem ciclo negativo)" : "FALSO (ciclo negativo)"));
        System.out.println("Vertice | d  | caminho minimo");
        for (int v = 0; v < g.nomes.length; v++) {
            System.out.printf("   %s    | %2d | %s%n", g.nomes[v], g.d[v], g.caminho(v));
        }
    }

    private static void exercicio6() {
        BellmanFord g = grafoSlides(-9);
        System.out.println("Grafo dos slides com w(y,x) = -9. Ciclo x -> t -> y -> x: -2 + 8 + (-9) = -3");
        boolean ok = g.executar(g.indice("s"), false);
        System.out.println("Apos |V|-1 = 4 passadas: " + g.estado());
        System.out.println("Retorno: " + (ok ? "VERDADE" : "FALSO — detectou ciclo negativo, nao ha caminhos minimos bem definidos"));
        System.out.println("Com mais passadas as distancias continuariam diminuindo indefinidamente.");
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

    private static void pausar(String mensagem) {
        if (Ritmo.ativo()) {
            System.out.print(mensagem);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
    }
}
