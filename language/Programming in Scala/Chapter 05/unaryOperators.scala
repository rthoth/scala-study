/**
	Remember, Scala doesn't have operators...
*/

class Operand {
	def * () = this

	def * (o:Operand) = this

	// Only +, -, ! and ~ can be unary
	def unary_! () = this

	def unary_* () = this
}

val o1 = new Operand
val o2 = new Operand


// if o1 * => o1.*() then it's equal o1
assert ((o1 *) eq o1)

// if o2 * o1 => o2.*(o1) then it's equal o2
assert ((o2 * o1) eq o2)

// if !o2 => o2.unary_! then it's equal o2
assert ((!o2) eq o2)

assert (o2.unary_* eq o2)