import java.util.List;
import java.util.Map;
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
        System.out.println("\n=== Aula 03 — Análise Assintótica ===");
        System.out.println("1 - Busca sequencial e O(n)");
        System.out.println("2 - Crescimento assintótico");
        System.out.println("3 - Melhor caso / caso médio / pior caso");
        System.out.println("4 - Duplicidade de clientes");
        System.out.println("5 - Análise do Selection Sort");
        System.out.println("6 - Medição real do Selection Sort");
        System.out.println("7 - Autocomplete: decisão assintótica");
        System.out.println("8 - Fibonacci: constante, linear e quadrático");
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
                    1) Exercicio 1 — Quando uma tarefa comeca a ficar lenta?
                    ---------------------------------------------------------
                    O que e: mede o tempo real de uma busca sequencial O(n)
                    para tamanhos crescentes de lista (100 a 100.000), tanto
                    buscando o ultimo elemento quanto um valor inexistente.
                    O que observar: o tempo cresce de forma proporcional a n,
                    consistente com O(n), apesar do ruido de JIT/warm-up.
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Exercicio 2 — O crescimento importa mais que o numero
                    de hoje
                    ---------------------------------------------------------
                    O que e: comparacao entre as classes O(1), O(n), O(n^2)
                    e O(2^n), projetando o tempo estimado para n = 1.000,
                    10.000, 100.000 e 1.000.000.
                    Licao: "funciona bem hoje" e uma afirmacao sobre um
                    ponto; crescimento assintotico e uma afirmacao sobre a
                    trajetoria.
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Exercicio 3 — O melhor caso pode enganar
                    ---------------------------------------------------------
                    O que e: classificacao de uma busca linear em melhor
                    caso (primeira posicao), caso medio (posicao
                    intermediaria) e pior caso (ultima posicao ou
                    inexistente).
                    Licao: testar so o melhor caso subestima sistematicamente
                    o tempo de resposta real percebido pelo usuario.
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Exercicio 4 — Duas solucoes funcionam. Qual voce
                    escolheria?
                    ---------------------------------------------------------
                    O que e: deteccao de clientes duplicados comparando um
                    algoritmo O(n) (chave normalizada + hash) com um
                    algoritmo O(n^2) (comparacao de todo par de registros).
                    Licao: a base cresce continuamente, entao o algoritmo
                    O(n) e a escolha sustentavel no longo prazo.
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Exercicio 5 — O codigo que parece simples pode
                    esconder um problema
                    ---------------------------------------------------------
                    O que e: analise estrutural do Selection Sort — dois
                    lacos aninhados, o laco interno depende do externo,
                    portanto a classe de complexidade e O(n^2).
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Exercicio 6 — Selection Sort: funciona, mas ate
                    quando?
                    ---------------------------------------------------------
                    O que e: medicao real (System.nanoTime()) do tempo de
                    execucao do Selection Sort para tamanhos crescentes
                    (100 a 20.000), com verificacao de que o vetor de fato
                    ficou ordenado ao final.
                    O que observar: dobrar n deve aproximadamente quadruplicar
                    o tempo (2^2 = 4), confirmando O(n^2).
                    ---------------------------------------------------------""");
            case 7 -> System.out.println("""

                    ---------------------------------------------------------
                    7) Exercicio 7 — A IAG recomendou o algoritmo errado?
                    ---------------------------------------------------------
                    O que e: matriz de decisao para um endpoint de
                    autocomplete, comparando as solucoes A (O(1)), B (O(n))
                    e C (O(n^2)) em desempenho, custo, escalabilidade,
                    complexidade de implementacao, manutencao e adequacao.
                    ---------------------------------------------------------""");
            case 8 -> System.out.println("""

                    ---------------------------------------------------------
                    8) Exercicio 8 — Fibonacci: tres algoritmos, tres
                    decisoes
                    ---------------------------------------------------------
                    O que e: comparacao entre fibonacciConstante (formula de
                    Binet, O(1)), fibonacciLinear (O(n)) e
                    fibonacciQuadratico (O(n^2)), medindo tempo real para
                    n = 10 e n = 20.
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void exercicio1() {
        System.out.println("Busca sequencial: algoritmo em O(n) no pior caso.");
        List<Integer> tamanhos = BuscaSequencialAnalise.gerarTamanhos();
        for (Integer tamanho : tamanhos) {
            List<String> lista = BuscaSequencialAnalise.gerarListaComTamanho(tamanho);
            long inicio = System.nanoTime();
            BuscaSequencialAnalise.buscaSequencial(lista, "CPF-" + (tamanho - 1));
            long fim = System.nanoTime();
            long inicioInexistente = System.nanoTime();
            BuscaSequencialAnalise.buscaSequencial(lista, "CPF-999999");
            long fimInexistente = System.nanoTime();
            System.out.printf("n=%d | ultimo=%.3f ms | inexistente=%.3f ms%n",
                    tamanho,
                    (fim - inicio) / 1_000_000.0,
                    (fimInexistente - inicioInexistente) / 1_000_000.0);
        }
    }

    private static void exercicio2() {
        System.out.println("Comparação de crescimento assintótico:");
        for (Map.Entry<String, String> e : CrescimentoAssintotico.estimarTempos().entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    private static void exercicio3() {
        int tamanho = 10;
        System.out.println(BuscaPedido.classificarCaso(0, tamanho));
        System.out.println(BuscaPedido.classificarCaso(5, tamanho));
        System.out.println(BuscaPedido.classificarCaso(9, tamanho));
        System.out.println(BuscaPedido.classificarCaso(-1, tamanho));
    }

    private static void exercicio4() {
        List<String> clientes = List.of("Ana", "ana", "Beto", "Carlos", "carlos", "Diana");
        System.out.println("Algoritmo A (O(n)): " + DeteccaoDuplicidade.algoritmoA(clientes));
        System.out.println("Algoritmo B (O(n²)): " + DeteccaoDuplicidade.algoritmoB(clientes));
    }

    private static void exercicio5() {
        System.out.println("Selection Sort: dois laços aninhados => O(n²).");
        System.out.println("Laço externo: i = 0..n-2");
        System.out.println("Laço interno: j = i+1..n-1");
        System.out.println("Portanto, o custo cresce quadraticamente com n.");
    }

    private static void exercicio6() {
        System.out.println("Medições reais do Selection Sort:");
        for (String s : SelectionSort.medirVarios()) {
            System.out.println(s);
        }
    }

    private static void exercicio7() {
        System.out.println("Matriz de decisão para autocomplete:");
        for (Map.Entry<String, Integer> e : Autocomplete.montarMatriz().entrySet()) {
            System.out.println(e.getKey() + " -> nota " + e.getValue());
        }
        System.out.println(Autocomplete.escolherSolucao());
    }

    private static void exercicio8() {
        System.out.println("Comparação de Fibonacci:");
        System.out.println(Fibonacci.comparar(10));
        System.out.println(Fibonacci.comparar(20));
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
