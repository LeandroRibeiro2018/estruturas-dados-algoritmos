/**
 * Exercício 4 — escolha entre busca sequencial e busca binária.
 */
public class DecisaoBusca {
    public static String escolherEstrategia(boolean dadosOrdenados, int quantidadeRegistros,
                                          boolean leiturasFrequentes, boolean escritasFrequentes) {
        if (!dadosOrdenados) {
            return "Busca sequencial (sem ordenação, a pré-condição da busca binária não vale).";
        }

        if (quantidadeRegistros <= 1000 && !leiturasFrequentes) {
            return "Busca sequencial (coleção pequena e custo de manter ordenação pode superar o ganho).";
        }

        if (leiturasFrequentes && !escritasFrequentes) {
            return "Busca binária com índice/estrutura ordenada (melhor custo de leitura e manutenção amortizada).";
        }

        return "Busca sequencial ou índice mantido pelo SGBD conforme o padrão de acesso real.";
    }
}
