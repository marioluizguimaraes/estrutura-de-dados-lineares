# Fila com Reversão em O(1) (Java 21)

Implementação de uma fila baseada em **array circular**, com suporte a:

- `enqueue` em O(1) amortizado
- `dequeue` em O(1) amortizado
- `first` em O(1)
- `reverse` em **O(1)** (sem percorrer elementos)
- crescimento automático ao ficar cheia (duplica capacidade)
- redução automática quando o tamanho chega a 1/3 da capacidade

## Estrutura do projeto

- `src/main/java/org/example/interfaces/IFilaReversivel.java`
- `src/main/java/org/example/exceptions/FilaVaziaException.java`
- `src/main/java/org/example/model/FilaReversivelArrayCircular.java`
- `src/main/java/org/example/Main.java`
- `src/test/java/org/example/model/FilaReversivelArrayCircularTest.java`

## Ideia da reversão O(1)

A fila mantém um índice de início lógico (`inicio`) e um estado booleano (`invertida`).

- Quando `invertida = false`, o deslocamento é calculado no sentido normal.
- Quando `invertida = true`, o deslocamento é calculado no sentido inverso.

Assim, a operação `reverse()` apenas alterna `invertida`, em tempo O(1), sem mexer nos elementos do array.

## Redimensionamento

- Se `tamanho == capacidade`, dobra o array.
- Se `tamanho <= capacidade/3`, reduz pela metade (respeitando capacidade mínima).

## Executar

Com Maven instalado:

- Rodar testes: `mvn test`
- Gerar artefato: `mvn package`

> Se preferir, execute pela IDE usando a classe `Main`.
