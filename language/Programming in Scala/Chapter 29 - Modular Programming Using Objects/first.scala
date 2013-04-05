package org.stairwaybook.recipe {

	abstract class Food(val name: String) {
		override def toString = name
	}

	class Recipe (
		val name: String,
		val ingredients: List[Food],
		val instructions: String
	) {

		override def toString = name
	}

	object Apple extends Food("Apple")
	object Orange extends Food("Orange")
	object Cream extends Food("Cream")
	object Sugar extends Food("Sugar")

}
////////////////////////////////////////////////////////////////////////////////
package org.stairwaybook.recipe.modules {
	// Hard link way...

	import org.stairwaybook.recipe._

	object FruitSalad extends Recipe (
		"fruit salad",
		List(Apple, Orange, Cream, Sugar),
		"Stir it all together"
	)

	object SimpleDatabase {
		def allFoods = List(Apple, Orange, Cream, Sugar)

		def foodName(name: String): Option[Food] =
			allFoods.find(_.name == name)

		def allRecipes: List[Recipe] = List(FruitSalad)

		case class FoodCategory(name: String, foods: List[Food])

		private var categories = List(
			FoodCategory("fruits", List(Apple, Orange)),
			FoodCategory("misc", List(Cream, Sugar))
		)

		def allCategories = categories
	}

	object SimpleBrowser {
		def recipesUsing(food:Food) =
			SimpleDatabase.allRecipes.filter { recipe =>
				recipe.ingredients.contains(food)
			}

		def displayCategory(category: SimpleDatabase.FoodCategory) {
			println(category)
		}
	}
}

////////////////////////////////////////////////////////////////////////////////
package org.stairwaybook.recipe.m1 {
	// More soft link way...
	import org.stairwaybook.recipe._

	abstract class Browser {
		val database: Database

		def recipesUsing(food: Food) =
			database.allRecipes.filter { recipe =>
				recipe.ingredients.contains(food)
			}

		def displayCategory(category: database.FoodCategory) {
			println(category)
		}
	}

	abstract class Database {
		def allFoods: List[Food]
		def allRecipes: List[Recipe]

		def foodNamed(name: String) =
			allFoods.find(_.name == name)

		case class FoodCategory(name: String, foods: List[Food])

		def allCategories: List[FoodCategory]
	}

	object SimpleDatabase extends Database {
		// We need implement
		def allFoods = List(Apple, Orange, Cream, Sugar)

		object FruitSalad extends Recipe (
			"fruit salad(m1)",
			List(Apple, Orange, Cream, Sugar),
			"Stir it all together(m1)"
		)

		private var categories = List(
			FoodCategory("fruit(m1)", List(Apple, Orange)),
			FoodCategory("misc(m1)", List(Cream, Sugar))
		)


		def allRecipes: List[Recipe] = List(FruitSalad)
		def allCategories = categories
	}

	object SimpleBrowser extends Browser {
		val database = SimpleDatabase
	}
}
////////////////////////////////////////////////////////////////////////////////
package t1 {
	import org.stairwaybook.recipe.modules._

	object T extends App {
		println("fruit:")
		val apple = SimpleDatabase.foodName("Apple").get
		println(apple)

		println("recipes:")
		val recipes = SimpleBrowser.recipesUsing(apple)
		println(recipes)

		println("Categories:")
		for (category <- SimpleDatabase.allCategories)
			SimpleBrowser.displayCategory(category)
	}
}

package t2 {
	import org.stairwaybook.recipe.m1._

	object T extends App {
		
		val apple = SimpleDatabase.foodNamed("Apple").get

		val recipes = SimpleBrowser.recipesUsing(apple)

		println(recipes)
	}
}