/*
	Well...

*/

def sum(x:Int, y:Int, z:Int) = x + y * z


val f1 = sum _

assert(f1(1,2,3) == 7)



val f2 = sum(-1, -2, _:Int)

assert(f2(3) == -7)


def sum2(f:(Int,Int,Int)=>Int) = {
	f(-1,-2,-3) * f(1,2,3)
}
assert(sum2(f1) == 35)