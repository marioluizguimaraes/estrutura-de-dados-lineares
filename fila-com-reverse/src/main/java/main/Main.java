package main;

import exceptions.FilaVaziaException;
import model.FilaReversivelArrayCircular;

public class Main {
    public static void main(String[] args) {
        
        FilaReversivelArrayCircular<Integer> fila = new FilaReversivelArrayCircular<>(4);

        System.out.println("=== ESTADO INICIAL ===");
        printDadosFila(fila);


        System.out.println("\n=== INSERÇÕES INICIAIS (enqueue) ===");
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        printDadosFila(fila);
        System.out.println("first(): " + fila.first());


        System.out.println("\n=== REMOÇÕES INICIAIS (dequeue) ===");
        System.out.println("dequeue(): " + fila.dequeue());
        System.out.println("dequeue(): " + fila.dequeue());
        printDadosFila(fila);
        System.out.println("first() após remoções: " + fila.first());


        System.out.println("\n=== TESTE DE REVERSE O(1) ===");
        fila.enqueue(10);
        fila.enqueue(20);

        fila.enqueue(40);
        fila.enqueue(50);
        System.out.println("Antes do reverse: " + fila);
        fila.reverse();
        System.out.println("Após reverse: " + fila);
        printDadosFila(fila);


        System.out.println("\n=== ENQUEUE NO NOVO FIM APÓS REVERSE ===");

        fila.enqueue(99);
        System.out.println("enqueue(99)");
        System.out.println("Array completo: " + fila.estadoInterno());
        System.out.println("-");

        System.out.println("dequeue(): " + fila.dequeue());
        System.out.println("Array completo: " + fila.estadoInterno());
        System.out.println("-");

        System.out.println("dequeue(): " + fila.dequeue());
        System.out.println("Array completo: " + fila.estadoInterno());
        System.out.println("-");

        System.out.println("dequeue(): " + fila.dequeue());
        System.out.println("Array completo: " + fila.estadoInterno());
        System.out.println("-");

        System.out.println("dequeue(): " + fila.dequeue());
        System.out.println("Array completo: " + fila.estadoInterno());
        System.out.println("-");

        fila.enqueue(55);
        System.out.println("enqueue(55)");
        System.out.println("-");

        printDadosFila(fila);


        System.out.println("\n=== TESTE DE DUPLA REVERSÃO ===");
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        System.out.println("Estado base: " + fila);
        fila.reverse();
        System.out.println("Após 1º reverse: " + fila);
        fila.reverse();
        System.out.println("Após 2º reverse: " + fila);
        System.out.println("dequeue(): " + fila.dequeue());
        System.out.println("dequeue(): " + fila.dequeue());
        System.out.println("dequeue(): " + fila.dequeue());
        printDadosFila(fila);


        System.out.println("\n=== TESTE DE CRESCIMENTO AUTOMÁTICO ===");
        for (int i = 1; i <= 12; i++) {
            fila.enqueue(i * 10);
        }
        printDadosFila(fila);


        System.out.println("\n=== TESTE DE REDUÇÃO AUTOMÁTICA (1/3) ===");
        int capacidadeAntes = fila.capacidadeAtual();
        System.out.println("Capacidade antes das remoções: " + capacidadeAntes);

        while (fila.size() > 2) {
            fila.dequeue();
        }

        printDadosFila(fila);
        System.out.println("Capacidade após redução: " + fila.capacidadeAtual());


        System.out.println("\n=== TESTE DE EXCEÇÕES ===");
        while (!fila.isEmpty()) {
            fila.dequeue();
        }

        try {
            fila.dequeue();
        } catch (FilaVaziaException e) {
            System.out.println("Exceção esperada (dequeue vazio): " + e.getMessage());
        }

        try {
            fila.first();
        } catch (FilaVaziaException e) {
            System.out.println("Exceção esperada (first vazio): " + e.getMessage());
        }

        System.out.println("\nExecução finalizada com sucesso.");
    }

    private static void printDadosFila(FilaReversivelArrayCircular<Integer> fila) {
        System.out.println("Fila: " + fila);
        System.out.println("array completo: " + fila.estadoInterno());
        System.out.println("size=" + fila.size() +
                ", isEmpty=" + fila.isEmpty() +
                ", capacidadeAtual=" + fila.capacidadeAtual());
    }

}