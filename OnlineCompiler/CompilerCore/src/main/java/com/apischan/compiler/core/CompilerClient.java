package com.apischan.compiler.core;

import com.apischan.compiler.exception.CompilationException;
import com.apischan.compiler.model.SourceProvider;

import java.util.List;

public interface CompilerClient {

    /**
     * Get list of classes
     *
     * @param sources instances that implements <code>SourceProvider</code> interface and contains full name of class
     *                and source code
     * @return list of <code>Class<?></code> instances
     * @throws CompilationException in case of exception during compilation
     */
    List<Class<?>> compileClasses(List<SourceProvider> sources) throws CompilationException;

}
