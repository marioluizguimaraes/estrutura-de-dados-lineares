public class Item {

    private int chave;
    private Object valor;

    public Item(int chave, Object valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public int getChave() {
        return chave;
    }

    public Object getValor() {
        return valor;
    }
}