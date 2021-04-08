package org.example.basic

object Struct {
	/**
	 * list是不可变对象
	 * Scala可以通过 ::: 方法连接两个列表，实际上就是创建了一个新列表
	 * 也可以通过 :: 向List中添加一个新的元素
	 * 		Scala规定所有以 : 开头的操作符都是右操作符
	 */
	def listTest(): Unit = {
		val oneTwo = List(1, 2)
		val threeFour = List(3, 4)
		val oneToFour = oneTwo ::: threeFour
		println(oneTwo + " and " + threeFour + " were not mutated")
		println("Thus,  " + oneToFour + " is a new List")
	}
	
	/**
	 * 与List不同，Tuples可以包含不同类型的数据，而list只能包含同类型的数据。Tuples在方法返回多个结果的时候非常有用
	 *
	 * scala支持的元组的最大长度为22.可以根据自己的需要扩展更长的元组
	 */
	def tupleTest(): Unit = {
		val pair = (99, "Luftballons")
		println(pair._1)
		println(pair._2)
	}
	
	def setTest(): Unit = {
		var jetSet = Set("Tom", "Job")
		jetSet += "John"
		println(jetSet.contains("Cessna"))
	}
	
	def mapTest(): Unit = {
		val romanNumber = Map(1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV")
		print(romanNumber(3))
	}
	
	def main(args: Array[String]): Unit = {
		listTest()
		setTest()
	}
}
