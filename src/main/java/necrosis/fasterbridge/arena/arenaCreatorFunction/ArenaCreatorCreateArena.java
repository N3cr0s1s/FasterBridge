package necrosis.fasterbridge.arena.arenaCreatorFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.arena.Direction;
import org.bukkit.Location;

public class ArenaCreatorCreateArena {

    private FasterBridge plugin;

    public ArenaCreatorCreateArena(FasterBridge plugin){
        this.plugin = plugin;
    }

    public ArenaClass createArena(String arenaName, int maxPlayer, boolean isActive, Location[] locations, int deathZoneHorizontal, int deathZoneVertical, Direction direction){
        return new ArenaClass(
                arenaName,
                maxPlayer,
                isActive,
                locations,
                deathZoneHorizontal,
                deathZoneVertical,
                direction,
                this.plugin
        )
                .register()
                .save();
    }

    public ArenaClass createArena(String arenaName){
        return this.createArena(
                arenaName,
                2,
                false,
                null,
                10,
                5,
                Direction.NORTH
                );
    }

    public ArenaClass createArena(String arenaName,int maxPlayer){
        return this.createArena(
                arenaName,
                maxPlayer,
                false,
                null,
                10,
                5,
                Direction.NORTH
        );
    }

    public ArenaClass createArena(String arenaName,int maxPlayer,int deathZoneHorizontal,int deathzoneVertical){
        return this.createArena(
                arenaName,
                maxPlayer,
                false,
                null,
                deathZoneHorizontal,
                deathzoneVertical,
                Direction.NORTH
        );
    }
}
