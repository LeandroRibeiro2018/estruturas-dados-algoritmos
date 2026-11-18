/**
 * Exercício 2 — Encontrando o caso base: alçadas de aprovação de despesas.
 *
 * aprovar(valor, nivel): se o valor cabe na alçada do nível, aprova ali
 * (caso base 1); se o nível é o último (diretoria), decide ali (caso base 2);
 * senão encaminha para o nível acima. O que muda entre chamadas é o nível,
 * que sempre aumenta e é limitado pelo número de níveis: por isso termina.
 */
public class AprovacaoHierarquica {
    static final String[] NIVEIS = {"Supervisor", "Gerente", "Diretor", "Diretoria"};
    static final double[] ALCADA = {1_000, 10_000, 100_000, 1_000_000};

    public static String aprovar(double valor, int nivel, StringBuilder trace) {
        trace.append("  aprovar(").append(String.format("%.0f", valor)).append(", ")
                .append(NIVEIS[nivel]).append(")\n");
        if (valor <= ALCADA[nivel]) { // caso base 1: dentro da alçada
            return "aprovado por " + NIVEIS[nivel];
        }
        if (nivel == NIVEIS.length - 1) { // caso base 2: não há nível acima
            return "acima de todas as alcadas: decisao extraordinaria da " + NIVEIS[nivel];
        }
        return aprovar(valor, nivel + 1, trace); // problema menor: menos níveis restantes
    }

    private static int profundidade;

    /** Versão SEM caso base e sem progresso: sempre reencaminha, nunca para. */
    private static String aprovarSemCasoBase(double valor, int nivel) {
        profundidade++;
        return aprovarSemCasoBase(valor, nivel); // nada muda entre chamadas
    }

    public static String demonstrarSemCasoBase() {
        profundidade = 0;
        try {
            return aprovarSemCasoBase(500, 0);
        } catch (StackOverflowError e) {
            return "StackOverflowError apos " + profundidade + " chamadas empilhadas";
        }
    }
}
