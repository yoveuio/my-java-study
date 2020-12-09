import java.util.Enumeration;
import java.util.Properties;

/**
 * @author yoveuio
 * @version 1.0
 * @className Test
 * @description TODO
 * @date 2020/12/8 10:20
 */
public class Test {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.setProperty("userame", "tom");
        Enumeration names = p.propertyNames();
        while (names.hasMoreElements()) {
            String key = (String) names.nextElement();
            String value = p.getProperty(key);
            System.out.println(key + " = " + value);
        }
    }
}
