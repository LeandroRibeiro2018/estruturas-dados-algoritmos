import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    /** Ordem de inserção do exemplo dos slides (gera RD, RE, RED e RDE). */
    private static final int[] SEQUENCIA_AVL = {70, 50, 40, 80, 90, 15, 25, 45, 42};
    /** Heap de máximo usado nos slides. */
    private static final int[] HEAP_SLIDES = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};

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
        System.out.println("\n=== Aula 08 — Árvores Balanceadas e Filas de Prioridade ===");
        System.out.println("1 - Fator de balanceamento: AVL x não AVL");
        System.out.println("2 - Inserção na AVL passo a passo (RD, RE, RED, RDE)");
        System.out.println("3 - BST comum x AVL com chaves crescentes");
        System.out.println("4 - Remoção na AVL com rebalanceamento");
        System.out.println("5 - Heap de máximo no vetor (pai k/2, filhos 2k e 2k+1)");
        System.out.println("6 - Inserção no heap (subir / heapify up)");
        System.out.println("7 - Remoção no heap (descer / heapify down)");
        System.out.println("8 - Heap Sort (construção subindo x descendo)");
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
                    1) Propriedade AVL e fator de balanceamento
                    ---------------------------------------------------------
                    O que e: as duas arvores do slide de exemplos, com o
                    FB = hE - hD de cada no.
                    O que observar: na primeira todo FB esta em {-1, 0, 1}
                    (AVL); na segunda o no 6 tem FB = 2 (nao AVL).
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Insercao na arvore AVL
                    ---------------------------------------------------------
                    O que e: insercao de 70, 50, 40, 80, 90, 15, 25, 45, 42
                    seguindo o pseudocodigo dos slides.
                    O que observar: "reta" (esq+esq / dir+dir) pede rotacao
                    simples (RD / RE); "curva" (esq+dir / dir+esq) pede
                    rotacao dupla (RED / RDE). Cada rotacao e O(1).
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Por que balancear?
                    ---------------------------------------------------------
                    O que e: as mesmas chaves crescentes inseridas em uma
                    BST comum e em uma AVL, para n = 15, 1.000 e 10.000.
                    O que observar: a BST degenera em lista (h = n - 1),
                    enquanto a AVL mantem h = O(log n).
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Remocao na arvore AVL
                    ---------------------------------------------------------
                    O que e: remocao como na BST (0/1 filho ou sucessor
                    logico para 2 filhos), aplicando rotacao no caminho de
                    volta quando algum FB vira +2 ou -2.
                    O que observar: a arvore continua AVL e a in-ordem
                    continua ordenada apos cada remocao.
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Heap de maximo em vetor
                    ---------------------------------------------------------
                    O que e: o heap 16, 14, 10, 8, 7, 9, 3, 2, 4, 1 guardado
                    nas posicoes 1..n de um vetor; a arvore e implicita
                    nos indices (pai = floor(k/2), filhos 2k e 2k + 1).
                    Cuidado: heap nao e arvore binaria de busca.
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Insercao no heap
                    ---------------------------------------------------------
                    O que e: o novo no (50) entra sempre no final do heap e
                    sobe trocando com o pai enquanto for maior que ele.
                    Complexidade: O(log n).
                    ---------------------------------------------------------""");
            case 7 -> System.out.println("""

                    ---------------------------------------------------------
                    7) Remocao no heap
                    ---------------------------------------------------------
                    O que e: remove-se sempre a raiz (o maximo): o ultimo
                    no ocupa a raiz e desce trocando com o maior filho.
                    Complexidade: O(log n). Extrair o maximo repetidamente
                    devolve as chaves em ordem decrescente.
                    ---------------------------------------------------------""");
            case 8 -> System.out.println("""

                    ---------------------------------------------------------
                    8) Heap Sort
                    ---------------------------------------------------------
                    O que e: o vetor passa a condicao de heap (construcao
                    descendo, a partir do ultimo pai) e a raiz e trocada
                    com o ultimo elemento n vezes. Complexidade O(n log n).
                    O que observar: construir "descendo" faz bem menos
                    trocas que construir "subindo" para o mesmo vetor.
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void exercicio1() {
        NoAVL avl = construirSemBalancear(3, 1, 6, 2, 4, 7, 5);
        NoAVL naoAvl = construirSemBalancear(3, 1, 6, 2, 4, 5);
        System.out.println("Arvore 1: " + ArvoreAVL.parenteses(avl));
        System.out.println("  e AVL? " + ArvoreAVL.ehAVL(avl));
        System.out.println("Arvore 2: " + ArvoreAVL.parenteses(naoAvl));
        System.out.println("  e AVL? " + ArvoreAVL.ehAVL(naoAvl));
    }

    private static void exercicio2() {
        NoAVL raiz = null;
        for (int chave : SEQUENCIA_AVL) {
            ArvoreAVL.log.clear();
            raiz = ArvoreAVL.insere(raiz, chave);
            String rotacao = ArvoreAVL.log.isEmpty() ? "sem rotacao" : String.join(", ", ArvoreAVL.log);
            System.out.printf("insere(%d) -> %-26s | %s%n", chave, rotacao, parenteses(raiz));
        }
        StringBuilder saida = new StringBuilder();
        ArvoreAVL.lateral(raiz, 0, saida);
        System.out.println("\nArvore final (deitada):");
        System.out.print(saida);
        System.out.println("Altura = " + ArvoreAVL.altura(raiz) + " | e AVL? " + ArvoreAVL.ehAVL(raiz));
    }

    private static void exercicio3() {
        for (int n : new int[]{15, 1_000, 10_000}) {
            NoAVL bst = null;
            NoAVL avl = null;
            for (int chave = 1; chave <= n; chave++) {
                if (n <= 1_000) { // a BST degenerada usa recursão de profundidade n
                    bst = ArvoreAVL.insereSemBalancear(bst, chave);
                }
                avl = ArvoreAVL.insere(avl, chave);
            }
            String hBst = bst == null ? (n - 1) + " (n - 1, sem montar a lista)" : String.valueOf(ArvoreAVL.altura(bst));
            int lgPiso = 31 - Integer.numberOfLeadingZeros(n);
            System.out.printf("n=%6d | BST comum: h=%s | AVL: h=%d | floor(lg n)=%d%n",
                    n, hBst, ArvoreAVL.altura(avl), lgPiso);
        }
        ArvoreAVL.log.clear();
    }

    private static void exercicio4() {
        NoAVL raiz = null;
        for (int chave : SEQUENCIA_AVL) {
            raiz = ArvoreAVL.insere(raiz, chave);
        }
        System.out.println("Arvore inicial: " + parenteses(raiz));
        for (int k : new int[]{15, 80, 50, 99}) {
            ArvoreAVL.log.clear();
            raiz = ArvoreAVL.remove(raiz, k);
            String rotacao = ArvoreAVL.log.isEmpty() ? "sem rotacao" : String.join(", ", ArvoreAVL.log);
            List<Integer> in = new ArrayList<>();
            ArvoreAVL.inOrdem(raiz, in);
            System.out.println("\nremove(" + k + ") -> " + rotacao);
            System.out.println("  arvore:   " + parenteses(raiz));
            System.out.println("  in-ordem: " + in + " | e AVL? " + ArvoreAVL.ehAVL(raiz));
        }
    }

    private static void exercicio5() {
        HeapMaximo heap = HeapMaximo.deVetor(HEAP_SLIDES);
        System.out.println("Vetor (posicoes 1.." + heap.tamanho() + "): " + heap);
        System.out.println("E heap de maximo? " + heap.ehHeap());
        for (int k = 1; k <= heap.tamanho(); k++) {
            String esq = 2 * k <= heap.tamanho() ? String.valueOf(heap.valor(2 * k)) : "-";
            String dir = 2 * k + 1 <= heap.tamanho() ? String.valueOf(heap.valor(2 * k + 1)) : "-";
            String pai = k > 1 ? String.valueOf(heap.valor(k / 2)) : "-";
            System.out.printf("k=%2d valor=%2d | pai[k/2]=%2s | esq[2k]=%2s | dir[2k+1]=%2s%n",
                    k, heap.valor(k), pai, esq, dir);
        }
        System.out.println("Parenteses: " + heap.parenteses(1));
        System.out.println("O vetor ordenado [1, 2, 3, 4, 7, 8, 9, 10, 14, 16] seria BST in-ordem,"
                + " mas nao e heap de maximo: " + HeapMaximo.deVetor(new int[]{1, 2, 3, 4, 7, 8, 9, 10, 14, 16}).ehHeap());
    }

    private static void exercicio6() {
        HeapMaximo heap = HeapMaximo.deVetor(HEAP_SLIDES);
        System.out.println("Antes:  " + heap);
        heap.insere(50);
        System.out.println("insere(50) -> " + heap + " | trocas=" + heap.trocas());
        System.out.println("E heap? " + heap.ehHeap());
        StringBuilder saida = new StringBuilder();
        heap.lateral(1, 0, saida);
        System.out.println("\nVisualizacao bidimensional:");
        System.out.print(saida);
    }

    private static void exercicio7() {
        HeapMaximo heap = HeapMaximo.deVetor(HEAP_SLIDES);
        System.out.println("Antes:  " + heap);
        Integer max = heap.remove();
        System.out.println("remove() -> " + max + " | " + heap + " | E heap? " + heap.ehHeap());
        System.out.println("Parenteses: " + heap.parenteses(1));
        StringBuilder extracoes = new StringBuilder();
        while (heap.tamanho() > 0) {
            extracoes.append(heap.remove()).append(' ');
        }
        System.out.println("Extraindo o restante: " + extracoes.toString().trim());
    }

    private static void exercicio8() {
        int[] vetor = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        System.out.println("Vetor original:   " + Arrays.toString(vetor));
        HeapMaximo subindo = HeapMaximo.deVetor(vetor);
        subindo.construirHeapSubindo();
        HeapMaximo descendo = HeapMaximo.deVetor(vetor);
        descendo.construirHeapDescendo();
        System.out.println("Heap (subindo):   " + subindo + " | trocas=" + subindo.trocas());
        System.out.println("Heap (descendo):  " + descendo + " | trocas=" + descendo.trocas());
        System.out.println("Heap Sort:        " + Arrays.toString(HeapMaximo.heapSort(vetor)));

        System.out.println("\nConstrucao do heap com chaves crescentes (pior caso para subir):");
        for (int n : new int[]{1_000, 10_000, 100_000}) {
            int[] crescente = new int[n];
            for (int i = 0; i < n; i++) {
                crescente[i] = i;
            }
            HeapMaximo s = HeapMaximo.deVetor(crescente);
            s.construirHeapSubindo();
            HeapMaximo d = HeapMaximo.deVetor(crescente);
            d.construirHeapDescendo();
            System.out.printf("n=%7d | trocas subindo=%9d | trocas descendo=%7d%n", n, s.trocas(), d.trocas());
        }

        int n = 200_000;
        int[] aleatorio = new Random(42).ints(n, 0, 1_000_000).toArray();
        long inicio = System.nanoTime();
        int[] ordenado = HeapMaximo.heapSort(aleatorio);
        long fim = System.nanoTime();
        int[] esperado = aleatorio.clone();
        Arrays.sort(esperado);
        System.out.printf("%nHeap Sort com n=%d aleatorios: %.3f ms | ordenado corretamente: %b%n",
                n, (fim - inicio) / 1_000_000.0, Arrays.equals(ordenado, esperado));
    }

    private static NoAVL construirSemBalancear(int... chaves) {
        NoAVL raiz = null;
        for (int c : chaves) {
            raiz = ArvoreAVL.insereSemBalancear(raiz, c);
        }
        return raiz;
    }

    /** Parênteses compactos (sem FB) para acompanhar as rotações. */
    private static String parenteses(NoAVL r) {
        if (r == null) {
            return "()";
        }
        if (r.esq == null && r.dir == null) {
            return String.valueOf(r.chave);
        }
        return r.chave + "(" + (r.esq == null ? "-" : parenteses(r.esq)) + ","
                + (r.dir == null ? "-" : parenteses(r.dir)) + ")";
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
