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
            System.out.println("Opcao invalida: '" + argumento + "'. Informe um numero de 0 a 10.");
        } catch (IllegalArgumentException e) {
            System.out.println("Entrada invalida: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== P2 — Estruturas de Dados na Linha de Producao (BigO Motors) ===");
        System.out.println("1  - Fila (entrada de veiculos)");
        System.out.println("2  - Pilha (retrabalho)");
        System.out.println("3  - Lista ligada (componentes)");
        System.out.println("4  - Arvore (montagem sistema/subsistema)");
        System.out.println("5  - Arvore binaria (checkpoint pass/fail)");
        System.out.println("6  - Arvore binaria de busca (peca por codigo)");
        System.out.println("7  - Arvore AVL (BST degenerada x AVL)");
        System.out.println("8  - Grafo (setores interligados)");
        System.out.println("9  - BFS (distribuicao por proximidade)");
        System.out.println("10 - DFS (investigacao retroativa de falha)");
        System.out.println("0  - Sair");
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
            case 7 -> questao7();
            case 8 -> questao8();
            case 9 -> questao9();
            case 10 -> questao10();
            case 0 -> System.out.println("Programa encerrado.");
            default -> System.out.println("Opcao inexistente.");
        }
    }

    private static void exibirDescricao(int opcao) {
        switch (opcao) {
            case 1 -> System.out.println("""

                    ---------------------------------------------------------
                    1) Questao 1 — Fila (Queue): entrada de veiculos
                    ---------------------------------------------------------
                    Estrutura: Fila (FIFO) com ArrayDeque.
                    Por que: a ordem de chegada deve ser mantida — o
                    primeiro veiculo que entra e o primeiro a ser montado.
                    Custo: enqueue e dequeue sao O(1).
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Questao 2 — Pilha (Stack): retrabalho
                    ---------------------------------------------------------
                    Estrutura: Pilha (LIFO) com ArrayDeque.
                    Por que: o retrabalho retoma exatamente o ultimo defeito
                    tratado, como o botao "desfazer" de um editor.
                    Custo: push e pop sao O(1).
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Questao 3 — Lista ligada: componentes do veiculo
                    ---------------------------------------------------------
                    Estrutura: Lista ligada (LinkedList).
                    Por que: o conjunto de componentes muda de tamanho com
                    frequencia; a lista ligada evita realocar um array
                    inteiro a cada mudanca.
                    Custo: inserir/remover em posicao ja localizada e O(1);
                    buscar um componente especifico e O(n).
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Questao 4 — Arvore: decomposicao do veiculo
                    ---------------------------------------------------------
                    Estrutura: Arvore (Tree).
                    Por que: veiculo -> sistemas -> pecas e uma decomposicao
                    hierarquica classica (relacao pai-filho).
                    Custo: percorrer a arvore inteira e O(n) nos; localizar
                    um subsistema a partir da raiz e O(altura da arvore).
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Questao 5 — Arvore binaria: checkpoint pass/fail
                    ---------------------------------------------------------
                    Estrutura: Arvore binaria.
                    Por que: cada checkpoint tem no maximo 2 saidas
                    (aprovado/reprovado), e uma reprovacao pode se
                    desdobrar em outra decisao binaria.
                    Custo: percorrer do topo ate uma folha e O(altura da
                    arvore de decisao).
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Questao 6 — Arvore binaria de busca (BST): peca por
                    codigo
                    ---------------------------------------------------------
                    Estrutura: BST.
                    Por que: cada peca tem um codigo unico; a BST mantem os
                    codigos ordenados e permite busca eficiente mesmo com o
                    inventario mudando.
                    Custo: busca, insercao e remocao sao O(log n) se
                    balanceada, O(n) se degenerar.
                    ---------------------------------------------------------""");
            case 7 -> System.out.println("""

                    ---------------------------------------------------------
                    7) Questao 7 — Arvore AVL: eficiencia em grande volume
                    ---------------------------------------------------------
                    Estrutura: AVL.
                    Por que: inserir codigos em ordem crescente numa BST
                    comum degenera a arvore em uma lista (busca vira O(n));
                    a AVL rebalanceia automaticamente apos cada insercao.
                    Custo: busca, insercao e remocao sao O(log n)
                    garantido, mesmo no pior padrao de entrada.
                    ---------------------------------------------------------""");
            case 8 -> System.out.println("""

                    ---------------------------------------------------------
                    8) Questao 8 — Grafo: fabrica como sistema interligado
                    ---------------------------------------------------------
                    Estrutura: Grafo.
                    Por que: os setores se conectam de forma arbitraria (nao
                    hierarquica) e podem formar ciclos — so um grafo modela
                    isso corretamente.
                    Custo: percorrer o grafo inteiro (BFS/DFS) e O(V+E).
                    ---------------------------------------------------------""");
            case 9 -> System.out.println("""

                    ---------------------------------------------------------
                    9) Questao 9 — BFS: distribuicao de pecas por
                    proximidade
                    ---------------------------------------------------------
                    Algoritmo: Busca em largura (BFS).
                    Por que: BFS visita primeiro tudo que esta a 1 rota de
                    distancia do deposito, depois a 2 rotas, e assim por
                    diante — a ordem certa para entregar por proximidade.
                    Custo: O(V+E).
                    ---------------------------------------------------------""");
            case 10 -> System.out.println("""

                    ---------------------------------------------------------
                    10) Questao 10 — DFS: investigacao retroativa de falha
                    ---------------------------------------------------------
                    Algoritmo: Busca em profundidade (DFS).
                    Por que: a investigacao segue um unico caminho (o
                    historico do veiculo) ate achar a origem — precisa ir
                    fundo em um caminho, nao explorar por camadas.
                    Custo: O(V+E) no pior caso.
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void questao1() {
        System.out.println("Fila (FIFO) — ordem de chegada preservada:");
        FilaVeiculos.demonstrar(new int[]{1, 2, 3, 4, 5, 6});
    }

    private static void questao2() {
        System.out.println("Pilha (LIFO) — retrabalho retoma o ultimo defeito:");
        PilhaRetrabalho.demonstrar(new int[]{7, 8, 9});
    }

    private static void questao3() {
        System.out.println("Lista ligada — componentes do veiculo:");
        ListaComponentes.demonstrar();
    }

    private static void questao4() {
        System.out.println("Arvore — decomposicao do veiculo:");
        ArvoreMontagem.No veiculo = ArvoreMontagem.montarExemplo();
        ArvoreMontagem.imprimir(veiculo, 0);
        System.out.println("Total de nos na arvore: " + ArvoreMontagem.contarNos(veiculo));
    }

    private static void questao5() {
        System.out.println("Arvore binaria — decisao pass/fail:");
        ArvoreBinariaDecisao.Decisao raiz = ArvoreBinariaDecisao.montarExemplo();
        System.out.println("Veiculo cod 9 (reprova, depois recuperavel): "
                + ArvoreBinariaDecisao.caminhoReprovadoRecuperavel(raiz));
        System.out.println("Veiculo cod 0 (reprova, depois irrecuperavel): "
                + ArvoreBinariaDecisao.caminhoReprovadoIrrecuperavel(raiz));
        System.out.println("Veiculo aprovado direto: " + ArvoreBinariaDecisao.caminhoAprovadoDireto(raiz));
    }

    private static void questao6() {
        System.out.println("Arvore binaria de busca (BST) — peca por codigo:");
        int[] codigos = {5, 2, 8, 1, 4, 7, 9};
        BST.No raiz = null;
        for (int c : codigos) {
            raiz = BST.inserir(raiz, c);
        }
        int[] buscas = {7, 1, 3};
        for (int alvo : buscas) {
            boolean encontrado = BST.buscar(raiz, alvo);
            System.out.printf("busca(%d) -> encontrado=%b, comparacoes=%d%n", alvo, encontrado, BST.comparacoes);
        }
    }

    private static void questao7() {
        System.out.println("Arvore AVL — BST comum degenerada x AVL rebalanceada:");
        int[] codigos = {1, 2, 3, 4, 5, 6, 7, 9};

        BST.No raizBST = null;
        for (int c : codigos) {
            raizBST = BST.inserir(raizBST, c);
        }
        System.out.println("BST comum, insercoes em ordem crescente " + java.util.Arrays.toString(codigos));
        System.out.println("Altura da BST comum: " + BST.altura(raizBST) + " (degenerada, busca O(n))");

        ArvoreAVL.No raizAVL = null;
        for (int c : codigos) {
            raizAVL = ArvoreAVL.inserir(raizAVL, c);
        }
        System.out.println("Altura da AVL apos as mesmas insercoes: " + ArvoreAVL.altura(raizAVL)
                + " (mantida O(log n))");
    }

    private static void questao8() {
        System.out.println("Grafo — setores da fabrica interligados por rotas:");
        GrafoSetores grafo = GrafoSetores.montarExemplo();
        System.out.println("V (setores) = " + grafo.totalVertices());
        System.out.println("E (rotas) = " + grafo.totalArestas());
        for (String setor : grafo.setores()) {
            System.out.println(setor + " -> " + grafo.vizinhos(setor));
        }
    }

    private static void questao9() {
        System.out.println("BFS — distribuicao por proximidade a partir do deposito central:");
        GrafoSetores grafo = new GrafoSetores();
        grafo.addRota("DepositoCentral", "Solda");
        grafo.addRota("DepositoCentral", "Pintura");
        grafo.addRota("DepositoCentral", "Montagem");
        grafo.addRota("Solda", "Estacao1");
        grafo.addRota("Pintura", "Estacao2");
        grafo.addRota("Pintura", "Estacao3");
        grafo.addRota("Montagem", "Estacao4");

        Map<String, Integer> distancias = BFSDistribuicao.distanciasAPartirDe(grafo, "DepositoCentral");
        for (Map.Entry<String, Integer> e : distancias.entrySet()) {
            System.out.println("nivel " + e.getValue() + ": " + e.getKey());
        }
    }

    private static void questao10() {
        System.out.println("DFS — investigacao retroativa de falha a partir de Expedicao:");
        GrafoSetores grafo = new GrafoSetores();
        grafo.addRota("Expedicao", "InspecaoFinal");
        grafo.addRota("InspecaoFinal", "Montagem");
        grafo.addRota("Montagem", "Pintura");
        grafo.addRota("Pintura", "Solda");

        for (String etapa : DFSInvestigacao.investigar(grafo, "Expedicao")) {
            System.out.println("investigando: " + etapa);
        }
        System.out.println("Causa raiz identificada: Solda");
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
