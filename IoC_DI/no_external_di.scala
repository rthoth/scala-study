/*
	ideas from vhttp://marakana.com/s/post/1108/dependency_injection_in_scala
*/


trait Statement {

	def apply(args:Any*):Statement

	def close():Statement
}


trait Connection {
	def stmt(cmd:String):Statement
}

def setUserPwd(id:String, pwd:String)(c:Connection) {
	val stm = c.stmt("update user set pwd = ? where id = ?")
	stm(pwd, id)
	stm.close()
}

def getUserPwd(userId:String) {
	q
}


case class DB[A](g:Connection=>A) {
	def apply(c:Connection) = g(c)

	def map[B](f:A=>B):DB[B] = DB(c => f(g(c)))

	def flatMap[B](f:A=>DB[B]):DB[B] = DB(c =>f(g(c))(c))
}


def pure[A](a:A):DB[A] = DB(c => a)

def changePwd(userId:String, oldPwd:String, newPwd:String): DB[Boolean] = 
	for {
		pwd <- getUserPwd(userId)
		eq <- if (pwd == oldPwd) for {
							_ <- setUserPwd(userId, newPwd)
						} yield true
					else
						pure(false)
	} yield eq