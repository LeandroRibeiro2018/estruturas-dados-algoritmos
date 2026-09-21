/**
 * <b>Titulo:</b> Funcao Logaritmica e Monotonicidade — Busca Binaria O(log n).
 * <p>
 * <b>Funcao testada:</b> {@link #buscaBinaria(int[], int)} — localiza um
 * elemento em um vetor ordenado explorando a monotonicidade, reduzindo o
 * espaco de busca pela metade a cada iteracao.
 * <p>
 * <b>Exercicio pertinente:</b> Parte I, secao 2.2 do guia — "Busca Binaria
 * Classica em Java — Complexidade O(log n)". Conceito relacionado ao
 * Exercicio 4 do guia ("O Investimento em uma Habilidade Esta Crescendo ou
 * Diminuindo?" — Exponencial, Resolucao Logaritmica, Monotonicidade e
 * B-Trees), que tambem cita busca binaria em lista ordenada.
 *
 * <p><b>Como executar pelo menu:</b> rode a classe {@code Main} e escolha
 * a opcao {@code 4 - Busca binaria}. Informe o tamanho do vetor, seus
 * valores em ordem crescente e o valor que deseja procurar.
 */
public class BuscaBinaria {
    public int buscaBinaria(int[] arr, int alvo) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
// Prevenção de Integer Overflow: equivalente a (lo + hi) / 2
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == alvo) return mid;
            if (arr[mid] < alvo) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1; // Elemento não encontrado
    }
}
