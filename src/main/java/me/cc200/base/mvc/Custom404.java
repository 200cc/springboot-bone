package me.cc200.base.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 重写404
 */
@Slf4j
@RestController
public class Custom404 implements ErrorController {

    public static final String ERR_PATH = "/error";

    @RequestMapping(ERR_PATH)
    public Rest<String> error(HttpServletRequest request) {
        log.warn("404 error: request uri = {}", request.getHeader("referer"));
        return Rest.err("404", "page not found");
    }

    @Override
    public String getErrorPath() {
        return ERR_PATH;
    }
}
