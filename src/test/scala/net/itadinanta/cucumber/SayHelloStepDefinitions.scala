package net.itadinanta.cucumber

import cucumber.api.Scenario
import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.Assertions._
import cucumber.api.PendingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestContextManager
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext

class SayHelloStepDefinitions extends ScalaDsl with EN with ContextInjection {
	@Autowired
	var factory: SayHelloFactory = _
	var sayHello: SayHello = _

	When("""^I say (.+)$""") { encountered: String =>
		sayHello = factory.createSayHello(encountered)
	}
	
	When("""^she says (.+)$"""){ greeting: String =>
		sayHello.greeting = greeting
	}

	Then("""^you reply '(.+)'$""") { expected: String =>
		expectResult(expected) { sayHello.reply() }
	}
}