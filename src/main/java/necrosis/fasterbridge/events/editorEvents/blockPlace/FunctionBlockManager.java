package necrosis.fasterbridge.events.editorEvents.blockPlace;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.events.editorEvents.blockPlace.blocks.DeathZoneBlock;
import necrosis.fasterbridge.events.editorEvents.blockPlace.blocks.SlotPlaceBlock;
import necrosis.fasterbridge.exceptions.FuncBlockDoesNotExistException;

import java.util.HashMap;

public class FunctionBlockManager {

    private FasterBridge plugin;

    private HashMap<String,FunctionBlockAbstract> functionBlock = new HashMap<>();

    public FunctionBlockManager(FasterBridge plugin){
        this.plugin = plugin;
    }

    public FunctionBlockAbstract registerFunctionBlock(FunctionBlockAbstract functionBlock){
        this.functionBlock.put(functionBlock.name(), functionBlock);
        return functionBlock;
    }

    public void registerFunctionBlock(FunctionBlockAbstract... functionBlock){
        for(FunctionBlockAbstract f:functionBlock){
            this.registerFunctionBlock(f);
        }
    }

    public FunctionBlockAbstract getFunctionBlock(String name) throws FuncBlockDoesNotExistException{
        if(!this.functionBlock.containsKey(name)) throw new FuncBlockDoesNotExistException("Function block does not exist.");
        return this.functionBlock.get(name);
    }

    public void registerFunctionBlock(){
        this.registerFunctionBlock(
                new SlotPlaceBlock(),
                new DeathZoneBlock()
        );
        this.plugin.logger().getInfo("&aObjects registered. &5[&7 Function blocks &5]");
    }
}
