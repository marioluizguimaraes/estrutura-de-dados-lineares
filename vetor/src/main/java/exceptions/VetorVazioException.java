package exceptions;

public class VetorVazioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VetorVazioException(String mensagem) {
        super(mensagem);
    }
}
