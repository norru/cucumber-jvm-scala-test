package net.itadinanta.cucumber

class SayHelloFactory {
	def createSayHello(s: String) = new SayHello(s)
}

class SayHello(val encountered: String) {
	def reply() = "Hello, " + encountered + "!"
}

