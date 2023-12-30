package org.example.config;

import org.example.security.userdetails.UserDetailsImpl;
import org.example.security.userdetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    static final String CLIEN_ID = "techie";
    static final String CLIENT_SECRET = "password";
    static final String GRANT_TYPE = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

    private String privatekey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIICXQIBAAKBgQDrb59amQ2omQ7ZUn4hoULDJn6knWUm6I17eD0MQGwF666b7xP5\n" +
            "McnvyacqWwXegzQx9cX8n/Wgn1O8YlnEtMbjM68f9tdA20DKvdDAfgr1/kqxJL5r\n" +
            "XYqWmtpBs6YIhIlQEIbkHt0n30L2auF2HT4M3y2go6HoKsasX1zUXaYdgQIDAQAB\n" +
            "AoGBANkGYCKni4uq66ExBcwCVwSFa6Ba5m9jV2hFgiDVEb6zbpWXYRYI61j6zfmh\n" +
            "RlvDHk4ffI5O9aFGvW2eS5mj+uCYkeIOhe+YPf8dOyy2F3OHtUyGi9RbXuJH0MiG\n" +
            "t11pNZOc3S/hmvDwDv8dWr9Pk1G7zi1nZdaNgHLZ2y1HMRFBAkEA+H8GYT1NjOU9\n" +
            "acOfG6SSUjD8p6xPdsDImWsI2T4mRIa7LIUFP/CW2jzMBXbRbW+LYAenuKTNqAZv\n" +
            "lpv7pFprNQJBAPKLoyJW64ib5eWTn2Mpeuom39kXn1qyjxMib5Zz3fnkHlSAvYA2\n" +
            "AFHCHR9jGP1cJ1vbtUNAgaxEMZ8DXPW2pp0CQCD66uPY+QPsINx6pBFNJEZYThAK\n" +
            "5HkEWRtTg1ch0n5hy1G7TdkQm1TXoNRQ0rbNiRfzrCTraogUYpcAmMNVZMkCQQDg\n" +
            "AQTuXxZy+YCO0hMfcxzZCoQsrLt4+XZYNrKWYnZ9Jyi35Jqfwb/zwnJBHbXjsOuM\n" +
            "09KJxRa98dFeSa3eJQ2lAkA5exxlQUJYrhUvcv+3Dg/IEEtXQERm21BAomLhHcTF\n" +
            "u6P7Imx815l5hIgu57k50Os+GUJcTZakNmtjX6r3Nn7/\n" +
            "-----END RSA PRIVATE KEY-----\n";

    private String publickey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDrb59amQ2omQ7ZUn4hoULDJn6k\n" +
            "nWUm6I17eD0MQGwF666b7xP5McnvyacqWwXegzQx9cX8n/Wgn1O8YlnEtMbjM68f\n" +
            "9tdA20DKvdDAfgr1/kqxJL5rXYqWmtpBs6YIhIlQEIbkHt0n30L2auF2HT4M3y2g\n" +
            "o6HoKsasX1zUXaYdgQIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl impl;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privatekey);
        converter.setVerifierKey(publickey);
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer
                .inMemory()
                .withClient(CLIEN_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
                refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer()).userDetailsService(impl);
    }
}
