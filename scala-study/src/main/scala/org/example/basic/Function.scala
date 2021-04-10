package org.example.basic

import java.io.{File, PrintWriter}
import java.util.Date

import javax.management.Query

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
	
	/**
	 * 所有的函数可以分成两个部分：一个是共有部分，这部分在该函数的调用都是相同的，另外一部分是非公共部分，这部分在每次调用该函数是可以不同的。
	 * 公共部分为函数的定义体，非公共部分是函数的参数。使用函数类型值作为另外一个函数的参数时，函数的非公共部分也是一个算法。
	 * 调用该函数时，每次都可以传入不同类型的值作为参数，这个函数称为高阶函数——函数的参数也可以时另一个函数
	 *
	 * 使用高级函数可以简化代码，支持创建一个新的程序控制结构来减少代码重复
	 *
	 * 如下面例子
	 */
	object FileMatcher {
		private def filesHere = new File(".").listFiles
		
		/**
		 * 我们定义以下三个API
		 */
		def filesEnding(query: String): Array[File] = {
			filesMatching(_.endsWith(query))
		}
		
		def filesContaining(query: String): Array[File] = {
			filesMatching(_.contains(query))
		}
		
		def filesRegex(query: String): Array[File] = {
			filesMatching(_.matches(query))
		}
		
		/**
		 * 上面三个函数都非常相似，不同的知识过滤条件不同，所以在Scala中可以定义一个高阶函数。
		 * 将三个不同过滤条件抽象成一个函数作为参数传给搜索算法
		 */
		def filesMatching(matcher: String => Boolean): Array[File] = {
			for (file <- filesHere if matcher(file.getName))
				yield file
		}
	}
	
	/**
	 * 柯里化函数
	 * Scala允许程序员自己新创建一些控制结构，并且可以使得这些控制结构，并且使得这些控制结构在语法看起来和Scala内置的控制结构一样
	 * 在Scala中需要借助柯里化，柯里化是把接受多个参数的函数变换成接受一个单一参数的函数，并且返回接受余下的参数并且返回结果的新函数的技术
	 *
	 * 下面是一个未使用柯里化的函数定义，实现一个加法函数
	 */
	def plainOldSum(x: Int, y: Int): Int = x + y
	
	/**
	 * 使用柯里化
	 * 原来的一个参数列表，柯里化把函数定义为多个参数列表
	 * 调用下面的函数时，实际上是依次调用两个普通函数
	 * first + second
	 */
	def curriedSum(x: Int)(y: Int): Int = x + y
	
	/**
	 * 只需要调用下面的方法就可以调用的参数加1
	 */
	/*val onePlus: Int => Int = curriedSum(1)
	
	val first: (Int) => Int = _:Int
	val second: (Int) => Int = _:Int*/
	
	
	/**
	 * 新的控制结构
	 * 下面是一个双倍的控制结构
	 */
	def twice(op: Double => Double, x: Double): Double = op(op(x))
	
	/**
	 * 在写代码的时候，如果发现某些操作选哟重复多次，就可以试着将这个重复操作写成新的控制结构
	 * 如上面的filesMatching，可以进一步的通用化处理
	 * 下面这个称为租赁模式，这种数据结构可以保证不再使用的资源被释放
	 */
	def withPrintWriter(file: File, op: PrintWriter => Unit): Unit = {
		val writer = new PrintWriter(file)
		try {
			op(writer)
		} finally {
			writer.close()
		}
	}
	
	/**
	 * 可以像下面这样调用
	 */
	val file = new File("date.txt")
	
	/**
	 * 柯里化
	 * @param file 文件
	 * @param op 操作
	 */
	def withPrintWriter2(file: File) (op: PrintWriter=>Unit) {
		val writer = new PrintWriter(file)
		try {
			op(writer)
		} finally {
			writer.close()
		}
	}
	
	withPrintWriter2(file){
		_.println(new Date())
	}
	
	/**
	 * 传名参数
	 * 函数传递一般有两种形式——传值和引用，这是第三种方式
	 * 下面是传名函数的一个例子
	 *
	 * 这段代码是将一个表达式传入
	 * 例如 myAssert(3 > 5)
	 * predicate就相当于3 > 5
	 */
	var assertionsEnable = true
	def myAssert(predicate: => Boolean): Unit = {
		if (assertionsEnable && !predicate) {
			throw new AssertionError()
		}
	}
}
