package interfaces;

public interface Pilha {
    int size();
    boolean isEmpty();
    Object top();
    void push(Object o);
    Object pop();
}
