	/*
		Pattern Matching and Case Classes are things very cool or not!
	*/



sealed abstract class Expr

case class Var(name:String) extends Expr
case class Number(num:Double) extends Expr
case class UnOp(operator:String, arg:Expr) extends Expr
case class BinOp(operator:String, left:Expr, right:Expr) extends Expr


def simplifyTop(exp:Expr):Expr =  exp match {
	case UnOp("-", UnOp("-", e)) => e
	case BinOp("+", e, Number(0)) => e
	case BinOp("*", e, Number(1)) => e		
	case _ => exp
}


assert( Number(10) == simplifyTop(UnOp("-", UnOp("-", Number(10)))))

assert( Number(4) == simplifyTop(BinOp("*", Number(4), Number(1))))



// Sequence pattern


assert( "Ok" == (

	List(1,0,2) match {
		case List(1,2) => "Sorry!"
		case List(1,_,2) => "Ok"
		case _ => "Sorry!"
	}
	
))

assert("Ok" == (

	List(1,-1,2,3,4,5,6) match {
		case List(1) => "Sorry!"
		case List(1,-1,_*) => "Ok"
		case _ => "Sorry!"
	}
	

))


// Tuples patterns

assert ("Ok" == (

	(1,2,3).asInstanceOf[Any] match {
		case 1 => "Sorry"
		case (a,b,c,d) => "Sorry"
		case (a,b,c) => "Ok"
		case _ => "Sorry"
	}

))


// Type patterns

def tellmeType (a:Any):String = a match {
	case s:String => "string"
	case v:Double => "double"
	case a:Array[_] => "array"
	case c:Map[_, _] => "map"
	case c:Iterable[_] => "iterable"
	case _ => "dunno"
}


assert("string" == tellmeType("a"), "s1")
assert("double" == tellmeType(1.04), "s2")
assert("array" == tellmeType(Array(1,3,true)), "s3")
assert("map" == tellmeType(Map(4->"5")), "s4")
assert("dunno" == tellmeType( ()=>4 ), "s5")
assert("iterable" == tellmeType(Seq(1,2,3,4,5)), "s6")



// Variable binding...

assert ( Var("c") == (

	BinOp("+", UnOp("-", Number(3)), Var("c")) match {
		case BinOp(_, BinOp(_, _, _), _) => null
		case BinOp(_, UnOp(_, _), c @ Var("d")) => null
		case BinOp(_, UnOp(_, _), v @ Var("c")) => v
		case _ => null
	}
	

), "Variable binding #1")


// Patterns guards

assert ( BinOp("*", Var("a"), Number(2)) == (

	BinOp("+", Var("a"), Var("a")) match {
		case BinOp("+", Var(a), Var(b)) if a == b => BinOp("*", Var(a), Number(2))
		case x => x
	}
	
), "Patterns guards #1")



// Patterns everywhere ...

val tuple = (123, "abc", false)
val (number, string, bool) = tuple

assert(number == 123 && string == "abc" && bool == false, "Patterns everywhere #1")


val exp:Expr = BinOp("+", Var("a"), Number(2))

val BinOp(op, o1, o2) = exp

assert (op == "+" && o1 == Var("a") && o2 == Number(2), "Patterns everywhere #2")



// Case sequence as partial functions

val widthDefault:Option[Int] => Int = {
	case Some(x) if x > 0 => x
	case Some(x) if x < 0 => -x
	case None => 0
}

assert(widthDefault(Some(1)) == 1, "case sequence functions #1")
assert(widthDefault(Some(-1)) == 1, "case sequence functions #2")
assert(widthDefault(None) == 0, "case sequence functions #3")


val second: List[Int]=>Int = {
	case x :: y :: _ => y
}


assert(second(List(1,-1,0)) == -1, "case sequence functions #4")

assert ({
	try {
		second(List(1)) == 0
	} catch {
		case e: MatchError => true
		case _: Throwable => false
	}
}, "case sequence functions #5")

val secondPartial:PartialFunction[List[Int], Int] = {
	case x :: y :: _ => y
}


assert(secondPartial.isDefinedAt(List(-1,-2,3)), "case sequence functions #7")
assert(!secondPartial.isDefinedAt(List()), "case sequence functions #8")
assert(!secondPartial.isDefinedAt(List(0)), "case sequence functions #9")

assert(-2 == secondPartial(List(0,-2,3)))


// Here makes sense....case as partial functions

val capitals = Map("France"->"Paris", "USA"->"Washington", "Brazil"->"Brasília")

val cities = for ((country, city) <- capitals) yield city

var head = cities.head
var tail = cities.tail

assert(head == "Paris", "make sense #1")
head = tail.head
tail = tail.tail
assert(head == "Washington", "make sense #2")
head = tail.head
tail = tail.tail
assert(head == "Brasília", "make sense #3")
assert(tail.isEmpty, "make sense #4")

val list = List(Some(1), Some(2), Some(3), None, Some(5), None, Some(6))


// Do you understand?
val factors = for (Some(n) <- list) yield n

assert(factors.size == 5, "make sense #5")
assert (factors.reduce( (x, y) => x * y ) == 180, "make sense #6")


