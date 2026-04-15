package model;

import exceptions.ListaLigadaVaziaException;
import interfaces.IlistaLigada;

public class ListaLigada implements IlistaLigada {
	private No inicio;
	private No fim;
	private int tamanho;

	@Override
	public void addLast(Object element) {
		No novoNo = new No();
		novoNo.setElemento(element);

		if (isEmpty()) {
			inicio = novoNo;
			fim = novoNo;
		} else {
			fim.setProximo(novoNo);
			fim = novoNo;
		}
		tamanho++;
	}

	@Override
	public Object removeFirst() {
		if (isEmpty()) {
			throw new ListaLigadaVaziaException("A lista ligada está vazia");
		}

		Object elementoRemovido = inicio.getElemento();
		inicio = inicio.getProximo();
		tamanho--;

		if (isEmpty()) {
			fim = null;
		}

		return elementoRemovido;
	}

	@Override
	public Object getFirst() {
		if (isEmpty()) {
			throw new ListaLigadaVaziaException("A lista ligada está vazia");
		}
		return inicio.getElemento();
	}

	@Override
	public int size() {
		return tamanho;
	}

	@Override
	public boolean isEmpty() {
		return tamanho == 0;
	}
}
