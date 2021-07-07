package necrosis.fasterbridge.exceptions;

public class TooManyArenasException extends Exception{

    private final String message;
    private final int arenas;

    public TooManyArenasException(String message){
        super(message);
        this.message = message;
        this.arenas = 0;
    }

    public TooManyArenasException(String message,int arenas){
        super(message);
        this.message = message;
        this.arenas = arenas;
    }
}
