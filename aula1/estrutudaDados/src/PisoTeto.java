/**
 * <b>Titulo:</b> Funcao Piso e Funcao Teto — Paginacao de APIs REST.
 * <p>
 * <b>Funcao testada:</b> {@link #calcularPaginas(int, int)} — calcula a
 * paginacao usando aritmetica inteira exata: ⌈a / b⌉ = (a + b - 1) / b,
 * para a ≥ 0 e b > 0. Evita o erro classico de usar divisao inteira pura
 * (que arredonda para baixo/piso implicitamente).
 * <p>
 * <b>Exercicio pertinente:</b> Parte I, secao 3 do guia — "Funcao Piso e
 * Funcao Teto" (bloco "Calculo de Paginacao em APIs REST"). Conceito
 * relacionado ao Exercicio 3 do guia ("Quantas Pessoas Realmente Cabem em um
 * Orcamento?" — Funcao Piso ⌊x⌋, Funcao Teto ⌈x⌉ e Dimensionamento
 * Operacional), aplicado aqui ao calculo de paginas de uma listagem.
 *
 * <p><b>Como executar pelo menu:</b> rode a classe {@code Main} e escolha
 * a opcao {@code 2 - Calcular piso/teto de paginas}. Informe o total de
 * registros e a quantidade de registros por pagina.
 */
public class PisoTeto {
    public int calcularPaginas(int totalRegistros, int tamanhoPagina) {
        if (totalRegistros < 0 || tamanhoPagina <= 0) {
            throw new IllegalArgumentException(
                    "Registros deve ser >= 0 e tamanho da pagina deve ser > 0.");
        }

        // Abordagem idiomática profissional usando aritmética inteira exata:
        // Fórmula: ⌈a / b⌉ = (a + b - 1) / b, para a ≥ 0, b > 0
        // ARMADILHA CLÁSSICA: int/int executa piso implícito!
        // 1001 registros com página de 100: 1001 / 100 = 10 páginas (O 1001o some da tela!)
        // Correto com teto: (1001 + 100 - 1) / 100 = 1100 / 100 = 11 páginas.
        return (totalRegistros + tamanhoPagina - 1) / tamanhoPagina;
    }
}
