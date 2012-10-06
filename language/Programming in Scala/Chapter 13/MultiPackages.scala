/*
	Where are my classes?
*/

package outerspace {
	trait Alien
}

package ufla {

	class Teacher

	class Student

	package dcc {
		class Teacher extends outerspace.Alien {
					override def toString = "ET phone home"
		}

		class Coder extends Student with outerspace.Alien
	}

	package deg {
		object SuperTeacher extends Teacher {
		}
	}

	object Supervisor {
		val strange = new dcc.Teacher
		val common:Teacher = deg.SuperTeacher 
	}


}

package ufla {
	object Rector {
		val vice:Teacher = deg.SuperTeacher
		val spy = Supervisor

		val friend = new dcc.Coder
	}


	package rectory {
		class Desk {
			val boss = Rector
			val colorfulFriend = new dcc.Coder
		}
	}
}