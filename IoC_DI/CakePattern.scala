/**
	In long form!

	based @ http://jonasboner.com/2008/10/06/real-world-scala-dependency-injection-di/
**/

class User(val name:String, val password:String)

trait UserRepository {

	def authenticate(name:String, password:String):User

	def create(name:String, password:String):User

	def delete(user:User)

}


trait UserRepositoryComponent {
	val userRepository:UserRepository
}



class UserRepositoryImpl extends UserRepository {

	def authenticate(name:String, password:String):User = {
		println(this)
		println("authenticating user: " + name)
		new User(name, password)
	}

	def create(name:String, password:String) = {
		println("creating user: " + name)
		new User(name, password)
	}

	def delete(user:User) = {
		println("deleting user: " + user.name)
	}
}


trait UserServiceComponent { this:UserRepositoryComponent =>

	val userService = new UserService

	class UserService {
		def authenticate(name:String, password:String):User = 
			userRepository.authenticate(name, password)

		def create(name:String, password:String):User =
			userRepository.create(name, password)

		def delete(user:User) = 
			userRepository.delete(user)
	}
}

trait UserRepositoryImplComponent extends UserRepositoryComponent {
	lazy val userRepository = new UserRepositoryImpl
}


object module extends UserServiceComponent with UserRepositoryComponent {
	val userRepository = new UserRepositoryImpl
}

class nModule extends UserServiceComponent with UserRepositoryImplComponent {

}


module.userService.authenticate("rthoth", "secret")

val m = new nModule
m.userService.authenticate("super", "secret")

val mm = new nModule
m.userService.authenticate("caos", "earth")