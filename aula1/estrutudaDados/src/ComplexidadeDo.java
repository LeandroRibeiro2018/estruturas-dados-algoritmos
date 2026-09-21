import java.math.BigDecimal;
import java.util.List;

/**
 * <b>Titulo:</b> Somatorio ∑ — Custo Total de Pedido.
 * <p>
 * <b>Funcao testada:</b> {@link #calcularCustoTotal(java.util.List)} —
 * mapeia diretamente o somatorio ∑ (preco_i · quantidade_i) usando a
 * Stream API do Java (reduce), otimizando o calculo do custo de um pedido.
 * <p>
 * <b>Exercicio pertinente:</b> Parte I, secao 5.2 do guia — "Acumulacao
 * Funcional com Java Stream API (Reduce)". Conceito relacionado ao
 * Exercicio 5 do guia ("Vale a Pena Automatizar? Analise de Retorno sobre
 * Investimento (ROI)" — Somatorio ∑, Analise Financeira de Engenharia e
 * Ponto de Equilibrio), aplicado aqui ao calculo do custo total de itens.
 *
 * <p><b>Como executar pelo menu:</b> rode a classe {@code Main} e escolha
 * a opcao {@code 5 - Custo total do pedido}. Informe a quantidade de itens
 * e, para cada um, o SKU, o preco unitario e a quantidade comprada.
 */
public class ComplexidadeDo {
    public record ItemPedido(String sku, BigDecimal preco, int quantidade) {}
    public BigDecimal calcularCustoTotal(List<ItemPedido> itens) {
// Mapeamento direto do somatório ∑ (preco_i * quantidade_i)
        return itens.stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
