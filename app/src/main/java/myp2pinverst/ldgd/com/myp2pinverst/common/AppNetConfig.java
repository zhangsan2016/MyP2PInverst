package myp2pinverst.ldgd.com.myp2pinverst.common;

/**
 * Created by shkstart on 2016/12/2 0002.
 * 配置网络请求相关的地址
 */
public class AppNetConfig {
     // http://192.168.1.157:8080/P2PInvest/index
    //  http://127.0.0.0.1:8080/P2PInvest/index
    //  http://192.168.1.157:8080/P2PInvest/index
    public static final String IPADDRESS = "192.168.1.157";

    public static final String BASE_URL = "http://" + IPADDRESS + ":8080/P2PInvest/";

    public static final String PRODUCT = BASE_URL + "product";//访问“全部理财”产品

    public static final String LOGIN = BASE_URL + "login";//登录

    public static final String INDEX = BASE_URL + "index";//访问“homeFragment”

    public static final String USERREGISTER = BASE_URL + "UserRegister";//访问“homeFragment”

    public static final String FEEDBACK = BASE_URL + "FeedBack";//注册

    public static final String UPDATE = BASE_URL + "update.json";//更新应用


}
