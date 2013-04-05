/**
	Woohoo....
*/

def g(f:(Int,Int)=>Int) = {
	f(1,2)
}

assert(g(_ * _) == 2)

assert(g(_ + _) == 3)

assert(g(_ / _) == 0)

assert(g(_ - _) == -1)