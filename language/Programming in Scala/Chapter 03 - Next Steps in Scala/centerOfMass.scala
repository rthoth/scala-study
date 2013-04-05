/**
	Hey, Can you search center of mass?
*/

def centerOfMass(dots:List[Tuple2[Int, Int]]):Tuple3[BigDecimal, BigDecimal, Int] =
	
	if (dots == null || dots.isEmpty)
		null
	else if (dots.size == 1) {
		val mc = new java.math.MathContext(64)
		(BigDecimal(dots.head._1, mc), BigDecimal(dots.head._2, mc), 1)
	} else {
		val tail = centerOfMass(dots.tail)
		val head = dots.head;
		val x = tail._1 * tail._3 + head._1
		val y = tail._2 * tail._3 + head._2
		val mass = tail._3 + 1
		(x/mass, y/mass, mass)
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

for (i <- -500 to 500) 
	bigLine = (i, 0) :: bigLine


val dot = (0,500) :: Nil

println (centerOfMass(bigLine))
println (centerOfMass(bigLine.reverse))

println (centerOfMass( dot ::: bigLine ))
println (centerOfMass(bigLine ::: dot))
println (centerOfMass( dot ::: bigLine.reverse ))
println (centerOfMass(bigLine.reverse ::: dot))