import java.util.TreeMap;

/**
 * @author: 董政辰
 * @date: 2021/3/16 14:48
 * @description:
 * @email：532587041@qq.com
 */
public class Val {

    // 线程根据值来判断是否循环锁屏 为0时系统不在锁屏 为1时继续锁屏
    public static int key = 1;

    public static final String LOCK = "lock";
    public static final String UNLOCK = "unlock";
}
