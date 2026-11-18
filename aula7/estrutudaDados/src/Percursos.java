import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Percursos em árvore binária: três em profundidade (pré, in e pós-ordem)
 * e um em largura (por nível). Todos visitam cada nó uma vez: O(n).
 * Em vez de imprimir, cada visita adiciona a chave em uma lista.
 */
public class Percursos {

    // preOrdem (r): raiz, esquerda, direita
    public static void preOrdem(NoArvore r, List<Integer> visita) {
        if (r != null) { // árvore não vazia
            visita.add(r.chave); // visita raiz
            preOrdem(r.esq, visita); // percorre subárvore esquerda
            preOrdem(r.dir, visita); // percorre subárvore direita
        }
    }

    // inOrdem (r): esquerda, raiz, direita — em uma BST sai ordenado
    public static void inOrdem(NoArvore r, List<Integer> visita) {
        if (r != null) {
            inOrdem(r.esq, visita);
            visita.add(r.chave);
            inOrdem(r.dir, visita);
        }
    }

    // posOrdem (r): esquerda, direita, raiz
    public static void posOrdem(NoArvore r, List<Integer> visita) {
        if (r != null) {
            posOrdem(r.esq, visita);
            posOrdem(r.dir, visita);
            visita.add(r.chave);
        }
    }

    // preOrdemIterativo (r) — pilha explícita no lugar da recursão
    public static List<Integer> preOrdemIterativo(NoArvore r, boolean rastrear) {
        List<Integer> visita = new ArrayList<>();
        if (r != null) { // árvore não vazia
            Deque<NoArvore> pilha = new ArrayDeque<>();
            pilha.push(r); // empilha a raiz
            while (!pilha.isEmpty()) {
                NoArvore t = pilha.pop(); // desempilha
                visita.add(t.chave);
                // o filho direito é empilhado primeiro para o esquerdo sair antes
                if (t.dir != null) {
                    pilha.push(t.dir);
                }
                if (t.esq != null) {
                    pilha.push(t.esq);
                }
                if (rastrear) {
                    System.out.println("pop " + t.chave + " -> pilha (topo a esquerda): " + chaves(pilha));
                }
            }
        }
        return visita;
    }

    // percursoEmLargura (r) — fila: nível 0, depois nível 1, ...
    public static List<Integer> largura(NoArvore r) {
        List<Integer> visita = new ArrayList<>();
        if (r != null) {
            Deque<NoArvore> fila = new ArrayDeque<>();
            fila.addLast(r); // enfileira a raiz
            while (!fila.isEmpty()) {
                NoArvore i = fila.removeFirst(); // desenfileira
                visita.add(i.chave);
                if (i.esq != null) {
                    fila.addLast(i.esq);
                }
                if (i.dir != null) {
                    fila.addLast(i.dir);
                }
            }
        }
        return visita;
    }

    // imprimeArvore (r) — parênteses aninhados com percurso pré-ordem
    public static String parenteses(NoArvore r) {
        if (r == null) {
            return "()";
        }
        return "( " + r.chave + " " + parenteses(r.esq) + " " + parenteses(r.dir) + " )";
    }

    // imprimeArvore (r, b) — visualização "deitada" com percurso in-ordem
    // (direita, raiz, esquerda); b é o nível do nó r
    public static void lateral(NoArvore r, int b, StringBuilder saida) {
        if (r == null) { // árvore vazia
            saida.append("-----".repeat(b)).append("NULL\n");
            return;
        }
        lateral(r.dir, b + 1, saida); // imprime subárvore direita
        saida.append("-----".repeat(b)).append(r.chave).append('\n'); // imprime raiz
        lateral(r.esq, b + 1, saida); // imprime subárvore esquerda
    }

    private static List<Integer> chaves(Deque<NoArvore> nos) {
        List<Integer> lista = new ArrayList<>();
        for (NoArvore n : nos) {
            lista.add(n.chave);
        }
        return lista;
    }
}
