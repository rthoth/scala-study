/*
	If you know what you did? Why to test?

											Go Horse!......
*/

import org.scalatest.{Suite, FunSuite}

class SimpleSuite extends Suite {
	def test2plus2() {
		assert(2 + 2 == 4)
	}

	def test3plus5() {
		assert(3 + 5 === 7)
	}
}


class FunSuiteTest extends FunSuite {
	test("Test 2 / 2 == 1") {
		expect(1) {
			2.1 / 2.1
		}
	}

	test("when something strange happen!") {
		intercept[ArithmeticException] {
			1 / 0
		}
	}
}
