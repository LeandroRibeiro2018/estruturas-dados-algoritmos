import java.util.Arrays;
import java.util.Map;
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
        System.out.println("\n=== Aula 04 — Análise Assintótica e Algoritmos Recursivos ===");
        System.out.println("1 - Mapeando um problema recursivo (pastas e subpastas)");
        System.out.println("2 - Encontrando o caso base (alçadas de aprovação)");
        System.out.println("3 - Dividir para conquistar (respostas de pesquisa)");
        System.out.println("4 - A recorrência representa o problema real? (Método Mestre)");
        System.out.println("5 - Árvore de recursão de T(n) = 2T(n/2) + n");
        System.out.println("6 - MergeSort ou QuickSort: qual usar?");
        System.out.println("7 - Desconfie da resposta da IAG (verificação independente)");
        System.out.println("8 - Funciona hoje. E amanhã? (10 mil -> 1 milhão)");
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
                    1) Exercicio 1 — Mapeando um problema recursivo
                    ---------------------------------------------------------
                    O que e: calcular o tamanho total de uma pasta.
                    Problema original -> tamanho da pasta raiz.
                    Subproblemas      -> tamanho de cada subpasta.
                    Caso base         -> pasta sem subpastas (soma direta
                                         dos arquivos).
                    O problema fica menor a cada etapa porque cada chamada
                    desce um nivel de uma hierarquia finita.
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Exercicio 2 — Encontrando o caso base
                    ---------------------------------------------------------
                    O que e: aprovar(valor, nivel) encaminha a despesa para
                    o nivel acima ate alguem ter alcada.
                    Casos base: o valor cabe na alcada do nivel atual, ou o
                    nivel e o ultimo (Diretoria). O nivel sempre aumenta e
                    e limitado, entao o processo termina.
                    O que observar: sem caso base (e sem progresso) a pilha
                    de chamadas estoura.
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Exercicio 3 — Dividir para conquistar
                    ---------------------------------------------------------
                    Fluxo: dividir (duas metades) -> resolver partes
                    (contagem independente) -> combinar (somar contagens).
                    Vantagem: partes independentes podem ser paralelizadas.
                    Desvantagem: custo extra de dividir/combinar; para uma
                    simples contagem nao ha ganho assintotico (O(n) nos dois).
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Exercicio 4 — A recorrencia representa o problema?
                    ---------------------------------------------------------
                    Merge Sort: n = elementos a ordenar; a = 2 subproblemas;
                    b = 2 (cada um com metade); f(n) = n (intercalar).
                    Simplificacao: a recorrencia real usa ceil(n/2) e
                    floor(n/2) e constantes; a forma 2T(n/2) + n vale
                    exatamente so para n potencia de 2.
                    Em seguida o Teorema Mestre e aplicado aos exemplos.
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Exercicio 5 — Arvore de recursao
                    ---------------------------------------------------------
                    O que e: niveis da arvore de T(n) = 2T(n/2) + n para
                    n = 1024 (nos, tamanho de cada no, custo do nivel).
                    O que observar: todo nivel custa n e ha lg n + 1
                    niveis, logo T(n) = n lg n + n = Theta(n lg n).
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Exercicio 6 — MergeSort ou QuickSort?
                    ---------------------------------------------------------
                    O que e: os dois algoritmos dos slides em tres perfis de
                    dados (aleatorio, ja ordenado e com muitas repeticoes),
                    medindo tempo, comparacoes e profundidade da recursao.
                    O que observar: o QuickSort com pivo = ultimo elemento
                    degrada para O(n^2) justamente em dados ja ordenados.
                    ---------------------------------------------------------""");
            case 7 -> System.out.println("""

                    ---------------------------------------------------------
                    7) Exercicio 7 — Desconfie da resposta da IAG
                    ---------------------------------------------------------
                    O que e: verificacao independente, com os valores
                    exatos da recorrencia, das solucoes do material e de
                    uma resposta tipica de IAG.
                    O que observar: as constantes da prova por substituicao
                    e o particionamento do QuickSort precisam de correcao.
                    O resumo da conversa com a IAG e seu, nao do programa.
                    ---------------------------------------------------------""");
            case 8 -> System.out.println("""

                    ---------------------------------------------------------
                    8) Exercicio 8 — Uma solucao funciona hoje. E amanha?
                    ---------------------------------------------------------
                    O que e: medicao real do pior caso do QuickSort (O(n^2))
                    e do MergeSort (O(n lg n)) e projecao de 10.000 para
                    1.000.000 registros.
                    Licao: a decisao depende da trajetoria de crescimento,
                    nao do tempo observado hoje.
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void exercicio1() {
        StringBuilder trace = new StringBuilder();
        long total = EstruturaPastas.tamanhoTotal(EstruturaPastas.exemplo(), 0, trace);
        System.out.print(trace);
        System.out.println("Tamanho total = " + total + " KB");
    }

    private static void exercicio2() {
        for (double valor : new double[]{800, 25_000, 2_000_000}) {
            StringBuilder trace = new StringBuilder();
            String resultado = AprovacaoHierarquica.aprovar(valor, 0, trace);
            System.out.printf("Despesa de %.0f:%n%s  -> %s%n", valor, trace, resultado);
        }
        System.out.println("Sem caso base: " + AprovacaoHierarquica.demonstrarSemCasoBase());
    }

    private static void exercicio3() {
        String[] pequenas = DivisaoConquista.gerarRespostas(16, 7);
        StringBuilder trace = new StringBuilder();
        Map<String, Integer> r = DivisaoConquista.contarDividindo(pequenas, 0, pequenas.length, 4, 0, trace);
        System.out.print(trace);
        System.out.println("Combinado:  " + r);
        System.out.println("Sequencial: " + DivisaoConquista.contarSequencial(pequenas));

        String[] grandes = DivisaoConquista.gerarRespostas(2_000_000, 42);
        long t0 = System.nanoTime();
        Map<String, Integer> seq = DivisaoConquista.contarSequencial(grandes);
        long t1 = System.nanoTime();
        Map<String, Integer> dc = DivisaoConquista.contarDividindo(grandes, 0, grandes.length, 10_000, 0, null);
        long t2 = System.nanoTime();
        System.out.printf("%nn=2.000.000 | sequencial=%.1f ms | divisao e conquista=%.1f ms | mesmo resultado: %b%n",
                (t1 - t0) / 1e6, (t2 - t1) / 1e6, seq.equals(dc));
    }

    private static void exercicio4() {
        System.out.println("Merge Sort instrumentado (n potencia de 2):");
        for (int k : new int[]{4, 10, 16}) {
            int n = 1 << k;
            int[] v = new Random(1).ints(n).toArray();
            Ordenacao.mergeSort(v);
            System.out.printf("n=%6d | chamadas=%7d (2n-1=%7d) | comparacoes=%8d <= n lg n=%8d | T(n)=n lg n + n=%8d%n",
                    n, Ordenacao.chamadas, 2L * n - 1, Ordenacao.comparacoes, (long) n * k,
                    Recorrencia.tMergeSort(n));
        }
        System.out.println("\nTeorema Mestre:");
        System.out.println("Exemplo (a) 4T(n/2) + n:\n  " + MetodoMestre.resolver(4, 2, 1, 0));
        System.out.println("Exemplo (b) T(2n/3) + 1:\n  " + MetodoMestre.resolver(1, 1.5, 0, 0));
        System.out.println("Exemplo (c) 3T(n/4) + n lg n:\n  " + MetodoMestre.resolver(3, 4, 1, 1));
        System.out.println("Merge Sort 2T(n/2) + n:\n  " + MetodoMestre.resolver(2, 2, 1, 0));
        System.out.println("2T(n/2) + n lg n:\n  " + MetodoMestre.resolver(2, 2, 1, 1));
        System.out.println("T(n-1) + n (pior caso do QuickSort): nao tem a forma aT(n/b); "
                + "resolvido pelo metodo iterativo -> Theta(n^2).");
    }

    private static void exercicio5() {
        int n = 1024;
        int niveis = (int) Recorrencia.lg(n);
        System.out.println("Nivel | nos  | tamanho de cada no | custo do nivel");
        long total = 0;
        for (int i = 0; i <= niveis; i++) {
            long nos = 1L << i;
            long tamanho = n >> i;
            long custo = nos * tamanho;
            total += custo;
            if (i <= 4 || i == niveis) {
                System.out.printf("%5d | %4d | %18d | %d%n", i, nos, tamanho, custo);
            } else if (i == 5) {
                System.out.println("  ... | (cada nivel continua custando " + n + ")");
            }
        }
        System.out.printf("%nNiveis = lg n + 1 = %d | soma = %d | n lg n + n = %d | T(n) exato = %d%n",
                niveis + 1, total, (long) n * niveis + n, Recorrencia.tMergeSort(n));
    }

    private static void exercicio6() {
        int n = 20_000;
        Random r = new Random(42);
        int[][] perfis = {
                r.ints(n, 0, 1_000_000).toArray(),
                java.util.stream.IntStream.range(0, n).toArray(),
                r.ints(n, 0, 10).toArray()
        };
        String[] nomes = {"aleatorio", "ja ordenado", "muitas repeticoes (0..9)"};
        System.out.printf("n=%d%n", n);
        for (int i = 0; i < perfis.length; i++) {
            medir("MergeSort", nomes[i], perfis[i].clone(), true);
            medir("QuickSort", nomes[i], perfis[i].clone(), false);
        }
    }

    private static void medir(String algoritmo, String perfil, int[] v, boolean merge) {
        long[] resultado = new long[4];
        Ordenacao.comPilhaGrande(() -> {
            long t0 = System.nanoTime();
            if (merge) {
                Ordenacao.mergeSort(v);
            } else {
                Ordenacao.quickSort(v);
            }
            resultado[0] = System.nanoTime() - t0;
            resultado[1] = Ordenacao.comparacoes;
            resultado[2] = Ordenacao.profundidadeMaxima;
            resultado[3] = Ordenacao.ordenado(v) ? 1 : 0;
        });
        System.out.printf("%-9s | %-24s | %9.2f ms | comparacoes=%10d | profundidade=%6d | ok=%b%n",
                algoritmo, perfil, resultado[0] / 1e6, resultado[1], resultado[2], resultado[3] == 1);
    }

    private static void exercicio7() {
        System.out.println("Recorrencia: T(1)=1, T(n)=T(ceil(n/2)) + T(floor(n/2)) + n");
        System.out.println("T(2)=" + Recorrencia.tMergeSort(2) + ", T(3)=" + Recorrencia.tMergeSort(3)
                + ", T(4)=" + Recorrencia.tMergeSort(4) + ", T(1024)=" + Recorrencia.tMergeSort(1024));

        long limite = 200_000;
        System.out.println("\nPonto 1 — constante da prova por substituicao (T(n) <= c n lg n):");
        System.out.println("  c = 2/3 (conclusao do slide): primeira falha em n = "
                + Recorrencia.primeiraFalha(2.0 / 3, limite) + " (T(2)=4 > 4/3)");
        long falha3 = Recorrencia.primeiraFalha(3, limite);
        System.out.println("  c = 3   (usado no passo base): "
                + (falha3 == -1 ? "vale para todo 2 <= n <= " + limite : "falha em n = " + falha3));
        System.out.printf("  menor c que funciona para 2 <= n <= %d: %.4f%n", limite, Recorrencia.menorConstante(limite));
        System.out.println("  Correcao: para concluir <= c n lg n o passo indutivo precisa de");
        System.out.println("  -c floor(n/2) + n <= 0, isto e, c >= n / floor(n/2) (ate 3, em n = 3).");
        System.out.println("  Com c = 3 base e passo fecham; c = 2/3 nao satisfaz nem o passo base.");
        System.out.println("  (Numericamente c = 2 ja basta: a prova usa majoracoes folgadas.)");

        System.out.println("\nPonto 2 — formula fechada do metodo iterativo (n potencia de 2):");
        boolean confere = true;
        for (int k = 0; k <= 20; k++) {
            long n = 1L << k;
            confere &= Recorrencia.tMergeSort(n) == n * k + n;
        }
        System.out.println("  T(n) = n lg n + n para n = 2^0 .. 2^20: " + confere);

        System.out.println("\nPonto 3 — QuickSort dos slides recursando em [p..q]:");
        System.out.println("  Com pivo A[r], se A[r] e o maior elemento, particiona devolve q = r e");
        System.out.println("  quickSort(A, p, q) repete a mesma chamada para sempre. A implementacao");
        System.out.println("  usa [p..q-1] e [q+1..r] (o pivo ja esta na posicao final).");
        int[] v = {2, 8, 7, 1, 3, 5, 6, 4};
        int q = Ordenacao.particiona(v, 0, v.length - 1);
        System.out.println("  particiona([2,8,7,1,3,5,6,4]) -> " + Arrays.toString(v) + ", q=" + q
                + " (igual ao slide: 2 1 3 [4] 7 5 6 8)");
    }

    private static void exercicio8() {
        System.out.println("Medicao real (entrada ja ordenada = pior caso do QuickSort com pivo A[r]):");
        double[] tempoQuick = new double[1];
        double[] tempoMerge = new double[1];
        int nMedido = 0;
        // aquecimento do JIT para reduzir ruído nas medições
        Ordenacao.comPilhaGrande(() -> {
            for (int i = 0; i < 3; i++) {
                Ordenacao.quickSort(java.util.stream.IntStream.range(0, 10_000).toArray());
                Ordenacao.mergeSort(java.util.stream.IntStream.range(0, 10_000).toArray());
            }
        });
        for (int n : new int[]{10_000, 20_000, 40_000}) {
            int[] base = java.util.stream.IntStream.range(0, n).toArray();
            int[] q = base.clone();
            int[] m = base.clone();
            Ordenacao.comPilhaGrande(() -> {
                long t0 = System.nanoTime();
                Ordenacao.quickSort(q);
                tempoQuick[0] = (System.nanoTime() - t0) / 1e6;
            });
            long t0 = System.nanoTime();
            Ordenacao.mergeSort(m);
            tempoMerge[0] = (System.nanoTime() - t0) / 1e6;
            System.out.printf("n=%6d | QuickSort pior caso=%9.2f ms | MergeSort=%7.2f ms%n",
                    n, tempoQuick[0], tempoMerge[0]);
            nMedido = n;
        }

        System.out.println("\nProjecao por operacoes (n^2/2 x n lg n):");
        for (int n : new int[]{10_000, 100_000, 1_000_000}) {
            double quad = (double) n * n / 2;
            double nlogn = n * Recorrencia.lg(n);
            System.out.printf("n=%9d | n^2/2=%,22.0f | n lg n=%,14.0f | razao=%,10.0fx%n",
                    n, quad, nlogn, quad / nlogn);
        }
        double fatorQuick = Math.pow(1_000_000.0 / nMedido, 2);
        double fatorMerge = (1_000_000.0 * Recorrencia.lg(1_000_000)) / (nMedido * Recorrencia.lg(nMedido));
        System.out.printf("%nExtrapolando a medicao de n=%d para 1.000.000:%n", nMedido);
        System.out.printf("  QuickSort pior caso ~ %.0f s (%.1f min) | MergeSort ~ %.0f ms%n",
                tempoQuick[0] * fatorQuick / 1000, tempoQuick[0] * fatorQuick / 60000, tempoMerge[0] * fatorMerge);
        System.out.println("  (recursao de profundidade n: com 1 milhao, a pilha padrao da JVM estoura)");
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
