import exceptions.RankInvalidoException;
import exceptions.VetorVazioException;
import model.VetorArray;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        VetorArray<String> vetor = new VetorArray<>();

        System.out.println("=== ESTADO INICIAL ===");
        printDadosVetor(vetor);

        System.out.println("\n=== TESTE DE VETOR VAZIO ===");
        try {
            vetor.elemAtRank(0);
        } catch (RankInvalidoException e) {
            System.out.println("Excecao esperada (elemAtRank vazio): " + e.getMessage());
        }

        try {
            vetor.replaceAtRank(0, "Teste");
        } catch (RankInvalidoException e) {
            System.out.println("Excecao esperada (replaceAtRank vazio): " + e.getMessage());
        }

        try {
            vetor.removeAtRank(0);
        } catch (VetorVazioException e) {
            System.out.println("Excecao esperada (removeAtRank vazio): " + e.getMessage());
        }

        System.out.println("\n=== INSERCOES INICIAIS ===");
        vetor.insertAtRank(0, "Ana");
        System.out.println("insertAtRank(0, Ana)");
        printDadosVetor(vetor);

        vetor.insertAtRank(1, "Bruno");
        System.out.println("insertAtRank(1, Bruno)");
        printDadosVetor(vetor);

        vetor.insertAtRank(1, "Carla");
        System.out.println("insertAtRank(1, Carla)");
        printDadosVetor(vetor);

        vetor.insertAtRank(0, "Daniel");
        System.out.println("insertAtRank(0, Daniel)");
        printDadosVetor(vetor);

        System.out.println("\n=== ACESSOS POR RANK ===");
        System.out.println("elemAtRank(0): " + vetor.elemAtRank(0));
        System.out.println("elemAtRank(1): " + vetor.elemAtRank(1));
        System.out.println("elemAtRank(2): " + vetor.elemAtRank(2));
        System.out.println("elemAtRank(3): " + vetor.elemAtRank(3));

        System.out.println("\n=== TESTE DE RANKS INVALIDOS ===");
        try {
            vetor.elemAtRank(-1);
        } catch (RankInvalidoException e) {
            System.out.println("Excecao esperada (rank negativo): " + e.getMessage());
        }

        try {
            vetor.elemAtRank(vetor.size());
        } catch (RankInvalidoException e) {
            System.out.println("Excecao esperada (rank igual ao tamanho): " + e.getMessage());
        }

        try {
            vetor.insertAtRank(vetor.size() + 1, "Erro");
        } catch (RankInvalidoException e) {
            System.out.println("Excecao esperada (insercao fora do limite): " + e.getMessage());
        }

        try {
            vetor.removeAtRank(-1);
        } catch (RankInvalidoException e) {
            System.out.println("Excecao esperada (remocao com rank negativo): " + e.getMessage());
        }

        printDadosVetor(vetor);

        System.out.println("\n=== SUBSTITUICOES ===");
        System.out.println("replaceAtRank(0, Eduarda): " + vetor.replaceAtRank(0, "Eduarda"));
        printDadosVetor(vetor);

        System.out.println("replaceAtRank(2, Fernanda): " + vetor.replaceAtRank(2, "Fernanda"));
        printDadosVetor(vetor);

        System.out.println("replaceAtRank(3, Gabriel): " + vetor.replaceAtRank(3, "Gabriel"));
        printDadosVetor(vetor);

        System.out.println("\n=== TESTE DE CRESCIMENTO AUTOMATICO ===");
        int capacidadeAntes = vetor.capacidade();
        System.out.println("Capacidade antes do crescimento: " + capacidadeAntes);

        vetor.insertAtRank(vetor.size(), "Helena");
        vetor.insertAtRank(vetor.size(), "Igor");
        vetor.insertAtRank(vetor.size(), "Julia");
        vetor.insertAtRank(vetor.size(), "Kaique");
        vetor.insertAtRank(vetor.size(), "Larissa");
        vetor.insertAtRank(3, "Marcos");

        printDadosVetor(vetor);
        System.out.println("Capacidade depois do crescimento: " + vetor.capacidade());

        System.out.println("\n=== REMOCOES ===");
        System.out.println("removeAtRank(0): " + vetor.removeAtRank(0));
        printDadosVetor(vetor);

        System.out.println("removeAtRank(2): " + vetor.removeAtRank(2));
        printDadosVetor(vetor);

        System.out.println("removeAtRank(ultimo): " + vetor.removeAtRank(vetor.size() - 1));
        printDadosVetor(vetor);

        System.out.println("\n=== TESTE COM ELEMENTO NULL ===");
        vetor.insertAtRank(0, null);
        System.out.println("insertAtRank(0, null)");
        printDadosVetor(vetor);
        System.out.println("elemAtRank(0): " + vetor.elemAtRank(0));
        System.out.println("replaceAtRank(0, Nulo substituido): " + vetor.replaceAtRank(0, "Nulo substituido"));
        printDadosVetor(vetor);

        System.out.println("\n=== TESTE DE REDUCAO AUTOMATICA ===");
        while (!vetor.isEmpty()) {
            System.out.println("removeAtRank(0): " + vetor.removeAtRank(0));
        }
        printDadosVetor(vetor);

        System.out.println("\n=== TESTE FINAL DE EXCECOES ===");
        try {
            vetor.elemAtRank(0);
        } catch (RankInvalidoException e) {
            System.out.println("Excecao esperada (elemAtRank vazio): " + e.getMessage());
        }

        try {
            vetor.removeAtRank(0);
        } catch (VetorVazioException e) {
            System.out.println("Excecao esperada (removeAtRank vazio): " + e.getMessage());
        }

        System.out.println("\nExecucao finalizada com sucesso.");
    }

    private static void printDadosVetor(VetorArray<String> vetor) {
        System.out.println("Vetor: " + vetor);
        System.out.println("Array: " + Arrays.toString(vetor.toArray()));
        System.out.println("size=" + vetor.size() +
                ", isEmpty=" + vetor.isEmpty() +
                ", capacidade=" + vetor.capacidade());
    }
}
