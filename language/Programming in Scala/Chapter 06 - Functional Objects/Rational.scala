/**
	When be rational it's more important than happy


										PS. I don't speak english!
*/

class Rational (n:Int, d:Int) {
	require(d != 0, "Denominator doesn't be equal 0")

	def this(n:Int) = this(n, 1) // Auxiliary Constructor...

	private val g = gcd(n.abs, d.abs)

	val numerator = n / g

	val denominator = d / g

	def + (that:Rational) =
		new Rational(
				numerator * that.denominator + that.numerator * denominator,
				denominator * that.denominator
		)

	def + (i:Int) =
		new Rational(numerator + i * denominator, denominator)

	def - (that:Rational) =
		new Rational(
			numerator * that.denominator - that.numerator * denominator,
			denominator * that.denominator
		)

	def - (i:Int) =
		new Rational(numerator - i * denominator, denominator)

	def * (that:Rational) =
		new Rational(
			numerator * that.numerator,
			denominator * that.denominator
		)

	def * (i:Int) =
		new Rational(numerator * i, denominator)

	def / (that:Rational) =
		new Rational(numerator * that.denominator, denominator * that.numerator)

	def / (i:Int) =
		new Rational(numerator, denominator * i)

	override def toString() = numerator + "/" + denominator

	private def gcd(a:Int, b:Int):Int = 
		if (b == 0) a else gcd(b, a % b)

}


/*
val o1 = new Rational(4)
val o2 = new Rational(1,4)

println(o1 + o2)
println(o1 * o2)
println(o2 + o1)
println(o2 * o1)

println(o1 + o1 * o2)

new Rational(1, 0)
*/