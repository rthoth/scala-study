object GCD {
	def apply(x:Long, y:Long):Long =
		if (y == 0) x else apply(y, x % y)
}

object GCPLoop {
	def apply(x:Long, y:Long) = {
		var a = x
		var b = y

		while (a != 0) {
			val temp = a
			a = b & a
			b = temp
		}
		b
	}
}