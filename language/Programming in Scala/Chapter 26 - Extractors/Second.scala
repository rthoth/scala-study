object EMail {
	def unapply(s: String): Option[(String, String)] = {
		var parts = s split "@"
		if (parts.length == 2)
			Some(parts(0), parts(1))
		else
			None
	}
}

object UpperCase {
	def unapply(s: String): Boolean = s.toUpperCase == s
}

object Twice {
	def unapply(s: String): Option[String] = {
		val length = s.length / 2
		val half = s.substring(0, length)
		if (half == s.substring(length))
			Some(half)
		else
			None
	}
}

def userTwiceUpper(s: String) = {
	val result = s match {
		case EMail(Twice(x @ UpperCase()), domain) => 
			"match: " + x + " in domain " + domain
		case _ =>
				"no match"
	}

	println (result)
}

userTwiceUpper("DIDI@gmail.com")
userTwiceUpper("DIDO@gmail.com")
userTwiceUpper("didi@gmail.com")
userTwiceUpper("di1di2@gmail.com")
userTwiceUpper("A2A2@gmail.com")
userTwiceUpper("2A2A@gmail.com")
userTwiceUpper(".A.A@gmail.com")