/**
 * My personal filter with for test
 */

class Filteable[A] {

	import scala.collection.mutable.Buffer

	lazy val buffer = Buffer[A]()

	def +=(a: A): Filteable[A] = {
		buffer += a

		this
	}

	def foreach(f: (A)=>Unit): Unit = {
		// try compile to natural java style
		for (i <- 0 until buffer.size)
			f(buffer(i))
	}

	/*def withFilter(f: (A)=>Boolean): Filteable[A] = {

		println(f)

		this
	}*/

}



implicit class RichInt(i: Int) {
	def =%= (o: Int): RichInt = new RichInt(i % o)
}

implicit class Filter(x: RichInt) {

}

implicit class PimpFilterable[A](f: Filteable[A]) {

	def withFilter(f: A => Filter): Seq[A] = {

		null
	}

}


val filteable = new Filteable[Int]

filteable += 0
filteable += 1
filteable += 2
filteable += 3
filteable += 4
filteable += 5
filteable += 6

println ("All elements")
for (i <- filteable) {
	println(i)
}

println("All i % 2 == 0")
for (i <- filteable if (i =%= 0))
	println(i)