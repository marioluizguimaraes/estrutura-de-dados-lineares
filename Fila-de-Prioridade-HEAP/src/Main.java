public class Main {
    public static void main(String[] args) {

        Heap heap = new Heap();

        heap.insert(41, "Item 41");
        heap.insert(15, "Item 15");
        heap.insert(8, "Item 8");
        heap.insert(22, "Item 22");
        heap.insert(4, "Item 4");
        heap.insert(10, "Item 10");
        heap.insert(3, "Item 3");

        System.out.println("Tamanho do heap: " + heap.size());

        Item menor = heap.min();
        System.out.println("Menor item:");
        System.out.println("Chave: " + menor.getChave());
        System.out.println("Valor: " + menor.getValor());

        System.out.println("\nRemovendo os elementos pela prioridade:");

        while (!heap.isEmpty()) {
            System.out.println(heap.removeMin());
        }

        System.out.println("\nTamanho final do heap: " + heap.size());
    }
}