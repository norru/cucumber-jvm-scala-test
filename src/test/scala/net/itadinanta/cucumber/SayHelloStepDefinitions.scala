package net.itadinanta.cucumber

import org.scalatest.Assertions.expectResult
import org.springframework.beans.factory.annotation.Autowired

import cucumber.api.scala.EN
import cucumber.api.scala.ScalaDsl
import cucumber.runtime.scala.Transform.t2String

class SayHelloStepDefinitions extends ScalaDsl with EN with ContextInjection {
	@Autowired
	var factory: SayHelloFactory = _
	var sayHello: SayHello = _

	When("""^I say (.+)$""") { encountered: String =>
		sayHello = factory.createSayHello(encountered)
	}

	When("""^she says (.+)$""") { greeting: String =>
		sayHello.greeting = greeting
	}

	Then("""^you reply '(.+)'$""") { expected: String =>
		expectResult(expected) { sayHello.reply() }
	}
}