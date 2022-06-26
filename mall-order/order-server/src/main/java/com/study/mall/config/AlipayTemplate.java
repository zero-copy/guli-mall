package com.study.mall.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.study.mall.vo.PayVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Slf4j
@Data
public class AlipayTemplate {

    /**
     * 在支付宝创建的应用的id
     */
    private String app_id = "2021000121608219";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    private String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7IwPMPqXdUvk1IpDOQvLWtVJ7qYzTuz+DILL6jzJDkVmJHMhDqsx3NnOWFj0SZfZGL/KCVp7Xs1yVnYfSIoocbWA/4GWpsegWCbHRrL2wcqHH8706M6p4wevqrBvKDddm77zAoGsaqp+L1lTeEVkV/93fIPFbMw10Z7nnMOmZtxrICa1uzl8T2pcp/c0NEtm8J39KbnDPPQek3vNFqAG0LS3y/vjq+SUvV/wrs+blowQZN36XJ1sWgXn3DYNX+OPlm4m5dvnamCIR+rwqoqJvOzrCw7DqGkZbtqE9l76JZJCQPVLpTiV/ni+6T1jAIWD59+KJ1swS8CPaiBjsh4LdAgMBAAECggEANS1DBAP9YAIW/Mlguqv9k5Oc57ULZpM7/ggJs/eDAf5IohTCt6/OQEYB3HFfU14T+GfhkrYIlyTJt143XTghZOiTxDMA0JLUb4a7t+hNUtmQLWCOsf5Cf+QjJOFZWDZMslyHl3k+ViqMAdGa9iuZ1A2MP759tzuvpBdhlVep2drY1ahF7LGIn7XsOufJOxid6DzaZIsLoM2DOC/mB7MnrvuImHjjRbPwPVaajrcsj6WJT1CDsReszr3BoaQYg2KEalyJaDcDPOoWWW4+igIO1Z06ljixLMKZp0jbJO8wfq0nbBbeQrxyHxZEZ5CIGLPz1UsuDJY41IhooI2OL0BMAQKBgQDh5n7G/2EQjQWfPdmIDkpibTuTFVk+41TP9Nmhdx0IfYwzH5Y9IZoMooFSn3GXvcZBbQUVP4eQZQCTNjGPZoDT/mr28h59fyroeCI9IK1vSpCTOGF4GTgdy96e5BlreQnBVgi18WhtyoZl7tQ85TQ/hzF+c5hmLtejKBFsTaIT3QKBgQDUEkk2LcgK6xdIN780K9UOyEdJYBNdR6Yf0/BI1f2rJTyRXO+FWzfkT1NJ+m08TE2F6ljVLL52Ih7QvWYaP3rJYrA9YB4qyWhQW6KHM2isDDX4zaep3EhGa7YMsJYZk/7YLEbA7zo0iBFnNSoXiRtWvWr/+YmV14q7xCAPwz+7AQKBgHVf0heLsd9rprQq6tAqvLOPlhjCSvDGJdc3nayru0YiFaqqiuGQh/5woUlQ1zDExjJQdwenTsTT9gyHaBDebam409Mj0nvai2zmnsOsKvFciOo1rU89q06wEyfz1NFmvo7aKQSAPoZtaI+mHFjXruIjWLAfV9wgEqMPup3PaJtxAoGBAKC4iwuofqbnrGlq9lmPrpTZbE1LpuFW36vXb4+Dvg/q1JBGIrCqhR1ekPzL8eV03nvTMye8FUxqZ3hDUhIqceAWDwM0ySgcJBZinDZi270oM+8vzRWKgKv+DaZoXhlcdBNY2vZ1V2Ngi+/v9sqHelSukV0VvRP2monbBYFss+wBAoGAWGVi+QyTsfjmqIS/xzNXfSfFIC2BYYdfuHxWcCiGBTbnXD0q3Ticw7g9kTQLt0R4vkZzfb71EZy3UqK+DdzRDLU3DYtHDnuaWElX0N5tlcVZf+nkMkOeDgy9lhJP/KRFHWR0qb6rqIYR0bbOVMSbo1a68zlZi7tzv4JxB1+1VjA=";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    private String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjhMfMKGvQxT/JQ+37cvcf2zzpT/BxCNyY0pE5l9ck2ZltZLdLvcBVzxu5TLBPmjs2/NjJemCcH0TMhnz6TCttFZZ3DJoWGi+CdvupZYmuWMPAjSvd6REAI+yWG6+MHiLsBuqTGoCHGh7VsMWwoTTu7Otv5PIPHYhKu3nLHHKaBzWYgwHt+rnPy9EzhSb7q38TXruRUb7E2lC0s+pRSA2ZD+717gMNYbNu1iPzyGNso0DybrQhYPo0bNrwH5hY4FF2QHdSjwY+o3sTUar6BLkcvK6HFARqpkkALXbIfwvmlr2nzhy9QnUmSva+mZ0w+p80D+zxz7ecGALxN6HoQdD2wIDAQAB";
    /**
     * 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     * 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
     */
    private String notify_url = "http://gulipay:8088/gulipay/notify_url.jsp";

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     * 同步通知，支付成功，一般跳转到成功页
     */
    private String return_url = "http://member.gulimall.com/gulipay/memberOrder.html";

    /**
     * 签名方式
     */
    private String sign_type = "RSA2";

    /**
     * 字符编码格式
     */
    private String charset = "utf-8";

    /**
     * 支付宝网关； https://openapi.alipaydev.com/gateway.do
     */
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public String pay(PayVo vo) throws AlipayApiException {
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);
        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        return result;
    }
}
