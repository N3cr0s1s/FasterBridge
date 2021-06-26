package necrosis.fasterbridge.events.editorEvents.blockPlace.blocks;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.events.editorEvents.blockPlace.FunctionBlockAbstract;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DeathZoneBlock extends FunctionBlockAbstract {
    @Override
    public String name() {
        return "deathBlock";
    }

    @Override
    public Material itemMaterial() {
        return Material.getMaterial("BRICK");
    }

    @Override
    public String displayName() {
        return FasterBridge.instance.cfs("functionBlocks.deathZone.name");
    }

    @Override
    public String[] lore() {
        return new String[]{
                FasterBridge.instance.cfs("functionBlocks.deathZone.lore0"),
                FasterBridge.instance.cfs("functionBlocks.deathZone.lore1"),
                FasterBridge.instance.cfs("functionBlocks.deathZone.lore2"),
                FasterBridge.instance.cfs("functionBlocks.deathZone.lore3")
        };
    }

    @Override
    public void function(Player player, Location location) {
        try {
            FasterBridge.instance.getArenaManager().getArena(
                    FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getInArena()
            ).setDeathZoneVertical(
                    (int) location.getY()
            ).save();
            player.sendMessage(FasterBridge.instance.cfs("functionBlocks.deathZone.set","%placeHolder%",FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getInArena() + "&r&7, deathZone: &l&7" + (int) location.getY()));
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        }
    }
}
