package necrosis.fasterbridge.gadget;


import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.GadgetNotExistException;
import java.util.HashMap;

public class GadgetManager {

    private FasterBridge plugin;

    private HashMap<String,GadgetAbstract> gadgets = new HashMap<String,GadgetAbstract>();

    public GadgetManager(FasterBridge plugin){
        this.plugin = plugin;
    }

    public void registerGadget(GadgetAbstract gadget){
        this.gadgets.put(gadget.name(),gadget);
    }

    public GadgetAbstract getGadget(String name) throws GadgetNotExistException {
        if(!this.gadgets.containsKey(name)) throw new GadgetNotExistException("Gadget dosn't exist.",name);
        return this.gadgets.get(name);
    }
}
