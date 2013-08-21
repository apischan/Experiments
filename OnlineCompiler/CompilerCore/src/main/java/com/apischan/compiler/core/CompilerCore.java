package com.apischan.compiler.core;

import com.apischan.compiler.exception.CompilationException;
import com.apischan.compiler.exception.ExceptionHandler;
import com.apischan.compiler.model.JavaClassObject;
import com.apischan.compiler.model.SourceProvider;

import javax.tools.*;
import java.util.ArrayList;
import java.util.List;

class CompilerCore implements CompilerClient {

    /**
     * {@inheritDoc}
     */
    public List<Class<?>> compileClasses(List<SourceProvider> sources) throws CompilationException {
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager fileManager = new ClassFileManager(
                compiler.getStandardFileManager(null, null, null)
        );

        List<JavaFileObject> jFiles = new ArrayList<>();
        for (SourceProvider javaSourceData : sources) {
            jFiles.add(new JavaClassObject(javaSourceData.getFullClassName(), javaSourceData.getSourceCode()));
        }

        Boolean result = compiler.getTask(null, fileManager, diagnostics, null, null, jFiles).call();

        if (!result) {
            throw ExceptionHandler.newCompilationException(diagnostics);
        }

        ClassLoader classLoader = fileManager.getClassLoader(null);

        List<Class<?>> classes = new ArrayList<>();
        try {
            for (SourceProvider javaSourceData : sources) {
                classes.add(classLoader.loadClass(javaSourceData.getFullClassName()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }

}
