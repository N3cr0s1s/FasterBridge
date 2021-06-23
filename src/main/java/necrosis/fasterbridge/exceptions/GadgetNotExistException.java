package necrosis.fasterbridge.exceptions;

public class GadgetNotExistException extends Exception{

    private String message;
    private String gadgetName;

    public GadgetNotExistException(String message){
        super(message);
        this.message = message;
        this.gadgetName = "";
    }

    public GadgetNotExistException(String message,String gadgetName){
        super(message);
        this.message = message;
        this.gadgetName = gadgetName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getGadgetName() {
        return gadgetName;
    }
}
