package necrosis.fasterbridge.commands.subcommand;

import org.bukkit.entity.Player;

public interface SubCommandInterface {
    String name();
    String description();
    String syntax();
    String permission();
    void execute(Player player,String[] args);
}
