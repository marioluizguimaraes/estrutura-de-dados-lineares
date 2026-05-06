import exceptions.ListaVaziaException;
import exceptions.PosicaoInvalidaException;
import exceptions.RankInvalidoException;
import model.ListaDuplamenteEncadeada;
import model.No;

public class Main {

    private static void mostrarLista(String mensagem, ListaDuplamenteEncadeada<String> lista) {
        System.out.println(mensagem + " " + lista + " | tamanho: " + lista.size());
    }

    public static void main(String[] args) {
        ListaDuplamenteEncadeada<String> lista = new ListaDuplamenteEncadeada<>();

        System.out.println("=== TESTE 1: estado inicial ===");
        System.out.println("isEmpty(): " + lista.isEmpty());
        System.out.println("size(): " + lista.size());
        mostrarLista("lista:", lista);

        System.out.println("\n=== TESTE 2: inserindo nas pontas ===");
        No<String> noAna = lista.insertFirst("Ana");
        No<String> noBruno = lista.insertLast("Bruno");
        No<String> noCarla = lista.insertLast("Carla");
        mostrarLista("apos insertFirst e insertLast:", lista);
        System.out.println("first(): " + lista.first().element());
        System.out.println("last(): " + lista.last().element());
        System.out.println("isFirst(Ana): " + lista.isFirst(noAna));
        System.out.println("isLast(Carla): " + lista.isLast(noCarla));

        System.out.println("\n=== TESTE 3: inserindo antes e depois de posicoes ===");
        No<String> noDaniel = lista.insertAfter(noAna, "Daniel");
        No<String> noEva = lista.insertBefore(noCarla, "Eva");
        mostrarLista("apos insertAfter(Ana) e insertBefore(Carla):", lista);
        System.out.println("after(Ana): " + lista.after(noAna).element());
        System.out.println("before(Carla): " + lista.before(noCarla).element());
        System.out.println("rankOf(Daniel): " + lista.rankOf(noDaniel));
        System.out.println("rankOf(Eva): " + lista.rankOf(noEva));

        System.out.println("\n=== TESTE 4: acessando por rank com atRank ===");
        System.out.println("atRank(0): " + lista.atRank(0).element());
        System.out.println("atRank(2): " + lista.atRank(2).element());
        System.out.println("elemAtRank(4): " + lista.elemAtRank(4));
        System.out.println("Obs: atRank procura a partir do inicio ou do fim, usando a metade mais proxima.");

        System.out.println("\n=== TESTE 5: alterando elementos ===");
        System.out.println("replaceElement(Bruno, Bianca) retornou: " + lista.replaceElement(noBruno, "Bianca"));
        System.out.println("replaceAtRank(0, Amanda) retornou: " + lista.replaceAtRank(0, "Amanda"));
        mostrarLista("apos substituicoes:", lista);

        System.out.println("\n=== TESTE 6: trocando elementos entre duas posicoes ===");
        lista.swapElements(noDaniel, noCarla);
        mostrarLista("apos swapElements(Daniel, Carla):", lista);

        System.out.println("\n=== TESTE 7: inserindo e removendo por rank ===");
        lista.insertAtRank(2, "Felipe");
        mostrarLista("apos insertAtRank(2, Felipe):", lista);
        System.out.println("removeAtRank(3): " + lista.removeAtRank(3));
        mostrarLista("apos removeAtRank(3):", lista);

        System.out.println("\n=== TESTE 8: removendo por posicao ===");
        System.out.println("remove(Eva): " + lista.remove(noEva));
        mostrarLista("apos remover Eva:", lista);
        System.out.println("remove(first): " + lista.remove(lista.first()));
        mostrarLista("apos remover primeira posicao:", lista);

        System.out.println("\n=== TESTE 9: esvaziando a mesma lista ===");
        System.out.println("removeAtRank(0): " + lista.removeAtRank(0));
        System.out.println("removeAtRank(0): " + lista.removeAtRank(0));
        System.out.println("removeAtRank(0): " + lista.removeAtRank(0));
        mostrarLista("lista vazia:", lista);
        System.out.println("isEmpty(): " + lista.isEmpty());

        System.out.println("\n=== TESTE 10: testando excecoes ===");
        try {
            lista.first();
        } catch (ListaVaziaException e) {
            System.out.println("OK - first em lista vazia: " + e.getMessage());
        }

        try {
            lista.atRank(0);
        } catch (RankInvalidoException e) {
            System.out.println("OK - atRank invalido: " + e.getMessage());
        }

        try {
            lista.remove(noEva);
        } catch (PosicaoInvalidaException e) {
            System.out.println("OK - remover posicao ja removida: " + e.getMessage());
        }

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}
