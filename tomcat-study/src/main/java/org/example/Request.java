package org.example;

import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * @ClassName Servlet
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/13 21:35
 * @Version 1.0
 */
public interface Request extends ServletRequest {

    /**
     * 用于解析原始数据，调用私有方法parseUri()来解析HTTP请求的URI，除此之外没有做太多的工作。
     */
    public void parse();


    /**
     * 返回资源Uri
     * @return
     */
    public String getUri();

}
