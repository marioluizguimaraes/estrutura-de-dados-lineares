package interfaces;

import model.No;

public interface Lista<T> {

    int size();

    boolean isEmpty();

    boolean isFirst(No<T> no);

    boolean isLast(No<T> no);

    No<T> first();

    No<T> last();

    No<T> before(No<T> no);

    No<T> after(No<T> no);

    void replaceElement(No<T> no, T elemento);

    void swapElements(No<T> primeiroNo, No<T> segundoNo);

    void insertBefore(No<T> no, T elemento);

    void insertAfter(No<T> no, T elemento);

    void insertFirst(T elemento);

    void insertLast(T elemento);

    T remove(No<T> no);
}
