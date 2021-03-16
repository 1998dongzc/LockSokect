import java.util.TreeMap;

/**
 * @author: 董政辰
 * @date: 2021/3/16 14:48
 * @description:
 * @email：532587041@qq.com
 */
public class Val {

    // 线程根据值来判断是否循环锁屏 为0时系统不在锁屏 为1时继续锁屏
    public static int key=1;

    // 线程获取当前是lock还是unlock状态
    //true lock状态 false unlock状态 默认false状态
    public static boolean lock= false;
}
