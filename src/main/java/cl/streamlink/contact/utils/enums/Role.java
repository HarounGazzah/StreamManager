package cl.streamlink.contact.utils.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_CLIENT,
    ROLE_RESOURCE;

    public String getAuthority() {
        return name();
    }

}
