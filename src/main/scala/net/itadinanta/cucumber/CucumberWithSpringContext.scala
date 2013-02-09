package net.itadinanta.cucumber

import cucumber.api.junit.Cucumber
import org.springframework.test.context.TestContextManager
import org.springframework.test.context.ContextConfiguration
import scala.collection.JavaConversions._
import org.junit.runners.ParentRunner
import org.junit.runner.notification.RunNotifier
import cucumber.runtime.junit.FeatureRunner
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Set

object ContextInjectionRegistry {
	val registered = new ArrayBuffer[ContextInjection]
	def register(autowired: ContextInjection) { registered += autowired }
	def apply(block: AnyRef => Unit) = { registered.foreach(block) }
}

trait ContextInjection { ContextInjectionRegistry.register(this) }

class CucumberWithSpringContext(c: Class[AnyRef]) extends Cucumber(c) {
	val testContextManager = new TestContextManager(getClass())
	ContextInjectionRegistry { testContextManager.prepareTestInstance(_) }
}