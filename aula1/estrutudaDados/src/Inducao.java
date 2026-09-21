import java.util.List;

/**
 * <b>Titulo:</b> Inducao Forte — Contagem de Nos em uma Arvore de Categorias.
 * <p>
 * <b>Funcao testada:</b> {@link #contarNos(Categoria)} — prova por inducao
 * forte que a contagem total de nos de uma arvore é 1 (o proprio no) mais a
 * soma recursiva dos nos de cada sub-arvore filha.
 * <p>
 * <b>Exercicio pertinente:</b> Parte I, secao 6.2 do guia — "Inducao Forte
 * (Segundo Principio da Inducao)" (bloco "Recursao sobre Estrutura em
 * Arvore Exigindo Inducao Forte"). Conceito relacionado ao Exercicio 8 do
 * guia ("Prove Que Seu Algoritmo Sempre Funciona" — Inducao Matematica,
 * Corretude de Algoritmos e Ataque Defensivo), que demonstra o mesmo tipo
 * de prova formal aplicada a um algoritmo recursivo (indução fraca sobre
 * soma de vetor, aqui estendida à indução forte sobre árvores).
 *
 * <p><b>Como executar pelo menu:</b> rode a classe {@code Main} e escolha
 * a opcao {@code 6 - Contar nos por inducao}. Informe o nome do no raiz,
 * a quantidade de filhos e, recursivamente, os dados de cada filho.
 */
public class Inducao {
    public record Categoria(String nome, List<Categoria> filhos) {}
    public int contarNos(Categoria c) {
// Passo Base: nó folha
        if (c.filhos().isEmpty()) return 1;
// Passo Indutivo Forte: o cálculo confia no resultado exato de cada sub-árvore
        return 1 + c.filhos().stream()
                .mapToInt(this::contarNos)
                .sum();
    }
}
