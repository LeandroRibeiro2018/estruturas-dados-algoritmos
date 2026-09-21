/**
 * Exercício 8 — busca binária recursiva.
 */
public class BuscaBinariaRecursiva {
    public static int buscaBinaria(int[] vetor, int alvo) {
        return buscaBinaria(vetor, alvo, 0, vetor.length - 1);
    }

    private static int buscaBinaria(int[] vetor, int alvo, int esquerda, int direita) {
        if (esquerda > direita) {
            return -1;
        }

        int meio = esquerda + (direita - esquerda) / 2;
        if (vetor[meio] == alvo) {
            return meio;
        }

        if (alvo < vetor[meio]) {
            return buscaBinaria(vetor, alvo, esquerda, meio - 1);
        }

        return buscaBinaria(vetor, alvo, meio + 1, direita);
    }
}
