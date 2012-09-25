import scala.collection.mutable.Map

class CheckSumAccumulator {
	
	private var sum = 0

	/** 
		Look: Without "=" def add has result type Unit.

		It's like a "procedure"
	*/
	def add (b:Byte) { sum += b }

	def checksum():Int = ~(sum & 0xFF) + 1

	override def toString() = "CheckSumAccumulator(sum=" + sum + ")"

}

object CheckSumAccumulator {
	private val cache = Map[String, Int]()

	def calculate (s:String):Int = 
		if (cache.contains(s))
			cache(s)
		else {
			val acc = new CheckSumAccumulator
			for (c <- s)
				acc.add (c.toByte)

			val cs = acc.checksum()
			cache	+= 	(s -> cs)
			cs
		}
}