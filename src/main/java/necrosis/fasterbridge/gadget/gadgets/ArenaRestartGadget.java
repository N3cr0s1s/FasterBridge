package necrosis.fasterbridge.gadget.gadgets;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.customevents.game.PlayerArenaResetEvent;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.gadget.GadgetAbstract;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ArenaRestartGadget extends GadgetAbstract {
    @Override
    public String name() {
        return "arenaRestartGadget";
    }

    @Override
    public Material itemMaterial() {
        return Material.getMaterial("FEATHER");
    }

    @Override
    public String displayName() {
        return FasterBridge.instance.cfs("gadgets.arenaRestartGadget.name");
    }

    @Override
    public String[] lore() {
        return new String[]{
                FasterBridge.instance.getConfig().getString("gadgets."+this.name()+".lore0"),
                FasterBridge.instance.getConfig().getString("gadgets."+this.name()+".lore1"),
                FasterBridge.instance.getConfig().getString("gadgets."+this.name()+".lore2"),
                FasterBridge.instance.getConfig().getString("gadgets."+this.name()+".lore3")
        };
    }

    @Override
    public String permission() {
        return "fastbridge.arenaRestartGadget";
    }

    @Override
    public void itemFunction(Player player) {
        PlayerClass playerClass = FasterBridge.instance.getPlayerManager().getPlayerClass(player);
        if(!playerClass.getGame().isInGame()) {
            player.sendMessage(FasterBridge.instance.cfs("gadgets.arenaRestartGadget.no-game"));
            return;
        }
        //  Respawn player
        FasterBridge.instance.getGameManager().getGamePlayerDeath().playerDeath(player);
        //  Call PlayerArenaRestartEvent
        try {
            Bukkit.getServer().getPluginManager().callEvent(
                    new PlayerArenaResetEvent(
                            player,
                            playerClass.getGame().getInArena(),
                            playerClass,
                            FasterBridge.instance.getArenaManager().getArena(playerClass.getGame().getInArena())
                    )
            );
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        }
    }
}
