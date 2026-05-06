import exceptions.RankInvalidoException;
import exceptions.VetorVazioException;
import model.VetorArray;

import java.util.Arrays;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        VetorArray<String> vetor = new VetorArray<>();

        System.out.println("=== Testando TAD Vetor com Array ===");
        System.out.println("Vetor vazio? " + vetor.isEmpty());

        vetor.insertAtRank(0, "Ana");
        vetor.insertAtRank(1, "Bruno");
        vetor.insertAtRank(2, "Carla");
        vetor.insertAtRank(1, "Daniel");

        System.out.println("Depois das insercoes: " + vetor);
        System.out.println("Tamanho: " + vetor.size());
        System.out.println("Elemento no rank 1: " + vetor.elemAtRank(1));

        String substituido = vetor.replaceAtRank(2, "Beatriz");
        System.out.println("Elemento substituido no rank 2: " + substituido);
        System.out.println("Depois da substituicao: " + vetor);

        String removido = vetor.removeAtRank(0);
        System.out.println("Elemento removido do rank 0: " + removido);
        System.out.println("Depois da remocao: " + vetor);

        executarTestesAutomaticos();
        System.out.println("Todos os testes passaram com sucesso.");
    }

    private static void executarTestesAutomaticos() {
        testarEstadoInicial();
        testarInsercaoNoInicioMeioEFim();
        testarConsultaPorRank();
        testarSubstituicaoNoInicioMeioEFim();
        testarRemocaoNoInicioMeioEFim();
        testarRanksInvalidos();
        testarVetorVazio();
        testarRedimensionamento();
        testarUsoComTiposDiferentes();
        testarElementoNulo();
    }

    private static void testarEstadoInicial() {
        VetorArray<Integer> vetor = new VetorArray<>(1);

        assertTrue(vetor.isEmpty(), "O vetor deveria iniciar vazio.");
        assertEquals(0, vetor.size(), "Tamanho inicial incorreto.");
        assertEquals(2, vetor.capacidade(), "A capacidade minima deveria ser respeitada.");
        assertArrayEquals(new Object[]{}, vetor.toArray(), "O array inicial deveria estar vazio.");
    }

    private static void testarInsercaoNoInicioMeioEFim() {
        VetorArray<Integer> vetor = new VetorArray<>();

        vetor.insertAtRank(0, 20);
        assertArrayEquals(new Object[]{20}, vetor.toArray(), "Falha ao inserir no vetor vazio.");

        vetor.insertAtRank(0, 10);
        assertArrayEquals(new Object[]{10, 20}, vetor.toArray(), "Falha ao inserir no inicio.");

        vetor.insertAtRank(2, 40);
        assertArrayEquals(new Object[]{10, 20, 40}, vetor.toArray(), "Falha ao inserir no fim.");

        vetor.insertAtRank(2, 30);
        assertArrayEquals(new Object[]{10, 20, 30, 40}, vetor.toArray(), "Falha ao inserir no meio.");

        assertEquals(4, vetor.size(), "Tamanho apos insercoes incorreto.");
        assertFalse(vetor.isEmpty(), "O vetor nao deveria estar vazio apos insercoes.");
    }

    private static void testarConsultaPorRank() {
        VetorArray<Integer> vetor = criarVetorBase();

        assertEquals(10, vetor.elemAtRank(0), "Elemento do rank 0 incorreto.");
        assertEquals(20, vetor.elemAtRank(1), "Elemento do rank 1 incorreto.");
        assertEquals(30, vetor.elemAtRank(2), "Elemento do rank 2 incorreto.");
        assertEquals(40, vetor.elemAtRank(3), "Elemento do ultimo rank incorreto.");
        assertEquals(4, vetor.size(), "elemAtRank nao deveria alterar o tamanho.");
    }

    private static void testarSubstituicaoNoInicioMeioEFim() {
        VetorArray<Integer> vetor = criarVetorBase();

        assertEquals(10, vetor.replaceAtRank(0, 11), "replaceAtRank deve retornar o elemento antigo do inicio.");
        assertArrayEquals(new Object[]{11, 20, 30, 40}, vetor.toArray(), "Falha ao substituir no inicio.");

        assertEquals(30, vetor.replaceAtRank(2, 33), "replaceAtRank deve retornar o elemento antigo do meio.");
        assertArrayEquals(new Object[]{11, 20, 33, 40}, vetor.toArray(), "Falha ao substituir no meio.");

        assertEquals(40, vetor.replaceAtRank(3, 44), "replaceAtRank deve retornar o elemento antigo do fim.");
        assertArrayEquals(new Object[]{11, 20, 33, 44}, vetor.toArray(), "Falha ao substituir no fim.");
        assertEquals(4, vetor.size(), "replaceAtRank nao deveria alterar o tamanho.");
    }

    private static void testarRemocaoNoInicioMeioEFim() {
        VetorArray<Integer> vetor = criarVetorBase();

        assertEquals(10, vetor.removeAtRank(0), "removeAtRank deve retornar o elemento removido do inicio.");
        assertArrayEquals(new Object[]{20, 30, 40}, vetor.toArray(), "Falha ao deslocar para esquerda apos remover no inicio.");

        assertEquals(30, vetor.removeAtRank(1), "removeAtRank deve retornar o elemento removido do meio.");
        assertArrayEquals(new Object[]{20, 40}, vetor.toArray(), "Falha ao deslocar para esquerda apos remover no meio.");

        assertEquals(40, vetor.removeAtRank(1), "removeAtRank deve retornar o elemento removido do fim.");
        assertArrayEquals(new Object[]{20}, vetor.toArray(), "Falha ao remover no fim.");
        assertEquals(1, vetor.size(), "Tamanho apos remocoes incorreto.");
    }

    private static void testarRanksInvalidos() {
        VetorArray<Integer> vetor = criarVetorBase();

        assertLancaExcecao(RankInvalidoException.class, () -> vetor.elemAtRank(-1));
        assertLancaExcecao(RankInvalidoException.class, () -> vetor.elemAtRank(vetor.size()));
        assertLancaExcecao(RankInvalidoException.class, () -> vetor.replaceAtRank(-1, 99));
        assertLancaExcecao(RankInvalidoException.class, () -> vetor.replaceAtRank(vetor.size(), 99));
        assertLancaExcecao(RankInvalidoException.class, () -> vetor.insertAtRank(-1, 99));
        assertLancaExcecao(RankInvalidoException.class, () -> vetor.insertAtRank(vetor.size() + 1, 99));
        assertLancaExcecao(RankInvalidoException.class, () -> vetor.removeAtRank(-1));
        assertLancaExcecao(RankInvalidoException.class, () -> vetor.removeAtRank(vetor.size()));

        assertArrayEquals(new Object[]{10, 20, 30, 40}, vetor.toArray(), "Operacoes invalidas nao devem alterar o vetor.");
    }

    private static void testarVetorVazio() {
        VetorArray<Integer> vetor = new VetorArray<>();

        assertLancaExcecao(RankInvalidoException.class, () -> vetor.elemAtRank(0));
        assertLancaExcecao(RankInvalidoException.class, () -> vetor.replaceAtRank(0, 10));
        assertLancaExcecao(VetorVazioException.class, () -> vetor.removeAtRank(0));

        vetor.insertAtRank(0, 10);
        assertArrayEquals(new Object[]{10}, vetor.toArray(), "Deve ser possivel inserir no rank 0 quando o vetor esta vazio.");
    }

    private static void testarRedimensionamento() {
        VetorArray<Integer> vetor = new VetorArray<>(2);

        int capacidadeInicial = vetor.capacidade();

        for (int i = 0; i < 20; i++) {
            vetor.insertAtRank(vetor.size(), i);
        }

        assertTrue(vetor.capacidade() > capacidadeInicial, "O array deveria crescer quando ficasse cheio.");
        assertEquals(20, vetor.size(), "Tamanho apos muitas insercoes incorreto.");

        for (int i = 0; i < 20; i++) {
            assertEquals(i, vetor.elemAtRank(i), "Ordem incorreta apos crescimento do array.");
        }

        for (int i = 0; i < 18; i++) {
            assertEquals(i, vetor.removeAtRank(0), "Remocao sequencial retornou elemento incorreto.");
        }

        assertArrayEquals(new Object[]{18, 19}, vetor.toArray(), "Ordem incorreta apos varias remocoes.");
        assertTrue(vetor.capacidade() >= vetor.size(), "Capacidade nunca pode ficar menor que o tamanho.");
    }

    private static void testarUsoComTiposDiferentes() {
        VetorArray<String> nomes = new VetorArray<>();

        nomes.insertAtRank(0, "Ana");
        nomes.insertAtRank(1, "Bruno");
        nomes.insertAtRank(1, "Carla");

        assertArrayEquals(new Object[]{"Ana", "Carla", "Bruno"}, nomes.toArray(), "Falha ao usar vetor com String.");
        assertEquals("Carla", nomes.removeAtRank(1), "Remocao com String retornou valor incorreto.");
    }

    private static void testarElementoNulo() {
        VetorArray<String> vetor = new VetorArray<>();

        vetor.insertAtRank(0, null);
        vetor.insertAtRank(1, "A");

        assertEquals(null, vetor.elemAtRank(0), "O vetor deve aceitar elemento nulo.");
        assertEquals(null, vetor.replaceAtRank(0, "B"), "replaceAtRank deve retornar null quando esse era o antigo valor.");
        assertArrayEquals(new Object[]{"B", "A"}, vetor.toArray(), "Falha ao substituir elemento nulo.");
    }

    private static VetorArray<Integer> criarVetorBase() {
        VetorArray<Integer> vetor = new VetorArray<>();

        vetor.insertAtRank(0, 10);
        vetor.insertAtRank(1, 20);
        vetor.insertAtRank(2, 30);
        vetor.insertAtRank(3, 40);

        return vetor;
    }

    private static void assertEquals(Object esperado, Object atual, String mensagem) {
        if (!Objects.equals(esperado, atual)) {
            throw new AssertionError(mensagem + " Esperado: " + esperado + ", atual: " + atual);
        }
    }

    private static void assertArrayEquals(Object[] esperado, Object[] atual, String mensagem) {
        if (!Arrays.equals(esperado, atual)) {
            throw new AssertionError(
                    mensagem + " Esperado: " + Arrays.toString(esperado) + ", atual: " + Arrays.toString(atual)
            );
        }
    }

    private static void assertTrue(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

    private static void assertFalse(boolean condicao, String mensagem) {
        assertTrue(!condicao, mensagem);
    }

    private static void assertLancaExcecao(Class<? extends RuntimeException> tipo, Runnable acao) {
        try {
            acao.run();
        } catch (RuntimeException exception) {
            if (tipo.isInstance(exception)) {
                return;
            }

            throw new AssertionError("Excecao inesperada: " + exception.getClass().getSimpleName());
        }

        throw new AssertionError("Era esperada a excecao: " + tipo.getSimpleName());
    }
}
