/**
	Yeahhh!


*/

def curriedSum(x:Int)(y:Int) = x + y

assert(curriedSum(2)(4) == 6)


// Well, I'll simulate this...

def g(x:Int) = (y:Int) => x + y
assert(g(2)(4) == 6)
assert(g(2).toString == "<function1>")

assert((curriedSum(2) _).toString == "<function1>")

// Partially
val sum = curriedSum(4) _

assert(sum(10) == 14)