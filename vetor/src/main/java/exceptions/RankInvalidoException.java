package exceptions;

public class RankInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RankInvalidoException(String mensagem) {
        super(mensagem);
    }
}
