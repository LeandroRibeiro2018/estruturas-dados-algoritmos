import java.util.Map;

/**
 * Árvore de expressão (árvore de derivação, em compiladores) para
 * (a * b) + (c / (d + e)). Os operadores ficam nos nós internos e os
 * operandos nas folhas, então a precedência fica explícita sem parênteses.
 *
 * Cada percurso gera uma notação: pré-ordem = prefixa, in-ordem = infixa,
 * pós-ordem = pós-fixa (notação polonesa reversa).
 */
public class ArvoreExpressao {

    static class NoExpressao {
        final String valor;
        final NoExpressao esq;
        final NoExpressao dir;

        NoExpressao(String valor, NoExpressao esq, NoExpressao dir) {
            this.valor = valor;
            this.esq = esq;
            this.dir = dir;
        }

        NoExpressao(String valor) {
            this(valor, null, null);
        }
    }

    public static NoExpressao exemploDosSlides() {
        NoExpressao mult = new NoExpressao("*", new NoExpressao("a"), new NoExpressao("b"));
        NoExpressao soma = new NoExpressao("+", new NoExpressao("d"), new NoExpressao("e"));
        NoExpressao div = new NoExpressao("/", new NoExpressao("c"), soma);
        return new NoExpressao("+", mult, div);
    }

    public static String prefixa(NoExpressao r) {
        if (r == null) {
            return "";
        }
        return (r.valor + " " + prefixa(r.esq) + " " + prefixa(r.dir)).trim().replaceAll(" +", " ");
    }

    public static String infixa(NoExpressao r) {
        if (r.esq == null && r.dir == null) {
            return r.valor;
        }
        return "(" + infixa(r.esq) + " " + r.valor + " " + infixa(r.dir) + ")";
    }

    public static String posfixa(NoExpressao r) {
        if (r == null) {
            return "";
        }
        return (posfixa(r.esq) + " " + posfixa(r.dir) + " " + r.valor).trim().replaceAll(" +", " ");
    }

    /** Avalia a expressão com um percurso pós-ordem: primeiro os filhos, depois o operador. */
    public static double avaliar(NoExpressao r, Map<String, Double> variaveis) {
        if (r.esq == null && r.dir == null) {
            return variaveis.get(r.valor);
        }
        double e = avaliar(r.esq, variaveis);
        double d = avaliar(r.dir, variaveis);
        return switch (r.valor) {
            case "+" -> e + d;
            case "-" -> e - d;
            case "*" -> e * d;
            case "/" -> e / d;
            default -> throw new IllegalArgumentException("Operador desconhecido: " + r.valor);
        };
    }
}
