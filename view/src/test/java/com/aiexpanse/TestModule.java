package com.aiexpanse;

import com.aiexpanse.ioc.guice.ViewModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.concurrent.atomic.AtomicReference;

public class TestModule extends AbstractModule {

    private static AtomicReference<TestModule> instance = new AtomicReference<>();
    private static AtomicReference<Injector> injectorRef = new AtomicReference<>();

    public static Injector getInjector() {
        if (injectorRef.get() == null) {
            Injector injector = Guice.createInjector(TestModule.getInstance());
            injectorRef.compareAndSet(null, injector);
        }
        return injectorRef.get();
    }

    public static TestModule getInstance() {
        if (instance.get() == null) {
            instance.compareAndSet(null, new TestModule());
        }
        return instance.get();
    }

    @Override
    protected void configure() {
        install(new ViewModule());
    }

}