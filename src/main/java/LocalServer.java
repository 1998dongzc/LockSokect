import redis.clients.jedis.Jedis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;


public class LocalServer {
    public static BufferedReader in;
    private static Jedis jedis;


    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9998);
        init();
        while (true) {
            Socket accept = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));

            String res = null;
            if ((res = in.readLine()) != null) {
                System.out.println(res);
                String[] split = res.split("\\|");
                String ops=split[0];
                String ip=split[1];
                doLock(ops,ip);
            }
            in.close();
            accept.close();
        }
    }

    private static boolean doLock(String res,String ip) {
        jedis = new Jedis("121.4.252.231", 6379);
        jedis.auth("Dzc021258");
        try {
            if (res.equals("lock")) {
                Val.key = 1;
                Thread2 t2 = new Thread2();
                t2.start();
            } else if (res.equals("unlock")) {
                Val.key = 0;
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyPress(KeyEvent.VK_ENTER);
            }
            return true;
        } catch (AWTException e) {
            e.printStackTrace();
            return false;
        }finally {
            jedis.set(ip,res);
            jedis.close();
        }

    }

    public static void init() {
        Val.lock = true;
        Val.key = 1;
        Thread2 t2 = new Thread2();
        t2.start();
    }

    public static void setRedis() {

    }

    public static void main1(String[] args) {
        setRedis();
    }
}
