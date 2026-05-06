package model;

import exceptions.ListaVaziaException;
import exceptions.PosicaoInvalidaException;
import exceptions.RankInvalidoException;
import interfaces.Lista;

import java.util.ArrayList;
import java.util.List;

public class ListaDuplamenteEncadeada<T> implements Lista<T> {

    private final No<T> inicio;
    private final No<T> fim;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        inicio = new No<>();
        fim = new No<>();
        inicio.setProximo(fim);
        fim.setAnterior(inicio);
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
    public boolean isFirst(No<T> no) {
        return validarNo(no) == inicio.getProximo();
    }

    @Override
    public boolean isLast(No<T> no) {
        return validarNo(no) == fim.getAnterior();
    }

    @Override
    public No<T> first() {
        verificarListaVazia("Nao existe primeira posicao: a lista esta vazia.");
        return inicio.getProximo();
    }

    @Override
    public No<T> last() {
        verificarListaVazia("Nao existe ultima posicao: a lista esta vazia.");
        return fim.getAnterior();
    }

    @Override
    public No<T> before(No<T> no) {
        no = validarNo(no);
        if (no.getAnterior() == inicio) {
            throw new PosicaoInvalidaException("A primeira posicao nao possui anterior.");
        }
        return no.getAnterior();
    }

    @Override
    public No<T> after(No<T> no) {
        no = validarNo(no);
        if (no.getProximo() == fim) {
            throw new PosicaoInvalidaException("A ultima posicao nao possui proxima.");
        }
        return no.getProximo();
    }

    @Override
    public T replaceElement(No<T> no, T elemento) {
        no = validarNo(no);
        T antigo = no.getElemento();
        no.setElemento(elemento);
        return antigo;
    }

    @Override
    public void swapElements(No<T> primeiroNo, No<T> segundoNo) {
        primeiroNo = validarNo(primeiroNo);
        segundoNo = validarNo(segundoNo);

        T elementoTemporario = primeiroNo.getElemento();
        primeiroNo.setElemento(segundoNo.getElemento());
        segundoNo.setElemento(elementoTemporario);
    }

    @Override
    public No<T> insertBefore(No<T> no, T elemento) {
        no = validarNo(no);
        return inserirEntre(no.getAnterior(), no, elemento);
    }

    @Override
    public No<T> insertAfter(No<T> no, T elemento) {
        no = validarNo(no);
        return inserirEntre(no, no.getProximo(), elemento);
    }

    @Override
    public No<T> insertFirst(T elemento) {
        return inserirEntre(inicio, inicio.getProximo(), elemento);
    }

    @Override
    public No<T> insertLast(T elemento) {
        return inserirEntre(fim.getAnterior(), fim, elemento);
    }

    @Override
    public T remove(No<T> no) {
        no = validarNo(no);
        T elementoRemovido = no.getElemento();

        no.getAnterior().setProximo(no.getProximo());
        no.getProximo().setAnterior(no.getAnterior());

        no.setAnterior(null);
        no.setProximo(null);
        no.setElemento(null);
        tamanho--;

        return elementoRemovido;
    }

    @Override
    public No<T> atRank(int rank) {
        validarRankExistente(rank);

        No<T> noAtual;
        if (rank <= tamanho / 2) {
            noAtual = inicio.getProximo();
            for (int i = 0; i < rank; i++) {
                noAtual = noAtual.getProximo();
            }
        } else {
            noAtual = fim.getAnterior();
            for (int i = tamanho - 1; i > rank; i--) {
                noAtual = noAtual.getAnterior();
            }
        }

        return noAtual;
    }

    @Override
    public int rankOf(No<T> no) {
        No<T> noBuscado = validarNo(no);
        No<T> noAtual = inicio.getProximo();
        int rank = 0;

        while (noAtual != fim) {
            if (noAtual == noBuscado) {
                return rank;
            }
            noAtual = noAtual.getProximo();
            rank++;
        }

        throw new PosicaoInvalidaException("A posicao informada nao pertence a esta lista.");
    }

    @Override
    public T elemAtRank(int rank) {
        return atRank(rank).element();
    }

    @Override
    public T replaceAtRank(int rank, T elemento) {
        return replaceElement(atRank(rank), elemento);
    }

    @Override
    public void insertAtRank(int rank, T elemento) {
        validarRankParaInsercao(rank);

        if (rank == tamanho) {
            insertLast(elemento);
        } else {
            insertBefore(atRank(rank), elemento);
        }
    }

    @Override
    public T removeAtRank(int rank) {
        return remove(atRank(rank));
    }

    public List<T> toList() {
        List<T> elementos = new ArrayList<>();
        No<T> noAtual = inicio.getProximo();

        while (noAtual != fim) {
            elementos.add(noAtual.getElemento());
            noAtual = noAtual.getProximo();
        }

        return elementos;
    }

    @Override
    public String toString() {
        return toList().toString();
    }

    private No<T> inserirEntre(No<T> anterior, No<T> proximo, T elemento) {
        No<T> novoNo = new No<>(elemento);
        novoNo.setAnterior(anterior);
        novoNo.setProximo(proximo);
        anterior.setProximo(novoNo);
        proximo.setAnterior(novoNo);
        tamanho++;
        return novoNo;
    }

    private No<T> validarNo(No<T> no) {
        if (no == null) {
            throw new PosicaoInvalidaException("O no informado nao pode ser nulo.");
        }

        if (no == inicio || no == fim || no.getAnterior() == null || no.getProximo() == null) {
            throw new PosicaoInvalidaException("O no informado e invalido ou ja foi removido.");
        }

        return no;
    }

    private void verificarListaVazia(String mensagem) {
        if (isEmpty()) {
            throw new ListaVaziaException(mensagem);
        }
    }

    private void validarRankExistente(int rank) {
        if (isEmpty()) {
            throw new RankInvalidoException("Rank invalido: a lista esta vazia.");
        }

        if (rank < 0 || rank >= tamanho) {
            throw new RankInvalidoException(
                    "Rank invalido: " + rank + ". Use um valor entre 0 e " + (tamanho - 1) + "."
            );
        }
    }

    private void validarRankParaInsercao(int rank) {
        if (rank < 0 || rank > tamanho) {
            throw new RankInvalidoException(
                    "Rank invalido para insercao: " + rank + ". Use um valor entre 0 e " + tamanho + "."
            );
        }
    }
}
