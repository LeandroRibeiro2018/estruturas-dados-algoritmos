import java.util.List;

/**
 * Exercício 1 — Mapeando um problema recursivo: tamanho total de uma pasta.
 *
 * Problema original: tamanho total da pasta raiz.
 * Subproblemas: tamanho total de cada subpasta (mesmo problema, menor).
 * Caso base: pasta sem subpastas -> tamanho = soma dos seus arquivos.
 * Cada chamada desce um nível da hierarquia, que é finita; por isso termina.
 */
public class EstruturaPastas {

    public record Pasta(String nome, long tamanhoArquivosKb, List<Pasta> subpastas) {
        static Pasta folha(String nome, long kb) {
            return new Pasta(nome, kb, List.of());
        }
    }

    public static Pasta exemplo() {
        return new Pasta("Projetos", 120, List.of(
                new Pasta("Mestrado", 300, List.of(
                        Pasta.folha("Aula03", 850),
                        Pasta.folha("Aula04", 920))),
                new Pasta("Trabalho", 50, List.of(
                        new Pasta("Clientes", 400, List.of(
                                Pasta.folha("ClienteA", 1200),
                                Pasta.folha("ClienteB", 640))),
                        Pasta.folha("Relatorios", 2100))),
                Pasta.folha("Pessoal", 780)));
    }

    public static long tamanhoTotal(Pasta p, int nivel, StringBuilder trace) {
        String recuo = "  ".repeat(nivel);
        if (p.subpastas().isEmpty()) { // caso base
            trace.append(recuo).append(p.nome()).append(" -> caso base: ")
                    .append(p.tamanhoArquivosKb()).append(" KB\n");
            return p.tamanhoArquivosKb();
        }
        trace.append(recuo).append(p.nome()).append(" -> divide em ")
                .append(p.subpastas().size()).append(" subpastas\n");
        long total = p.tamanhoArquivosKb();
        for (Pasta sub : p.subpastas()) {
            total += tamanhoTotal(sub, nivel + 1, trace); // subproblema menor
        }
        trace.append(recuo).append(p.nome()).append(" = ").append(total).append(" KB (combina)\n");
        return total;
    }
}
