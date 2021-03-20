import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dzc.admin.dao.UserInfoMapper;
import com.dzc.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 董政辰
 * @date: 2021/3/4 10:36
 * @description:
 * @email：532587041@qq.com
 */
public class JwtUtil {

    /**
     * 自定义 JWT私钥
     */
    private static String SECRET = "admin^&^";

    //过期时间 单位：秒
    private static final int EXPIRE_TIME = 10*60;

    /**
     * 校验token是否合法
     */
    public static boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (JWTVerificationException e) {
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return true;
    }

}
