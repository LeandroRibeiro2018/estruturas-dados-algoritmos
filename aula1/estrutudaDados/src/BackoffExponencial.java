/**
 * <b>Titulo:</b> Funcao Exponencial — Backoff Exponencial com Teto Limitador.
 * <p>
 * <b>Funcao testada:</b> {@link #delayMs(int)} — calcula o atraso de uma
 * nova tentativa (retry) seguindo f(t) = base · 2^t, com um teto (cap) de
 * seguranca para nao sobrecarregar o sistema.
 * <p>
 * <b>Exercicio pertinente:</b> Parte I, secao 2.1 do guia — "Algoritmo de
 * Backoff Exponencial com Teto Limitador" (Resiliencia). Conceito
 * diretamente relacionado ao Exercicio 2 do guia ("Seu Celular Esta
 * Consumindo Sua Produtividade?" — Funcao Exponencial, Taxa de Crescimento,
 * Limites Fisicos e Cap), aplicado aqui a retentativas de conexao.
 *
 * <p><b>Como executar pelo menu:</b> rode a classe {@code Main} e escolha
 * a opcao {@code 3 - Backoff exponencial}. Informe o numero da tentativa.
 */
public class BackoffExponencial {
    public long delayMs(int tentativa) {
        long base = 200L; // Atraso base de 200 ms
// Cálculo exponencial: f(t) = base * 2^tentativa
        long delay = (long) (base * Math.pow(2, tentativa));
// Teto de segurança (Cap) para impedir contenção de recursos
        return Math.min(delay, 30_000L);
    }
}
