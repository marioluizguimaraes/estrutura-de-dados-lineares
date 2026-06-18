package Dicionario.Interfaces;

public interface Hash {
    public Item findElement(int k);
    public void insertItem(int k, Object o);
    public Item removeElement(int k);

    public int size();
    public boolean isEmpty();
}