package exceptions;

public class PosicaoInvalidaException extends RuntimeException {

    public PosicaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
