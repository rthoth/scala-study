/**
	Hey, Can you search center of mass?
*/

def centerOfMass(dots:List[Tuple2[Int, Int]]):Tuple3[Double,Double, Int] = {
	if (dots == null || dots.isEmpty)
		(0,0,0)
	else if (dots.size == 1)
		(dots.head._1, dots.head._2, 1)
	else {
		val me = dots.head;
		val other = centerOfMass(dots.tail)
		val ix = (me._1 - other._1) / (other._3 + 1)
		val iy = (me._2 - other._2) / (other._3 + 1)
		(other._1 + ix, other._2 + iy, other._3 + 1)
	}
}


val square = List((0,0), (1,0), (1,1), (0, 1))

println (centerOfMass(square))
println (centerOfMass(square.reverse))

val square2 = List((1,0), (0,0), (1,1), (0,1))

println (centerOfMass(square2))
println (centerOfMass(square2.reverse))

val square3 = List((0,0), (1,0), (0,1), (1,1))

println (centerOfMass(square3))
println (centerOfMass(square3.reverse))
