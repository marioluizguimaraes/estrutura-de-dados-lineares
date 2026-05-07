package exceptions;

public class NoInvalidoException extends RuntimeException {

    public NoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
