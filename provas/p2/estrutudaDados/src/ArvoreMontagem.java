import java.util.ArrayList;
import java.util.List;

/**
 * Questão 4 — Árvore (Tree) para a decomposição do veículo.
 *
 * Veículo -> sistemas -> peças é uma decomposição hierárquica clássica
 * (relação pai-filho). Percorrer a árvore inteira é O(n) nós; localizar um
 * subsistema a partir da raiz é O(altura da árvore).
 */
public class ArvoreMontagem {
    public static class No {
        final String nome;
        final List<No> filhos = new ArrayList<>();

        No(String nome) {
            this.nome = nome;
        }

        No add(No filho) {
            filhos.add(filho);
            return this;
        }
    }

    public static int contarNos(No raiz) {
        if (raiz == null) {
            return 0;
        }
        int total = 1;
        for (No filho : raiz.filhos) {
            total += contarNos(filho);
        }
        return total;
    }

    public static void imprimir(No no, int profundidade) {
        System.out.println("  ".repeat(profundidade) + no.nome);
        for (No filho : no.filhos) {
            imprimir(filho, profundidade + 1);
        }
    }

    public static No montarExemplo() {
        No motor = new No("Motor").add(new No("Pistao")).add(new No("Virabrequim"));
        No carroceria = new No("Carroceria").add(new No("Chassi")).add(new No("Portas"));
        No eletronica = new No("Eletronica").add(new No("ECU")).add(new No("Chicote"));
        return new No("Veiculo").add(motor).add(carroceria).add(eletronica);
    }
}
