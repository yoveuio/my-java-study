package org.example.basic

/**
 * 本章重点介绍Scala类的组合与继承的问题
 *
 */
class ObjectStudy {
	
}

object ObjectStudy {
	def main(args: Array[String]): Unit = {
		val ae = new ArrayElement(Array("hello", "world"))
		println(ae.height + " " + ae.width)
		
		val le = new LineElement("hello world!")
		println(ae above le)
		println(ae widen 12 beside le widen 12)
		println(ae widen 12)
		println(ae heighten 2)
	}
}

/**
 * 抽象类
 */
abstract class Element{
	/**
	 * 二维布局的内容
	 * @return 返回二维布局的内容
	 */
	def contents: Array[String]
	def height: Int = contents.length
	def width: Int = if (height == 0) 0 else contents(0).length
	def above(that: Element): Element = new ArrayElement(this.contents ++ that.contents)
	def beside(that: Element): Element = {
		new ArrayElement(
			for ((line1, line2) <- this.contents zip that.contents) yield line1 + line2
		)
	}
	def widen(w: Int): Element = if (w <= width) this else {
		val left = Element.elem(' ', (w - width)/2, height)
		val right = Element.elem(' ', w - width - left.width, height)
		left beside this beside right
	}
	def heighten(h: Int): Element = if (h <= height) this else {
		val top = Element.elem(' ', width, (h - height) / 2)
		val bot = Element.elem(' ', width, h - height - top.height)
		top above this above bot
	}

	override def toString: String = contents mkString "\n"
}

/**
 *
 */
object Element{
	def elem(contents: Array[String]): Element = new ArrayElement(contents)
	
	def elem(chr: Char, width: Int, height: Int): Element = new UniformElement(chr, width, height)
	
	def elem(line: String): Element = new LineElement(line)
}

/**
 * 和Java稍有不同，Scala的成员变量和成员函数的地位几乎相同，而且也处在一个命名空间当中
 * 也就是说Scala中不允许定义同名的成员函数和成员变量
 * 这样带来的一个好处就是，成员变量可以重载成员函数
 * scala支持的命名空间是`值`和`类型`
 */
class ArrayElement(override val contents: Array[String]) extends Element {
	/**
	 * 如果某一个方法不希望被子类重载，可以使用final关键字修饰
	 */
	final def demo(): Unit = {
		println("this function is not allowed to be overridden")
	}
}

/**
 * 调用基类构造器
 * 通过extends关键字，可以在基类后面直接加上括号完成主构造器的调用
 * @param s 行
 */
class LineElement(s: String) extends ArrayElement(Array(s)) {
	override def height: Int = s.length
	
	override def width: Int = 1
}

class UniformElement(ch: Char,
					 override val width: Int,
					 override val height: Int
					) extends Element {
	private val line = ch.toString * width
	
	override def contents: Array[String] = Array.fill(height)(line)
}









