package com.doopp.boot3.web.admin.server.boot.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import com.doopp.boot3.web.core.exception.AssertException;
import com.doopp.boot3.web.core.exception.AssertMessage;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
// @Component  
// @Order(-1) 
public class ApiFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        // 不过滤
        List<String> acceptUriList = new ArrayList<String>(){{
            add("/favicon.ico");
            add("/api/");
            add("/css/");
            add("/img/");
            add("/js/");
            add("/view/");
            add("/swagger-ui.html");
        }};

        // 请求的无参 URI
        String[] uriPaths = request.getRequestURI().split("/");
        if (uriPaths.length<2) {
            writeExceptionResponse(response, AssertMessage.SYSTEM_ERROR.getCode(), "Accessed content that does not exist");
            return;
        }
        String verifyUri = uriPaths.length>2 ? "/" + uriPaths[1] + "/" : "/" + uriPaths[1];
        if (!acceptUriList.contains(verifyUri)) {
            writeExceptionResponse(response, AssertMessage.SYSTEM_ERROR.getCode(), "Accessed content that does not exist");
            return;
        }

        try {
            filterChain.doFilter(request, response);
        }
        catch (AssertException ce) {
            log.error("CodeException : ", ce);
            writeExceptionResponse(response, ce.getCode(), ce.getMessage());
        }
        catch (ServletException ne) {
            Throwable re = ne.getCause();
            if (re instanceof AssertException) {
                log.error("NestedServletException.getCause().CodeException : ", re);
                AssertException cre = (AssertException) re;
                writeExceptionResponse(response, cre.getCode(), cre.getMessage());
                return;
            }
            log.error("NestedServletException : ", ne);
            writeExceptionResponse(response, AssertMessage.SYSTEM_ERROR.getCode(), ne.getMessage());
        }
        catch (Exception e) {
            Throwable re = e.getCause();
            if (re instanceof AssertException) {
                log.error("Exception.getCause().CodeException : ", re);
                AssertException cre = (AssertException) re;
                writeExceptionResponse(response, cre.getCode(), cre.getMessage());
                return;
            }
            log.error("Exception : ", e);
            writeExceptionResponse(response, AssertMessage.SYSTEM_ERROR.getCode(), e.getMessage());
        }
    }

    private void writeExceptionResponse(HttpServletResponse response, String errorCode, String errorMessage) throws IOException {
        // log.info("errorMessage {}", errorMessage);
        int n = errorMessage.indexOf(": ");
        if (n!=-1) {
            errorMessage = errorMessage.substring(n+2);
        }
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write("{\"code\":\"" + errorCode + "\", \"msg\":\"" + errorMessage + "\", \"data\":null}");
    }
}