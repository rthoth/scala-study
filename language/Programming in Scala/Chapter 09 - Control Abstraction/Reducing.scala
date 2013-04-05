/**
	More is more?
*/

def matching(list:List[Int], matcher:(Int)=>Boolean) =
	for (item <- list; if matcher(item)) yield item



val list = List(1, 2, 3, 5, 7, 11, 13, 17, 19, 23)

val fiveList = matching(list, _ == 5)
val noobList = matching(list, _.toString.length > 1)

assert(fiveList == 5 :: Nil)
assert(noobList == 11 :: 13 :: 17 :: 19 :: 23 :: Nil)

import scala.math.pow

val sqrtList = matching(list, pow(_, 2) % 5 == 1)
assert(sqrtList == 1 :: 11 :: 19 :: Nil)

val maxList = matching(list, _.max(11) < 3)
assert(maxList == Nil)