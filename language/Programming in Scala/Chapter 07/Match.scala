/**
	Humf, So I'll go, well, where???????????????????????????/


						PS. I don't speak english
*/

/* Scala's match is good and strange, but powerful */

val firstArg = if (args.isEmpty) "salt" else args(0)

/* You say me a food I say a friend! */
var friend =
	firstArg match {
		case "salt" => "pepper"
		case "chips" => "salsa"
		case "eggs" => "eggs"
		case _ => "huh?"
	}

println(firstArg + " is good with " + friend)



	