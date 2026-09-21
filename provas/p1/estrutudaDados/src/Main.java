import java.util.Arrays;
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
            opcao = lerInteiro("Escolha uma questao: ");
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
            System.out.println("Opcao invalida: '" + argumento + "'. Informe um numero de 0 a 6.");
        } catch (IllegalArgumentException e) {
            System.out.println("Entrada invalida: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== P1 — Plano de Reflorestamento Sustentavel com Drones ===");
        System.out.println("1 - Comparacoes do Selection Sort (n(n-1)/2)");
        System.out.println("2 - Soma dos pesos 2^i (2^n - 1)");
        System.out.println("3 - Busca binaria e invariante (iteracoes reais)");
        System.out.println("4 - Planejamento recursivo (Θ(n log n))");
        System.out.println("5 - Monitoramento escalonado (Θ(n log n))");
        System.out.println("6 - Comparacao Selection Sort x Arrays.sort");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao) {
        if (opcao != 0) {
            exibirDescricao(opcao);
        }
        switch (opcao) {
            case 1 -> questao1();
            case 2 -> questao2();
            case 3 -> questao3();
            case 4 -> questao4();
            case 5 -> questao5();
            case 6 -> questao6();
            case 0 -> System.out.println("Programa encerrado.");
            default -> System.out.println("Opcao inexistente.");
        }
    }

    private static void exibirDescricao(int opcao) {
        switch (opcao) {
            case 1 -> System.out.println("""

                    ---------------------------------------------------------
                    1) Questao 1 — Quantas comparacoes o Selection Sort faz?
                    ---------------------------------------------------------
                    O que e: contagem real de comparacoes do Selection Sort
                    comparada com a formula fechada n(n-1)/2, provada por
                    inducao simples: n(n-1)/2 + n = n(n+1)/2.
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Questao 2 — Soma dos pesos 2^i
                    ---------------------------------------------------------
                    O que e: soma pelo laco 2^0 + 2^1 + ... + 2^(n-1)
                    comparada com a formula fechada 2^n - 1, provada por
                    inducao: (2^n - 1) + 2^n = 2^(n+1) - 1.
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Questao 3 — Por que a busca binaria funciona
                    (invariante)
                    ---------------------------------------------------------
                    O que e: busca binaria iterativa que sempre mantem o
                    alvo dentro do intervalo restante (se ele existir);
                    conta as iteracoes reais e compara com o limite teorico
                    ceil(log2 n) + 1.
                    Como testar: informe o tamanho do vetor ordenado e o
                    valor procurado.
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Questao 4 — Quanto custa o planejamento recursivo?
                    ---------------------------------------------------------
                    O que e: recursao que divide o intervalo ao meio e
                    inspeciona tudo em cada nivel (T(n) = 2T(n/2) + n),
                    resolvida pelo Teorema Mestre como Θ(n log n).
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Questao 5 — Laco de monitoramento escalonado
                    ---------------------------------------------------------
                    O que e: para cada posicao i, um laco interno divide i
                    por 2 ate chegar a zero; a soma dessas contagens para
                    todo i de 1 a n se aproxima de n·log2(n), ou seja,
                    Θ(n log n).
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Questao 6 — Complexidade total e troca do algoritmo
                    ---------------------------------------------------------
                    O que e: a etapa mais pesada domina a complexidade
                    total — Θ(n^2) do Selection Sort torna o algoritmo
                    inteiro Θ(n^2). Compara o tempo real de Selection Sort
                    com Arrays.sort (Θ(n log n)) para tamanhos crescentes.
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void questao1() {
        System.out.println("Questao 1 — comparacoes reais vs. formula n(n-1)/2:");
        int[] tamanhos = {10, 100, 1_000, 5_000};
        for (int n : tamanhos) {
            double[] vetor = SelectionSortComparacoes.gerarVetorAleatorio(n);
            long reais = SelectionSortComparacoes.comparacoesReais(vetor);
            long formula = SelectionSortComparacoes.formula(n);
            System.out.printf("n=%-6d comparacoes reais=%-10d formula=%-10d iguais=%b%n",
                    n, reais, formula, reais == formula);
        }
    }

    private static void questao2() {
        System.out.println("Questao 2 — soma pelo loop vs. formula 2^n - 1:");
        int[] tamanhos = {1, 5, 10, 15, 20};
        for (int n : tamanhos) {
            long loop = SomaPesosGeometrica.somaPeloLoop(n);
            long formula = SomaPesosGeometrica.formula(n);
            System.out.printf("n=%-4d soma pelo loop=%-10d formula=%-10d iguais=%b%n",
                    n, loop, formula, loop == formula);
        }
    }

    private static void questao3() {
        int n = lerInteiro("Tamanho do vetor ordenado (ex.: 1000000): ");
        if (n <= 0) {
            throw new IllegalArgumentException("O tamanho deve ser positivo.");
        }
        int[] vetor = new int[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = i;
        }
        int alvo = lerInteiro("Valor procurado (-1 para simular ausencia): ");
        int posicao = BuscaBinariaIterativa.buscar(vetor, alvo);
        int limite = BuscaBinariaIterativa.limiteTeorico(n);
        System.out.printf("posicao=%d | iteracoes=%d | limite teorico=ceil(log2 n)+1=%d | dentro do limite? %b%n",
                posicao, BuscaBinariaIterativa.iteracoes, limite, BuscaBinariaIterativa.iteracoes <= limite);
    }

    private static void questao4() {
        System.out.println("Questao 4 — inspecoes reais vs. n*log2(n):");
        int[] tamanhos = {8, 16, 64, 256, 1_024, 4_096};
        for (int n : tamanhos) {
            PlanejamentoRecursivo.inspecoes = 0;
            PlanejamentoRecursivo.planejar(0, n - 1);
            double aproximado = PlanejamentoRecursivo.nLogN(n);
            double razao = aproximado == 0 ? 0 : PlanejamentoRecursivo.inspecoes / aproximado;
            System.out.printf("n=%-6d inspecoes reais=%-8d n*log2(n) aprox=%-10.1f razao=%.3f%n",
                    n, PlanejamentoRecursivo.inspecoes, aproximado, razao);
        }
    }

    private static void questao5() {
        System.out.println("Questao 5 — total real vs. n*log2(n):");
        int[] tamanhos = {100, 1_000, 10_000, 100_000};
        for (int n : tamanhos) {
            long total = MonitoramentoEscalonado.totalIteracoes(n);
            double aproximado = MonitoramentoEscalonado.nLogN(n);
            double razao = aproximado == 0 ? 0 : total / aproximado;
            System.out.printf("n=%-7d total real=%-10d n*log2(n) aprox=%-10.1f razao=%.3f%n",
                    n, total, aproximado, razao);
        }
    }

    private static void questao6() {
        System.out.println("Questao 6 — Selection Sort O(n^2) x Arrays.sort O(n log n):");
        int[] tamanhos = {1_000, 5_000, 10_000, 20_000};
        for (int n : tamanhos) {
            System.out.println(ComparacaoOrdenacao.compararTempos(n));
        }
        System.out.println("Conclusao: a etapa mais pesada (Selection Sort, Θ(n²)) domina a "
                + "complexidade total; trocando por um algoritmo Θ(n log n), o algoritmo inteiro "
                + "passa a ser Θ(n log n).");
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
