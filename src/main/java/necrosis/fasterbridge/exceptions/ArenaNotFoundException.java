package necrosis.fasterbridge.exceptions;

public class ArenaNotFoundException extends Exception{

    private String arenaName;
    private String message;

    public ArenaNotFoundException(String message){
        super(message);
        this.message = message;
    }

    public ArenaNotFoundException(String message,String arenaName){
        super(message);
        this.message = message;
        this.arenaName = arenaName;
    }

    public String getArenaName(){
        return this.arenaName;
    }

    public String getMessage(){return this.message;};
}
