import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    /** BST do exemplo dos slides (35, 14, 80, 12, 22, 48, 95, 20, 37). */
    private static final int[] CHAVES_BST = {35, 14, 80, 12, 22, 48, 95, 20, 37};
    /** Árvore usada nos slides de percursos e de impressão. */
    private static final int[] CHAVES_PERCURSO = {50, 40, 60, 35, 45, 55, 65};

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
        System.out.println("\n=== Aula 07 — Árvores Binárias e seus Percursos ===");
        System.out.println("1 - Raiz, folha, nó interno, nível e altura");
        System.out.println("2 - Árvore de expressão (a * b) + (c / (d + e))");
        System.out.println("3 - Tipos de árvores binárias e limites da altura");
        System.out.println("4 - Árvore binária de busca: inserção e busca");
        System.out.println("5 - Árvore binária de busca: remoção (3 situações)");
        System.out.println("6 - Percursos pré-ordem, in-ordem, pós-ordem e largura");
        System.out.println("7 - Pré-ordem iterativo com pilha explícita");
        System.out.println("8 - Impressão da árvore (parênteses e lateral)");
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
                    1) Terminologia: raiz, folha, no interno, nivel e altura
                    ---------------------------------------------------------
                    O que e: para cada no da BST do exemplo dos slides
                    (35, 14, 80, 12, 22, 48, 95, 20, 37) mostra o tipo do
                    no, o nivel (arestas ate a raiz) e a altura (arestas do
                    caminho mais longo ate uma folha).
                    O que observar: a raiz esta no nivel 0, folhas tem
                    altura 0 e a altura da arvore e a altura da raiz.
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Arvore de expressao (arvore de derivacao)
                    ---------------------------------------------------------
                    O que e: a expressao (a * b) + (c / (d + e)) com
                    operadores nos nos internos e operandos nas folhas.
                    O que observar: pre-ordem gera a notacao prefixa,
                    in-ordem a infixa e pos-ordem a pos-fixa; a avaliacao e
                    um percurso pos-ordem (filhos antes do operador).
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Tipos de arvores binarias e limites da altura
                    ---------------------------------------------------------
                    O que e: classificacao das quatro arvores dos slides
                    (estritamente binaria, quase completa, completa e
                    degenerada) e verificacao de floor(lg n) <= h <= n - 1.
                    O que observar: inserir 1..15 em ordem crescente gera
                    uma BST degenerada (h = n - 1), enquanto uma ordem
                    "balanceada" gera h = floor(lg n).
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Arvore binaria de busca: insercao e busca
                    ---------------------------------------------------------
                    O que e: a mesma sequencia inserida com a versao
                    recursiva e com a versao iterativa (resultados
                    identicos) e buscas mostrando o caminho percorrido.
                    Complexidade: O(h) — O(n) no pior caso e O(lg n) no
                    caso medio.
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Arvore binaria de busca: remocao
                    ---------------------------------------------------------
                    O que e: remocao de um no folha (caso 1), de um no com
                    1 filho (caso 2) e de um no com 2 filhos (caso 3).
                    O que observar: no caso 3 a chave e substituida pelo
                    sucessor logico (menor valor da subarvore direita) e a
                    in-ordem continua ordenada apos cada remocao.
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Percursos em arvore binaria
                    ---------------------------------------------------------
                    O que e: pre-ordem (raiz, esq, dir), in-ordem (esq,
                    raiz, dir), pos-ordem (esq, dir, raiz) e largura (nivel
                    a nivel, com fila) na arvore 50, 40, 60, 35, 45, 55, 65.
                    O que observar: em uma BST a in-ordem sai ordenada.
                    Todos sao O(n).
                    ---------------------------------------------------------""");
            case 7 -> System.out.println("""

                    ---------------------------------------------------------
                    7) Pre-ordem iterativo
                    ---------------------------------------------------------
                    O que e: a recursao e substituida por uma pilha
                    explicita que "lembra" os pontos onde a busca desce.
                    O que observar: o filho direito e empilhado antes do
                    esquerdo, para que o esquerdo seja visitado primeiro.
                    O resultado e identico ao da versao recursiva.
                    ---------------------------------------------------------""");
            case 8 -> System.out.println("""

                    ---------------------------------------------------------
                    8) Impressao da arvore binaria
                    ---------------------------------------------------------
                    O que e: representacao por parenteses aninhados
                    (raiz (esq) (dir)) com pre-ordem, e a visualizacao
                    "deitada" (direita, raiz, esquerda) em que cada nivel
                    acrescenta um recuo de "-----".
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void exercicio1() {
        NoArvore raiz = ArvoreBinariaBusca.construir(CHAVES_BST);
        System.out.println("Arvore (parenteses): " + Percursos.parenteses(raiz));
        System.out.println("Nos em ordem de nivel:");
        for (int chave : Percursos.largura(raiz)) {
            NoArvore no = ArvoreBinariaBusca.busca(raiz, chave);
            String tipo = no == raiz ? "raiz"
                    : (no.esq == null && no.dir == null) ? "folha" : "no interno";
            System.out.printf("  %3d | %-10s | nivel=%d | altura=%d%n",
                    chave, tipo, nivel(raiz, chave), ArvoreBinariaBusca.altura(no));
        }
        System.out.println("Altura da arvore = " + ArvoreBinariaBusca.altura(raiz));
    }

    private static void exercicio2() {
        ArvoreExpressao.NoExpressao raiz = ArvoreExpressao.exemploDosSlides();
        System.out.println("Pre-ordem (prefixa):   " + ArvoreExpressao.prefixa(raiz));
        System.out.println("In-ordem (infixa):     " + ArvoreExpressao.infixa(raiz));
        System.out.println("Pos-ordem (pos-fixa):  " + ArvoreExpressao.posfixa(raiz));
        Map<String, Double> valores = Map.of("a", 2.0, "b", 3.0, "c", 10.0, "d", 1.0, "e", 4.0);
        System.out.println("Com a=2, b=3, c=10, d=1, e=4: (2 * 3) + (10 / (1 + 4)) = "
                + ArvoreExpressao.avaliar(raiz, valores));
    }

    private static void exercicio3() {
        System.out.println(TiposArvore.resumo("Estritamente binaria", TiposArvore.estritamenteBinaria()));
        System.out.println(TiposArvore.resumo("Quase completa", TiposArvore.quaseCompleta()));
        System.out.println(TiposArvore.resumo("Completa", TiposArvore.completa()));
        System.out.println(TiposArvore.resumo("Degenerada", TiposArvore.degenerada()));

        int n = 15;
        int[] crescente = new int[n];
        for (int i = 0; i < n; i++) {
            crescente[i] = i + 1;
        }
        int[] balanceada = TiposArvore.ordemBalanceada(n);
        NoArvore bstCrescente = ArvoreBinariaBusca.construir(crescente);
        NoArvore bstBalanceada = ArvoreBinariaBusca.construir(balanceada);
        System.out.println("\nBST com 1..15 inserido em ordem crescente:");
        System.out.println(TiposArvore.resumo("Ordem crescente", bstCrescente));
        System.out.println("BST com 1..15 inserido na ordem " + java.util.Arrays.toString(balanceada) + ":");
        System.out.println(TiposArvore.resumo("Ordem balanceada", bstBalanceada));
    }

    private static void exercicio4() {
        NoArvore recursiva = null;
        NoArvore iterativa = null;
        for (int c : CHAVES_BST) {
            recursiva = ArvoreBinariaBusca.insereRecursivo(recursiva, new NoArvore(c));
            iterativa = ArvoreBinariaBusca.insereIterativo(iterativa, new NoArvore(c));
        }
        String r = Percursos.parenteses(recursiva);
        String i = Percursos.parenteses(iterativa);
        System.out.println("Insercao recursiva: " + r);
        System.out.println("Insercao iterativa: " + i);
        System.out.println("Mesma arvore: " + r.equals(i));
        for (int k : new int[]{20, 37, 95, 50}) {
            NoArvore achou = ArvoreBinariaBusca.busca(recursiva, k);
            System.out.println("busca(" + k + ") -> " + (achou == null ? "NULL" : "encontrado")
                    + " | caminho: " + ArvoreBinariaBusca.caminhoBusca(recursiva, k));
        }
    }

    private static void exercicio5() {
        NoArvore raiz = ArvoreBinariaBusca.construir(CHAVES_BST);
        System.out.println("Arvore inicial: " + Percursos.parenteses(raiz));
        for (int k : new int[]{12, 22, 35, 99}) {
            String caso = ArvoreBinariaBusca.casoRemocao(raiz, k);
            raiz = ArvoreBinariaBusca.remove(raiz, k);
            System.out.println("\nremove(" + k + ") -> " + caso);
            System.out.println("  arvore:   " + Percursos.parenteses(raiz));
            System.out.println("  in-ordem: " + inOrdem(raiz));
        }
    }

    private static void exercicio6() {
        NoArvore raiz = ArvoreBinariaBusca.construir(CHAVES_PERCURSO);
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        Percursos.preOrdem(raiz, pre);
        Percursos.inOrdem(raiz, in);
        Percursos.posOrdem(raiz, pos);
        System.out.println("Pre-ordem:          " + pre);
        System.out.println("In-ordem:           " + in + " (ordenado)");
        System.out.println("Pos-ordem:          " + pos);
        System.out.println("Largura (por nivel): " + Percursos.largura(raiz));
    }

    private static void exercicio7() {
        NoArvore raiz = ArvoreBinariaBusca.construir(CHAVES_PERCURSO);
        System.out.println("push 50 -> pilha: [50]");
        List<Integer> iterativo = Percursos.preOrdemIterativo(raiz, true);
        List<Integer> recursivo = new ArrayList<>();
        Percursos.preOrdem(raiz, recursivo);
        System.out.println("Pre-ordem iterativo: " + iterativo);
        System.out.println("Pre-ordem recursivo: " + recursivo);
        System.out.println("Iguais: " + iterativo.equals(recursivo));
    }

    private static void exercicio8() {
        NoArvore raiz = ArvoreBinariaBusca.construir(CHAVES_PERCURSO);
        System.out.println("Parenteses aninhados (pre-ordem):");
        System.out.println(Percursos.parenteses(raiz));
        System.out.println("\nVisualizacao lateral (in-ordem direita-raiz-esquerda):");
        StringBuilder saida = new StringBuilder();
        Percursos.lateral(raiz, 0, saida);
        System.out.print(saida);
    }

    private static int nivel(NoArvore raiz, int chave) {
        return ArvoreBinariaBusca.caminhoBusca(raiz, chave).size() - 1;
    }

    private static List<Integer> inOrdem(NoArvore raiz) {
        List<Integer> visita = new ArrayList<>();
        Percursos.inOrdem(raiz, visita);
        return visita;
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
