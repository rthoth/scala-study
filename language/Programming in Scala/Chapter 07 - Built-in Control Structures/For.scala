/**
	Scala's fors, fives, sixs, sevens...

						I'm sorry I don't speak english!
*/

val limit = 20
val list = 1 to limit

for (element <- list)
	assert(element >= 1 && element <= limit)



/* For ands Ifs...*/
for (
	number <- list 
	if number % 2 == 0
	if number % 7 == 0

) 
	assert(number == 14)


/* See well: Brazilian's expression in literal english! */
/* With curly braces we can forget some semicolons */
for {
	number <- list
	if number % 2 == 0
	even <- 1 to number
	string = even.toString + "+"
	if even % 11 == 0
} 
	assert(string == "11+" && even == 11)


val strange = for {
	number <- list
	if number % 5 == 0
	some <- 1 to number
	if some % 3 == 0
} yield
	some * number

assert(strange == 
		List(
			5 * 3,
			10 * 3, 10 * 6, 10 * 9,
			15 * 3, 15 * 6, 15 * 9, 15 * 12, 15 * 15,
			20 * 3, 20 * 6, 20 * 9, 20 * 12, 20 * 15, 20 * 18
		)
)


var ooo:Any = null;

try {
	ooo = throw new RuntimeException("n must be even")
} catch {
	case e: Exception => println(ooo)
}