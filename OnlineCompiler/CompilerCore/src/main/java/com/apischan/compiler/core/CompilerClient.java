package com.apischan.compiler.core;

import com.apischan.compiler.exception.CompilationException;
import com.apischan.compiler.model.SourceProvider;

import java.util.List;
import java.util.Map;

public interface CompilerClient {

    /**
     * Get map of classes where key is class name and value is instance of <code>Class<?></code>
     *
     *
     * @param sources instances that implements <code>SourceProvider</code> interface and contains full name of class
     *                and source code
     * @return map where key is the class name and value is instance of <code>Class<?></code>
     * @throws CompilationException in case of exception during compilation
     */
    Map<String, Class<?>> compileClasses(List<SourceProvider> sources) throws CompilationException;

}
