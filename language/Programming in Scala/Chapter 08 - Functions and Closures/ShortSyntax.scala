/*
	When I see a long code, I think why?


							PS. I don't speak english!
*/

def gimeAFunction(f:(Int)=>String) = {
	f(0)
}


// This is a very verbose form
val r1 = gimeAFunction( (x:Int)=> {
	"-5"
})

assert(r1 == "-5")


// And now, a short form using target typing....

val r2 = gimeAFunction((x) => {"Boogie"})

assert(r2 == "Boogie")

// more shorting.....
val r3 = gimeAFunction((x) => "Boogie 2")

assert(r3 == "Boogie 2")

// more...
val r4 = gimeAFunction(x => "Boogie Boogie")
assert(r4 == "Boogie Boogie")


// more, more, more....
val r5 = gimeAFunction(_.toString() + "oogie!")
assert(r5 == "0oogie!")

// Maybe, you need something like this....
val f1 = (_:Int) + (_:String)
assert(f1(-1, "ong") == "-1ong")


val f2 = (_:String) * 5
assert(f2("a") == "aaaaa")



