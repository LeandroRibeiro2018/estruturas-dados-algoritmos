import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Exercício 3 — Dividir para conquistar: contagem de respostas de uma
 * pesquisa por categoria.
 *
 * Dividir: separa as respostas em duas metades.
 * Resolver: conta cada metade (de forma independente — poderiam ir para
 * pessoas, threads ou máquinas diferentes).
 * Combinar: soma as contagens das duas metades.
 */
public class DivisaoConquista {
    static final String[] CATEGORIAS = {"Muito satisfeito", "Satisfeito", "Neutro", "Insatisfeito"};

    public static String[] gerarRespostas(int n, long semente) {
        Random r = new Random(semente);
        String[] respostas = new String[n];
        for (int i = 0; i < n; i++) {
            respostas[i] = CATEGORIAS[r.nextInt(CATEGORIAS.length)];
        }
        return respostas;
    }

    public static Map<String, Integer> contarSequencial(String[] respostas) {
        Map<String, Integer> contagem = new TreeMap<>();
        for (String r : respostas) {
            contagem.merge(r, 1, Integer::sum);
        }
        return contagem;
    }

    public static Map<String, Integer> contarDividindo(String[] respostas, int ini, int fim, int limite,
                                                       int nivel, StringBuilder trace) {
        if (fim - ini <= limite) { // caso base: parte pequena, conta direto
            Map<String, Integer> parcial = new TreeMap<>();
            for (int i = ini; i < fim; i++) {
                parcial.merge(respostas[i], 1, Integer::sum);
            }
            if (trace != null) {
                trace.append("  ".repeat(nivel)).append("resolve [").append(ini).append(", ").append(fim)
                        .append(") -> ").append(parcial).append('\n');
            }
            return parcial;
        }
        int meio = (ini + fim) / 2; // dividir
        if (trace != null) {
            trace.append("  ".repeat(nivel)).append("divide [").append(ini).append(", ").append(fim)
                    .append(") em [").append(ini).append(", ").append(meio).append(") e [")
                    .append(meio).append(", ").append(fim).append(")\n");
        }
        Map<String, Integer> esq = contarDividindo(respostas, ini, meio, limite, nivel + 1, trace);
        Map<String, Integer> dir = contarDividindo(respostas, meio, fim, limite, nivel + 1, trace);
        Map<String, Integer> combinado = new TreeMap<>(esq); // combinar
        dir.forEach((k, v) -> combinado.merge(k, v, Integer::sum));
        return combinado;
    }
}
