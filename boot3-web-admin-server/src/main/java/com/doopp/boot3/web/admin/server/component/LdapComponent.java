package com.doopp.boot3.web.admin.server.component;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LdapComponent {

    @Resource
    private RestTemplate restTemplate;

    public String verifyTicketAndGetUsername(String ticket) {
        return "yi.liu";
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
