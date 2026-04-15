package main;

import exceptions.FilaVaziaException;
import model.Fila;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TESTE 1: estado inicial ===");
        Fila fila = new Fila();
        System.out.println("isEmpty(): " + fila.isEmpty());
        System.out.println("size(): " + fila.size());

        System.out.println("\n=== TESTE 2: enqueue / enfileirar ===");
        fila.enqueue("A");
        fila.enqueue("B");
        fila.enfileirar("C");
        System.out.println("size(): " + fila.size());
        System.out.println("tamanho(): " + fila.tamanho());
        System.out.println("first(): " + fila.first());
        System.out.println("inicio(): " + fila.inicio());

        System.out.println("\n=== TESTE 3: dequeue / desenfileirar ===");
        System.out.println("dequeue(): " + fila.dequeue());
        System.out.println("desenfileirar(): " + fila.desenfileirar());
        System.out.println("first() após remoções: " + fila.first());
        System.out.println("size() após remoções: " + fila.size());

        System.out.println("\n=== TESTE 4: esvaziar fila e checar estado ===");
        System.out.println("dequeue(): " + fila.dequeue());
        System.out.println("isEmpty(): " + fila.isEmpty());
        System.out.println("estaVazia(): " + fila.estaVazia());
        System.out.println("size(): " + fila.size());

        System.out.println("\n=== TESTE 5: exceções em fila vazia ===");
        try {
            fila.dequeue();
        } catch (FilaVaziaException e) {
            System.out.println("OK - dequeue em fila vazia lançou: " + e.getMessage());
        }

        try {
            fila.first();
        } catch (FilaVaziaException e) {
            System.out.println("OK - first em fila vazia lançou: " + e.getMessage());
        }

        System.out.println("\n=== TESTE 6: classe de compatibilidade Fila ===");
        Fila filaCompat = new Fila();
        filaCompat.enqueue(10);
        filaCompat.enqueue(20);
        filaCompat.enqueue(30);
        System.out.println("first(): " + filaCompat.first());
        System.out.println("dequeue(): " + filaCompat.dequeue());
        System.out.println("dequeue(): " + filaCompat.dequeue());
        System.out.println("dequeue(): " + filaCompat.dequeue());
        System.out.println("isEmpty(): " + filaCompat.isEmpty());

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}