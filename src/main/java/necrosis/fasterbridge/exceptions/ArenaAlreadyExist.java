package necrosis.fasterbridge.exceptions;

public class ArenaAlreadyExist extends Exception{

    private final String message;
    private final String arenaName;

    public ArenaAlreadyExist(String message){
        super(message);
        this.message = message;
        this.arenaName = "";
    }

    public ArenaAlreadyExist(String message,String arenaName){
        super(message);
        this.message = message;
        this.arenaName = arenaName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getArenaName() {
        return arenaName;
    }
}
