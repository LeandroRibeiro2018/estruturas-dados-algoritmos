/**
 * Questão 5 — Árvore binária para checkpoints pass/fail.
 *
 * Cada checkpoint tem no máximo 2 saídas (aprovado/reprovado), e uma
 * reprovação pode se desdobrar em outra decisão binária. Percorrer do topo
 * até uma folha custa O(altura da árvore de decisão).
 */
public class ArvoreBinariaDecisao {
    public static class Decisao {
        final String nome;
        Decisao aprovado;
        Decisao reprovado;

        Decisao(String nome) {
            this.nome = nome;
        }
    }

    public static Decisao montarExemplo() {
        Decisao geral = new Decisao("InspecaoGeral");
        geral.aprovado = new Decisao("PASS");
        geral.reprovado = new Decisao("FAIL");
        geral.reprovado.aprovado = new Decisao("Retrabalho(cod9)");
        geral.reprovado.reprovado = new Decisao("Descarte(cod0)");
        return geral;
    }

    public static String caminhoReprovadoRecuperavel(Decisao raiz) {
        return raiz.nome + " -> " + raiz.reprovado.nome + " -> " + raiz.reprovado.aprovado.nome;
    }

    public static String caminhoReprovadoIrrecuperavel(Decisao raiz) {
        return raiz.nome + " -> " + raiz.reprovado.nome + " -> " + raiz.reprovado.reprovado.nome;
    }

    public static String caminhoAprovadoDireto(Decisao raiz) {
        return raiz.nome + " -> " + raiz.aprovado.nome;
    }
}
