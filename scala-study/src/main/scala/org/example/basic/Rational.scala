package org.example.basic

import scala.annotation.tailrec

/**
 * 规范定义一个有理数
 * 需要用户提供分子和分母
 *
 * scala可以在定义类的同时定义主构造函数
 */
class Rational(n:Int, d:Int) {
	
	/**
	 * 辅助构造函数
	 * 如定义一个整数不需要特意定义分母为1
	 */
	def this(n:Int) = this(n, 1)
	/**
	 * 有理数当然分母不能为0
	 * 面向对象编程的一个优点就是数据的封装，可以确保在其生命周期过程中是有效的。
	 * Scala可以使用require方法在定义对象时判断
	 */
	require(d != 0)
	override def toString: String = n + "/" + d
	
	/**
	 * 私有成员变量
	 */
	private val g = gcd(n.abs, d.abs)
	
	/**
	 * 成员变量
	 */
	val number:Int = n / g
	val demon:Int = d / g
	/**
	 * 尽管类参数在新定义的函数的访问范围之内，但仅限于定义类的方法本身(比如之前定义的toString方法，可以直接访问参数)
	 * 对于that来说，无法使用that.d访问d。一位内that不在定义的类可以访问的范围之内。此时需要类的成员变量。
	 * @param that 相加的另一个有理数
	 * @return 返回相加后的结果
	 */
	def add(that: Rational) : Rational = new Rational(number * that.demon + that.number * demon, demon * that.demon)
	
	/**
	 * 方法重载
	 *
	 * 如果有隐式类型转换，这一步非必要
	 */
	def add(n: Int): Rational = new Rational(number + demon * n, demon)
	
	/**
	 * 定义操作符
	 * Scala中的操作符和函数没有什么区别
	 */
	def +(that: Rational) = new Rational(number * that.demon + that.number * demon, demon * that.demon)
	
	def *(that: Rational) = new Rational(number * that.number, demon * that.demon)
	
	/**
	 * 私有方法
	 */
 	@tailrec
	private def gcd(a:Int, b:Int) :Int = if (b == 0) a else gcd(b, a % b)
	
}

object Rational{
	def main(args: Array[String]): Unit = {
		val oneHalf = new Rational(1, 2)
		val twoThirds = new Rational(2, 3)
		println(oneHalf add twoThirds)
		println(1 + oneHalf)
	}
	/**
	 * 隐式类型转换
	 *
	 * 有了这个东西，add那个int的重载也是非必要的了
	 */
	implicit def int2Rational(x:Int): Rational = new Rational(x)
}