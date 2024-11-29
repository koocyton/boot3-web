package com.doopp.boot3.web.admin.server.component;

import com.doopp.boot3.web.admin.server.pojo.dto.ldap.LdapResult;
import com.doopp.boot3.web.core.exception.AssertException;
import com.doopp.boot3.web.core.exception.AssertMessage;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Log4j2
@Service
public class LdapComponent {

    @Resource
    private RestTemplate restTemplate;

    public String verifyTicketAndGetUsername(String ticket) {
        return "";
    }

    private String getTokenByTicket(String ticket) {
        return "";
    }

    private String getUsernameByToken(String token) {
        return "";
    }

    public boolean logout(String token) {
        return true;
    }

}
