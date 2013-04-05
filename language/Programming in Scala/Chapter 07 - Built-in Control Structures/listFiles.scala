/**
	Scala can be cool, but it's little strange
*/

val filesThere = new java.io.File("/tmp").listFiles

for (file <- filesThere)
	println(file)