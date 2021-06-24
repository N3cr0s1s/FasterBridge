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
        return "&5[&l&0DeathZoneSet&r&5]";
    }

    @Override
    public String[] lore() {
        return new String[]{
                "Set deathzone",
                "under this value",
                "player dies."
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
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&aVertical deathzone successfully setted. &5[&l&7" +
                                    FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getInArena() +
                                    "&r&7, deathZone: &l&7" + (int) location.getY() + "&r&5]"
                            )
            );
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        }
    }
}
