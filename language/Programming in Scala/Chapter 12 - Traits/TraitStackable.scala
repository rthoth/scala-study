/**
	And now, we'll learn about others Scala's traits power


	Traits 
*/


abstract class IntQueue {
	def get():Int
	def put(x:Int)
}



class BasicIntQueue extends IntQueue {
	import scala.collection.mutable.ArrayBuffer


	private val buffer = new ArrayBuffer[Int]

	def get() = buffer.remove(0)

	def put(x:Int) { buffer += x }
}

val q1 = new BasicIntQueue

q1 put 1
q1 put 2
q1 put 3

assert(q1.get() == 1)
assert(q1.get() == 2)
assert(q1.get() == 3)


trait Doubling extends IntQueue {
	/* It's so cool*/
	/* Look, this put "wrap" invoke of put in concrete class */
	abstract override def put(x:Int) { super.put(2 * x) }
}


class MyDoublingQueue extends BasicIntQueue with Doubling


val q2:IntQueue = new MyDoublingQueue

q2.put(1)
q2.put(2)
q2.put(3)

assert(q2.get() == 2)
assert(q2.get() == 4)
assert(q2.get() == 6)

/* Do I need create a new class? Scala says no!*/

val q3 = new BasicIntQueue with  Doubling

q3.put(0)
q3.put(3)
q3.put(11)

assert(q3.get() == 0)
assert(q3.get() == 6)
assert(q3.get() == 22)


trait Filtering extends IntQueue {
	abstract override def put(x:Int) {
		if (x > 0)
			super.put(x)
	}
}

val q4 = new BasicIntQueue with Filtering

q4.put(1)
q4.put(-1)
q4.put(3)
q4.put(0)
q4.put(2)

assert(q4.get() == 1)
assert(q4.get() == 3)
assert(q4.get() == 2)


trait Inverting extends IntQueue {
	abstract override def put(x:Int) {
		super.put(0 - x)
	}
}

val qIF = new BasicIntQueue with Filtering with Inverting

qIF.put(-2)
qIF.put(-1)
qIF.put(0)
qIF.put(1)
qIF.put(2)
qIF.put(-3)

assert(qIF.get() == 2)
assert(qIF.get() == 1)
assert(qIF.get() == 3)


val qIDF = new BasicIntQueue with Filtering with Doubling with Inverting

qIDF.put(-1)
qIDF.put(-2)
qIDF.put(0)
qIDF.put(1)
qIDF.put(-3)

assert(qIDF.get() == 2)
assert(qIDF.get() == 4)
assert(qIDF.get() == 6)