package org.example.basic

import java.io.{BufferedReader, File, FileNotFoundException, InputStreamReader}

import scala.io.Source
import scala.util.control.Breaks.{break, breakable}


/**
 * 和其他高级语言相比，Scala只内置了为数不多的几种程序控制语句：if, while, for, try match以及函数调用。
 * 这是因为Scala诞生开始就包含了函数字面量，一些程序控制可以通过库来扩展
 */
class Control {
	
}

object Control {
	def main(args: Array[String]): Unit = {
		// Scala所有的控制语句都有返回值，可以让程序更加精炼
		val filename = if (!args.isEmpty) args(0) else "default.txt"
		
		tryTest(1)
	}
	
	/**
	 * Scala中应该尽量避免使用while
	 * 因为While循环没有值(赋值语句没有值)。Scala保留while语句只是为了某些时候使用循环代码更加容易理解
	 * 而如果纯函数化编程时，需要执行一些重复运行的代码，通常就需要使用回溯函数来实现。
	 */
	def forTest(): Unit = {
		// 基本用法
		val filesHere = new File(".").listFiles()
		for (file <- filesHere) println(file)
		println("---------------------")
		
		// 枚举range类型
		for (i <- 1 to 4) println("Interation" + i)
		println("---------------------")
		
		// 带过滤条件的for
		for (file <- filesHere if file.isFile) println(file)
		
		println("---------------------")
		// 带多个过滤条件
		for (file <- filesHere
			 if file.isFile
			 if file.getName endsWith ".xml") println(file)
		
		println("---------------------")
		
		// 嵌套迭代
		def fileLines(file: File): List[String] = Source.fromFile(file).getLines().toList
		
		for {file <- filesHere if file.getName.endsWith(".xml") // 或者使用()配合分号
			 line <- fileLines(file)
			 trimmed = line.trim
			 if trimmed.trim != null}
			println(line)
		
		// 生成新集合
		for (file <- filesHere if file.getName.endsWith(".xml")) yield file
	}
	
	/**
	 * Scala的异常处理和Java类似，一个方法可以通过抛出异常的方法而不返回值的方式终止相关代码的运行。异常会传递给函数的调用者
	 *
	 * 从技术上来说，抛出异常的类型为Nothing。对于下面这个例子，整个if表达式的类型为可以计算出值的那个分支的类型。不需要考虑throw表达式的类型
	 *
	 * 和Java异常处理不同，Scala不需要捕获checked的异常，这点和C#一样，也不要使用throw来声明某个异常，如果有需要可以通过@throw声明一个异常
	 * 但这不是必须的
	 */
	def tryTest(n: Int): Unit = {
		def half(n: Int): Int = if ((n & 1) == 0) n / 2 else throw new RuntimeException("n must be even")
		
		// 捕获异常
		val file = "hello.txt"
		try {
			val f = half(n)
		} catch {
			case ex: FileNotFoundException => ex.printStackTrace()
			case _: RuntimeException => println("Handle RunTimeException")
			case _: Exception => println("Exception")
		} finally {
			// file close
		}
	}
	
	/**
	 * Scala的match表达式支持从多个选择中选取一个，类似其他语言的switch语句。通常来说，Scala的match表达式支持任意的匹配模式
	 * 与Java相比有下面几个不同之处
	 * 	1. 任何类型的常量都可以同在case语句中
	 * 	2. 每个case语句无需break，Scala不支持"fall through"
	 * 	3. Scala的缺省匹配为'_'，作用类似于Java中default
	 * @param args 参数数组
	 */
	def matchTest(args: List[String]): Unit = {
		val firstArg = if (args.nonEmpty) args.head else ""
		firstArg match {
			case "slat" => println("pepper")
			case "chips" => println("salsa")
			case "eggs" => println("bacon")
			case _ => println("huh?")
		}
	}
	
	/**
	 * Scala为了更好的函数化编程，将break和continue关键字移出内置控制结构
	 * 不过不用担心，Scala提供了多种方式来代替break和continue的使用
	 * 例如：if条件语句、递归函数(函数化编程中，递归代替循环是非常常见的方法)，以及break的库函数
	 */
	def breakTest():Unit = {
		val in = new BufferedReader(new InputStreamReader(System.in))
		breakable {
			while (true) {
				println("? ")
				if (in.readLine() == "") break;
			}
		}
	}
}

