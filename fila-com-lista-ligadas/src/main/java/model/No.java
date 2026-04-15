package model;

public class No {
    private Object elemento;
    private No proximo;
    public Object getElemento() {
        return elemento;
    }
    public void setElemento(Object o){
        elemento = o;
    }
    public No getProximo(){
        return this.proximo;
    }
    public void setProximo(No proximoNo){
        this.proximo = proximoNo;
    }
}

