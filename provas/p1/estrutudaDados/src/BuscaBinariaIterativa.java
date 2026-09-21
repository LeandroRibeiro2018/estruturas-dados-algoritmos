/**
 * Questão 3 — Por que a busca binária funciona (invariante).
 *
 * O intervalo [inicio, fim] sempre contém o alvo, se ele existir; a cada
 * iteração o intervalo é reduzido pela metade, garantindo terminação em
 * no máximo log2(n) + 1 passos.
 */
public class BuscaBinariaIterativa {
    public static int iteracoes = 0;

    public static int buscar(int[] a, int alvo) {
        int inicio = 0;
        int fim = a.length - 1;
        iteracoes = 0;
        while (inicio <= fim) {
            iteracoes++;
            int meio = (inicio + fim) / 2;
            if (a[meio] == alvo) {
                return meio;
            } else if (a[meio] < alvo) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return -1;
    }

    public static int limiteTeorico(int n) {
        return (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
    }
}
