package org.example;

import Dicionario.HashTable;
import Dicionario.Interfaces.Item;

public class Main {
    public static void main(String[] args) {
        // Capacidade pequena (4) para forçar colisões e demonstrar o encadeamento.
        HashTable tabela = new HashTable(4);

        // insert()
        tabela.insertItem(1, "Ana");
        tabela.insertItem(5, "Bruno");   // 5 % 4 == 1  -> colide com a chave 1
        tabela.insertItem(9, "Carla");   // 9 % 4 == 1  -> colide com as chaves 1 e 5
        tabela.insertItem(2, "Daniel");

        System.out.println("Tabela apos insercoes (note o encadeamento no indice 1):");
        System.out.print(tabela);
        System.out.println("Tamanho: " + tabela.size());

        // find()
        System.out.println("\nfind(5)  -> " + valor(tabela.findElement(5)));
        System.out.println("find(9)  -> " + valor(tabela.findElement(9)));
        System.out.println("find(42) -> " + valor(tabela.findElement(42)));

        // atualizacao de valor existente
        tabela.insertItem(5, "Bruno (atualizado)");
        System.out.println("\nApos atualizar a chave 5:");
        System.out.println("find(5)  -> " + valor(tabela.findElement(5)));
        System.out.println("Tamanho: " + tabela.size());

        // remove()
        System.out.println("\nremove(5) -> " + valor(tabela.removeElement(5)));
        System.out.println("remove(7) -> " + valor(tabela.removeElement(7)));

        System.out.println("\nTabela final:");
        System.out.print(tabela);
        System.out.println("Tamanho: " + tabela.size());
        System.out.println("Vazia? " + tabela.isEmpty());
    }

    private static String valor(Item item) {
        return item == null ? "nao encontrado" : item.value().toString();
    }
}
