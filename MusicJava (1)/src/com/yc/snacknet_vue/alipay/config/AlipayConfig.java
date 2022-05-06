package com.yc.snacknet_vue.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000118603229";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQClRn7YV2RQdf8ndT99N6E1OwL+ZBAxbrjWLW+dZLBMe0r+BWCFCHkmtiZsrKB0pV8kWqDHNheBI+Qi+rXma2AK7MbN0Qi30NeVhx66YYD1FUfkIxQFUv58Nnrxz+uu6MZbPQ6UW4pm7pbjvnfEqPCKFd1A3PfA+LiE3LWvZfi/2upwBjteCAMVNYaAexXQ5bchwiJHMGIfmOPMl3oThsboZYcr6SpIBPh+191WDBpS9WVTW6A0gTLpCoeOIjANP6wUdPj3R7fuFkA1w8nPdXsyMNsHJNa+GhIkjf4yiR7ESJny8rqXe37S6UypGtiu1ekc4pTm9HTH1wp9r1sPo94VAgMBAAECggEAUKGs3pT8Lrjya2irOca7EkIg+sUMlWFEMdFm462syRNZicZLyXtRx84Tvu8VNPM9EHpad/Xuz6UX9uzDc4+tAodzkhPKIgt0MdIC7sEE7gyczzNB4DsWRrk5j+F1RRbxNq/5/e0O/5qk6KUSv1IAUA3MrIlhIbb2JnOa++BO1CIH/DMtVNd1xcgoJ/Mv5Xc1ROhMn5iirFtdKMhzyBBCEcxS/Z7Aw3Hgjiddgd8D+usaEW5phYld9RMQo8u9cOdS2raxwBpnzMlgdBpJZ6xSOyqVCoapqEUwZcNNlgJIey4Xful4eb0gX4SP12yt11LlhVmeHn7I0yaiN/IX7CIMXQKBgQD4Qce0Zad4AKd3GR59ngB3E2ljyQv1KOjn/c71qJ9Hmg0TxeK7Zo+8dB25hJAOdDyvfRYJmlQxBNKdDxSYtRzKaOFIBidKeO/w4UFm0nfkNOegdfLTh4rNQKT0klcNFb6XhyLPUlYEkYlMKLmNJeJtt99mq1YuqOgHCHSkvFQ0qwKBgQCqbiUV5AJ1gAnF5qBXRlng9XkegrZKYevvH7Xt1qKGqe5/wuvrcLGU2kNKVDLbmQm/LTadtGQMxOVUH16LrFx30hO3Ki567nNZVbA6stWOidJ2gT+O+sZBEDz0IAhRY0l6RCetj3PIYVCBidkO1/7y7OsBI09+LTjXQYkE8zC4PwKBgQDICfkpW1/+Skimpw7ymYY2bOXXCgEgor5JcTJ6sEzoHTG8ZQ+1gYcAi3AINND/VJoxrdE8G6cLeJ9c/vHRut5gGvKsXoj6cUimCFvyxlTlBdw+Z8wqtmUZzqUcjW6nlIXnA3UBKY82Ty8atxYMriIt1qOkzxq6rk+Imz+iHj6b0QKBgDwLHBOvRdKBrm/Dhb/ZsDaaJPbD1Fc2UnnncR/+2zmYSsdGM/m5tUac8tIQAe7t5sossq4y7IC3KZhOiO+qZNVt9LY2LTPwKEFT+AdmE+jo03YeqPbFkO1BPEhBtMx/r1ZdBqGWBZOV/Vf16bvaCx8oqs620v3ADuN/WjedOg/fAoGBANqCmVOR9reCffVeDvAI6Vb5U7GFTB7ghf72gKvol0rgOq1jy54IaAvyj0Y3t2t/B9dHs23YX+WOPet2CSwC7s44xplf5J0kfPnXJxf+dEOpOsDvY/Cgn31GA6mMYeA1n8/50eCuLSGARZY45gf7M8RN6NhA2rUNFp1EN/Yl2TeK";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiZISG7Ni/XaksEAp1G9Bf5a6yD4BPDgYWkbSBp75wWl/pZ9OHw9rNx4nUvoId0oW3fhOhwd0LuUZfFxogeO/RFT3jL7yTHu8bF0Cg3ap7rkilMXvVCfd8MDTqK4bdLJ0cHrToxji0eXcvvKvhftjYBKCRhoq0EeahzosxozzdSdYtAWbvLGTvNz8ozosJXKHfVVtFNVjySziGYbRhozDrHtHeSXW7ueOWqiA8naPDWC4jVrwE55worStH5jMv3PbEnxGe3f3ox5zd2sm6YgQDWo/n3FbSh9ZVVrLg62yKuKdOowgBshZn/Kj8i5u9M842YRQkyYB67QVXSdw1PKnewIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/Music/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/Music/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

