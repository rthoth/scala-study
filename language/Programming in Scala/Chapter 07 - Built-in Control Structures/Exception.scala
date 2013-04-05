/**
	When something strange happen...we should run to home


												PS. I don't speak english!
*/
import java.net.URL
import java.net.MalformedURLException

def urlFor(path:String) =
	try {
		new URL(path)
	} catch {
		case e:MalformedURLException =>
			new URL("http://www.scala-lang.org")
	}

val ufla = "http://www.ufla.br/"
val url = urlFor(ufla) //

// Alright, it's ok!
assert(url == new URL(ufla), url + " = " + new URL(ufla))


// Hum! It's a bad idea, whoo protocol?
val badIdea = urlFor("whoo://www.woohoo.com")

assert(badIdea == new URL("http://www.scala-lang.org"))