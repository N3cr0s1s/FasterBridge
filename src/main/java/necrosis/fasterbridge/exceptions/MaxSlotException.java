package necrosis.fasterbridge.exceptions;

public class MaxSlotException extends Exception{

    private int maxPlayer;
    private String arenaName;
    private int slot;
    private String message;

    public MaxSlotException(String message){
        super(message);
        this.message = message;
    }

    public MaxSlotException(String message,String arenaName,int maxPlayer,int slot){
        super(message);
        this.message = message;
        this.arenaName = arenaName;
        this.maxPlayer = maxPlayer;
        this.slot = slot;
    }

    public int getMaxPlayer(){
        return this.maxPlayer;
    }

    public String getArenaName() {
        return this.arenaName;
    }

    public int getSlot() {
        return this.slot;
    }

    public String getMessage(){
        return this.message;
    }
}
