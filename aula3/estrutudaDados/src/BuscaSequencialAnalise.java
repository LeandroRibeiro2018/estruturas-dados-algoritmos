import java.util.ArrayList;
import java.util.List;

/**
 * Exercício 1 — busca sequencial em lista em memória.
 */
public class BuscaSequencialAnalise {
    public static int buscaSequencial(List<String> lista, String alvo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(alvo)) {
                return i;
            }
        }
        return -1;
    }

    public static List<Integer> gerarTamanhos() {
        return List.of(100, 500, 1_000, 5_000, 10_000, 50_000, 100_000);
    }

    public static List<String> gerarListaComTamanho(int tamanho) {
        List<String> lista = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            lista.add("CPF-" + i);
        }
        return lista;
    }
}
