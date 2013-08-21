package com.apischan.compiler.exception;

public class CompilationException extends Exception {

    private static final long serialVersionUID = -8217673258662757630L;

    public CompilationException() {
        super();
    }

    public CompilationException(String message, Throwable cause,
                                boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CompilationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompilationException(String message) {
        super(message);
    }

    public CompilationException(Throwable cause) {
        super(cause);
    }

}
