package Dicionario;

import Dicionario.Interfaces.Hash;
import Dicionario.Interfaces.Item;

import java.util.ArrayList;

/**
 * Tabela hash com tratamento de colisões por encadeamento (chaining).
 *
 * Cada posição (bucket) do array é uma lista encadeada (ArrayList) que
 * armazena todos os itens cujas chaves colidem no mesmo índice.
 *
 * Operações principais:
 *   - insertItem (insert)
 *   - findElement (find)
 *   - removeElement (remove)
 */
public class HashTable implements Hash {

    private static final int CAPACIDADE_PADRAO = 17;

    // Array principal da tabela hash. Cada posição guarda uma lista de
    // colisão (ArrayList), que armazena todos os itens cujas chaves caem
    // no mesmo índice. É isso que implementa o encadeamento.
    private final ArrayList<Item>[] tabela;
    private final int capacidade;
    private int size;

    public HashTable() {
        this(CAPACIDADE_PADRAO);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }
        this.capacidade = capacidade;
        this.tabela = new ArrayList[capacidade];
        for (int i = 0; i < capacidade; i++) {
            this.tabela[i] = new ArrayList<>();
        }
        this.size = 0;
    }

    /**
     * Função de hash: mapeia uma chave para um índice válido do array.
     */
    private int hash(int k) {
        return Math.floorMod(k, capacidade);
    }

    /**
     * Insere um novo item. Se a chave já existir, atualiza o valor.
     */
    @Override
    public void insertItem(int k, Object o) {
        ArrayList<Item> listaDeColisao = tabela[hash(k)];

        // Se a chave já existe, apenas atualiza o valor.
        for (Item item : listaDeColisao) {
            if (item.key() == k) {
                item.setValue(o);
                return;
            }
        }

        // Caso contrário, adiciona ao final da lista (encadeamento).
        listaDeColisao.add(new ItemImpl(k, o));
        size++;
    }

    /**
     * Busca o item associado à chave informada.
     * @return o Item encontrado ou null se a chave não existir.
     */
    @Override
    public Item findElement(int k) {
        ArrayList<Item> listaDeColisao = tabela[hash(k)];
        for (Item item : listaDeColisao) {
            if (item.key() == k) {
                return item;
            }
        }
        return null;
    }

    /**
     * Remove o item associado à chave informada.
     * @return o Item removido ou null se a chave não existir.
     */
    @Override
    public Item removeElement(int k) {
        ArrayList<Item> listaDeColisao = tabela[hash(k)];
        for (int i = 0; i < listaDeColisao.size(); i++) {
            Item item = listaDeColisao.get(i);
            if (item.key() == k) {
                listaDeColisao.remove(i);
                size--;
                return item;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacidade; i++) {
            if (!tabela[i].isEmpty()) {
                sb.append("[").append(i).append("] ").append(tabela[i]).append("\n");
            }
        }
        return sb.toString();
    }
}
