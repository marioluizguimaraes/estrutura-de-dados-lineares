package main;

import exceptions.PilhaVaziaException;
import model.PilhaDuplaArray;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        PilhaDuplaArray piha = new PilhaDuplaArray();

        System.out.println("=== ESTADO INICIAL ===");
        printDadosPilha(piha);

        System.out.println("\n=== INSERÇÕES INICIAIS ===");
        piha.pushRed("R1");
        piha.pushRed("R2");
        piha.pushRed("R3");
        piha.pushRed("R4");

        piha.pushBlack("P1");
        piha.pushBlack("P2");

        printDadosPilha(piha);
        System.out.println("Topo vermelho: " + piha.topRed());
        System.out.println("Topo preto: " + piha.topBlack());


        System.out.println("\n=== TESTE DE CRESCIMENTO AUTOMÁTICO ===");
        for (int i = 4; i <= 12; i++) {
            piha.pushRed("R" + i);
        }
        for (int i = 3; i <= 10; i++) {
            piha.pushBlack("P" + i);
        }

        printDadosPilha(piha);
        System.out.println("Capacidade após crescimento: " + piha.capacidade());


        System.out.println("\n=== REMOÇÕES ===");
        System.out.println("popRed: " + piha.popRed());
        System.out.println("popRed: " + piha.popRed());
        System.out.println("popBlack: " + piha.popBlack());
        System.out.println("popBlack: " + piha.popBlack());
        printDadosPilha(piha);

        System.out.println("\n=== TESTE DE REDUÇÃO AUTOMÁTICA ===");
        while (!piha.isEmptyRed()) {
            piha.popRed();
        }
        while (!piha.isEmptyBlack()) {
            piha.popBlack();
        }

        printDadosPilha(piha);
        System.out.println("Capacidade após redução: " + piha.capacidade());


        System.out.println("\n=== TESTE DE EXCEÇÃO ===");
        try {
            piha.popRed();
        } catch (PilhaVaziaException e) {
            System.out.println("Exceção esperada (vermelha): " + e.getMessage());
        }

        try {
            piha.popBlack();
        } catch (PilhaVaziaException e) {
            System.out.println("Exceção esperada (pop preto vazio): " + e.getMessage());
        }

        try {
            piha.topBlack();
        } catch (PilhaVaziaException e) {
            System.out.println("Exceção esperada (preta): " + e.getMessage());
        }

        try {
            piha.topRed();
        } catch (PilhaVaziaException e) {
            System.out.println("Exceção esperada (topo vermelho vazio): " + e.getMessage());
        }

        System.out.println("\nExecução finalizada com sucesso.");
    }

    private static void printDadosPilha(PilhaDuplaArray pilhas) {
        System.out.println(pilhas.estadoInterno());
        System.out.println("sizeRed=" + pilhas.sizeRed() +
                ", sizeBlack=" + pilhas.sizeBlack() +
                ", total=" + pilhas.totalElementos());
        System.out.println("array completo: " + Arrays.toString(pilhas.elementosAtivos()));
        System.out.println("Pilha vermelha: " + Arrays.toString(pilhas.pilhaRed()));
        System.out.println("Pilha preta: " + Arrays.toString(pilhas.pilhaBlack()));
    }
}


