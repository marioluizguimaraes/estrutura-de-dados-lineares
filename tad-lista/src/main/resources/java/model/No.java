package model;

public class No<T> {

    private No<T> anterior;
    private T conteudo;
    private No<T> proximo;

    public No(T conteudo) {
        this.conteudo = conteudo;
    }

    public No<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(No<T> anterior) {
        this.anterior = anterior;
    }

    public T getConteudo() {
        return conteudo;
    }

    public void setConteudo(T conteudo) {
        this.conteudo = conteudo;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return String.valueOf(conteudo);
    }
}
