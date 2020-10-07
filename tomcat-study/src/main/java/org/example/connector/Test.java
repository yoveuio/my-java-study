package org.example.connector;


import org.apache.tomcat.util.res.StringManager;

import java.io.File;
import java.io.FileFilter;

/**
 * @ClassName Test
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/19 11:00
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {



    }

    public void test(File dir) {

        File[] files = dir.listFiles((File pathname)-> {
            if (pathname.isDirectory()){
                return true;
            }
            return pathname.getName().toLowerCase().endsWith(".java");
        });
    }

}
