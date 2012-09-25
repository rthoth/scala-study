/**
	Well, Scala's operators?

	Scala doesn't have operators


						PS. I don't speak english!
*/
class Operand {
	def + (o:Operand):Operand = {
		println("+")
		new Operand
	}

	def ++ (o:Operand):Operand = {
		println("++")
		new Operand
	}

	def * (o:Operand):Operand = {
		println("*")
		new Operand
	}

	def ** (o:Operand):Operand = {
		println("**")
		new Operand
	}

	def *+ (o:Operand):Operand = {
		println("*+")
		new Operand
	}

	def +* (o:Operand):Operand = {
		println("+*")
		new Operand
	}

	/*
	def +a (o:Operand):Operand = {
		println("+a")
		new Operand
	}

	def *a (o:Operand):Operand = {
		println("*a")
		new Operand
	}
	*/

	def operator (o:Operand):Operand = {
		println("operator")
		new Operand
	}
}

object Operand {
	def apply():Operand = new Operand
}

Operand() + Operand() * Operand()
println("___________________________________________________________")

Operand() * Operand() + Operand()
println("___________________________________________________________")

Operand() * Operand() + Operand() * Operand()
println("___________________________________________________________")

Operand() +* Operand() + Operand() *+ Operand()
println("___________________________________________________________")

Operand() + Operand() * Operand() *+ Operand() +* Operand()
println("___________________________________________________________")

Operand() +* Operand() *+ Operand()
println("___________________________________________________________")

Operand() *+ Operand() +* Operand()
println("___________________________________________________________")
