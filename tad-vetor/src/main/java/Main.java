import exceptions.EmptyVectorException;
import exceptions.RankForaDoLimiteException;
import model.VetorArray;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        VetorArray<String> vetor = new VetorArray<>();

        System.out.println("=== ESTADO INICIAL ===");
        System.out.println("Vetor: " + vetor);
        System.out.println("Array: " + Arrays.toString(vetor.toArray()));
        System.out.println("size=" + vetor.size() +
                ", isEmpty=" + vetor.isEmpty() +
                ", capacidade=" + vetor.capacidade());

        System.out.println("\n=== TESTES COM VETOR VAZIO ===");
        try {
            vetor.elemAtRank(0);
        } catch (EmptyVectorException e) {
            System.out.println("Excecao esperada em elemAtRank vazio: " + e.getMessage());
        }

        try {
            vetor.replaceAtRank(0, "Teste");
        } catch (EmptyVectorException e) {
            System.out.println("Excecao esperada em replaceAtRank vazio: " + e.getMessage());
        }

        try {
            vetor.removeAtRank(0);
        } catch (EmptyVectorException e) {
            System.out.println("Excecao esperada em removeAtRank vazio: " + e.getMessage());
        }

        System.out.println("\n=== INSERCOES POR RANK ===");
        vetor.insertAtRank(0, "Ana");
        System.out.println("insertAtRank(0, Ana) -> " + vetor);

        vetor.insertAtRank(1, "Bruno");
        System.out.println("insertAtRank(1, Bruno) -> " + vetor);

        vetor.insertAtRank(1, "Carla");
        System.out.println("insertAtRank(1, Carla) -> " + vetor);

        vetor.insertAtRank(0, "Daniel");
        System.out.println("insertAtRank(0, Daniel) -> " + vetor);

        vetor.insertAtRank(vetor.size(), "Eduarda");
        System.out.println("insertAtRank(size, Eduarda) -> " + vetor);

        System.out.println("size=" + vetor.size() +
                ", isEmpty=" + vetor.isEmpty() +
                ", capacidade=" + vetor.capacidade());

        System.out.println("\n=== ACESSOS POR RANK ===");
        System.out.println("elemAtRank(0): " + vetor.elemAtRank(0));
        System.out.println("elemAtRank(1): " + vetor.elemAtRank(1));
        System.out.println("elemAtRank(2): " + vetor.elemAtRank(2));
        System.out.println("elemAtRank(3): " + vetor.elemAtRank(3));
        System.out.println("elemAtRank(4): " + vetor.elemAtRank(4));

        System.out.println("\n=== TESTES DE RANK INVALIDO ===");
        try {
            vetor.elemAtRank(-1);
        } catch (RankForaDoLimiteException e) {
            System.out.println("Excecao esperada em rank negativo: " + e.getMessage());
        }

        try {
            vetor.elemAtRank(vetor.size());
        } catch (RankForaDoLimiteException e) {
            System.out.println("Excecao esperada em rank igual ao tamanho: " + e.getMessage());
        }

        try {
            vetor.insertAtRank(vetor.size() + 1, "Erro");
        } catch (RankForaDoLimiteException e) {
            System.out.println("Excecao esperada em insercao fora do limite: " + e.getMessage());
        }

        try {
            vetor.replaceAtRank(-1, "Erro");
        } catch (RankForaDoLimiteException e) {
            System.out.println("Excecao esperada em replaceAtRank com rank negativo: " + e.getMessage());
        }

        try {
            vetor.replaceAtRank(vetor.size(), "Erro");
        } catch (RankForaDoLimiteException e) {
            System.out.println("Excecao esperada em replaceAtRank com rank igual ao tamanho: " + e.getMessage());
        }

        try {
            vetor.removeAtRank(-1);
        } catch (RankForaDoLimiteException e) {
            System.out.println("Excecao esperada em remocao com rank negativo: " + e.getMessage());
        }

        System.out.println("\n=== SUBSTITUICOES ===");
        System.out.println("replaceAtRank(0, Amanda) retornou: " + vetor.replaceAtRank(0, "Amanda"));
        System.out.println("Vetor -> " + vetor);

        System.out.println("replaceAtRank(2, Fernanda) retornou: " + vetor.replaceAtRank(2, "Fernanda"));
        System.out.println("Vetor -> " + vetor);

        System.out.println("replaceAtRank(ultimo, Gabriel) retornou: " +
                vetor.replaceAtRank(vetor.size() - 1, "Gabriel"));
        System.out.println("Vetor -> " + vetor);

        System.out.println("\n=== CRESCIMENTO AUTOMATICO DO ARRAY ===");
        System.out.println("Capacidade antes: " + vetor.capacidade());
        vetor.insertAtRank(vetor.size(), "Helena");
        vetor.insertAtRank(vetor.size(), "Igor");
        vetor.insertAtRank(vetor.size(), "Julia");
        vetor.insertAtRank(vetor.size(), "Kaique");
        vetor.insertAtRank(3, "Larissa");
        System.out.println("Vetor depois de varias insercoes -> " + vetor);
        System.out.println("Array: " + Arrays.toString(vetor.toArray()));
        System.out.println("size=" + vetor.size() + ", capacidade=" + vetor.capacidade());

        System.out.println("\n=== TESTE COM ELEMENTO NULL ===");
        vetor.insertAtRank(0, null);
        System.out.println("insertAtRank(0, null) -> " + vetor);
        System.out.println("elemAtRank(0): " + vetor.elemAtRank(0));
        System.out.println("replaceAtRank(0, Nulo substituido) retornou: " +
                vetor.replaceAtRank(0, "Nulo substituido"));
        System.out.println("Vetor -> " + vetor);

        System.out.println("\n=== REMOCOES POR RANK ===");
        System.out.println("removeAtRank(0) retornou: " + vetor.removeAtRank(0));
        System.out.println("Vetor -> " + vetor);

        System.out.println("removeAtRank(2) retornou: " + vetor.removeAtRank(2));
        System.out.println("Vetor -> " + vetor);

        System.out.println("removeAtRank(ultimo) retornou: " + vetor.removeAtRank(vetor.size() - 1));
        System.out.println("Vetor -> " + vetor);

        System.out.println("\n=== ESVAZIANDO O MESMO VETOR ===");
        while (!vetor.isEmpty()) {
            System.out.println("Removido: " + vetor.removeAtRank(0) +
                    " -> " + vetor +
                    " | size=" + vetor.size() +
                    ", capacidade=" + vetor.capacidade());
        }

        System.out.println("\n=== ESTADO FINAL ===");
        System.out.println("Vetor: " + vetor);
        System.out.println("Array: " + Arrays.toString(vetor.toArray()));
        System.out.println("size=" + vetor.size() +
                ", isEmpty=" + vetor.isEmpty() +
                ", capacidade=" + vetor.capacidade());

        if (vetor.capacidade() < 2) {
            throw new IllegalStateException("Erro: a capacidade ficou menor que a capacidade minima.");
        }

        System.out.println("Capacidade minima respeitada: " + vetor.capacidade());

        try {
            vetor.removeAtRank(0);
        } catch (EmptyVectorException e) {
            System.out.println("Excecao esperada ao remover no final vazio: " + e.getMessage());
        }

        System.out.println("\nExecucao finalizada com sucesso.");
    }
}
