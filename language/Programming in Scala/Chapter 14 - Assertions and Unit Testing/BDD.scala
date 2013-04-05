/*
	Do you want know what code do?

			No, thanks! I'm just plumber!
*/

/* In BDD the System speaks what it do*/



trait IntQueue {

	def push(i:Int):IntQueue

	def pop():Int
}


class ArrayQueue extends IntQueue {
	import scala.collection.mutable.ArrayBuffer

	private val buffer = new ArrayBuffer[Int]

	override def push(i:Int):IntQueue = {
		buffer += i
		this
	}

	override def pop() = buffer.remove(0)
}


trait PositiveQueue extends IntQueue {
	override abstract def push(i:Int):IntQueue = {
		if (i >= 0)
			super.push(i)
		this
	}
}

trait InverseQueue extends IntQueue {
	override abstract def push(i:Int) = {
		super.push(0 - i)
		this
	}
}

trait NoEvenQueue extends IntQueue {
	override abstract def push(i:Int) = {
		if (i % 2 == 0)
			throw new IllegalArgumentException("Invalid " +i)

		super.push(i)
	}
}


import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class QueueSpec extends FlatSpec with  ShouldMatchers {


	"A basic Int Queue" should 
		"add 2 twice" in {
			
			val queue = new ArrayQueue
			queue.push(2)
			queue.push(2)

			queue.pop() should be (2)
			queue.pop() should be (2)
		}

	"A inverse queue" should
		"inverse -1 to 1" in {
			val queue = new ArrayQueue with  InverseQueue

			queue.push(-1)

			queue.pop() should be (1)
		}

	it should "inverse 0 to 0" in {
		val queue = new ArrayQueue with InverseQueue

		queue.push(0)
		queue.pop() should be (0)
	}

	it should "inverse 101 to -101" in {
		val queue = new ArrayQueue with InverseQueue

		queue.push(101)
		queue.pop() should be (-101)
	}

	"PositiveQueue" should
		"Add 1 -1 2 doesn't have -1" in {
			val queue = new ArrayQueue with PositiveQueue

			queue.
			push(1).
			push(-1).
			push(2)

			queue.pop() should be (1)
			queue.pop() should be (2)
		}

	"NoEvenQueue" should
		"runs good with odd numbers" in {
			val queue = new ArrayQueue with NoEvenQueue

			queue.
			push(1).
			push(3).
			push(-11)

			queue.pop() should be (1)
			queue.pop() should be (3)
			queue.pop() should be (-11)
		}	

		it should "Badly with even numbers" in {
			val queue = new ArrayQueue with  NoEvenQueue
			queue.push(21)

			queue.pop() should be (21)
			evaluating {
				queue.push(1024)
			} should produce [IllegalArgumentException]
		}
}