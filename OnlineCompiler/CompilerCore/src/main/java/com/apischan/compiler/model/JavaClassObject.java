package com.apischan.compiler.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

public class JavaClassObject extends SimpleJavaFileObject implements SourceProvider {

    /**
     * Full name of class including package declaration
     */
    private String fullName;

    /**
     * CharSequence representing the source code to be compiled
     */
    private CharSequence content;

    /**
     * Byte code created by the compiler will be stored in this
     * ByteArrayOutputStream so that we can later get the
     * byte array out of it
     * and put it in the memory as an instance of our class.
     */
    protected final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    /**
     * Registers the compiled class object under URI
     * containing the class full name
     *
     * @param className
     *            Full name of the compiled class
     * @param kind
     *            Kind of the data. It will be CLASS in our case
     */
    public JavaClassObject(String className, JavaFileObject.Kind kind) {
        super(URI.create("string:///" + className.replace('.', '/') + kind.extension), kind);
        this.fullName = className;
    }

    /**
     * This constructor will store the source code in the
     * internal "content" variable and register it as a
     * source code, using a URI containing the class full name
     *
     * @param className
     *            name of the public class in the source code
     * @param content
     *            source code to compile
     */
    public JavaClassObject(String className, CharSequence content) {
        super(URI.create("string:///" + className.replace('.', '/')
                + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
        this.fullName = className;
        this.content = content;
    }

    /**
     * Answers the CharSequence to be compiled. It will give
     * the source code stored in variable "content"
     */
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return content;
    }


    /**
     * Will be used by our file manager to get the byte code that
     * can be put into memory to instantiate our class
     *
     * @return compiled byte code
     */
    public byte[] getBytes() {
        return bos.toByteArray();
    }

    /**
     * Will provide the compiler with an output stream that leads
     * to our byte array. This way the compiler will write everything
     * into the byte array that we will instantiate later
     */
    @Override
    public OutputStream openOutputStream() throws IOException {
        return bos;
    }

    @Override
    public String getFullClassName() {
        return fullName;
    }

    @Override
    public String getSourceCode() {
        return content.toString();
    }
}
