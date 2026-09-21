/**
 * Exercício 1 — busca sequencial em vetor.
 *
 * Mantém a lógica da lista resolvida: percorre o vetor de trás para frente
 * e retorna a primeira ocorrência encontrada (a mais à direita).
 */
public class BuscaSequencial {
    public static int buscaSequencial(String[] vetor, String alvo) {
        int k = vetor.length - 1;
        while (k >= 0 && !vetor[k].equals(alvo)) {
            k--;
        }
        return k;
    }
}
