/**
	Hey, Can you search center of mass?
*/
def centerOfMass(dots:List[Tuple2[Int, Int]]):Tuple3[BigDecimal,BigDecimal, Int] = {
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

println (centerOfMass (Nil))
println (centerOfMass(null))


var bigLine:List[Tuple2[Int, Int]] = Nil;
for (i <- 0 to 2000) 
	bigLine = (i, 0) :: bigLine


val dot = (0,10) :: Nil

println (centerOfMass(bigLine))
println (centerOfMass(bigLine.reverse))

println (centerOfMass( dot ::: bigLine ))
println (centerOfMass( dot ::: bigLine.reverse ))
println (centerOfMass(bigLine ::: dot))
println (centerOfMass(bigLine.reverse ::: dot))