package model;

import exceptions.EmptyVectorException;
import exceptions.RankForaDoLimiteException;
import interfaces.Vetor;

import java.util.Arrays;

public class VetorArray<T> implements Vetor<T> {

    private static final int CAPACIDADE_MINIMA = 2;

    private Object[] elementos;
    private int tamanho;

    public VetorArray() {
        this(CAPACIDADE_MINIMA);
    }

    public VetorArray(int capacidadeInicial) {
        int capacidade = Math.max(CAPACIDADE_MINIMA, capacidadeInicial);
        elementos = new Object[capacidade];
        tamanho = 0;
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
    public T elemAtRank(int rank) {
        validarRankExistente(rank);
        return elemento(rank);
    }

    @Override
    public T replaceAtRank(int rank, T elemento) {
        validarRankExistente(rank);

        T elementoAntigo = elemento(rank);
        elementos[rank] = elemento;
        return elementoAntigo;
    }

    @Override
    public void insertAtRank(int rank, T elemento) {
        validarRankParaInsercao(rank);
        aumentarCapacidadeSeNecessario();

        for (int i = tamanho; i > rank; i--) {
            elementos[i] = elementos[i - 1];
        }

        elementos[rank] = elemento;
        tamanho++;
    }

    @Override
    public T removeAtRank(int rank) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vetor esta vazio.");
        }

        validarRankExistente(rank);

        T elementoRemovido = elemento(rank);

        for (int i = rank; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        tamanho--;
        elementos[tamanho] = null;
        reduzirCapacidadeSeNecessario();

        return elementoRemovido;
    }

    public int capacidade() {
        return elementos.length;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementos, tamanho);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @SuppressWarnings("unchecked")
    private T elemento(int rank) {
        return (T) elementos[rank];
    }

    private void validarRankExistente(int rank) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vetor esta vazio.");
        }

        if (rank < 0 || rank >= tamanho) {
            throw new RankForaDoLimiteException(
                    "Rank fora do limite: " + rank + ". Use um valor entre 0 e " + (tamanho - 1) + "."
            );
        }
    }

    private void validarRankParaInsercao(int rank) {
        if (rank < 0 || rank > tamanho) {
            throw new RankForaDoLimiteException(
                    "Rank fora do limite para insercao: " + rank + ". Use um valor entre 0 e " + tamanho + "."
            );
        }
    }

    private void aumentarCapacidadeSeNecessario() {
        if (tamanho == elementos.length) {
            redimensionar(elementos.length * 2);
        }
    }

    private void reduzirCapacidadeSeNecessario() {
        if (elementos.length <= CAPACIDADE_MINIMA) {
            return;
        }

        if (tamanho <= elementos.length / 4) {
            redimensionar(Math.max(CAPACIDADE_MINIMA, elementos.length / 2));
        }
    }

    private void redimensionar(int novaCapacidade) {
        elementos = Arrays.copyOf(elementos, novaCapacidade);
    }
}
