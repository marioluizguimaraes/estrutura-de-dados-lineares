package model;

import exceptions.FilaVaziaException;
import exceptions.ListaLigadaVaziaException;
import interfaces.Ifila;

public class FIla implements Ifila {
	private final ListaLigada lista;

	public FIla() {
		this.lista = new ListaLigada();
	}

	@Override
	public void enqueue(Object o) {
		lista.addLast(o);
	}

	@Override
	public Object dequeue() {
		try {
			return lista.removeFirst();
		} catch (ListaLigadaVaziaException e) {
			throw new FilaVaziaException("A fila está vazia");
		}
	}

	@Override
	public Object first() {
		try {
			return lista.getFirst();
		} catch (ListaLigadaVaziaException e) {
			throw new FilaVaziaException("A fila está vazia");
		}
	}

	@Override
	public int size() {
		return lista.size();
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}
}
