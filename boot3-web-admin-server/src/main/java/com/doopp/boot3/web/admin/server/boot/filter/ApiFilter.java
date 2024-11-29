package com.doopp.boot3.web.admin.server.boot.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import com.doopp.boot3.web.admin.server.boot.util.WriteResponseUtil;
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
            WriteResponseUtil.writeMessageResponse(response, AssertMessage.SYSTEM_ERROR.getCode(), "Accessed content that does not exist");
            return;
        }
        String verifyUri = uriPaths.length>2 ? "/" + uriPaths[1] + "/" : "/" + uriPaths[1];
        if (!acceptUriList.contains(verifyUri)) {
            WriteResponseUtil.writeMessageResponse(response, AssertMessage.SYSTEM_ERROR.getCode(), "Accessed content that does not exist");
            return;
        }

        WriteResponseUtil.catchWriteResponse(response, ()->{
            filterChain.doFilter(request, response);
        });
    }
}
