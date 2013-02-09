package net.itadinanta.cucumber;

import java.io.IOException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;

@ContextConfiguration(locations = { "classpath:net/itadinanta/context/applicationContext.xml" })
public class Main {
	public static void main(String[] argv) throws Throwable {
		new Main().run(argv, Thread.currentThread().getContextClassLoader());
	}

	public void run(String[] argv, ClassLoader classLoader) throws Exception {
		TestContextManager testContextManager = new TestContextManager(
				getClass());
		RuntimeOptions runtimeOptions = new RuntimeOptions(
				System.getProperties(), argv);

		Runtime runtime = new Runtime(new MultiLoader(classLoader),
				classLoader, runtimeOptions);
		for (ContextInjection registered : ContextInjectionRegistry
				.getRegistered()) {
			testContextManager.prepareTestInstance(registered);
		}
		runtime.writeStepdefsJson();
		runtime.run();
		System.exit(runtime.exitStatus());
	}
}
