package interfaces;

public interface IlistaLigada {
	void addLast(Object element);

	Object removeFirst();

	Object getFirst();

	int size();

	boolean isEmpty();

	default void inserirNoFim(Object elemento) {
		addLast(elemento);
	}

	default Object removerDoInicio() {
		return removeFirst();
	}

	default Object primeiro() {
		return getFirst();
	}
}
