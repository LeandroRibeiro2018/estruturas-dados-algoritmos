import java.util.Arrays;
import java.util.List;
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
            int opcao = Integer.parseInt(argumento.trim());
            executar(opcao);
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida: '" + argumento + "'. Informe um numero de 0 a 8.");
        } catch (IllegalArgumentException e) {
            System.out.println("Entrada invalida: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Aula 11 — Algoritmos Gulosos ===");
        System.out.println("1 - Problema do troco: os três exemplos dos slides");
        System.out.println("2 - Quando o troco guloso é ótimo? (contraexemplos)");
        System.out.println("3 - Mochila fracionária (valor/peso)");
        System.out.println("4 - Mochila binária (0-1): o guloso falha");
        System.out.println("5 - Dijkstra passo a passo (exemplo dos slides)");
        System.out.println("6 - Dijkstra: vetor O(V²) x heap O((V+E) log V)");
        System.out.println("7 - Dijkstra com peso negativo: por que falha");
        System.out.println("8 - Kruskal + Union-Find (árvore geradora mínima)");
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
                    1) Problema do troco (Coin Change)
                    ---------------------------------------------------------
                    O que e: trocoGuloso(C, valor) dos slides — usa sempre a
                    moeda de maior valor que ainda cabe — nos tres exemplos:
                    N=12 {1,2,5}, N=8 {1,4,6} e N=19 {2,3,6}.
                    O que observar: no 1o o guloso e otimo; no 2o devolve 3
                    moedas quando 2 bastam; no 3o nem encontra solucao. A
                    resposta otima e conferida por programacao dinamica.
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Quando o troco guloso funciona?
                    ---------------------------------------------------------
                    O que e: busca automatica do menor valor em que o guloso
                    erra, para varios sistemas de moedas.
                    Licao: para provar que o guloso NAO e sempre otimo basta
                    um contraexemplo; nao achar contraexemplo num intervalo
                    nao e prova de otimalidade, apenas evidencia.
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Mochila fracionaria
                    ---------------------------------------------------------
                    O que e: objetos ($60, 10), ($100, 20), ($120, 30) e
                    mochila W = 50. Ordena por valor/peso (6 > 5 > 4) e
                    coloca a maior fracao possivel de cada objeto.
                    O que observar: resultado $240 (objetos 1 e 2 inteiros +
                    20/30 do objeto 3). Complexidade O(n log n), dominada
                    pela ordenacao.
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Mochila binaria (0-1)
                    ---------------------------------------------------------
                    O que e: os mesmos objetos, mas sem fracionar. O guloso
                    por valor/peso e comparado com o otimo exato (PD).
                    Licao: a mochila fracionaria admite solucao gulosa
                    otima; a binaria nao.
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Algoritmo de Dijkstra
                    ---------------------------------------------------------
                    O que e: o grafo dirigido dos slides (s, t, x, y, z) a
                    partir de s. A cada passo: Extrai-Min (vertice de menor
                    d ainda nao finalizado) e relaxa(u, v, w) nos vizinhos.
                    O que observar: os estados batem com as figuras (a)-(f):
                    s=0, y=5, z=7, t=8, x=9.
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Dijkstra: a fila de prioridade importa
                    ---------------------------------------------------------
                    O que e: a mesma busca com Q em vetor (Extrai-Min varre
                    tudo: O(V^2)) e com Q em heap binario (O((V+E) log V)),
                    em grafos esparsos aleatorios cada vez maiores.
                    O que observar: as distancias sao identicas; o tempo do
                    vetor tende a crescer ~4x quando V dobra (V^2), o do
                    heap pouco mais de 2x.
                    ---------------------------------------------------------""");
            case 7 -> System.out.println("""

                    ---------------------------------------------------------
                    7) Dijkstra exige pesos nao negativos
                    ---------------------------------------------------------
                    O que e: um grafo pequeno com a aresta b -> a de peso -3.
                    O que observar: Dijkstra finaliza "a" cedo demais (escolha
                    gulosa sem retrocesso) e erra a distancia ate c; o
                    Bellman-Ford, que nao e guloso, acerta.
                    ---------------------------------------------------------""");
            case 8 -> System.out.println("""

                    ---------------------------------------------------------
                    8) Kruskal e Union-Find
                    ---------------------------------------------------------
                    O que e: o grafo dos slides (a..i, 14 arestas). As
                    arestas saem da mais barata para a mais cara; Union-Find
                    diz se as pontas ja estao no mesmo conjunto (ciclo).
                    O que observar: 8 arestas aceitas, w(T) = 37, igual ao
                    slide. Complexidade O(|E| log |E|).
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void exercicio1() {
        mostrarTroco(12, new int[]{1, 2, 5});
        mostrarTroco(8, new int[]{1, 4, 6});
        mostrarTroco(19, new int[]{2, 3, 6});
    }

    private static void mostrarTroco(int valor, int[] moedas) {
        System.out.println("\nN = " + valor + ", M = " + Arrays.toString(moedas));
        Troco.Resultado g = Troco.guloso(moedas, valor);
        for (String passo : g.passos()) {
            System.out.println("  " + passo);
        }
        List<Integer> otimo = Troco.otimo(moedas, valor);
        String guloso = g.encontrou(valor)
                ? g.moedas().size() + " moedas " + g.moedas()
                : "NAO encontrou solucao (somou " + g.soma() + " com " + g.moedas() + ")";
        System.out.println("  Guloso: " + guloso);
        System.out.println("  Otimo:  " + (otimo == null ? "nao existe" : otimo.size() + " moedas " + otimo));
        String veredito = !g.encontrou(valor) ? "o guloso falhou"
                : g.moedas().size() == otimo.size() ? "o guloso foi otimo" : "o guloso NAO foi otimo";
        System.out.println("  -> " + veredito);
    }

    private static void exercicio2() {
        int[][] sistemas = {
                {1, 5, 10, 25, 50, 100}, // centavos do real
                {1, 2, 5},
                {1, 4, 6},
                {1, 3, 4},
                {2, 3, 6}
        };
        int limite = 1000;
        for (int[] m : sistemas) {
            int v = Troco.menorContraexemplo(m, limite);
            if (v == -1) {
                System.out.printf("%-26s nenhum contraexemplo ate %d%n", Arrays.toString(m), limite);
            } else {
                Troco.Resultado g = Troco.guloso(m, v);
                List<Integer> o = Troco.otimo(m, v);
                System.out.printf("%-26s menor contraexemplo N=%d: guloso %s | otimo %s%n", Arrays.toString(m), v,
                        g.encontrou(v) ? g.moedas() : "falha", o);
            }
        }
        System.out.println("\nObservacao sobre a complexidade do slide (O(n), n = |C|):");
        Troco.Resultado r = Troco.guloso(new int[]{1}, 1000);
        System.out.println("  com C = {1} e valor 1000 o laco roda " + (r.passos().size())
                + " vezes: o custo real e O(|C| + k), k = moedas devolvidas (depende do valor).");
    }

    private static List<Mochila.Objeto> objetosDosSlides() {
        return List.of(new Mochila.Objeto(1, 60, 10), new Mochila.Objeto(2, 100, 20), new Mochila.Objeto(3, 120, 30));
    }

    private static void exercicio3() {
        List<Mochila.Objeto> objetos = objetosDosSlides();
        System.out.println("Objeto | Valor | Peso | Valor/Peso");
        for (Mochila.Objeto o : objetos) {
            System.out.printf("   %d   | $%4d | %4d | %.0f%n", o.id(), o.valor(), o.peso(), o.razao());
        }
        Mochila.Solucao s = Mochila.fracionaria(objetos, 50);
        System.out.println("\nMochila W = 50:");
        s.passos().forEach(p -> System.out.println("  " + p));
        StringBuilder m = new StringBuilder();
        for (int i = 0; i < s.ordem().size(); i++) {
            m.append("objeto ").append(s.ordem().get(i).id()).append('=')
                    .append(String.format("%.3f", s.fracoes()[i])).append("  ");
        }
        System.out.println("Vetor m (fracoes): " + m.toString().trim());
        System.out.printf("Valor total = $%.0f%n", s.valorTotal());
    }

    private static void exercicio4() {
        List<Mochila.Objeto> objetos = objetosDosSlides();
        Mochila.Solucao g = Mochila.binariaGulosa(objetos, 50);
        System.out.println("Guloso por valor/peso, sem fracionar (W = 50):");
        g.passos().forEach(p -> System.out.println("  " + p));
        System.out.printf("  valor guloso = $%.0f%n", g.valorTotal());
        int[] valor = new int[1];
        List<Integer> otimo = Mochila.binariaOtima(objetos, 50, valor);
        System.out.println("Otimo (programacao dinamica): objetos " + otimo + " -> valor = $" + valor[0]);
        System.out.printf("Perda do guloso: $%.0f (%.1f%%)%n", valor[0] - g.valorTotal(),
                100.0 * (valor[0] - g.valorTotal()) / valor[0]);
        System.out.println("O espaco que sobra (peso 20) nao pode ser preenchido com uma fracao do objeto 3.");
    }

    private static GrafoPonderado grafoDijkstraSlides() {
        GrafoPonderado g = new GrafoPonderado(true, "s", "t", "x", "y", "z");
        g.aresta("s", "t", 10);
        g.aresta("s", "y", 5);
        g.aresta("t", "x", 1);
        g.aresta("t", "y", 2);
        g.aresta("y", "t", 3);
        g.aresta("y", "x", 9);
        g.aresta("y", "z", 2);
        g.aresta("x", "z", 4);
        g.aresta("z", "x", 6);
        g.aresta("z", "s", 7);
        return g;
    }

    private static void exercicio5() {
        GrafoPonderado g = grafoDijkstraSlides();
        Dijkstra dj = new Dijkstra(g);
        System.out.println("Inicializa: d[s]=0, demais = inf, pi = NULL");
        dj.comVetor(g.indice("s"), true);
        System.out.println("\nVertice | d | caminho minimo a partir de s");
        for (int v = 0; v < g.n(); v++) {
            System.out.printf("   %s    | %d | %s%n", g.nome(v), dj.d[v], String.join(" -> ", dj.caminho(v)));
        }
    }

    private static void exercicio6() {
        Dijkstra aquecimento = new Dijkstra(GrafoPonderado.aleatorio(3_000, 5, 1));
        for (int i = 0; i < 5; i++) { // aquecimento do JIT, para medir o código já otimizado
            aquecimento.comVetor(0, false);
            aquecimento.comHeap(0);
        }
        for (int n : new int[]{5_000, 10_000, 20_000}) {
            GrafoPonderado g = GrafoPonderado.aleatorio(n, 5, 42);
            Dijkstra vetor = new Dijkstra(g);
            Dijkstra heap = new Dijkstra(g);
            double tVetor = melhorDe3(() -> vetor.comVetor(0, false));
            double tHeap = melhorDe3(() -> heap.comHeap(0));
            System.out.printf("V=%5d E=%6d | vetor=%8.2f ms | heap=%6.2f ms | mesmas distancias: %b%n",
                    n, g.arestas().size(), tVetor, tHeap, Arrays.equals(vetor.d, heap.d));
        }
        System.out.println("(melhor tempo de 3 execucoes, para reduzir o ruido de GC/JIT)");
    }

    private static double melhorDe3(Runnable tarefa) {
        double melhor = Double.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            long t0 = System.nanoTime();
            tarefa.run();
            melhor = Math.min(melhor, (System.nanoTime() - t0) / 1e6);
        }
        return melhor;
    }

    private static void exercicio7() {
        GrafoPonderado g = new GrafoPonderado(true, "s", "a", "b", "c");
        g.aresta("s", "a", 1);
        g.aresta("s", "b", 3);
        g.aresta("b", "a", -3);
        g.aresta("a", "c", 1);
        System.out.println("Arestas: s->a 1, s->b 3, b->a -3, a->c 1");
        Dijkstra dj = new Dijkstra(g);
        dj.comVetor(0, true);
        long[] bf = Dijkstra.bellmanFord(g, 0);
        System.out.println("\nVertice | Dijkstra | Bellman-Ford (correto)");
        for (int v = 0; v < g.n(); v++) {
            System.out.printf("   %s    | %8d | %d%s%n", g.nome(v), dj.d[v], bf[v], dj.d[v] != bf[v] ? "   <- erro" : "");
        }
        System.out.println("Dijkstra finalizou c com d=2 usando a=1; so depois descobriu a=0 via b,");
        System.out.println("mas o guloso nao volta atras para corrigir c (o caminho certo e s->b->a->c = 1).");
    }

    private static void exercicio8() {
        GrafoPonderado g = new GrafoPonderado(false, "a", "b", "c", "d", "e", "f", "g", "h", "i");
        // Ordem de cadastro igual à sequência mostrada nos slides para os empates de peso.
        g.aresta("h", "g", 1);
        g.aresta("i", "c", 2);
        g.aresta("g", "f", 2);
        g.aresta("a", "b", 4);
        g.aresta("c", "f", 4);
        g.aresta("i", "g", 6);
        g.aresta("c", "d", 7);
        g.aresta("h", "i", 7);
        g.aresta("a", "h", 8);
        g.aresta("b", "c", 8);
        g.aresta("d", "e", 9);
        g.aresta("e", "f", 10);
        g.aresta("b", "h", 11);
        g.aresta("d", "f", 14);
        Kruskal.Resultado r = Kruskal.executar(g, true);
        StringBuilder soma = new StringBuilder();
        for (GrafoPonderado.Aresta e : r.arvore()) {
            soma.append(soma.length() == 0 ? "" : " + ").append(e.peso());
        }
        System.out.println("\nArvore geradora minima: " + r.arvore().size() + " arestas (|V| - 1 = " + (g.n() - 1) + ")");
        System.out.println("w(T) = " + soma + " = " + r.pesoTotal());
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

    /** No modo passo a passo (Ritmo), espera ENTER antes de seguir. */
    private static void pausar(String mensagem) {
        if (Ritmo.ativo()) {
            System.out.print(mensagem);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
    }
}
