package com.application.authentication.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@Configuration
@PropertySources({
        @PropertySource(value = "classpath:securityconfig.properties", ignoreResourceNotFound=true),
        @PropertySource("classpath:securityconfig.properties")
})
@ToString
@Getter
@Setter
public class ConfigProperitesFiles {

    @Value("${server.context.path}")
    private String contextPath;
    @Value("${security.basic.enable}")
    private String securityBasicEnable;
    @Value("${security.all.url}")
    private String[] securityAllUrl;

//    @Value("${security.admin.url}")
    private String[] securityAdminUrl;

//    @Value("${security.user.url}")
    private String[] securityUserUrl;

    @Value("${resource.all.url}")
    private String[] resourceAllUrl;

    @Value("${resource.authentication.url}")
    private String[] resourceAuthenticationUrl;

    @Value("${authorizationserverconfig.withclient}")
    private String authorizationserverconfigWithclient;

    @Value("${authorizationserverconfig.authorities}")
    private String[] authorizationserverconfigAuthorities;

    @Value("${authorizationserverconfig.scopes}")
    private String[] authorizationserverconfigScopes;


    @Value("${authorizationserverconfig.resourceIds}")
    private String authorizationserverconfigResourceIds;


    @Value("${authorizationserverconfig.secret}")
    private String authorizationserverconfigSecret;

    @Value("${authorizationserverconfig.authorizedGrantTypes}")
    private String[] authorizationserverconfigAuthorizedGrantTypes;

    @Value("${authorizationserverconfig.accessTokenValiditySeconds}")
    private String authorizationserverconfigAccessTokenValiditySeconds;

}
