package com.dhd.config;

import org.springframework.security.web.firewall.StrictHttpFirewall;

public class CustomHttpFirewall extends StrictHttpFirewall {
    public CustomHttpFirewall() {
        setAllowSemicolon(true);
    }
}