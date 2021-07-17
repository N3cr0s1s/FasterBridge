package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import org.bukkit.entity.Player;

public class BlockSelectSubCommand implements SubCommandInterface{
    @Override
    public String name() {
        return "blocks";
    }

    @Override
    public String description() {
        return FasterBridge.instance.cfs("messages.blocks.desc");
    }

    @Override
    public String syntax() {
        return "/fb blocks";
    }

    @Override
    public String permission() {
        return "fasterbridge.blocks";
    }

    @Override
    public void execute(Player player, String[] args) {
        FasterBridge.instance.getUiManager().openBlocks(player);
    }
}
