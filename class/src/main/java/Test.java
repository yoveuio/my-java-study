import java.net.*;
import java.util.Enumeration;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName Test
 * @Description 测试类
 * @Author yoveuio
 * @Date 2020/8/30 13:18
 * @Version 1.0
 */
@SuppressWarnings("unused")
public class Test {
    public static void main(String[] args) {
        Executors.newFixedThreadPool(5);
        ReentrantReadWriteLock lock;
        System.out.println(getHostIp());

    }

    private static String getIp() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local HostAddress: " +
                addr.getHostAddress());
        String hostname = addr.getHostName();
        System.out.println("Local host name: " + hostname);
        return hostname;
    }

    /**
     * 获取非127.0.0.1的本机地址
     */
    private static String getHostIp() {
        try {
            // 枚举所有网络接口
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    System.out.println(ip.getHostAddress());
                    if (ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && !ip.getHostAddress().contains(":")) {
                        System.out.println("本机的IP = " + ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String getHost() throws SocketException {
        Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();

        while (nifs.hasMoreElements()) {
            NetworkInterface nif = nifs.nextElement();

            // 获得与该网络接口绑定的 IP 地址，一般只有一个
            Enumeration<InetAddress> addresses = nif.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();

                if (addr instanceof Inet4Address) { // 只关心 IPv4 地址
                    System.out.println("网卡接口名称：" + nif.getName());
                    System.out.println("网卡接口地址：" + addr.getHostAddress());
                    System.out.println();
                    return addr.getHostAddress();
                }
            }
        }
        return null;
    }
}
