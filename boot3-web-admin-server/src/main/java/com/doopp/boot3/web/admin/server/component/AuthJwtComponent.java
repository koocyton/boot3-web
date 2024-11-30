package com.doopp.boot3.web.admin.server.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.doopp.boot3.web.admin.server.pojo.dto.auth.AuthInfo;
import com.doopp.boot3.web.admin.server.pojo.entity.AdminUser;
import com.doopp.boot3.web.core.exception.AssertException;
import com.doopp.boot3.web.core.exception.AssertMessageEnum;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Log4j2
@Component
public class AuthJwtComponent {

    private static final String USERNAME = "USERNAME";

    private static final String USERID = "USERID";

    private static final String ROLE_IDS = "ROLE_IDS";

    private static final String TOKEN_SECRET = "J%A^H*be#45D&Kaa";

    private static final Integer SESSION_TIMEOUT = 10000;

    public String buildTokenForUser(AdminUser adminUser) {
        Date expireTime = new Date(System.currentTimeMillis() + SESSION_TIMEOUT * 1000);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        return JWT.create()
                .withClaim(USERID, adminUser.getId())
                .withClaim(USERNAME, adminUser.getUsername())
                .withClaim(ROLE_IDS, adminUser.getRoleIds().stream().map(String::valueOf).collect(Collectors.joining(",")))
                .withExpiresAt(expireTime)
                .sign(algorithm);
    }

    public AuthInfo verifyTokenAndGetAuthInfo(String token) {
        // verify auth token
        AuthInfo authInfo = getAuthInfo(token);
        // if (authInfo.getTokenExpire().isBefore(LocalDateTime.now())) {
        //     log.info("session token expiration : {}", authInfo.getTokenExpire());
        //     throw new AssertException(AssertMessageEnum.TOKEN_EXPIRATION);
        // }
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(USERNAME, authInfo.getUsername())
                    .withClaim(USERID, authInfo.getUserId())
                    .withClaim(ROLE_IDS, authInfo.getRoleIds()==null ? "" : authInfo.getRoleIds().stream().map(String::valueOf).collect(Collectors.joining(",")))
                    .build();
            verifier.verify(token);
            return authInfo;
        }
        // if exception
        catch (Exception e) {
            log.info("token verify exception {} , now time: {}", e.getMessage(), LocalDateTime.now());
            throw new AssertException(AssertMessageEnum.TOKEN_VERIFY_FAILED);
        }
    }

    private AuthInfo getAuthInfo(String token) {
        DecodedJWT jwt = JWT.decode(token);
        AuthInfo authInfo = new AuthInfo();
        authInfo.setUsername(jwt.getClaim(USERNAME).asString());
        authInfo.setUserId(jwt.getClaim(USERID).asLong());
        String claimRoleIds = jwt.getClaim(ROLE_IDS).asString().trim();
        authInfo.setRoleIds(ObjectUtils.isEmpty(claimRoleIds) ? new ArrayList<>() : Arrays.stream(claimRoleIds.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
        authInfo.setTokenExpire(LocalDateTime.ofInstant(jwt.getExpiresAtAsInstant(), ZoneId.systemDefault()));
        return authInfo;
    }
}
