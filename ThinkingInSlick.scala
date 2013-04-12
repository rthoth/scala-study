package db {

	sealed case class Column[T](name: String, typeName: String)

	trait Profile {
		val driverName: String

		def specificTableName(name: String): String
		def specificColumn[T](name: String, typeName: String): Column[T]

		abstract class Table {
			val tableName: String
			final override def toString = specificTableName(tableName)
			def column[T](name: String, typeName: String): Column[T] = specificColumn[T](name, typeName)
		}
	}

	trait DB {
		profile: Profile =>

		trait DSL {
			type Table = profile.Table
		}

		val dsl = new DSL{}
	}
}

package db.h2 {
	import db.Column

	object H2DB extends db.DB with db.Profile {
		val driverName = "H2DB"

		def specificTableName(name: String) = s"H2DB[$name]"
		def specificColumn[T](name: String, typeName: String) = db.Column[T](name, s"h2_$typeName.Type")
	}
}

package db.pg {
	object PGDB extends db.DB with db.Profile {
		val driverName = "PGDB"
		def specificTableName(name: String) = s"pg_$name"
		def specificColumn[T](name: String, typeName: String) = db.Column[T](name, "pg_" + typeName)
	}
}

package app {
	import db.DB
	import users._

	trait Profile {
		val db: DB
	}

	case class Service(system: UsersComponent) {
		import system._
		val tableUsers: String = system.Users.toString
		val tableAccounts: String = system.Accounts.toString

		println (s"I'm a Service and using [$tableUsers, $tableAccounts]")

		println("Table Users column age=" + system.Users.age)
		println("Table Accounts column title=" + system.Accounts.title)
	}
}

package app.users {

	trait UsersComponent extends AccountsComponent {
		this: app.Profile =>
		import db.dsl._

		object Users extends Table {

			val tableName = "users"
			def countAccounts(name: String) = (Accounts / name).size

			def age = column[Int]("age", "int")
		}
	}

	trait AccountsComponent {
		this: app.Profile =>

		import db.dsl._

		object Accounts extends Table {
			val tableName = "accounts"

			def title = column[String]("title", "varchar[255]")

			def / (name: String): Seq[Int] = {
				for (i <- 1 to (scala.math.random * 100).intValue)
					yield i
			}
		}
	}
}

object Test extends App {

	import db._
	import db.h2._
	import db.pg._
	import app.users._

	val onH2DB = new UsersComponent with app.Profile {
		val db = H2DB
	}

	val onPG = new UsersComponent with app.Profile {
		val db = PGDB
	}


	val service = new app.Service (onH2DB)

	service.copy(onPG)

}