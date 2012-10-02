/**

	Scala's OO is more OO than Java's OO



	but! Scala is so cool!

								Remember! 2 + 2 = 4, never forget this!

*/


object Element {
	def elem(contents:Array[String]):Element =
		new ArrayElement(contents)

	def elem(chr:Char, width:Int, height:Int):Element =
		new UniformElement(chr, width, height)

	def elem(line:String):Element =
		new LineElement(line)

	private class ArrayElement(conts:Array[String]) extends Element {
		/*
			This is a difference between Scala and Java...

			Scala has 2 namespaces and Java has four

			Java:
			fields
			methods
			types
			packages

			Scala:
			values(fields, methods, packages and singleton objects)
			types(class and trait)


			**Please, see ParametricFields they are so cool!

		*/
		val contents = conts
	}

	/* Do you understand?  */
	private class LineElement(s:String) extends ArrayElement(Array(s))

	private class UniformElement (
		ch: Char,
		override val width:Int,
		override val height:Int
	) extends Element {
		private val line = ch.toString * width

		// Array.fill => [T](n:Int)(element=>T)
		// Make a exercise...
		def contents = Array.fill(height)(line)
	}

}

abstract class Element {
	def contents: Array[String]

	import Element.elem // I think do this here


	/*So, width and height*/

	def width = if (height == 0) 0 else contents(0).length
	def height = contents.length

	/* Look, width and height has no parameters, they are parameterless methods */
	/* The recomended convention only no side-effects members */


	def above(that:Element):Element = {
		val thisw = this widen that.width
		val thatw = that widen this.width

		elem(thisw.contents ++ thatw.contents)
	}

	/* It's more elegant */
	def beside(that:Element):Element = {
		val thish = this heighen that.height
		val thath = that heighen this.height
		elem(
			for (
				(l1, l2) <- thish.contents zip thath.contents
			) yield l1 + l2
		)
	}

	def heighen(h:Int):Element =
		if (h <= height)
			this
		else {
			val top = elem(' ', width, (h - height) /2)
			val bottom = elem(' ', width, h - height - top.height)
			top above this above bottom
		}


	override def toString = contents mkString "\n"

	def widen(w:Int):Element =
		if (w <= width)
			this
		else {
			val left = elem(' ', (w - width) / 2, height)
			val right = elem(' ', w - width - left.width, height)
			left beside this beside right
		}
}


object Spiral {
	import Element.elem

	val corner = elem("+")
	val space = elem(" ")




	def apply(nEdges:Int, direction:Int):Element = {
		if (nEdges == 1)
			elem("+")
		else {
			val sp = apply(nEdges -1, (direction + 3) % 4)
			def verticalBar = elem('|', 1, sp.height)
			def horizontalBar = elem('-', sp.width, 1)
			if (direction == 0)
				(corner beside horizontalBar) above (sp beside space)
			else if (direction == 1)
				(sp above space) beside (corner above verticalBar)
			else if (direction == 2)
				(space beside sp) above (horizontalBar beside corner)
			else
				(verticalBar above corner) beside (space above sp)
		}
	}

	def apply(nEdges:Int):Element = apply(nEdges, 0)
}