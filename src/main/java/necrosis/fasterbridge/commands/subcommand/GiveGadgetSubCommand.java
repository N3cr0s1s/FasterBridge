package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.GadgetNotExistException;
import necrosis.fasterbridge.exceptions.GadgetNotRegisteredException;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.omg.CORBA.NO_PERMISSION;

public class GiveGadgetSubCommand implements SubCommandInterface{
    @Override
    public String name() {
        return "givegadget";
    }

    @Override
    public String description() {
        return FasterBridge.instance.cfs("messages.GetGadget.desc");
    }

    @Override
    public String syntax() {
        return "/fasterbridge givegadget <gadgetName>";
    }

    @Override
    public String permission() {
        return "fastbridge.givegadget";
    }

    @Override
    public void execute(Player player, String[] args) {
        try {
            if(!player.hasPermission(FasterBridge.instance.getGadgetManager().getGadget(args[1]).permission())) throw new NO_PERMISSION();
            player.getInventory().addItem(FasterBridge.instance.getGadgetManager().getGadget(args[1]).getGadget());
        } catch (GadgetNotExistException | GadgetNotRegisteredException e) {
            player.sendMessage(FasterBridge.instance.cfs("messages.GetGadget.notfound","%gadget%",args[1]));
        } catch (ArrayIndexOutOfBoundsException ex){
            player.sendMessage(ChatColor.RED + this.syntax());
        } catch (NO_PERMISSION per){
            player.sendMessage(FasterBridge.instance.cfs("messages.subcommand.no-permission"));
        }
    }
}
