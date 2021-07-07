package necrosis.fasterbridge.exceptions;

import necrosis.fasterbridge.arena.ArenaClass;

public class FullSlotException extends Exception{
    private ArenaClass arena;

    public FullSlotException(String message){
        super(message);
    }

    public FullSlotException(String message,ArenaClass arena){
        super(message);
        this.arena = arena;
    }

    public String getArenaName(){
        return this.arena.getArenaName();
    }
}
