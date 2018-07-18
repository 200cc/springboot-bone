package me.cc200.base.utils;

import java.nio.charset.Charset;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAUtilTest {

    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDL9bomEAvoFry9bZDksJvqwrVhxMnqw62VuOl/LVHrWHhyS0x9I7lqEIlNW+zTA1SRflrEQKX/H2/yyh2uSTJbF3Cj7YA5XHoBfpi29Dbh9mioOJKyOC4+rop/DOYKL6zMMWZl3nlVNNswal4OkHEfDsO/rvlPCK5T1QM2scyq7wIDAQAB";

    public static final String PRIVATE_KEY = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAMv1uiYQC+gWvL1tkOSwm+rCtWHEyerDrZW46X8tUetYeHJLTH0juWoQiU1b7NMDVJF+WsRApf8fb/LKHa5JMlsXcKPtgDlcegF+mLb0NuH2aKg4krI4Lj6uin8M5govrMwxZmXeeVU02zBqXg6QcR8Ow7+u+U8IrlPVAzaxzKrvAgMBAAECgYEApJ9ctVYkVhrsEjU6SuulnVG1FwnANCXmmL9cb9MDmOKarR3LygGoqUxZlkAJIbRBcQG8f0Ty9r3IHP44ALGB2wPM7ohD6nAahFBp8cRPKf/nWmC8ywlbuun7O/jtU8hjHFQOKvqmQhFakkGFkPWmycsxx7pAsqaU8Pszo1XPTxECQQD6+SQQmgRGEOpxtU0wDzkQOi/9V2pQHFnsWfwK6t/m3xRmSrkvUlhR0qFmcEJuDNkCA8ai74QBPo003wvKpkkHAkEA0AuGyZKUo/CXgNCRIBuHviWNwyVZVA1S3ZS0wgs1t4Y6lSYpanoxBpKDXu74mXdeVv3HmIyJAYZNLN0uw0Qc2QJBAIndjIs2H/zhU/hMsO8inaUlYbFYcMU7HcaxE7xnk8F+b/VVPO8a2/tuqkzchBFyK09pBZgB3NDWLLmLgS28NMcCQQCySm6EqJ3BsRnlRthYT7+Q8NYAey2GnrR5OEuAT+MFGH9z+nRbz8PcAgube+iIfIfYvTwkO6veJp5U6Gh+5wxhAkEAkArjYvTglQj1w85CL2+ZoO31wqb/0r/dI9nxTgI6z1XfC3RIohraSqcqe6TB0qsubKHbL+0Z9Z7qwS4fzjWwvg==";

    public static void main(String[] args) {
        String str = "q4Yu9M5VuNbqdhH3d6cuX8DO02augItQRJ/21uSLk+IM7WSVLyM2ru58whNV5TkT5Z1JiAc51sLycAllZJZBTIeThr+55AehVGNB5suCOttbhZyrMb6+GUpcjV18RyvjgA3VV1NLmdCXYDA9i8Dadlfi5eu3h7LwwHoKSs/eRi0=";
        RSAPublicKey publicKey = RSAUtil.loadPublicKey(PUBLIC_KEY);
        RSAPrivateKey privateKey = RSAUtil.loadPrivateKey(PRIVATE_KEY);

        String res = RSAUtil.decrypt(publicKey, str, Charset.forName("ISO8859-1"));
        System.out.println(res);
        /*String resp = "it's a wonderful world!";

        String enResp = RSAUtil.encrypt(privateKey, resp);
        String enRespSign = RSAUtil.sign(privateKey, enResp);
        System.out.println(enResp);
        System.out.println(enRespSign);


        System.out.println(RSAUtil.decrypt(publicKey, enResp));
        System.out.println(RSAUtil.signCheck(publicKey, enResp, enRespSign));*/
    }
}
