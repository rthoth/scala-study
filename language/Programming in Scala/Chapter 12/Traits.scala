/**
	I have the power...
*/

trait Philosophical {
	def philosophize = "I consume memory, therefore I am!"
}

class Animal
trait HasLegs

class Frog extends Animal with Philosophical with HasLegs {
	override def toString = "green"

	override def philosophize =
		"It ain't easy being " + toString + "!"
}


val phrog:Philosophical = new Frog

assert(phrog.philosophize == "It ain't easy being green!")