/*
	Exemples using Trait to provide a "Graphical" API...

*/


/* We should do that*/
class Point (val x:Int, val y:Int)

object Point {
	def apply(x:Int, y:Int) = new Point(x, y)
}

/* No traits...*/
class RectanguleNoTrait (val topLeft:Point, val bottomRight:Point) {
	def left = topLeft.x
	def right = bottomRight.x
	def width = right - left

	def top = topLeft.y
	def bottom = bottomRight.y
	def height = bottom - top
}


abstract class ComponentNoTrait {

	def topLeft:Point
	/* and more things... */

}

/* I'm tired...*/

/* But, with Scala we can do more with less*/

/* Now with traits */

/*  0 x  */
/*  y    */
trait Rectangular {
	def topLeft:Point
	def bottomRight:Point

	def left = topLeft.x
	def right = bottomRight.x
	def top = topLeft.y
	def bottom = bottomRight.y

	def width = right - left
	def height = bottom - top
}

abstract class Component extends Rectangular

class Rectangule(val topLeft:Point, val bottomRight:Point) extends Rectangular

val rect = new Rectangule(Point(0, 0), Point(10, 20))

assert(rect.width == 10)
assert(rect.height == 20)