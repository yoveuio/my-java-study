package org.example.basic

import java.io.{File, PrintWriter}
import java.util.Date

;

/**
 * @author yoveuio
 */
object IOTest {
    val file = new File("hello.txt")
    
    def main(args: Array[String]): Unit = {
        withPrintWriter(file){
            _.println(new Date())
        }
    }
    
    def withPrintWriter(file: File) (op: PrintWriter=>Unit) {
        val writer = new PrintWriter(file)
        try {
            op(writer)
        } finally {
            writer.close()
        }
    }
    
}
