package model;

public class No<T> {

    private No<T> anterior;
    private T elemento;
    private No<T> proximo;

    public No() {
    }

    public No(T elemento) {
        this.elemento = elemento;
    }

    public T element() {
        return elemento;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public No<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(No<T> anterior) {
        this.anterior = anterior;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }
}
