package org.example.my_web_server;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName Request
 * @Description 处理
 * @Author yoveuio
 * @Date 2020/9/12 16:19
 * @Version 1.0
 */
public class Request {

    private InputStream input;
    private String uri;

    public Request(InputStream input){
        this.input = input;
    }

    /**
     * 用于解析原始数据，调用私有方法parseUri()来解析HTTP请求的URI，除此之外没有做太多的工作。
     * TODO 暂且直对请求头感兴趣。也就是说只针对文件路径做解析
     */
    public void parse(){
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j=0; j<i; ++j) {
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1!=-1) {
            index2 = requestString.indexOf(' ', index1+1);
            if (index2>index1) {
                return requestString.substring(index1+1, index2);
            }
        }
        return null;
    }

    public String getUri() {
        return uri;
    }
}
