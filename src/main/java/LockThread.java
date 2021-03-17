import java.io.IOException;

/**
 * @author: 董政辰
 * @date: 2021/3/16 14:47
 * @description:
 * @email：532587041@qq.com
 */
public class Thread2 extends Thread{

    @Override
    public void run() {
        while (Val.key==1){
                try {
                    Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

}
