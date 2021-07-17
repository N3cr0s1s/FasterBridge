package necrosis.fasterbridge.arena.arenaCreatorFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.arena.Direction;
import necrosis.fasterbridge.customevents.creator.ArenaCreateEvent;
import necrosis.fasterbridge.exceptions.ArenaAlreadyExist;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ArenaCreatorCreateArena {

    private FasterBridge plugin;

    public ArenaCreatorCreateArena(FasterBridge plugin){
        this.plugin = plugin;
    }

    public ArenaClass createArena(String arenaName, int maxPlayer, boolean isActive, Location[] locations, int deathZoneHorizontal, int deathZoneVertical, Direction direction)
    throws ArenaAlreadyExist {
        if(this.plugin.getArenaManager().isArenaExist(arenaName)) throw new ArenaAlreadyExist("Arena already exist.",arenaName);
        //  Call event
        Bukkit.getServer().getPluginManager().callEvent(
                new ArenaCreateEvent(
                        arenaName,
                        maxPlayer,
                        isActive,
                        locations,
                        deathZoneHorizontal,
                        deathZoneVertical,
                        direction
                )
        );

        //  Create arena
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

    public ArenaClass createArena(String arenaName,int maxPlayer) throws ArenaAlreadyExist {
        return this.createArena(
                arenaName,
                maxPlayer,
                false,
                new Location[maxPlayer],
                10,
                5,
                Direction.NORTH
        );
    }

    public ArenaClass createArena(String arenaName,int maxPlayer,int deathZoneHorizontal,int deathzoneVertical) throws ArenaAlreadyExist {
        return this.createArena(
                arenaName,
                maxPlayer,
                false,
                new Location[maxPlayer],
                deathZoneHorizontal,
                deathzoneVertical,
                Direction.NORTH
        );
    }
}
