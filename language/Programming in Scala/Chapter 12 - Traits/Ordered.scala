/*
	This is Copy and Paaaaaaaaaste!
*/
class Rational (n:Int, d:Int) extends Ordered[Rational] {
	require(d != 0, "Denominator doesn't be equal 0")

	def this(n:Int) = this(n, 1) // Auxiliary Constructor...

	private val g = gcd(n.abs, d.abs)

	val numerator = n / g

	val denominator = d / g


	override def compare(that:Rational) = 
		(numerator * that.denominator) - (denominator * that.numerator)

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


val u12 = new Rational(1,2)
val u13 = new Rational(1,3)
val u24 = new Rational(2,4)

assert(u13 < u12)
assert(u12 > u13)
assert(u24 < u12 == false)
assert(u12 < u24 == false)