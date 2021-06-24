package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.GadgetNotExistException;
import necrosis.fasterbridge.exceptions.GadgetNotRegisteredException;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GiveGadgetSubCommand implements SubCommandInterface{
    @Override
    public String name() {
        return "givegadget";
    }

    @Override
    public String description() {
        return "Give gadge to player.";
    }

    @Override
    public String syntax() {
        return "/fasterbridge givegadget <gadgetName>";
    }

    @Override
    public String permission() {
        return "craftkit.admin";
    }

    @Override
    public void execute(Player player, String[] args) {
        try {
            player.getInventory().addItem(FasterBridge.instance.getGadgetManager().getGadget(args[1]).getGadget());
        } catch (GadgetNotExistException | GadgetNotRegisteredException e) {
            player.sendMessage("Gadget not found.");
        } catch (ArrayIndexOutOfBoundsException ex){
            player.sendMessage(ChatColor.RED + this.syntax());
        }
    }
}