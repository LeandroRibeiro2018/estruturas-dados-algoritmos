import java.util.Arrays;
import java.util.Deque;
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
        System.out.println("\n=== Aula 02 — Corretude de Algoritmos ===");
        System.out.println("1 - Busca sequencial");
        System.out.println("2 - Reprocessamento de fila");
        System.out.println("3 - Bubble Sort");
        System.out.println("4 - Decisão entre busca sequencial e binária");
        System.out.println("5 - Insertion Sort");
        System.out.println("6 - Merge Sort");
        System.out.println("7 - Maior latência");
        System.out.println("8 - Busca binária recursiva");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao) {
        if (opcao != 0) {
            exibirDescricao(opcao);
        }
        switch (opcao) {
            case 1 -> exercicioBuscaSequencial();
            case 2 -> exercicioFila();
            case 3 -> exercicioBubbleSort();
            case 4 -> exercicioDecisaoBusca();
            case 5 -> exercicioInsertionSort();
            case 6 -> exercicioMergeSort();
            case 7 -> exercicioMaiorLatencia();
            case 8 -> exercicioBuscaBinariaRecursiva();
            case 0 -> System.out.println("Programa encerrado.");
            default -> System.out.println("Opcao inexistente.");
        }
    }

    private static void exibirDescricao(int opcao) {
        switch (opcao) {
            case 1 -> System.out.println("""

                    ---------------------------------------------------------
                    1) Exercicio 1 — Eu consigo encontrar essa informacao?
                    ---------------------------------------------------------
                    O que e: busca sequencial em um vetor de SKUs, varrendo de
                    tras para frente (k = n-1 ate 0), retornando a primeira
                    ocorrencia encontrada nesse sentido.
                    Corretude: garantida pelo invariante "o subvetor
                    A[k+1..n-1] nao contem x", valido antes da 1a iteracao e
                    preservado a cada decremento de k.
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Exercicio 2 — O algoritmo que nunca termina
                    ---------------------------------------------------------
                    O que e: reprocessamento de uma fila de itens (ex.:
                    webhooks) com limite maximo de tentativas por item.
                    Corretude/terminacao: o contador de tentativas de cada
                    item so cresce (nunca e resetado), garantindo que, no
                    pior caso, todo item eventualmente atinge o limite e sai
                    da fila ativa.
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Exercicio 3 — Funcionou nos meus testes. Entao esta
                    correto?
                    ---------------------------------------------------------
                    O que e: Bubble Sort classico (comparacoes e trocas de
                    elementos adjacentes) aplicado a um vetor de inteiros.
                    Corretude: nenhuma quantidade finita de testes prova
                    corretude para um dominio infinito de entradas — apenas
                    invariante de laco ou inducao fecham essa lacuna.
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Exercicio 4 — Qual algoritmo eu colocaria em producao?
                    ---------------------------------------------------------
                    O que e: decisao entre busca sequencial e busca binaria
                    (com estrutura ordenada/indice), considerando se os
                    dados estao ordenados, o volume de registros e a razao
                    entre frequencia de leitura e de escrita.
                    Como testar: informe se os dados estao ordenados, o
                    tamanho da colecao e os padroes de leitura/escrita.
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Exercicio 5 — O que permanece verdadeiro durante o
                    algoritmo?
                    ---------------------------------------------------------
                    O que e: Insertion Sort, com prova de corretude via
                    invariante de laco: "no inicio de cada iteracao j, o
                    subvetor A[1..j-1] esta ordenado" (inicio, manutencao e
                    termino).
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Exercicio 6 — Encontre um caso em que a IAG esteja
                    errada
                    ---------------------------------------------------------
                    O que e: Merge Sort (divisao e conquista), demonstrando
                    como um bug de indice no merge() pode escapar da prova
                    de corretude quando o codigo diverge do pseudocodigo.
                    ---------------------------------------------------------""");
            case 7 -> System.out.println("""

                    ---------------------------------------------------------
                    7) Exercicio 7 — Voce colocaria este algoritmo em
                    producao?
                    ---------------------------------------------------------
                    O que e: encontrar a maior latencia (ms) em uma janela de
                    monitoramento, com prova de corretude via invariante de
                    laco: "maior representa o maior valor de latencias[0..i-1]".
                    ---------------------------------------------------------""");
            case 8 -> System.out.println("""

                    ---------------------------------------------------------
                    8) Exercicio 8 — O algoritmo recursivo realmente resolve
                    o problema?
                    ---------------------------------------------------------
                    O que e: busca binaria recursiva sobre um vetor ordenado,
                    com prova por inducao forte: o caso base e o intervalo
                    vazio (esq > dir), e cada chamada recursiva reduz
                    estritamente o tamanho do intervalo.
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void exercicioBuscaSequencial() {
        String[] vetor = {"SKU-1001", "SKU-1002", "SKU-1003", "SKU-1004", "SKU-1005",
                "SKU-1006", "SKU-1007", "SKU-1008", "SKU-1009", "SKU-1010",
                "SKU-1011", "SKU-1012", "SKU-1004", "SKU-1013", "SKU-1014"};
        System.out.println("Vetor de SKU: " + Arrays.toString(vetor));
        String alvo = lerTexto("Informe o SKU a buscar: ");
        int indice = BuscaSequencial.buscaSequencial(vetor, alvo);
        System.out.println(indice >= 0 ? "Encontrado no indice " + indice + "." : "SKU nao encontrado.");
    }

    private static void exercicioFila() {
        Deque<Integer> fila = FilaReprocessamento.criarFilaExemplo();
        System.out.println("Fila inicial: " + fila);
        FilaReprocessamento.processarFila(fila, 3);
        System.out.println("Fila processada com limite de tentativas = 3.");
        System.out.println("Resultado final: " + fila);
    }

    private static void exercicioBubbleSort() {
        int[] vetor = {9, 4, 7, 2, 1, 8, 3, 5};
        System.out.println("Antes: " + Arrays.toString(vetor));
        BubbleSort.ordenar(vetor);
        System.out.println("Depois: " + Arrays.toString(vetor));
    }

    private static void exercicioDecisaoBusca() {
        boolean ordenado = lerBoolean("Os dados estao ordenados? (true/false): ");
        int tamanho = lerInteiro("Quantos registros ha? ");
        boolean leituras = lerBoolean("As leituras sao mais frequentes do que escritas? (true/false): ");
        boolean escritas = lerBoolean("As escritas sao frequentes? (true/false): ");
        System.out.println(DecisaoBusca.escolherEstrategia(ordenado, tamanho, leituras, escritas));
    }

    private static void exercicioInsertionSort() {
        int[] vetor = {7, 5, 4, 2, 3, 1, 9, 8};
        System.out.println("Antes: " + Arrays.toString(vetor));
        InsertionSort.ordenar(vetor);
        System.out.println("Depois: " + Arrays.toString(vetor));
    }

    private static void exercicioMergeSort() {
        int[] vetor = {12, 5, 7, 3, 1, 9, 15, 4};
        System.out.println("Antes: " + Arrays.toString(vetor));
        MergeSort.ordenar(vetor);
        System.out.println("Depois: " + Arrays.toString(vetor));
    }

    private static void exercicioMaiorLatencia() {
        long[] latencias = {120L, 315L, 198L, 440L, 205L, 350L};
        System.out.println("Latencias: " + Arrays.toString(latencias));
        System.out.println("Maior: " + MaiorLatencia.maiorLatencia(latencias) + " ms");
    }

    private static void exercicioBuscaBinariaRecursiva() {
        int[] vetor = {1, 3, 5, 7, 9, 11, 13, 15, 17};
        int alvo = lerInteiro("Informe o valor a buscar: ");
        int indice = BuscaBinariaRecursiva.buscaBinaria(vetor, alvo);
        System.out.println(indice >= 0 ? "Encontrado no indice " + indice + "." : "Valor nao encontrado.");
    }

    private static boolean lerBoolean(String mensagem) {
        System.out.print(mensagem);
        String valor = scanner.nextLine().trim();
        if (valor.equalsIgnoreCase("true")) {
            return true;
        }
        if (valor.equalsIgnoreCase("false")) {
            return false;
        }
        throw new IllegalArgumentException("Informe apenas true ou false.");
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

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }
}
