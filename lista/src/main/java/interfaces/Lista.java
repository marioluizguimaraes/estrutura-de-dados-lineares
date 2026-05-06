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

    T replaceElement(No<T> no, T elemento);

    void swapElements(No<T> primeiroNo, No<T> segundoNo);

    No<T> insertBefore(No<T> no, T elemento);

    No<T> insertAfter(No<T> no, T elemento);

    No<T> insertFirst(T elemento);

    No<T> insertLast(T elemento);

    T remove(No<T> no);

    No<T> atRank(int rank);

    int rankOf(No<T> no);

    T elemAtRank(int rank);

    T replaceAtRank(int rank, T elemento);

    void insertAtRank(int rank, T elemento);

    T removeAtRank(int rank);
}
