package model;

import exceptions.ListaVaziaException;
import exceptions.NoInvalidoException;
import exceptions.ObjetoNuloException;
import interfaces.Lista;

public class ListaDuplamenteEncadeada<T> implements Lista<T> {

    private final No<T> cabeca;
    private final No<T> cauda;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        cabeca = new No<>(null);
        cauda = new No<>(null);

        cabeca.setProximo(cauda);
        cauda.setAnterior(cabeca);
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
        validarNo(no);
        return no == cabeca.getProximo();
    }

    @Override
    public boolean isLast(No<T> no) {
        validarNo(no);
        return no == cauda.getAnterior();
    }

    @Override
    public No<T> first() {
        validarListaNaoVazia();
        return cabeca.getProximo();
    }

    @Override
    public No<T> last() {
        validarListaNaoVazia();
        return cauda.getAnterior();
    }

    @Override
    public No<T> before(No<T> no) {
        validarNo(no);

        if (isFirst(no)) {
            throw new NoInvalidoException("O primeiro no nao possui anterior.");
        }

        return no.getAnterior();
    }

    @Override
    public No<T> after(No<T> no) {
        validarNo(no);

        if (isLast(no)) {
            throw new NoInvalidoException("O ultimo no nao possui proximo.");
        }

        return no.getProximo();
    }

    @Override
    public void replaceElement(No<T> no, T elemento) {
        validarNo(no);
        validarElemento(elemento);
        no.setConteudo(elemento);
    }

    @Override
    public void swapElements(No<T> primeiroNo, No<T> segundoNo) {
        validarNo(primeiroNo);
        validarNo(segundoNo);

        T conteudoTemporario = primeiroNo.getConteudo();
        primeiroNo.setConteudo(segundoNo.getConteudo());
        segundoNo.setConteudo(conteudoTemporario);
    }

    @Override
    public void insertBefore(No<T> no, T elemento) {
        validarNo(no);
        validarElemento(elemento);

        No<T> novoNo = new No<>(elemento);
        inserirEntre(no.getAnterior(), no, novoNo);
    }

    @Override
    public void insertAfter(No<T> no, T elemento) {
        validarNo(no);
        validarElemento(elemento);

        No<T> novoNo = new No<>(elemento);
        inserirEntre(no, no.getProximo(), novoNo);
    }

    @Override
    public void insertFirst(T elemento) {
        validarElemento(elemento);

        No<T> novoNo = new No<>(elemento);
        inserirEntre(cabeca, cabeca.getProximo(), novoNo);
    }

    @Override
    public void insertLast(T elemento) {
        validarElemento(elemento);

        No<T> novoNo = new No<>(elemento);
        inserirEntre(cauda.getAnterior(), cauda, novoNo);
    }

    @Override
    public T remove(No<T> no) {
        validarNo(no);

        No<T> anterior = no.getAnterior();
        No<T> proximo = no.getProximo();

        anterior.setProximo(proximo);
        proximo.setAnterior(anterior);

        tamanho--;

        T conteudoRemovido = no.getConteudo();
        no.setAnterior(null);
        no.setProximo(null);
        no.setConteudo(null);

        return conteudoRemovido;
    }

    public Object[] toArray() {
        Object[] elementos = new Object[tamanho];
        No<T> atual = cabeca.getProximo();

        for (int i = 0; i < tamanho; i++) {
            elementos[i] = atual.getConteudo();
            atual = atual.getProximo();
        }

        return elementos;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("[");
        No<T> atual = cabeca.getProximo();

        while (atual != cauda) {
            resultado.append(atual.getConteudo());

            if (atual.getProximo() != cauda) {
                resultado.append(", ");
            }

            atual = atual.getProximo();
        }

        resultado.append("]");
        return resultado.toString();
    }

    private void inserirEntre(No<T> anterior, No<T> proximo, No<T> novoNo) {
        novoNo.setAnterior(anterior);
        novoNo.setProximo(proximo);
        anterior.setProximo(novoNo);
        proximo.setAnterior(novoNo);
        tamanho++;
    }

    private void validarListaNaoVazia() {
        if (isEmpty()) {
            throw new ListaVaziaException("A lista esta vazia.");
        }
    }

    private void validarNo(No<T> no) {
        validarListaNaoVazia();

        if (no == null) {
            throw new NoInvalidoException("O no informado nao pode ser nulo.");
        }

        if (!contemNo(no)) {
            throw new NoInvalidoException("O no informado nao pertence a lista.");
        }
    }

    private void validarElemento(T elemento) {
        if (elemento == null) {
            throw new ObjetoNuloException("O elemento nao pode ser nulo.");
        }
    }

    private boolean contemNo(No<T> no) {
        No<T> atual = cabeca.getProximo();

        while (atual != cauda) {
            if (atual == no) {
                return true;
            }

            atual = atual.getProximo();
        }

        return false;
    }
}
