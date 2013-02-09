package net.itadinanta.cucumber

import org.junit.runner.RunWith
import cucumber.api.junit.Cucumber
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestContextManager

@ContextConfiguration(locations = { Array("classpath:net/itadinanta/context/applicationContext.xml") })
class CucumberWithMyContext(c: Class[AnyRef]) extends CucumberWithSpringContext(c)

@RunWith(classOf[CucumberWithMyContext])
class RunCukesTest