package com.boilerplate.global.security.jwt;

import com.boilerplate.global.security.CustomUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.io.Serial;
import java.util.Set;

@Getter
public class UserContext extends CustomUser {

    @Serial
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // TODO: 유저 필드 정의
    private final String username;

    public UserContext(String username, Set<? extends GrantedAuthority> authorities) {
        super(username, null, authorities);  // TODO: username, password, authorities 재정의
        this.username = username;
    }
}
