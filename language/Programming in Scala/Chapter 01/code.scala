/**
	1.1

	Programas de diferentes tamanhos exigem contrutores de diferentes tamanhos.
*/

/**
	Veja como podemos fazer um mapa...
*/
var capital = Map("US"->"Washington", "France"->"Paris")

capital += ("Brazil"->"Brasília")

println(capital("Brazil"))

capital + ("Japan"->"Tokyo")

/**
	1.1

	Vendo o código dá a impressão de Scala é uma linguagem "leve", como outras 
	linguagens de "script" como Perl, PHP, Python. Scala não é, o que você está
	vendo é uma característica muito legal de Scala, você verá 
*/


/**
	1.1 Growing new types

	Fazendo as coisas parecerem nativas do Scala.
*/

def factorial (x:BigInt):BigInt = 
	if (x==0) 1 
	else x * factorial(x-1)

println ("10! = " + factorial(10))

/**
	1.1 Growing new types

	Como você viu o BigInt é um tipo definido pelo usuário que parece ser nativo
	da linguagem...
*/


/**
	1.1 Growing new control constructs
*/

object User {
	def loop (f:()=>Boolean) {
		while (f())
			println("executing...")
	}

	def loopT () {
		loop();
	}
}

if (tes) {

	} else {
		'a', false
	}

class T {

}

var g = 0;

User loop { () =>
	g += 1
	g < 10

	"A String"
}

/**
	1.2 What makes Scala scalable

*/