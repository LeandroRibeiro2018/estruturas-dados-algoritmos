/**
 * Simulação de recursão com pilha: o fatorial recursivo e a versão
 * iterativa que usa uma pilha explícita para guardar as "variáveis locais"
 * de cada chamada.
 */
public class FatorialPilha {

    // fatorial (n) — versão recursiva dos slides
    public static long fatorialRecursivo(int n) {
        long ret;
        if (n == 0) { // caso base
            ret = 1;
        } else { // caso geral
            long ant = fatorialRecursivo(n - 1); // instância menor
            ret = n * ant;
        }
        return ret;
    }

    // fatorial (n) — versão iterativa com pilha explícita
    public static long fatorialIterativo(int n, boolean rastrear) {
        PilhaDinamica pilha = new PilhaDinamica();
        while (n >= 0) {
            pilha.push(n); // empilha (equivale a uma chamada)
            if (rastrear) {
                System.out.println("push(" + n + ")  pilha: " + pilha);
            }
            n = n - 1;
        }
        long ret = 0;
        while (!pilha.vazia()) {
            int t = pilha.pop(); // desempilha (equivale a um retorno)
            if (t == 0) { // caso base
                ret = 1;
            } else { // caso geral
                long ant = ret; // instância menor
                ret = t * ant;
            }
            if (rastrear) {
                System.out.println("pop -> n=" + t + " | ret=" + ret + "   pilha: " + pilha);
            }
        }
        return ret;
    }
}
