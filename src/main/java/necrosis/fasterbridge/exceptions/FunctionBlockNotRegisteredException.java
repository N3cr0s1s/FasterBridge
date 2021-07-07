package necrosis.fasterbridge.exceptions;

public class FunctionBlockNotRegisteredException extends Exception{

    private final String message;
    private final String functionBlockName;

    public FunctionBlockNotRegisteredException(String message){
        super(message);
        this.message = message;
        this.functionBlockName = "";
    }

    public FunctionBlockNotRegisteredException(String message,String functionBlockName){
        super(message);
        this.message = message;
        this.functionBlockName = functionBlockName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getFunctionBlockName() {
        return functionBlockName;
    }
}
