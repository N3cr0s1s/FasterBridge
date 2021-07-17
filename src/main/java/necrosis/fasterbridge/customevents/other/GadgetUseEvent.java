package necrosis.fasterbridge.customevents.other;

import necrosis.fasterbridge.gadget.GadgetAbstract;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GadgetUseEvent extends Event {
    //  Make handler list
    private static final HandlerList handlers = new HandlerList();

    //  Event variables
    private final Player player;
    private final String gadgetName;
    private final GadgetAbstract gadget;

    public GadgetUseEvent(Player player,String gadgetName,GadgetAbstract gadget){
        this.player     =   player;
        this.gadgetName =   gadgetName;
        this.gadget     =   gadget;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public String getGadgetName() {
        return gadgetName;
    }

    public GadgetAbstract getGadget() {
        return gadget;
    }
}
