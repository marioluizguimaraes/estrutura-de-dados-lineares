import exceptions.ListaVaziaException;
import exceptions.NoInvalidoException;
import exceptions.ObjetoNuloException;
import model.ListaDuplamenteEncadeada;
import model.No;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ListaDuplamenteEncadeada<String> lista = new ListaDuplamenteEncadeada<>();

        System.out.println("=== ESTADO INICIAL ===");
        System.out.println("Lista: " + lista);
        System.out.println("Array: " + Arrays.toString(lista.toArray()));
        System.out.println("size=" + lista.size() + ", isEmpty=" + lista.isEmpty());

        System.out.println("\n=== TESTES COM LISTA VAZIA ===");
        try {
            lista.first();
        } catch (ListaVaziaException e) {
            System.out.println("Excecao esperada ao buscar primeiro: " + e.getMessage());
        }

        try {
            lista.last();
        } catch (ListaVaziaException e) {
            System.out.println("Excecao esperada ao buscar ultimo: " + e.getMessage());
        }

        System.out.println("\n=== INSERCOES NAS PONTAS ===");
        lista.insertFirst("Ana");
        System.out.println("insertFirst(Ana) -> " + lista);

        lista.insertLast("Bruno");
        System.out.println("insertLast(Bruno) -> " + lista);

        lista.insertFirst("Carla");
        System.out.println("insertFirst(Carla) -> " + lista);

        lista.insertLast("Daniel");
        System.out.println("insertLast(Daniel) -> " + lista);

        System.out.println("size=" + lista.size() + ", isEmpty=" + lista.isEmpty());

        System.out.println("\n=== ACESSO AO PRIMEIRO E ULTIMO NO ===");
        No<String> primeiro = lista.first();
        No<String> ultimo = lista.last();
        System.out.println("Primeiro: " + primeiro.getConteudo());
        System.out.println("Ultimo: " + ultimo.getConteudo());
        System.out.println("isFirst(primeiro): " + lista.isFirst(primeiro));
        System.out.println("isLast(ultimo): " + lista.isLast(ultimo));

        System.out.println("\n=== INSERCOES ANTES E DEPOIS DE UM NO ===");
        lista.insertAfter(primeiro, "Eduarda");
        System.out.println("insertAfter(primeiro, Eduarda) -> " + lista);

        lista.insertBefore(ultimo, "Fernanda");
        System.out.println("insertBefore(ultimo, Fernanda) -> " + lista);

        No<String> noDoMeio = lista.after(primeiro);
        System.out.println("No depois do primeiro: " + lista.after(primeiro).getConteudo());
        System.out.println("No antes do ultimo: " + lista.before(ultimo).getConteudo());

        System.out.println("\n=== TESTES NEGATIVOS DE PRIMEIRO E ULTIMO ===");
        System.out.println("isFirst(ultimo): " + lista.isFirst(ultimo));
        System.out.println("isLast(primeiro): " + lista.isLast(primeiro));
        System.out.println("isFirst(noDoMeio): " + lista.isFirst(noDoMeio));
        System.out.println("isLast(noDoMeio): " + lista.isLast(noDoMeio));

        System.out.println("\n=== SUBSTITUICAO E TROCA DE ELEMENTOS ===");
        lista.replaceElement(primeiro, "Amanda");
        System.out.println("replaceElement(primeiro, Amanda) -> " + lista);

        lista.swapElements(primeiro, ultimo);
        System.out.println("swapElements(primeiro, ultimo) -> " + lista);
        System.out.println("Conteudo do primeiro no guardado: " + primeiro.getConteudo());
        System.out.println("Conteudo do ultimo no guardado: " + ultimo.getConteudo());

        System.out.println("\n=== REMOCOES USANDO A MESMA LISTA ===");
        System.out.println("remove(primeiro): " + lista.remove(primeiro));
        System.out.println("Lista apos remover primeiro no guardado -> " + lista);

        No<String> novoPrimeiro = lista.first();
        System.out.println("remove(novoPrimeiro): " + lista.remove(novoPrimeiro));
        System.out.println("Lista apos remover novo primeiro -> " + lista);

        System.out.println("remove(ultimo): " + lista.remove(ultimo));
        System.out.println("Lista apos remover ultimo no guardado -> " + lista);

        System.out.println("\n=== TESTES DE EXCECOES ===");
        try {
            lista.before(lista.first());
        } catch (NoInvalidoException e) {
            System.out.println("Excecao esperada ao pedir before do primeiro: " + e.getMessage());
        }

        try {
            lista.after(lista.last());
        } catch (NoInvalidoException e) {
            System.out.println("Excecao esperada ao pedir after do ultimo: " + e.getMessage());
        }

        try {
            lista.remove(null);
        } catch (NoInvalidoException e) {
            System.out.println("Excecao esperada ao remover no nulo: " + e.getMessage());
        }

        try {
            lista.insertLast(null);
        } catch (ObjetoNuloException e) {
            System.out.println("Excecao esperada ao inserir null: " + e.getMessage());
        }

        No<String> noEstranho = new No<>("Intruso");
        try {
            lista.remove(noEstranho);
        } catch (NoInvalidoException e) {
            System.out.println("Excecao esperada ao remover no estrangeiro: " + e.getMessage());
        }

        try {
            lista.insertAfter(noEstranho, "Teste");
        } catch (NoInvalidoException e) {
            System.out.println("Excecao esperada ao inserir depois de no estrangeiro: " + e.getMessage());
        }

        try {
            lista.remove(primeiro);
        } catch (NoInvalidoException e) {
            System.out.println("Excecao esperada ao remover no antigo: " + e.getMessage());
        }

        System.out.println("\n=== ESVAZIANDO A LISTA ===");
        while (!lista.isEmpty()) {
            System.out.println("Removido: " + lista.remove(lista.first()) + " -> " + lista);
        }

        System.out.println("\n=== ESTADO FINAL ===");
        System.out.println("Lista: " + lista);
        System.out.println("Array: " + Arrays.toString(lista.toArray()));
        System.out.println("size=" + lista.size() + ", isEmpty=" + lista.isEmpty());
        System.out.println("\nExecucao finalizada com sucesso.");
    }
}
