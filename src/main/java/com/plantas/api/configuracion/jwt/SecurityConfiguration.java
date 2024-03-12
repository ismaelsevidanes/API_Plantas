package com.plantas.api.configuracion.jwt;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.plantas.api.servicios.UsuarioService;

import com.plantas.api.entidades.Role;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	UsuarioService usuarioService;
	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(handling -> handling.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1//**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.GET, "/api/v1/productos/**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/productos/{id}").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/productos").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/productos/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/productos/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.GET, "/api/v1/ventas/**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/ventas/{id}").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/ventas/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/ventas/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/ventas/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public HttpFirewall looseHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}



