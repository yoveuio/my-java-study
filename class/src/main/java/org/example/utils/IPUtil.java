package org.example.utils;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yoveuio
 * @version 1.0
 * @className IPUtil
 * @description IP工具
 * @date 2021/2/21 22:15
 */
public class IPUtil {

    public static void main(String[] args) throws Exception {
        System.out.println(IPUtil.getInterIP1());
        System.out.println(IPUtil.getInterIP2());
        System.out.println(IPUtil.getOutIPV4());
    }

    /**
     * 返回本地主机的地址
     * @return 返回主机host
     * @throws Exception 如果安全管理器不允许操作，则失败
     */
    public static String getInterIP1() throws Exception {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 返回内网IP
     * @return 返回内网IP host
     * @throws SocketException Socket套接字连接失败
     */
    public static String getInterIP2() throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        Enumeration<NetworkInterface> netInterfaces;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        boolean found = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !found) {
            NetworkInterface ni = netInterfaces.nextElement();
            // 返回此网络接口的所有地址
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {// 外网IP
                    netip = ip.getHostAddress();
                    found = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    public static String getOutIPV4() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read;
        URL url;
        HttpURLConnection urlConnection;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            while ((read = in.readLine()) != null) {
                inputLine.append(read).append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Pattern p = Pattern.compile("<dd class=\"fz24\">(.*?)</dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            ip = m.group(1);
        }
        return ip;
    }
}
