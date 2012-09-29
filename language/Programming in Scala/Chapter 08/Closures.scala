/**
	I don't should open Pandora's box

						But I'll
*/


val more = 1


val f1 = (x:Int) => (x * more).toString
assert(f1(-1) == "-1")


var mooo = 2
val f2 = (x:Int) => (x * mooo) toString

assert (f2(-2) == "-4")
mooo = -1
assert(f2(-2) == "2")


var last:String = null
def f3(x:Int, y:Int):String = {
	last = x + "," + y
	last
}

assert(last == null)
assert(f3(3,4) == "3,4")
assert(last == "3,4")

// Here a code very very very used in Javascript....

def mkIncrease(y:Int) = (x:Int) => x + y

val increase1 = mkIncrease(1)
val increase25 = mkIncrease(25)

assert (increase1(25) == 26)
assert (increase25(25) == 50)


