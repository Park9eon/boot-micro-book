package com.park9eon.micro.book.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableWebSecurity
@EnableResourceServer
open class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @Bean
    open fun accessTokenConverter(): JwtAccessTokenConverter {
        return JwtAccessTokenConverter()
                .apply {
            this.setSigningKey("123")
        }
    }

    @Bean
    open fun tokenStore(): JwtTokenStore {
        return JwtTokenStore(accessTokenConverter())
    }

    @Bean
    open fun defaultTokenServices(): DefaultTokenServices {
        return DefaultTokenServices()
                .apply {
                    this.setTokenStore(tokenStore())
                }
    }

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.tokenServices(defaultTokenServices())
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
    }
}