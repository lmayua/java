package ext.antlr4.expr.exception;

public class ExprCompileException extends RuntimeException{
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 1L;
    
    private String errorCode;
    
    /* get & set method begin */
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    /* get & set method end */
    
    /**
     * <default constructor>
     * @param message
     */
    public ExprCompileException(String message) {
        super(message);
    }
    
    /**
     * <default constructor>
     * @param errorCode
     * @param message
     */
    public ExprCompileException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * <default constructor>
     * @param message
     * @param cause
     */
    public ExprCompileException(String message, Throwable cause){
        super(message, cause);
    }
}
