package package_five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 获取计算机MAC地址
 * @author yoveuio
 */
public class InternetInformation {
    Set<String> hashSet = new HashSet<>();
    Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1-o2);
        queue.add(1);
        queue.add(100);
        queue.add(10);

        for (int i=0; i<queue.size(); ++i) {
            System.out.println(queue.poll());
        }
        System.out.println(queue);

    }
}
