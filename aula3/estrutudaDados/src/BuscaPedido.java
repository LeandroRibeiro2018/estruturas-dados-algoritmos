/**
 * Exercício 3 — melhor caso, caso médio e pior caso de busca linear.
 */
public class BuscaPedido {
    public static String classificarCaso(int posicao, int tamanho) {
        if (posicao == 0) return "Melhor caso: o pedido está na primeira posição.";
        if (posicao == tamanho - 1 || posicao == -1) return "Pior caso: está na última posição ou não existe.";
        return "Caso médio: o pedido está em uma posição intermediária.";
    }
}
