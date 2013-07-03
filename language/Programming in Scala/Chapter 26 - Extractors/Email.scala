/**
	Extractors:

	Bom escrevei em bom e velho português, afinal é a única língua que falo...

	Simplesmente é um objeto que tem o método unapply.
*/



object Email {
	def apply(user: String, domain: String) = user + "@" + domain

	def unapply(str: String): Option[(String, String)] = {
		var parts = str split "@"
		println(parts(0))
		if (parts.length == 2)
			Some(parts(0), parts(1))
		else
			None
	}
}

class Ip(a:String, b: String, c: String, d: String) {

}

object Ip {

	def apply(a1: String, a2: String, a3: String, a4: String) = new Ip(a1, a2, a3, a4)
	def unapply(str: String): Option[(String, String, String, String)] = {
		var parts = str split "\\."

		if (parts.length == 4)
			Some(parts(0), parts(1), parts(2), parts(3))
		else
			None
	}
}

def sayHello(user: String, domain: String) {
	println(s"Hello $user at $domain")
}

val a = "ronaldo.asilva@gmail.com" match {
	case Email(user, domain) => sayHello(user, domain)
	case _ => println("WTF?")
}

val b = "200.131.250.143" match {
	case Email(user, domain) => sayHello(user, domain)
	case a @ Ip(net1, net2, net3, node) => println("Network address! - " + a.getClass)
	case _ => println("WTF??")
}

val c = "ronaldo.asilva@gmail.com" match {
	case Ip(a1, a2, a3, a4) => println("Network?")
	case Email(user, domain) => sayHello(user, domain)
	case _ => println("WTF???")
}
