package interfaces;

public interface IFilaReversivel<T> {
    void enqueue(T elemento);

    T dequeue();

    T first();

    int size();

    boolean isEmpty();

    void reverse();
}
