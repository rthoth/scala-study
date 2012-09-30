/**
	Allright, sometimes we need more and more...how can we has this?


								Again, I don't speak english! Son of a Bitch

*/

def twice(op:Double=>Double, x:Double) = op(op(x))

assert(twice(_ + 5, 0) == 10)
assert(twice(_ * 5, 1) == 25)



import java.io.{File, PrintWriter}

object Helper {
	def operate (file:File, control:PrintWriter=>Unit)  {
		val writer = new PrintWriter(file)
		try {
			control(writer)
		} finally {
			writer.close()
		}
	}
}

// if there is a lib, then this is should be your code!
Helper operate (
	new File("/tmp/scala.out"),
	writer => writer println new java.util.Date
)


// Good, we can use curly braces instead parentheses...it's smell very better
// So, this is for just one argument

def curlyBraces(arg:String) = arg.toLowerCase

assert(curlyBraces("GGAtC") == "ggatc")

// And, nowwwww
assert (curlyBraces {"GGAtC"} == "ggatc")



def operate (file:File)(control:PrintWriter=>Unit) {
	val writer = new PrintWriter(file)

	try {
		control(writer)
	} finally {
		writer.close()
	}
}

operate (new File("/tmp/scala.chapter09.2")) {
	writer => writer println new java.util.Date
}


// Better than Java

// And now, by-name-parameters....code without writer =>....



// Asserting....

var assertEnabled = true

def myAssert(predicate:()=>Boolean) =
	if (assertEnabled && !predicate())
		throw new AssertionError


myAssert(() => 5 > 3) // Eca!
// myAssert(5 > 3) // Don't work


def byNameAssert(predicate: => Boolean) =
	if (assertEnabled && !predicate)
		throw new AssertionError

byNameAssert(5 > 3)

// Look!
try {
	1 / 0
} catch {
	case e:ArithmeticException => println {"1 / 0 this is a Error!"}
}


try {
	byNameAssert(1/0 == 0)
} catch {
	case e:ArithmeticException => println {"Something is strange!"}
}

assertEnabled = false
byNameAssert(1/0 == 0)
println {"Ok!"}


