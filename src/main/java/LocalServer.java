import redis.clients.jedis.Jedis;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


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
                String ops = split[0];
                String ip = split[1];
                String token = split[2];
                if (JwtUtil.verifyToken(token))
                    doLock(ops, ip);
                else
                    jedis.set(ip,"Token is not valid");
            }
            in.close();
            accept.close();
        }
    }

    private static boolean doLock(String res, String ip) {
        jedis = new Jedis("121.4.252.231", 6379);
        jedis.auth("Dzc021258");
        try {
            if (Val.LOCK.equals(res)) {
                Val.key = 1;
                LockThread t2 = new LockThread();
                t2.start();
            } else if (Val.UNLOCK.equals(res)) {
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
        } finally {
            jedis.set(ip, res);
            jedis.close();
        }

    }

    public static void init() {
        try {
            Val.key = 1;
            LockThread t2 = new LockThread();
            t2.start();
        } catch (Exception exception) {
            System.out.println("上锁失败");
        }
    }

}
