import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Se um argumento for informado (ex.: "java Main 2"), ele apenas seleciona
        // o exercicio. Os inputs pertinentes de cada exercicio sao sempre pedidos
        // interativamente pelo teclado logo em seguida.
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

    private static void executarOpcaoUnica(String argOpcao) {
        try {
            int opcao = Integer.parseInt(argOpcao.trim());
            executar(opcao);
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida: '" + argOpcao + "'. Informe um numero de 0 a 6.");
        } catch (IllegalArgumentException e) {
            System.out.println("Entrada invalida: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n===Aula 01 - Exercicios de Estruturas de Dados ===");
        System.out.println("1 - Calcular desconto");
        System.out.println("2 - Calcular piso/teto de paginas");
        System.out.println("3 - Backoff exponencial");
        System.out.println("4 - Busca binaria");
        System.out.println("5 - Custo total do pedido");
        System.out.println("6 - Contar nos por inducao");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao) {
        if (opcao != 0) {
            exibirDescricao(opcao);
        }
        switch (opcao) {
            case 1 -> calcularDesconto();
            case 2 -> calcularPaginas();
            case 3 -> calcularBackoff();
            case 4 -> realizarBuscaBinaria();
            case 5 -> calcularPedido();
            case 6 -> contarNos();
            case 0 -> System.out.println("Programa encerrado.");
            default -> System.out.println("Opcao inexistente.");
        }
    }

    private static void exibirDescricao(int opcao) {
        switch (opcao) {
            case 1 -> System.out.println("""

                    ---------------------------------------------------------
                    1) Funcoes e Validacao de Dominio (Defensive Programming)
                    ---------------------------------------------------------
                    O que e: mapeia uma regra de negocio como uma funcao
                    f : [0, infinito) -> {0.05, 0.20}, validando o dominio de
                    entrada (quantidade >= 0) antes de aplicar a regra.
                    Exercicio do guia: relacionado ao Exercicio 1 (Funcao
                    Afim, Dominio e Imagem).
                    Como testar: informe a quantidade de itens do pedido.
                      - quantidade <= 100  -> desconto de 5%  (0.05)
                      - quantidade  > 100  -> desconto de 20% (0.20)
                      - quantidade  < 0    -> erro de dominio (Fail-Fast)
                    Exemplo: quantidade = 150 -> Desconto: 0.20
                    ---------------------------------------------------------""");
            case 2 -> System.out.println("""

                    ---------------------------------------------------------
                    2) Funcao Piso e Funcao Teto (Paginacao de APIs REST)
                    ---------------------------------------------------------
                    O que e: calcula o numero de paginas necessarias usando
                    aritmetica inteira exata (funcao teto):
                    ceil(a / b) = (a + b - 1) / b, para a >= 0 e b > 0.
                    Exercicio do guia: relacionado ao Exercicio 3 (Funcao
                    Piso, Funcao Teto e Dimensionamento Operacional).
                    Como testar: informe o total de registros e quantos
                    registros cabem por pagina.
                    Exemplo: 1001 registros, 100 por pagina -> 11 paginas
                    (repare que 1001 / 100 = 10 usando divisao inteira pura,
                    mas o correto com teto e 11 - o 1001o registro nao pode
                    sumir da tela).
                    ---------------------------------------------------------""");
            case 3 -> System.out.println("""

                    ---------------------------------------------------------
                    3) Funcao Exponencial (Backoff Exponencial com Teto)
                    ---------------------------------------------------------
                    O que e: calcula o atraso (delay) de uma nova tentativa
                    de conexao (retry) seguindo f(t) = base * 2^t, limitado
                    por um teto de seguranca de 30 segundos.
                    Exercicio do guia: relacionado ao Exercicio 2 (Funcao
                    Exponencial, Taxa de Crescimento, Limites Fisicos e Cap).
                    Como testar: informe o numero da tentativa (>= 0).
                    Exemplo: tentativa = 0 -> 200 ms
                             tentativa = 4 -> 3200 ms
                             tentativa = 10 -> 30000 ms (atingiu o teto)
                    ---------------------------------------------------------""");
            case 4 -> System.out.println("""

                    ---------------------------------------------------------
                    4) Funcao Logaritmica e Monotonicidade (Busca Binaria)
                    ---------------------------------------------------------
                    O que e: localiza um valor em um vetor ORDENADO em
                    complexidade O(log n), aproveitando a monotonicidade do
                    vetor para descartar metade das opcoes a cada passo.
                    Exercicio do guia: relacionado ao Exercicio 4 (Exponencial,
                    Resolucao Logaritmica, Monotonicidade e B-Trees).
                    Como testar: informe o tamanho do vetor, os valores em
                    ordem crescente e o valor procurado.
                    Exemplo: valores = [10, 20, 30, 40, 50], alvo = 30
                             -> "Valor encontrado no indice 2."
                    Atencao: se o vetor nao estiver ordenado, o resultado
                    pode ser incorreto.
                    ---------------------------------------------------------""");
            case 5 -> System.out.println("""

                    ---------------------------------------------------------
                    5) Somatorio (Custo Total de Pedido)
                    ---------------------------------------------------------
                    O que e: calcula o custo total de um pedido mapeando
                    diretamente o somatorio soma(preco_i * quantidade_i)
                    usando a Stream API do Java (reduce).
                    Exercicio do guia: relacionado ao Exercicio 5 (Somatorio,
                    Analise Financeira de Engenharia e Ponto de Equilibrio).
                    Como testar: informe a quantidade de itens do pedido e,
                    para cada item, o SKU, o preco unitario e a quantidade.
                    Exemplo: 2 itens -> SKU A, preco 10.00, quantidade 3
                                      -> SKU B, preco  5.00, quantidade 2
                             Custo total = (10.00*3) + (5.00*2) = 40.00
                    ---------------------------------------------------------""");
            case 6 -> System.out.println("""

                    ---------------------------------------------------------
                    6) Inducao Forte (Contagem de Nos em uma Arvore)
                    ---------------------------------------------------------
                    O que e: conta o total de nos de uma arvore de categorias
                    somando recursivamente 1 (o proprio no) + a contagem de
                    cada sub-arvore filha (prova por inducao forte).
                    Exercicio do guia: relacionado ao Exercicio 8 (Inducao
                    Matematica, Corretude de Algoritmos e Ataque Defensivo).
                    Como testar: informe o nome do no raiz, a quantidade de
                    filhos e, para cada filho, repita o mesmo processo
                    (nome + quantidade de filhos) ate chegar nas folhas
                    (quantidade de filhos = 0).
                    Exemplo: raiz com 2 filhos, cada filho sem filhos
                             -> Total de nos: 3
                    ---------------------------------------------------------""");
            default -> { /* opcao invalida: nada a exibir aqui, tratado em executar() */ }
        }
    }

    private static void calcularDesconto() {
        int quantidade = lerInteiro("Quantidade: ");
        BigDecimal desconto = new CalculaDescontos().calcularDesconto(quantidade);
        System.out.println("Desconto: " + desconto);
    }

    private static void calcularPaginas() {
        int registros = lerInteiro("Quantidade de registros: ");
        int tamanhoPagina = lerInteiro("Registros por pagina: ");
        int paginas = new PisoTeto().calcularPaginas(registros, tamanhoPagina);
        System.out.println("Paginas necessarias: " + paginas);
    }

    private static void calcularBackoff() {
        int tentativa = lerInteiro("Numero da tentativa: ");
        System.out.println("Atraso: " + new BackoffExponencial().delayMs(tentativa) + " ms");
    }

    private static void realizarBuscaBinaria() {
        int tamanho = lerInteiro("Tamanho do vetor ordenado: ");
        if (tamanho < 0) {
            throw new IllegalArgumentException("O tamanho do vetor nao pode ser negativo.");
        }
        int[] valores = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            valores[i] = lerInteiro("Valor [" + i + "]: ");
        }
        int alvo = lerInteiro("Valor procurado: ");
        int indice = new BuscaBinaria().buscaBinaria(valores, alvo);
        System.out.println(indice >= 0
                ? "Valor encontrado no indice " + indice + "."
                : "Valor nao encontrado.");
    }

    private static void calcularPedido() {
        int quantidade = lerInteiro("Quantidade de itens: ");
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de itens nao pode ser negativa.");
        }
        List<ComplexidadeDo.ItemPedido> itens = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            String sku = lerTexto("SKU do item " + (i + 1) + ": ");
            BigDecimal preco = lerDecimal("Preco: ");
            int unidades = lerInteiro("Quantidade: ");
            itens.add(new ComplexidadeDo.ItemPedido(sku, preco, unidades));
        }
        System.out.println("Custo total: " + new ComplexidadeDo().calcularCustoTotal(itens));
    }

    private static void contarNos() {
        System.out.println("Informe a arvore a partir da raiz.");
        Inducao.Categoria raiz = lerCategoria();
        System.out.println("Total de nos: " + new Inducao().contarNos(raiz));
    }

    private static Inducao.Categoria lerCategoria() {
        String nome = lerTexto("Nome do no: ");
        int quantidadeFilhos = lerInteiro("Quantidade de filhos de " + nome + ": ");
        if (quantidadeFilhos < 0) {
            throw new IllegalArgumentException("A quantidade de filhos nao pode ser negativa.");
        }
        List<Inducao.Categoria> filhos = new ArrayList<>();
        for (int i = 0; i < quantidadeFilhos; i++) {
            filhos.add(lerCategoria());
        }
        return new Inducao.Categoria(nome, filhos);
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Digite um numero inteiro.");
            scanner.next();
            System.out.print(mensagem);
        }
        return scanner.nextInt();
    }

    private static BigDecimal lerDecimal(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextBigDecimal()) {
            System.out.println("Digite um valor decimal.");
            scanner.next();
            System.out.print(mensagem);
        }
        return scanner.nextBigDecimal();
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.next();
    }
}
