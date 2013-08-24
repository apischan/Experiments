package com.apischan.compiler.core;

import com.apischan.compiler.exception.CompilationException;
import com.apischan.compiler.exception.ExceptionHandler;
import com.apischan.compiler.model.JavaClassObject;
import com.apischan.compiler.model.SourceProvider;

import javax.tools.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CompilerCore implements CompilerClient {

    /**
     * {@inheritDoc}
     */
    public Map<String, Class<?>> compileClasses(List<SourceProvider> sources) throws CompilationException {
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

        Map<String, Class<?>> classes = new HashMap<>();
        try {
            for (SourceProvider javaSourceData : sources) {
                String fullClassName = javaSourceData.getFullClassName();
                classes.put(fullClassName, classLoader.loadClass(fullClassName));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }

}
