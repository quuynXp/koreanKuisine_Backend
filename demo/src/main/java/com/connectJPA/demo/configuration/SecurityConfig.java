package com.connectJPA.demo.configuration;

import com.connectJPA.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final String[] PUBLIC_POST_ENDPOINTS = {"/users", "/auth/login", "/auth/introspect", "/users/register",
    //dish
            "/carts/create", "/carts/add", "/carts/remove", "/orders/create", "/order-details/create"};
    // drinks
    // menu
    // };
    private final String[] PUBLIC_GET_ENDPOINTS = { "/dish/get-all", "/dish/search"
                                                    , "/dish/get-by-name","/dish/get-by-price",
                                                    "/dish/get-by-rating", "/dish/{id}",
//                                                    dink
                                                    "/drinks/get-all", "/drinks/search"
                                                    , "/drinks/get-by-name","/drinks/get-by-price",
                                                    "/drinks/get-by-rating", "/drinks/{id}",
//                                                    menu
                                                    "/menu/filter", "/menu/get-all"
                                                    ,"/menu/search" ,"/menu/search-by-basicInfo", "/menu/{productType}/{id}",
//                                                    //images
                                                    "/images/**",
                                                        //orders
                                                    "/orders/{userId}",
                                                    //order-details
                                                    "/order-details/{orderId}", "/carts/current"
                                                    };

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Value("${jwt.header}")
    private String jwtHeader;

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
                        .anyRequest().authenticated())
                        .csrf(AbstractHttpConfigurer::disable);

                httpSecurity.oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
                );

        return httpSecurity.build();
    }

    //    covert SCOPE_ to ROLE_
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

}
