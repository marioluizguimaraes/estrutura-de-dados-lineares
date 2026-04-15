package interfaces;

public interface Ifila {
	void enqueue(Object o);

	Object dequeue();

	Object first();

	int size();

	boolean isEmpty();

	default void enfileirar(Object o) {
		enqueue(o);
	}

	default Object desenfileirar() {
		return dequeue();
	}

	default Object inicio() {
		return first();
	}

	default int tamanho() {
		return size();
	}

	default boolean estaVazia() {
		return isEmpty();
	}
}
