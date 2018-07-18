package me.cc200.base.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@Slf4j
public abstract class HttpUtil {

    public static Charset UTF8 = Charset.forName("utf-8");

    public static Charset ISO88591 = Charset.forName("iso-8859-1");

    private HttpUtil() {}

    /**
     * 获取IP地址
     * @param request HttpServletRequest
     * @return ip
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 文件流输出
     * @param request  HttpServletRequest
     * @param response  HttpServletResponse
     * @param is  文件流
     * @param filename 文件名称
     */
    @SneakyThrows
    public static void outputFileStream(HttpServletRequest request,
                                        HttpServletResponse response,
                                        InputStream is,
                                        String filename) {
        String newfileName;
        String ua = request.getHeader("User-Agent");
        ua = null == ua ? "" : ua.toUpperCase();
        log.debug("outputFileStream ==> User-Agent = {}", ua);
        if (ua.contains("MSIE") || ua.contains("TRIDENT") || ua.contains("EDGE")) { // ie浏览器下文件名称处理
            newfileName = URLEncoder.encode(filename, "utf-8");
            newfileName = newfileName.replace("+", "%20");
        } else { // 非ie浏览器文件名称处理
            newfileName = new String(filename.getBytes(UTF8), ISO88591);
        }
        log.debug("outputFileStream ==> filename [{}] --> [{}]", filename, newfileName);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + newfileName);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
