package com.craftsmengroup.paymentsystem.authserver.secure;

import com.craftsmengroup.paymentsystem.authserver.secure.details.CustomClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final KeyPair keyPair;
    private final CustomClientDetailsService customClientDetailsService;
    @Value("${security.oauth2.authorizationserver.jwt.enabled:true}")
    private boolean jwtEnabled;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(this.authenticationManager)
                .tokenStore(tokenStore());

        if (this.jwtEnabled) {
            endpoints
                    .accessTokenConverter(accessTokenConverter());
        }
    }

    @Bean
    public TokenStore tokenStore() {
        if (this.jwtEnabled) {
            return new JwtTokenStore(accessTokenConverter());
        } else {
            return new InMemoryTokenStore();
        }
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(this.keyPair);

        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new SubjectAttributeUserTokenConverter());
        converter.setAccessTokenConverter(accessTokenConverter);

        return converter;
    }
}
