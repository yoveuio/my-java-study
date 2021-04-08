package org.example.basic

import scala.annotation.tailrec
import scala.io.Source

class Function(x:Int) {
	/**
	 * 函数：头等公民
	 * Scala中函数不仅可以定义一个函数然后调用它，而且可以写一个未命名的函数字面量，然后可以把它当成一个值传递给其他函数或是赋值给其他变量
	 */
	override def toString: String = x.toString
}

object Function {
	def main(args: Array[String]): Unit = {
		// 头等公民示例
		val someNumbers = List(-11, -10, -5, 0, 5, 10)
		someNumbers.foreach(x => println(x))
		
		println("--------------------")
		// 函数字面量的简化写法， 如
		someNumbers.filter(_ > 0).foreach(a => println(a))
		
		println("--------------------")
		// 部分应用的函数
		/// 可以使用_代替整个参数列表
		/// someNumbers.filter(_ > 0).foreach(println _)
		someNumbers.filter(_ > 0).foreach(println)
		
		println("--------------------")
		println(b(1, 5))
		
		println("--------------------")
		println(addMore(4))
		more = 3
		println(addMore(4))
	}
	
	def processFile(filename: String, width: Int): Unit = {
		val source = Source.fromFile(filename)
		for (line <- source.getLines()) processFile(filename, width, line)
	}
	
	private def processFile(filename:String, width:Int, line:String): Unit = {
		if (line.length > width)
			println(filename + ":" + line.trim)
	}
	
	/**
	 * 部分应用函数
	 */
	private def sum = (_:Int) + (_:Int) + (_:Int)
	
	
	private var more = 0
	
	private val addMore = (x: Int) => x + more
	
	private val b = (a: Int, b:Int) => sum(a, _:Int, b)
	
	/**
	 * 重复参数 *
	 * 定义函数时允许指定最后一个参数可以重复(变长参数)，从而允许函数调用者使用变长的参数列表调用该函数
	 */
	def echo(args: String *): Unit = {
		for (arg <- args) println(arg)
	}
	
	/**
	 * 命名参数
	 * 允许使用任意顺序传入参数
	 */
	def speed(distance:Float, time:Float): Float = distance / time
	
	val speed1:Float = speed(time = 10, distance = 100)
	
	def isGoodEnough(guess: Double): Boolean = ???
	
	def improve(guess: Double): Double = ???
	
	/**
	 * 函数尾递归
	 * 前面说了我们要善于使用递归替代迭代，但是在Java中递归的性能损耗明显惠比迭代大很多
	 * 然而在Scala中，由于函数尾递归的存在，这个差距是不大的
	 *
	 * 函数尾递归：
	 *	Scala在检测到尾递归而使用循环来代替
	 */
	@tailrec
	def approximate(guess: Double): Double = {
		if (isGoodEnough(guess)) guess
		else approximate(improve(guess))
	}
}
