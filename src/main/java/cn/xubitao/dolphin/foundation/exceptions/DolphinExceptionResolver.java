package cn.xubitao.dolphin.foundation.exceptions;

import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import cn.xubitao.dolphin.foundation.response.Response;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xubitao on 1/2/16.
 */
public class DolphinExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        try {
            int status = ex instanceof ClientException ? 400 : 500;
            response.setStatus(status);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(Response.error(ex).toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}