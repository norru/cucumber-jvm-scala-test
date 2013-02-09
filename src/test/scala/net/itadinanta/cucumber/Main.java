package net.itadinanta.cucumber;

import org.springframework.test.context.TestContextManager;

import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;

public class Main {
	public static void main(String[] argv) throws Throwable {
		new Main().run(argv, Thread.currentThread().getContextClassLoader());
	}

	public void run(String[] argv, ClassLoader classLoader) throws Exception {
		TestContextManager testContextManager = new TestContextManager(
				CucumberWithMyContext.class);
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
