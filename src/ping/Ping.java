package ping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * @ author 罗志刚
 * @ date 2020/12/28 15:45
 */
public class Ping {
    public static void main(String[] args) {
        String ipAddress="www.baidu.com";
        String line=null;
        try {
            Process process = Runtime.getRuntime().exec("ping " + ipAddress);
            BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
            }
            boolean status = InetAddress.getByName(ipAddress).isReachable(3000);
            System.out.println(status);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
