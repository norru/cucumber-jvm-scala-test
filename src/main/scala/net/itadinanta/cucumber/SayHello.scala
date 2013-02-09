package net.itadinanta.cucumber

class SayHelloFactory {
	def createSayHello(s: String) = new SayHello(s)
}

class SayHello(val encountered: String) {
	var greeting = "Hello"
	def reply() = greeting + ", " + encountered + "!"
}

