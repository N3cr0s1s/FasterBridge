package necrosis.fasterbridge.gadget;


import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.GadgetNotExistException;
import necrosis.fasterbridge.gadget.gadgets.*;

import java.util.HashMap;

public class GadgetManager {

    private FasterBridge plugin;

    private HashMap<String,GadgetAbstract> gadgets = new HashMap<String,GadgetAbstract>();

    public GadgetManager(FasterBridge plugin){
        this.plugin = plugin;
    }

    public void registerGadget(){
        this.registerGadget(
                new ArenaEditorGadget(),
                new ArenaLeaveGadget(),
                new ArenaSelectorGadget(),
                new ArenaRestartGadget(),
                new BlockSelectGadget()
        );
        this.plugin.logger().getInfo("&aObjects registered. &5[&7 Gadgets &5]");
    }

    public void registerGadget(GadgetAbstract... gadget){
        for(GadgetAbstract gdgt:gadget){
            this.registerGadget(gdgt);
        }
    }

    public void registerGadget(GadgetAbstract gadget){
        this.gadgets.put(gadget.name(),gadget);
    }

    public GadgetAbstract getGadget(String name) throws GadgetNotExistException {
        if(!this.gadgets.containsKey(name)) throw new GadgetNotExistException("Gadget dosn't exist.",name);
        return this.gadgets.get(name);
    }
}
