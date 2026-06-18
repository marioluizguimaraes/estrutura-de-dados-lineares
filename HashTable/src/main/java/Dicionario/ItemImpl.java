package Dicionario;

import Dicionario.Interfaces.Item;

public class ItemImpl implements Item {
    private int key;
    private Object value;

    public ItemImpl(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int key() {
        return key;
    }

    @Override
    public void setValue(Object o) {
        this.value = o;
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + key + " -> " + value + ")";
    }
}
