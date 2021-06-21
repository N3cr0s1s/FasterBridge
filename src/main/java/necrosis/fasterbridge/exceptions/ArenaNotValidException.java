package necrosis.fasterbridge.exceptions;

public class ArenaNotValidException extends Exception{

    private String message;

    public ArenaNotValidException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
