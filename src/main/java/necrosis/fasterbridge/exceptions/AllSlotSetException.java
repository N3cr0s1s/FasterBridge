package necrosis.fasterbridge.exceptions;

public class AllSlotSetException extends Exception{

    private String message;

    public AllSlotSetException(String message){
        super(message);
        this.message = message;
    }
}
