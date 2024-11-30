package com.doopp.boot3.web.admin.server.boot.util;

import java.io.IOException;

import com.doopp.boot3.web.core.exception.AssertException;
import com.doopp.boot3.web.core.exception.AssertMessageEnum;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WriteResponseUtil {

    public static Boolean catchWriteResponse(HttpServletResponse response, ExceptionFunction func) throws IOException {
        
        try {
            func.apply();
            return true;
        }
        catch (AssertException ce) {
            log.error("AssertException : ", ce);
            writeMessageResponse(response, ce.getCode(), ce.getMessage());
            return false;
        }
        catch (Exception e) {
            Throwable re = e.getCause();
            if (re instanceof AssertException) {
                log.error("Exception.getCause().AssertException : ", re);
                AssertException cre = (AssertException) re;
                writeMessageResponse(response, cre.getCode(), cre.getMessage());
            }
            log.error("Exception : ", e);
            writeMessageResponse(response, AssertMessageEnum.SYSTEM_ERROR.getCode(), e.getMessage());
            return false;
        }
    }

    public static void writeMessageResponse(HttpServletResponse response, String errorCode, String errorMessage) throws IOException {
        int n = errorMessage.indexOf(": ");
        if (n!=-1) {
            errorMessage = errorMessage.substring(n+2);
        }
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write("{\"code\":\"" + errorCode + "\", \"msg\":\"" + errorMessage + "\", \"data\":null}");
        response.getWriter().flush();
    }
}
