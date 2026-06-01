public class Heap {

    private Object[] array;
    private int tamanho;
    private int capacidade;

    public Heap() {
        this.capacidade = 10;
        this.array = new Object[capacidade + 1];
        this.tamanho = 0;
    }

    public void insert(int chave, Object valor) {
        if (tamanho == capacidade) {
            duplicarCapacidade();
        }

        Item item = new Item(chave, valor);

        tamanho++;
        array[tamanho] = item;

        upheap(tamanho);
    }

    public Object removeMin() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila de prioridade está vazia.");
        }

        Item minItem = (Item) array[1];

        array[1] = array[tamanho];
        array[tamanho] = null;
        tamanho--;

        if (!isEmpty()) {
            downheap(1);
        }

        return minItem.getValor();
    }

    public Item min() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila de prioridade está vazia.");
        }

        return (Item) array[1];
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    private void upheap(int index) {
        while (index > 1) {
            int pai = index / 2;

            Item atual = (Item) array[index];
            Item itemPai = (Item) array[pai];

            if (atual.getChave() >= itemPai.getChave()) {
                break;
            }

            trocar(index, pai);
            index = pai;
        }
    }

    private void downheap(int index) {
        while (2 * index <= tamanho) {
            int filhoEsquerdo = 2 * index;
            int filhoDireito = 2 * index + 1;
            int menorFilho = filhoEsquerdo;

            if (filhoDireito <= tamanho) {
                Item direito = (Item) array[filhoDireito];
                Item esquerdo = (Item) array[filhoEsquerdo];

                if (direito.getChave() < esquerdo.getChave()) {
                    menorFilho = filhoDireito;
                }
            }

            Item atual = (Item) array[index];
            Item filho = (Item) array[menorFilho];

            if (atual.getChave() <= filho.getChave()) {
                break;
            }

            trocar(index, menorFilho);
            index = menorFilho;
        }
    }

    private void trocar(int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void duplicarCapacidade() {
        capacidade *= 2;

        Object[] novoArray = new Object[capacidade + 1];

        for (int i = 1; i <= tamanho; i++) {
            novoArray[i] = array[i];
        }

        array = novoArray;
    }
}