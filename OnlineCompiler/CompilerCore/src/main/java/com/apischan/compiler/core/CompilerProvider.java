package com.apischan.compiler.core;

public class CompilerProvider {

    public static CompilerClient getCompiler() {
        return new CompilerCore();
    }

}
