/**

	What is your order sir?

	Hummm, it's good...I really like this!
*/

class Cat {
	val dangerous = false
}

class Tiger (
	override val dangerous:Boolean,
	private var age:Int
) extends Cat {}
