import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Exercício 4 — escolha entre algoritmo O(n) e O(n²).
 */
public class DeteccaoDuplicidade {
    public static boolean algoritmoA(List<String> clientes) {
        Set<String> vistos = new HashSet<>();
        for (String cliente : clientes) {
            String chave = normalizar(cliente);
            if (!vistos.add(chave)) {
                return true;
            }
        }
        return false;
    }

    public static boolean algoritmoB(List<String> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            for (int j = i + 1; j < clientes.size(); j++) {
                if (normalizar(clientes.get(i)).equals(normalizar(clientes.get(j)))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String normalizar(String nome) {
        return nome == null ? "" : nome.trim().toLowerCase();
    }
}
