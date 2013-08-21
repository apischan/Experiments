package com.apischan.compiler.core;

import com.apischan.compiler.model.JavaClassObject;

import java.io.IOException;
import java.security.SecureClassLoader;
import java.util.HashMap;
import java.util.Map;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

public class ClassFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
    /**
     * Instance of JavaClassObject that will store the
     * compiled bytecode of our class
     */
    private Map<String, JavaClassObject> jClassObjects = new HashMap<>();

    private ClassLoader classLoader;

    /**
     * Will initialize the manager with the specified
     * standard java file manager and given ClassLoader
     *
     * @param standardManager file manager
     */
    public ClassFileManager(StandardJavaFileManager standardManager, ClassLoader classLoader) {
        this(standardManager);
        this.classLoader = classLoader;
    }

    /**
     * Will initialize the manager with the specified
     * standard java file manager
     *
     * @param standardManager file manager
     */
    public ClassFileManager(StandardJavaFileManager standardManager) {
        super(standardManager);
    }

    /**
     * Will be used by us to get the class loader for our
     * compiled class. It creates an anonymous class
     * extending the SecureClassLoader which uses the
     * byte code created by the compiler and stored in
     * the JavaClassObject, and returns the Class for it
     */
    @Override
    public ClassLoader getClassLoader(Location location) {
        return new SecureClassLoaderWithParent(classLoader);
    }

    /**
     * Gives the compiler an instance of the JavaClassObject
     * so that the compiler can write the byte code into it.
     */
    @Override
    public JavaFileObject getJavaFileForOutput(Location location,
                                               String className, JavaFileObject.Kind kind, FileObject sibling)
            throws IOException {
        if (jClassObjects.containsKey(className)) {
            return jClassObjects.get(className);
        }
        JavaClassObject classObject = new JavaClassObject(className, kind);
        jClassObjects.put(className, classObject);
        return classObject;
    }

    private class SecureClassLoaderWithParent extends SecureClassLoader {

        public SecureClassLoaderWithParent(ClassLoader parentClassLoader) {
            super(parentClassLoader);
        }

        @Override
        protected Class<?> findClass(String name)
        throws ClassNotFoundException {
            byte[] b = jClassObjects.get(name).getBytes();
            return super.defineClass(name, b, 0, b.length);
        }
    }
}
