# Aula 4 — Análise Assintótica e Algoritmos Recursivos

Projeto que apoia a lista de exercícios da Aula 04 com programas que geram
os dados usados em cada análise (saídas reais, sem valores fabricados).

## Como executar

```bash
cd "aula4/estrutudaDados"
javac -d out src/*.java
java -cp out Main
```

## Exercícios implementados

1. Mapeando um problema recursivo — tamanho total de pastas e subpastas
2. Encontrando o caso base — alçadas de aprovação (e o estouro de pilha sem caso base)
3. Dividir para conquistar — contagem de respostas de pesquisa (dividir → resolver → combinar)
4. A recorrência representa o problema? — Merge Sort instrumentado e Teorema Mestre
5. Árvore de recursão de T(n) = 2T(n/2) + n — custo por nível e total n lg n + n
6. MergeSort ou QuickSort — dados aleatórios, ordenados e com repetições
7. Desconfie da resposta da IAG — verificação independente com os valores exatos da recorrência
8. Funciona hoje. E amanhã? — pior caso do QuickSort x MergeSort, projeção de 10 mil para 1 milhão

## Correções em relação aos slides

- **Método da substituição:** o slide conclui com c = 2/3, mas T(2) = 4 > (2/3)·2·lg 2.
  O passo indutivo exige c ≥ n/⌊n/2⌋ (até 3); com c = 3 base e passo fecham.
- **Quick Sort:** o slide recursa em [p..q] e [q+1..r] (divisão de Hoare) usando o
  particionamento de Lomuto; quando o pivô é o maior elemento (q = r) a chamada se
  repete para sempre. A implementação usa [p..q-1] e [q+1..r].

## O que fica para o aluno

As partes pessoais da lista — escolher a situação real, registrar a conversa com a
IAG (pergunta, resposta, o que aceitou/rejeitou), os pareceres e a reflexão final —
devem ser escritas por você; o programa fornece os dados para fundamentá-las.
