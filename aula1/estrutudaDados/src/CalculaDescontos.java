import java.math.BigDecimal;

/**
 * <b>Titulo:</b> Funcoes e Validacao de Dominio (Defensive Programming).
 * <p>
 * <b>Funcao testada:</b> {@link #calcularDesconto(int)} — mapeia uma regra de
 * negocio como uma funcao f : [0, ∞) → {0.05, 0.20}, protegendo o dominio de
 * entrada contra valores invalidos (Fail-Fast).
 * <p>
 * <b>Exercicio pertinente:</b> Parte I, secao 1.3 do guia — "Armadilha Comum:
 * Validacao do Dominio (Defensive Programming)". Conceito relacionado ao
 * Exercicio 1 do guia ("Quanto Realmente Custa uma Hora do Seu Trabalho?" —
 * Funcao Afim, Dominio e Imagem), aplicado aqui a uma regra de desconto por
 * quantidade de itens.
 *
 * <p><b>Como executar pelo menu:</b> rode a classe {@code Main} (com ou sem
 * argumentos) e escolha a opcao {@code 1 - Calcular desconto}. Sera pedida
 * apenas a quantidade de itens do pedido.
 *
 * <p><b>Como executar direto no IntelliJ:</b> abra este arquivo, crie um
 * pequeno metodo main de teste ou use o menu da classe {@code Main}.
 */
public class CalculaDescontos {

    public BigDecimal calcularDesconto(int quantidade) {
        // Proteção do domínio de entrada: quantidade >= 0
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa: " + quantidade);
        }

        // Regra de negócio: mais de 100 unidades recebem 20%; caso contrário, 5%.
        return quantidade > 100
                ? BigDecimal.valueOf(0.20)
                : BigDecimal.valueOf(0.05);
    }
}
