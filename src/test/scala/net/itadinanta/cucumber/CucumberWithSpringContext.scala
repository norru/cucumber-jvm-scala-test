package net.itadinanta.cucumber

import cucumber.api.junit.Cucumber
import org.springframework.test.context.TestContextManager
import scala.collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer

object ContextInjectionRegistry {
	val registered = new ArrayBuffer[ContextInjection]
	def getRegistered = registered.toArray
	def register(autowired: ContextInjection) { registered += autowired }
	def apply(block: AnyRef => Unit) = { registered.foreach(block) }
}

trait ContextInjection { ContextInjectionRegistry.register(this) }

class CucumberWithSpringContext(c: Class[AnyRef]) extends Cucumber(c) {
	val testContextManager = new TestContextManager(getClass())
	ContextInjectionRegistry { testContextManager.prepareTestInstance(_) }
}