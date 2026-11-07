import java.util.Random;
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
        System.out.println("\n=== Aula 06 — Lista Ligada, Pilha e Fila ===");
        System.out.println("1 - Lista ligada sem cabeça");
        System.out.println("2 - Lista ligada com cabeça");
        System.out.println("3 - Pilha estática (vetor)");
        System.out.println("4 - Pilha dinâmica (lista ligada)");
        System.out.println("5 - Simulação de recursão com pilha (fatorial)");
        System.out.println("6 - Fila estática circular (mod)");
        System.out.println("7 - Fila dinâmica (lista ligada)");
        System.out.println("8 - Fila de prioridade: lista não ordenada x ordenada");
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
                    1) Lista simplesmente ligada "sem cabeca"
                    ---------------------------------------------------------
                    O que e: insercao no inicio O(1), busca O(n) e remocao
                    O(n) seguindo o pseudocodigo dos slides.
                    O que observar: a insercao trata 2 casos (lista vazia /
                    nao vazia) e a remocao trata 3 casos (k no 1o no, k em
                    outra posicao, k ausente). Correto e eficiente, mas nao
                    elegante.
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Lista simplesmente ligada "com cabeca"
                    ---------------------------------------------------------
                    O que e: as mesmas operacoes com um no cabeca (dummy
                    node) que so marca o inicio e aqui guarda a quantidade
                    de elementos.
                    Licao: listas com cabeca nao reduzem a complexidade
                    assintotica, mas simplificam a implementacao — a
                    remocao passa a ter apenas 2 casos.
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Pilha (LIFO) — implementacao estatica
                    ---------------------------------------------------------
                    O que e: vetor de tamanho MAX = 5 controlado pelo indice
                    do topo (topo = -1 indica pilha vazia).
                    O que observar: push falha quando topo = MAX - 1 (pilha
                    cheia) e pop falha quando topo = -1 (pilha vazia).
                    Ambos sao O(1).
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Pilha (LIFO) — implementacao dinamica
                    ---------------------------------------------------------
                    O que e: lista ligada em que apenas o endereco do no do
                    topo e controlado. Push e pop sao O(1) e nao existe o
                    limite MAX da versao estatica.
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Simulacao de recursao com pilha
                    ---------------------------------------------------------
                    O que e: fatorial(4) recursivo comparado com a versao
                    iterativa que usa uma pilha explicita. Cada push
                    equivale a uma chamada (aloca as variaveis locais) e
                    cada pop equivale a um retorno.
                    Estrategia de projeto: escrever o algoritmo recursivo e
                    depois reescreve-lo como iterativo.
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Fila (FIFO) — implementacao estatica circular
                    ---------------------------------------------------------
                    O que e: vetor de MAX = 5 posicoes com inicio e
                    quantidade de elementos; o final e inferido por
                    (inicio + qtd) mod MAX.
                    O que observar: apos remover do inicio, novas insercoes
                    reaproveitam as posicoes 0, 1... ("dao a volta"),
                    resolvendo o falso "fila cheia".
                    ---------------------------------------------------------""");
            case 7 -> System.out.println("""

                    ---------------------------------------------------------
                    7) Fila (FIFO) — implementacao dinamica
                    ---------------------------------------------------------
                    O que e: lista ligada com referencias para o inicio e
                    para o final. Enqueue insere apos o ultimo no e dequeue
                    remove o primeiro, ambos em O(1).
                    O que observar: quando a fila fica vazia, o final
                    tambem volta a ser NULL.
                    ---------------------------------------------------------""");
            case 8 -> System.out.println("""

                    ---------------------------------------------------------
                    8) Fila de prioridade com lista
                    ---------------------------------------------------------
                    O que e: a mesma sequencia de chaves em uma lista nao
                    ordenada (insere O(1), extrai O(n)) e em uma lista
                    ordenada decrescente (insere O(n), extrai O(1)), seguida
                    de uma medicao real com n = 20.000 chaves aleatorias.
                    Licao: uma das operacoes sempre fica "lenta"; o heap
                    (Aula 08) resolve as duas em O(log n).
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void exercicio1() {
        ListaSemCabeca lista = new ListaSemCabeca();
        System.out.println("Lista vazia: " + lista);
        for (int chave : new int[]{10, 20, 30, 40}) {
            lista.insere(new No(chave));
            System.out.println("insere(" + chave + ") -> " + lista);
        }
        System.out.println("busca(20) -> " + descreverBusca(lista.busca(20)));
        System.out.println("busca(99) -> " + descreverBusca(lista.busca(99)));
        for (int k : new int[]{40, 20, 99}) {
            int caso = lista.remove(k);
            System.out.println("remove(" + k + ") -> Caso " + caso + " | " + lista);
        }
    }

    private static void exercicio2() {
        ListaComCabeca lista = new ListaComCabeca();
        System.out.println("Lista vazia: " + lista);
        for (int chave : new int[]{10, 20, 30, 40}) {
            lista.insere(new No(chave));
            System.out.println("insere(" + chave + ") -> " + lista);
        }
        System.out.println("busca(20) -> " + descreverBusca(lista.busca(20)));
        System.out.println("busca(99) -> " + descreverBusca(lista.busca(99)));
        for (int k : new int[]{40, 20, 99}) {
            boolean achou = lista.remove(k);
            System.out.println("remove(" + k + ") -> " + (achou ? "removido" : "nao encontrado")
                    + " | " + lista);
        }
        System.out.println("Remover o 1o no (40) e um no do meio (20) usou o mesmo codigo.");
    }

    private static void exercicio3() {
        PilhaEstatica pilha = new PilhaEstatica(5);
        System.out.println("Pilha vazia: " + pilha);
        for (int x = 1; x <= 6; x++) {
            boolean ok = pilha.push(x);
            System.out.println("push(" + x + ") -> " + (ok ? "true " : "false (pilha cheia) ") + pilha);
        }
        for (int i = 0; i < 6; i++) {
            Integer t = pilha.pop();
            System.out.println("pop -> " + (t == null ? "false (pilha vazia) " : "t=" + t + " ") + pilha);
        }
    }

    private static void exercicio4() {
        PilhaDinamica pilha = new PilhaDinamica();
        System.out.println("Pilha vazia: " + pilha);
        for (int x = 1; x <= 5; x++) {
            pilha.push(x);
            System.out.println("push(" + x + ") -> " + pilha);
        }
        for (int i = 0; i < 6; i++) {
            Integer t = pilha.pop();
            System.out.println("pop -> " + (t == null ? "false (pilha vazia) " : "t=" + t + " ") + pilha);
        }
    }

    private static void exercicio5() {
        System.out.println("fatorial(4) recursivo = " + FatorialPilha.fatorialRecursivo(4));
        System.out.println("\nfatorial(4) iterativo com pilha (estado da pilha a cada passo):");
        long ret = FatorialPilha.fatorialIterativo(4, true);
        System.out.println("Resultado = " + ret);

        System.out.println("\nConferencia das duas versoes:");
        for (int n : new int[]{0, 1, 5, 10, 20}) {
            long r = FatorialPilha.fatorialRecursivo(n);
            long i = FatorialPilha.fatorialIterativo(n, false);
            System.out.printf("n=%2d | recursivo=%d | iterativo=%d | iguais=%b%n", n, r, i, r == i);
        }
    }

    private static void exercicio6() {
        FilaEstatica fila = new FilaEstatica(5);
        System.out.println("Fila vazia: " + fila);
        for (int x = 1; x <= 5; x++) {
            fila.enqueue(x);
            System.out.println("enqueue(" + x + ") -> " + fila);
        }
        System.out.println("enqueue(6) -> " + (fila.enqueue(6) ? "true" : "false (fila cheia)"));
        for (int i = 0; i < 2; i++) {
            Integer v = fila.dequeue();
            System.out.println("dequeue -> " + v + " | " + fila);
        }
        for (int x = 6; x <= 7; x++) {
            fila.enqueue(x);
            System.out.println("enqueue(" + x + ") -> " + fila + "  (deu a volta)");
        }
        while (true) {
            Integer v = fila.dequeue();
            if (v == null) {
                System.out.println("dequeue -> false (fila vazia) | " + fila);
                break;
            }
            System.out.println("dequeue -> " + v + " | " + fila);
        }
    }

    private static void exercicio7() {
        FilaDinamica fila = new FilaDinamica();
        System.out.println("Fila vazia: " + fila);
        for (int x = 1; x <= 4; x++) {
            fila.enqueue(x);
            System.out.println("enqueue(" + x + ") -> " + fila);
        }
        for (int i = 0; i < 5; i++) {
            Integer v = fila.dequeue();
            System.out.println("dequeue -> " + (v == null ? "false (fila vazia)" : v) + " | " + fila);
        }
    }

    private static void exercicio8() {
        int[] chaves = {30, 10, 50, 20, 40};
        FilaPrioridadeNaoOrdenada naoOrdenada = new FilaPrioridadeNaoOrdenada();
        FilaPrioridadeOrdenada ordenada = new FilaPrioridadeOrdenada();
        for (int c : chaves) {
            naoOrdenada.insere(c);
            ordenada.insere(c);
        }
        System.out.println("Nao ordenada: " + naoOrdenada);
        System.out.println("Ordenada:     " + ordenada);
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < chaves.length; i++) {
            s1.append(naoOrdenada.extrai()).append(' ');
            s2.append(ordenada.extrai()).append(' ');
        }
        System.out.println("Extracoes (nao ordenada): " + s1.toString().trim());
        System.out.println("Extracoes (ordenada):     " + s2.toString().trim());

        int n = 20_000;
        int[] aleatorias = new Random(42).ints(n, 0, 1_000_000).toArray();
        naoOrdenada = new FilaPrioridadeNaoOrdenada();
        ordenada = new FilaPrioridadeOrdenada();

        long t0 = System.nanoTime();
        for (int c : aleatorias) {
            naoOrdenada.insere(c);
        }
        long t1 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            naoOrdenada.extrai();
        }
        long t2 = System.nanoTime();
        for (int c : aleatorias) {
            ordenada.insere(c);
        }
        long t3 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            ordenada.extrai();
        }
        long t4 = System.nanoTime();

        System.out.printf("%nMedicao real com n=%d:%n", n);
        System.out.printf("Lista nao ordenada | insercoes=%8.3f ms | extracoes=%8.3f ms%n",
                (t1 - t0) / 1_000_000.0, (t2 - t1) / 1_000_000.0);
        System.out.printf("Lista ordenada     | insercoes=%8.3f ms | extracoes=%8.3f ms%n",
                (t3 - t2) / 1_000_000.0, (t4 - t3) / 1_000_000.0);
    }

    private static String descreverBusca(No p) {
        return p == null ? "NULL (nao pertence a lista)" : "no com chave " + p.chave;
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
