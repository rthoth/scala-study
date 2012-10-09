package outerspace {
	package andromeda {
		object PlanetX {

			private val _name = "Secret"

			private [outerspace] def name = _name


		}
	}

	object Enterprise {

		def goFarway = andromeda.PlanetX.name

		def start(i:Int):String = show(i)


	}


}

package object outerspace {

	def show(i:Int):String = i.toString
}


package ufla.dcc {
	object BlaftStof {
		import outerspace.show

		override def toString = show(5)
	}
}