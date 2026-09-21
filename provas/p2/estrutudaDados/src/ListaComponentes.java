import java.util.LinkedList;

/**
 * Questão 3 — Lista ligada (LinkedList) para componentes do veículo.
 *
 * O conjunto de componentes muda de tamanho com frequência; a lista ligada
 * evita realocar um array inteiro a cada mudança. Inserir/remover numa
 * posição já localizada é O(1); buscar um componente é O(n).
 */
public class ListaComponentes {
    public static void demonstrar() {
        LinkedList<String> componentes = new LinkedList<>();
        componentes.addLast("Motor(cod1)");
        componentes.addLast("Cambio(cod2)");
        componentes.addLast("Painel(cod3)");
        componentes.addLast("Bancos(cod4)");
        componentes.addLast("Rodas(cod5)");
        System.out.println("Lista inicial: " + componentes);

        componentes.addLast("OpcionalTetoSolar(cod6)");
        System.out.println("Apos addLast (O(1)): " + componentes);

        componentes.remove("Painel(cod3)");
        System.out.println("Apos remover Painel por valor (O(n) para localizar): " + componentes);

        componentes.add(1, "FiltroAr(cod7)");
        System.out.println("Apos inserir na posicao 1 (O(1) apos localizar o no): " + componentes);
    }
}
