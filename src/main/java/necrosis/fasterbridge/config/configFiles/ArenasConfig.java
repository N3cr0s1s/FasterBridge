package necrosis.fasterbridge.config.configFiles;

import cornerlesscube.craftkit.utils.file.yaml.YamlClass;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.arena.Direction;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.ArenaNotValidException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public final class ArenasConfig {

    private final String configName = "arenasConfig";

    private final FasterBridge plugin;
    private final YamlClass arenasConfig;

    public ArenasConfig(FasterBridge plugin){
        this.plugin = plugin;
        YamlClass tempConfig;
        try {
            tempConfig = plugin.configYml().getYamlClass(configName);
        }catch (Exception e){
            tempConfig = plugin.configYml().createYaml(configName);
        }
        this.arenasConfig = tempConfig;
        tempConfig=null;
    }

    public boolean isArenaExist(String arenaName){
        if(this.arenasConfig.YamlConfig.isSet(arenaName)) {
            return true;
        }else{
            return false;
        }
    }

    public ArenaClass saveArena(String arenaName) throws ArenaNotFoundException, MaxSlotException {
        ArenaClass arena = this.plugin.getArenaManager().getArena(arenaName);

        this.arenasConfig.YamlConfig.createSection(arenaName);
        this.arenasConfig.write(arena.getArenaName()+".name",arena.getArenaName());
        this.arenasConfig.write(arena.getArenaName()+".maxPlayer",arena.getMaxPlayer());
        this.arenasConfig.write(arena.getArenaName()+".isActive",arena.isActive());
        this.arenasConfig.write(arena.getArenaName()+".deathZoneVertical",arena.getDeathZoneVertical());
        this.arenasConfig.write(arena.getArenaName()+".deathZoneHorizontal",arena.getDeathZoneHorizontal());
        this.arenasConfig.YamlConfig.createSection(arenaName+".slotLocations");
        for(int i = 0;i<arena.getMaxPlayer();i++){
            if(arena.getSlotLocation(i) == null) continue;
            this.arenasConfig.write(arena.getArenaName()+".slotLocations.slot"+i+".world",arena.getSlotLocation(i).getWorld().getName());
            this.arenasConfig.write(arena.getArenaName()+".slotLocations.slot"+i+".X",arena.getSlotLocation(i).getX());
            this.arenasConfig.write(arena.getArenaName()+".slotLocations.slot"+i+".Y",arena.getSlotLocation(i).getY());
            this.arenasConfig.write(arena.getArenaName()+".slotLocations.slot"+i+".Z",arena.getSlotLocation(i).getZ());
            this.arenasConfig.write(arena.getArenaName()+".slotLocations.slot"+i+".Yaw",arena.getSlotLocation(i).getYaw());
            this.arenasConfig.write(arena.getArenaName()+".slotLocations.slot"+i+".Pitch",arena.getSlotLocation(i).getPitch());
        }

        return arena;
    }

    public ArenaClass loadArena(String arenaName) throws ArenaNotFoundException, ArenaNotValidException {
        if(!this.isArenaExist(arenaName)) throw new ArenaNotFoundException("Arena does not exist.",arenaName);
        ArenaClass arena;
        try {
            String name = (String) this.arenasConfig.read(arenaName + ".name");
            int maxPlayer = (int) this.arenasConfig.read(arenaName + ".maxPlayer");
            boolean isActive = (boolean) this.arenasConfig.read(arenaName + ".isActive");
            int deathZoneVertical = (int)this.arenasConfig.read(arenaName + ".deathZoneVertical");
            int deathZoneHorizontal = (int)this.arenasConfig.read(arenaName + ".deathZoneHorizontal");
            Location[] locations = new Location[maxPlayer];
            for (int i = 0; i < maxPlayer; i++) {
                if(!this.arenasConfig.YamlConfig.isSet(arenaName + ".slotLocations.slot" + i)) {
                    this.plugin.logger().getError(
                            arenaName + " don't have all slots. slot " + i + " is null."
                    );
                    continue;
                }
                String world = (String) this.arenasConfig.read(arenaName + ".slotLocations.slot" + i + ".world");
                double X = (double) this.arenasConfig.read(arenaName + ".slotLocations.slot" + i + ".X");
                double Y = (double) this.arenasConfig.read(arenaName + ".slotLocations.slot" + i + ".Y");
                double Z = (double) this.arenasConfig.read(arenaName + ".slotLocations.slot" + i + ".Z");
                double Yaw = (double) this.arenasConfig.read(arenaName + ".slotLocations.slot" + i + ".Yaw");
                double Pitch = (double) this.arenasConfig.read(arenaName + ".slotLocations.slot" + i + ".Pitch");
                locations[i] = new Location(Bukkit.getWorld(world), X, Y, Z, (float) Yaw, (float) Pitch);
            }
            Direction direction = Direction.NORTH;
            try{
                for(Location l:locations){
                    if(l == null) continue;
                    plugin.getUtilsManager().getDirectionCalculator().getDirection(l.getYaw());
                    break;
                }
            }catch (Exception e){

            }
            arena = plugin.getArenaManager().editor().getCreateArena().createArena(
                    name,
                    maxPlayer,
                    isActive,
                    locations,
                    deathZoneHorizontal,
                    deathZoneVertical,
                    direction
            );
            this.plugin.logger().getInfo("&cArena successfully registered. &a[&2"+arenaName+"&a]");
        }catch (Exception e){
            throw new ArenaNotValidException("This arena isn't valid.");
        }
        return arena;
    }

    public void loadAllArena(){
        int i = 0;
        for (String arenaName:this.arenasConfig.YamlConfig.getKeys(false)){
            if(i == 0){i++; continue;}
            try {
                this.loadArena(arenaName);
            } catch (ArenaNotFoundException | ArenaNotValidException e) {
                this.plugin.logger().getInfo("&4Cant register this arena: "+arenaName+"");
                e.printStackTrace();
            }
        }
    }
}
