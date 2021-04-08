package org.example.basic

import scala.io.Source
;

/**
 * @author yoveuio
 */
object IOTest {
    def main(args: Array[String]): Unit = {
        if (args.length > 0) {
            for (line <- Source.fromFile(args(0)).getLines())
                println(line.length + " " + line)
        } else
            Console.err.println("Please enter filename")
    }
}
