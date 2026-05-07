package interfaces;

public interface Vetor<T> {

    int size();

    boolean isEmpty();

    T elemAtRank(int rank);

    T replaceAtRank(int rank, T elemento);

    void insertAtRank(int rank, T elemento);

    T removeAtRank(int rank);
}
