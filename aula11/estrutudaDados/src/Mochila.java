import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Problema da mochila (Knapsack). Na versão fracionária o guloso por
 * valor/peso é ótimo; na versão binária (0-1) o mesmo critério pode falhar,
 * o que é verificado com programação dinâmica.
 */
public class Mochila {

    public record Objeto(int id, int valor, int peso) {
        double razao() {
            return (double) valor / peso;
        }
    }

    /** Fração de cada objeto colocada na mochila (m[i] do slide) e o valor total. */
    public record Solucao(List<Objeto> ordem, double[] fracoes, double valorTotal, List<String> passos) {
    }

    // mochilaFracionaria (v[1..n], w[1..n], W)
    public static Solucao fracionaria(List<Objeto> objetos, int capacidade) {
        List<Objeto> o = ordenarPorRazao(objetos); // v[1]/w[1] >= v[2]/w[2] >= ...
        double[] m = new double[o.size()];
        List<String> passos = new ArrayList<>();
        double w = capacidade;
        double valor = 0;
        int i = 0;
        while (i < o.size() && w > 0) {
            Objeto obj = o.get(i);
            if (obj.peso() <= w) { // colocar objeto inteiro na mochila
                m[i] = 1;
                w = w - obj.peso();
                valor += obj.valor();
                passos.add(String.format("objeto %d inteiro (peso %d) -> capacidade restante %.0f", obj.id(), obj.peso(), w));
                i = i + 1;
            } else { // colocar fração do objeto na mochila
                m[i] = w / obj.peso();
                valor += m[i] * obj.valor();
                passos.add(String.format("objeto %d: fracao %.0f/%d = %.3f (valor %.2f) -> capacidade restante 0",
                        obj.id(), w, obj.peso(), m[i], m[i] * obj.valor()));
                w = 0;
            }
        }
        return new Solucao(o, m, valor, passos);
    }

    /** O mesmo critério guloso, mas só objetos inteiros (mochila 0-1). */
    public static Solucao binariaGulosa(List<Objeto> objetos, int capacidade) {
        List<Objeto> o = ordenarPorRazao(objetos);
        double[] m = new double[o.size()];
        List<String> passos = new ArrayList<>();
        int w = capacidade;
        double valor = 0;
        for (int i = 0; i < o.size(); i++) {
            Objeto obj = o.get(i);
            if (obj.peso() <= w) {
                m[i] = 1;
                w -= obj.peso();
                valor += obj.valor();
                passos.add("objeto " + obj.id() + " entra (peso " + obj.peso() + ") -> capacidade restante " + w);
            } else {
                passos.add("objeto " + obj.id() + " nao cabe (peso " + obj.peso() + " > " + w + ") -> fica de fora");
            }
        }
        return new Solucao(o, m, valor, passos);
    }

    /** Ótimo exato da mochila 0-1 por programação dinâmica; devolve os ids escolhidos. */
    public static List<Integer> binariaOtima(List<Objeto> objetos, int capacidade, int[] valorOtimo) {
        int n = objetos.size();
        int[][] melhor = new int[n + 1][capacidade + 1];
        for (int i = 1; i <= n; i++) {
            Objeto o = objetos.get(i - 1);
            for (int c = 0; c <= capacidade; c++) {
                melhor[i][c] = melhor[i - 1][c];
                if (o.peso() <= c) {
                    melhor[i][c] = Math.max(melhor[i][c], melhor[i - 1][c - o.peso()] + o.valor());
                }
            }
        }
        valorOtimo[0] = melhor[n][capacidade];
        List<Integer> escolhidos = new ArrayList<>();
        for (int i = n, c = capacidade; i > 0; i--) {
            if (melhor[i][c] != melhor[i - 1][c]) {
                Objeto o = objetos.get(i - 1);
                escolhidos.add(0, o.id());
                c -= o.peso();
            }
        }
        return escolhidos;
    }

    private static List<Objeto> ordenarPorRazao(List<Objeto> objetos) {
        List<Objeto> o = new ArrayList<>(objetos);
        o.sort(Comparator.comparingDouble(Objeto::razao).reversed()); // O(n log n)
        return o;
    }
}
