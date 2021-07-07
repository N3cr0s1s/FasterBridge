package necrosis.fasterbridge.exceptions;

public class FuncBlockDoesNotExistException extends Exception{

    private final String message;

    public FuncBlockDoesNotExistException(String message){
        super(message);
        this.message = message;
    }
}
