package com.apischan.compiler.exception;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.util.Locale;

public class ExceptionHandler {

    /**
     * Produce {@link CompilationException}
     * @param diagnostics diagnostics of compiler
     * @return instance of compilation exception with appropriate message
     */
    public static CompilationException newCompilationException(DiagnosticCollector<JavaFileObject> diagnostics) {
        StringBuilder message = new StringBuilder();
        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
            message.append("\nLine: ")
                   .append(diagnostic.getLineNumber())
                   .append("\nSource: ")
                   .append(diagnostic.getSource())
                   .append("\nMessage:")
                   .append(diagnostic.getMessage(Locale.getDefault()))
                   .append("\n");
        }
        return new CompilationException(message.toString());
    }

}
