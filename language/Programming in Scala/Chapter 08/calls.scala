/**

	There are many ways to call me, but I never attempt call someone


*/



def list(slices:String*) = for (slice <- slices) yield '"' + slice + '"'

assert(list("One", "Two", "Ouch!") == List("\"One\"", "\"Two\"", "\"Ouch!\""))





val array = Array("1", "zero", "?")

// if you try do this...

// list(array)

// error....

// To do this, you will do this...
assert (list(array: _*) == List("\"1\"", "\"zero\"", "\"?\""))


def callme(one:Int, two:Int, string:String) =
	if (one == 1 && two == 2 && string == "string")
		true
	else
		false

assert(callme(1, 2, "string"))
assert(callme(2, 1, "?") == false)

// Alright....
assert(callme(string="string", one = 1, two = 2))


def pow(base:Int, exp:Int = 2) = Math.pow(base, exp)


// val fac = (x:Int) => String { if (x == 0) 1 else x * fac(x -1) }