import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problema do troco (Coin Change): entregar o valor com o menor número de
 * moedas, com estoque infinito de cada valor.
 *
 * Escolha gulosa: usar sempre a moeda de maior valor que ainda cabe. É
 * rápida, mas nem sempre ótima (N = 8, M = {1, 4, 6}) e pode nem encontrar
 * solução (N = 19, M = {2, 3, 6}). Para conferir, {@link #otimo} calcula o
 * mínimo exato por programação dinâmica.
 */
public class Troco {

    /** Resultado do troco guloso: moedas escolhidas ou null se falhou. */
    public record Resultado(List<Integer> moedas, int soma, List<String> passos) {
        boolean encontrou(int valor) {
            return soma == valor;
        }
    }

    // trocoGuloso (C, valor) — C = conjunto de moedas para troco
    public static Resultado guloso(int[] moedasDisponiveis, int valor) {
        List<Integer> c = new ArrayList<>();
        for (int m : moedasDisponiveis) {
            c.add(m);
        }
        c.sort((a, b) -> b - a); // "moeda de maior valor em C" fica sempre em c.get(0)
        List<Integer> s = new ArrayList<>();
        List<String> passos = new ArrayList<>();
        int soma = 0;
        while (!c.isEmpty() && soma < valor) {
            int m = c.get(0); // moeda de maior valor em C
            if (soma + m <= valor) {
                passos.add((valor - soma) + " - " + m + " = " + (valor - soma - m));
                soma = soma + m;
                s.add(m);
            } else {
                passos.add("moeda " + m + " nao cabe no restante " + (valor - soma) + " -> descartada");
                c.remove(0); // C <- C - {m}
            }
        }
        return new Resultado(s, soma, passos);
    }

    /** Menor número de moedas (programação dinâmica) e as moedas usadas; null se impossível. */
    public static List<Integer> otimo(int[] moedas, int valor) {
        int[] min = new int[valor + 1];
        int[] ultima = new int[valor + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[0] = 0;
        for (int v = 1; v <= valor; v++) {
            for (int m : moedas) {
                if (m <= v && min[v - m] != Integer.MAX_VALUE && min[v - m] + 1 < min[v]) {
                    min[v] = min[v - m] + 1;
                    ultima[v] = m;
                }
            }
        }
        if (min[valor] == Integer.MAX_VALUE) {
            return null;
        }
        List<Integer> usadas = new ArrayList<>();
        for (int v = valor; v > 0; v -= ultima[v]) {
            usadas.add(ultima[v]);
        }
        usadas.sort((a, b) -> b - a);
        return usadas;
    }

    /**
     * Menor valor em [1, limite] em que o guloso não devolve o mínimo de
     * moedas (ou falha), ou -1 se não houver. Serve para achar contraexemplos.
     */
    public static int menorContraexemplo(int[] moedas, int limite) {
        for (int v = 1; v <= limite; v++) {
            Resultado g = guloso(moedas, v);
            List<Integer> o = otimo(moedas, v);
            if (o != null && (!g.encontrou(v) || g.moedas().size() > o.size())) {
                return v;
            }
        }
        return -1;
    }
}
