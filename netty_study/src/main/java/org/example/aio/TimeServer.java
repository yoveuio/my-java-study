package org.example.aio;

/**
 * @ClassName TimeServer
 * @Description 通过下列代码熟悉NIO 2.0 AIO的相关类库
 * @Author yoveuio
 * @Date 2020/10/10 19:21
 * @Version 1.0
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                port = 8080;
            }
        }
        //创建异步的时间服务器处理类，然后启动线程将AsyncTimeServerHandler拉起
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }

}
