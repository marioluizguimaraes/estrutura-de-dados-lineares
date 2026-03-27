package model;

import exceptions.PilhaVaziaException;
import interfaces.Pilha;

import java.util.Arrays;

public class PilhaDuplaArray {

    private static final int capacidadeMin = 1;

    private Object[] dados;
    private int topoVermelho;
    private int topoPreto;

    public PilhaDuplaArray() {
        this(capacidadeMin);
    }

    public PilhaDuplaArray(int capacidadeInicial) {
        int capacidade = Math.max(capacidadeMin, capacidadeInicial);
        this.dados = new Object[capacidade];
        this.topoVermelho = -1;
        this.topoPreto = dados.length;
    }

    // metodos da pilha vermelha
    public void pushRed(Object o) {
        verificaCapacidade();
        dados[++topoVermelho] = o;
    }

    public Object popRed() {
        if (isEmptyRed()) {
            throw new PilhaVaziaException("Pilha vermelha está vazia.");
        }

        Object removido = dados[topoVermelho];
        dados[topoVermelho] = null;
        topoVermelho--;

        reducaoNecessaria();
        return removido;
    }

    public Object topRed() {
        if (isEmptyRed()) {
            throw new PilhaVaziaException("Pilha vermelha está vazia.");
        }
        return dados[topoVermelho];
    }

    public int sizeRed() {
        return topoVermelho + 1;
    }

    public boolean isEmptyRed() {
        return topoVermelho == -1;
    }


    // metodos da pilha preta
    public void pushBlack(Object o) {
        verificaCapacidade();
        dados[--topoPreto] = o;
    }

    public Object popBlack() {
        if (isEmptyBlack()) {
            throw new PilhaVaziaException("Pilha preta está vazia.");
        }

        Object removido = dados[topoPreto];
        dados[topoPreto] = null;
        topoPreto++;

        reducaoNecessaria();
        return removido;
    }

    public Object topBlack() {
        if (isEmptyBlack()) {
            throw new PilhaVaziaException("Pilha preta está vazia.");
        }
        return dados[topoPreto];
    }

    public int sizeBlack() {
        return dados.length - topoPreto;
    }

    public boolean isEmptyBlack() {
        return topoPreto == dados.length;
    }


    // metodos gerais para teste
    public int capacidade() {
        return dados.length;
    }

    public int totalElementos() {
        return sizeRed() + sizeBlack();
    }

    public Object[] elementosAtivos() {
        return Arrays.copyOf(dados, dados.length);
    }

    public Object[] pilhaRed() {
        int qtdRed = sizeRed();
        Object[] red = new Object[qtdRed];

        if (qtdRed > 0) {
            for (int i = 0; i < qtdRed; i++) {
                red[i] = dados[i];
            }
        }

        return red;
    }

    public Object[] pilhaBlack() {
        int qtdBlack = sizeBlack();
        Object[] black = new Object[qtdBlack];

        if (qtdBlack > 0) {
            int indiceOrigem = topoPreto;
            for (int i = 0; i < qtdBlack; i++) {
                black[i] = dados[indiceOrigem + i];
            }
        }

        return black;
    }


    private void verificaCapacidade() {
        if (topoVermelho + 1 == topoPreto) {
            redimensionar(dados.length * 2);
        }
    }

    private void reducaoNecessaria() {
        int total = totalElementos();

        if (dados.length <= capacidadeMin) {
            return;
        }

        if (total * 3 <= dados.length) {
            int novaCapacidade = Math.max(capacidadeMin, dados.length / 2);
            redimensionar(novaCapacidade);
        }
    }

    private void redimensionar(int novaCapacidade) {
        Object[] novo = new Object[novaCapacidade];

        int qtdRed = sizeRed();
        if (qtdRed > 0) {
            for (int i = 0; i < qtdRed; i++) {
                novo[i] = dados[i];
            }
        }


        int qtdBlack = sizeBlack();
        int inicioBlackAntigo = topoPreto;
        int inicioBlackNovo = novaCapacidade - qtdBlack;

        if (qtdBlack > 0) {
            for (int i = 0; i < qtdBlack; i++) {
                novo[inicioBlackNovo + i] = dados[inicioBlackAntigo + i];
            }
        }

        dados = novo;
        topoVermelho = qtdRed - 1;
        topoPreto = inicioBlackNovo;
    }

    public String estadoInterno() {
        return "PilhaDuplaArray{" +
                "capacidade=" + dados.length +
                ", topoVermelho=" + topoVermelho +
                ", topoPreto=" + topoPreto +
                ", sizeRed=" + sizeRed() +
                ", sizeBlack=" + sizeBlack() +
                ", dados=" + Arrays.toString(dados) +
                '}';
    }

    // public Pilha redView() {
    //     return new Pilha() {
    //         @Override
    //         public int size() {
    //             return sizeRed();
    //         }

    //         @Override
    //         public boolean isEmpty() {
    //             return isEmptyRed();
    //         }

    //         @Override
    //         public Object top() {
    //             return topRed();
    //         }

    //         @Override
    //         public void push(Object o) {
    //             pushRed(o);
    //         }

    //         @Override
    //         public Object pop() {
    //             return popRed();
    //         }
    //     };
    // }

    // public Pilha blackView() {
    //     return new Pilha() {
    //         @Override
    //         public int size() {
    //             return sizeBlack();
    //         }

    //         @Override
    //         public boolean isEmpty() {
    //             return isEmptyBlack();
    //         }

    //         @Override
    //         public Object top() {
    //             return topBlack();
    //         }

    //         @Override
    //         public void push(Object o) {
    //             pushBlack(o);
    //         }

    //         @Override
    //         public Object pop() {
    //             return popBlack();
    //         }
    //     };
    // }
}
