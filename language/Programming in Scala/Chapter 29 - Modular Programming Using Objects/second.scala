
// a file
package recipes {
	abstract class Food(val name: String) {
		override def toString = name
	}

	object Apple extends Food("Apple")
}
// another file
package recipes {
	class Recipe (
		val name: String,
		val ingredients: List[Food],
		val instructions: String
	) {

		override def toString = name
	}
}
// another file
package recipes {
	trait FoodCategories {
		case class FoodCategory(name: String, foods: List[Food])

		def allCategories: List[FoodCategory]
	}
}
// another file
package recipes {

	abstract class Database extends FoodCategories {
		def allFoods: List[Food]
		def allRecipes: List[Recipe]

		def foodNamed(name: String) = 
			allFoods.find(_.name == name)
	}
}
// another file
package recipes {

	abstract class Browser {
		val database: Database

		def displayCategory(category: database.FoodCategory) {
			println(category)
		}
	}
}
// another file
package recipes {
	object SimpleDatabase extends Database 
		with SimpleFoods with SimpleRecipes
}
// another file
package recipes {
	trait SimpleFoods {
		object Pear extends Food("Pear")

		def allFoods = List(Apple, Pear)
		def allCategories = Nil
	}
}
// another file
package recipes {
	trait SimpleRecipes {
		this: SimpleFoods =>

		object FruitSalad extends Recipe (
			"fruit salad",
			List(Apple, Pear),
			"Mix it all together"
		)

		def allRecipes = List(FruitSalad)
	}
}
// another file
package recipes {
	object CommonDatabase extends Database {
		object Orange extends Food("Orange")
		object Sugar extends Food("Sugar")

		def allFoods = List(Apple, Orange, Sugar)

		object FruitSalad extends Recipe (
			"fruit salad",
			List(Apple, Orange, Sugar),
			"Mix it all together"
		)

		def allRecipes = List(FruitSalad)

		def allCategories = List(
			FoodCategory("fruits", Apple :: Orange :: Nil),
			FoodCategory("misc", Sugar :: Nil)
		)
	}

	object CommonBrowser extends Browser {
		val database: CommonDatabase.type = CommonDatabase
	}

	object StudentDatabase extends Database {
		object FrozenFood extends Food("FrozenFood")

		object HeatItUp extends Recipe(
			"heat it up",
			List(FrozenFood),
			"Microwave the 'food' for 10 minutes"
		)

		def allFoods = FrozenFood :: Nil
		def allRecipes = HeatItUp :: Nil
		def allCategories = List(
			FoodCategory("edible", FrozenFood :: Nil)
		)
	}

	object StudentBrowser extends Browser {
		val database: StudentDatabase.type = StudentDatabase
	}
}

package recipes.t1 {
	import recipes._
	object T extends App {
		println (SimpleDatabase.allFoods)
		println (SimpleDatabase.allCategories)
		println (SimpleDatabase.allRecipes)
	}
}

package recipes.t2 {
	import recipes._

	object T extends App {
		
		// Tracking module instances...
		val db: Database = CommonDatabase

		object B extends Browser {
			val database: db.type = db
		}

		for (category <- db.allCategories)
			B displayCategory category

	}
}