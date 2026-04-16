package model;

import exceptions.FilaVaziaException;
import interfaces.IFilaReversivel;

public class FilaReversivelArrayCircular<T> implements IFilaReversivel<T> {
    private static final int CAPACIDADE_MINIMA = 4;

    private Object[] elementos;
    private int inicio;
    private int tamanho;
    private boolean invertida;

    public FilaReversivelArrayCircular() {
        this(CAPACIDADE_MINIMA);
    }

    public FilaReversivelArrayCircular(int capacidadeInicial) {
        if (capacidadeInicial < CAPACIDADE_MINIMA) {
            capacidadeInicial = CAPACIDADE_MINIMA;
        }

        this.elementos = new Object[capacidadeInicial];
        this.inicio = 0;
        this.tamanho = 0;
        this.invertida = false;
    }

    @Override
    public void enqueue(T elemento) {
        if (tamanho == elementos.length) {
            redimensionar(elementos.length * 2);
        }

        int indiceNovoFim = indiceLogico(tamanho);
        elementos[indiceNovoFim] = elemento;
        tamanho++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila está vazia.");
        }

        int indicePrimeiro = inicio;
        @SuppressWarnings("unchecked")
        T removido = (T) elementos[indicePrimeiro];
        elementos[indicePrimeiro] = null;

        tamanho--;

        if (tamanho == 0) {
            inicio = 0;
        } else if (!invertida) {
            inicio = incrementar(inicio);
        } else {
            inicio = decrementar(inicio);
        }

        ajustarReducaoSeNecessario();
        return removido;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila está vazia.");
        }

        @SuppressWarnings("unchecked")
        T primeiro = (T) elementos[inicio];
        return primeiro;
    }

    @Override
    public int size() {
        return tamanho;
    }

    @Override
    public boolean isEmpty() {
        return tamanho == 0;
    }

    @Override
    public void reverse() {
        if (tamanho <= 1) {
            invertida = !invertida;
            return;
        }

        // O novo início lógico após inverter deve ser o antigo fim lógico.
        inicio = indiceLogico(tamanho - 1);
        invertida = !invertida;
    }

    public int capacidadeAtual() {
        return elementos.length;
    }

    public String estadoInterno() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < elementos.length; i++) {
            sb.append(elementos[i]);
            if (i < elementos.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void ajustarReducaoSeNecessario() {
        int capacidadeAtual = elementos.length;
        if (capacidadeAtual == CAPACIDADE_MINIMA) {
            return;
        }

        if (tamanho <= capacidadeAtual / 3) {
            int novaCapacidade = Math.max(CAPACIDADE_MINIMA, capacidadeAtual / 2);
            redimensionar(novaCapacidade);
        }
    }

    private void redimensionar(int novaCapacidade) {
        Object[] novoArray = new Object[novaCapacidade];

        for (int i = 0; i < tamanho; i++) {
            novoArray[i] = elementos[indiceLogico(i)];
        }

        elementos = novoArray;
        inicio = 0;
        invertida = false;
    }

    private int indiceLogico(int deslocamento) {
        int capacidade = elementos.length;

        if (!invertida) {
            return (inicio + deslocamento) % capacidade;
        }

        int indice = (inicio - deslocamento) % capacidade;
        return indice < 0 ? indice + capacidade : indice;
    }

    private int incrementar(int indice) {
        return (indice + 1) % elementos.length;
    }

    private int decrementar(int indice) {
        int valor = (indice - 1) % elementos.length;
        return valor < 0 ? valor + elementos.length : valor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tamanho; i++) {
            sb.append(elementos[indiceLogico(i)]);
            if (i < tamanho - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
