package com.application.authentication.security;

import com.application.authentication.config.ConfigProperitesFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@ComponentScan
@EnableAuthorizationServer
public class AurthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfigProperitesFiles configProperitesFiles;

    /**
     * Setting up the endpointsconfigurer authentication manager.
     * The AuthorizationServerEndpointsConfigurer defines the authorization and token endpoints and the token services.
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager);
    }

    /**
     * Setting up the clients with a clientId, a clientSecret, a scope, the grant types and the authorities.
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(configProperitesFiles.getAuthorizationserverconfigWithclient())
                .authorizedGrantTypes(configProperitesFiles.getAuthorizationserverconfigAuthorizedGrantTypes())
                .authorities(configProperitesFiles.getAuthorizationserverconfigAuthorities())
                .scopes(configProperitesFiles.getAuthorizationserverconfigScopes())
                .resourceIds(configProperitesFiles.getAuthorizationserverconfigResourceIds())
                .accessTokenValiditySeconds(Integer.parseInt(configProperitesFiles.getAuthorizationserverconfigAccessTokenValiditySeconds()))
                .refreshTokenValiditySeconds(Integer.parseInt(configProperitesFiles.getAuthorizationserverconfigAccessTokenValiditySeconds()))
                .secret(passwordEncoder.encode(configProperitesFiles.getAuthorizationserverconfigSecret()));
    }

    /**
     * We here defines the security constraints on the token endpoint.
     * We set it up to isAuthenticated, which returns true if the user is not anonymous
     * @param security the AuthorizationServerSecurityConfigurer.
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .checkTokenAccess("isAuthenticated()");
    }
}