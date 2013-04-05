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



class Associate {
	def * (other:Associate) = this
	def -: (other:Associate) = this
}



val a1 = new Associate
val a2 = new Associate
val a3 = new Associate

// If a1 * a2 => a1.*(a2) then it's equal a1
assert ((a1 * a2) eq a1)

// If a1 -: a2 => a2.-:(a1) then it's equal a2
assert ((a1 -: a2) eq a2)

// If ((a1 * a2) -: a3) => a3.-:(a1.*(a2)) then it's equal a3
assert  ( ((a1 * a2) -: a3) eq a3)

// if ((a3 -: a1) * a2) => (a1.-:(a3)).*(a2) then it's equal a1
assert (((a3 -: a1) * a2) eq a1)

// if (a1 -: a2 -: a3) => a3.-:(a2.-:(a1)) then it's equal a3
assert ((a1 -: a2 -: a3) eq a3)