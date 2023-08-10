package com.vrcs.livemenu.security;

// import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private JWTAuthenticationEntryPoint point;
        @Autowired
        private JwtAuthenticationFilter filter;

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // @Bean
        // WebMvcConfigurer webMvcConfigurer() {
        // return new WebMvcConfigurer() {
        // public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE",
        // "OPTIONS")
        // .allowedOrigins("*")
        // .allowedHeaders("*").allowCredentials(true);
        // }
        // };
        // }

        // @Bean
        // CorsConfigurationSource corsConfigurationSource() {
        // CorsConfiguration configuration = new CorsConfiguration();
        // configuration.setAllowedOrigins(Arrays.asList("*"));
        // configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH",
        // "DELETE", "OPTIONS"));
        // configuration.setAllowedHeaders(Arrays.asList("authorization",
        // "content-type", "x-auth-token"));
        // configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        // UrlBasedCorsConfigurationSource source = new
        // UrlBasedCorsConfigurationSource();
        // source.registerCorsConfiguration("/**", configuration);
        // return source;
        // }

        // @Bean
        // CorsConfigurationSource corsConfigurationSource() {
        // final UrlBasedCorsConfigurationSource source = new
        // UrlBasedCorsConfigurationSource();

        // CorsConfiguration corsConfiguration = new
        // CorsConfiguration().applyPermitDefaultValues();
        // source.registerCorsConfiguration("/**", corsConfiguration);

        // return source;
        // }

        @Bean
        UserDetailsService userDetailsService() {
                return new CustomUserDetailsService();
        }

        @Bean
        SecurityFilterChain finterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.authorizeHttpRequests((authorize) -> {
                        authorize.requestMatchers(HttpMethod.GET, "/api/menuBoard").permitAll();
                        authorize.requestMatchers("/api/registerUser", "/auth/login").permitAll();
                        authorize.requestMatchers(HttpMethod.POST, "/api/menuBoard").hasAnyAuthority("SUPER-USER",
                                        "ADMIN");
                        authorize.requestMatchers("/api/menuBoard/**").hasAnyAuthority("SUPER-USER", "ADMIN");

                        authorize.requestMatchers("/api/user", "/api/user/**").hasAuthority("ADMIN");
                        authorize.anyRequest().hasAnyAuthority("ADMIN", "SUPER-USER");
                        // authorize.exceptionHandling(ex -> ex.authenticationEntryPoint(point));
                });
                httpSecurity.csrf(t -> t.disable());
                // httpSecurity.cors(t -> t.disable());
                // httpSecurity.formLogin(Customizer.withDefaults());
                httpSecurity.exceptionHandling(ex -> ex.authenticationEntryPoint(point));
                httpSecurity.httpBasic(Customizer.withDefaults());
                httpSecurity.sessionManagement((session) -> {
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                });
                httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
                // .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider),
                // BasicAuthenticationFilter.class)
                return httpSecurity.build();
        }

        @Bean
        AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                authenticationProvider.setUserDetailsService(userDetailsService());
                authenticationProvider.setPasswordEncoder(passwordEncoder());
                return authenticationProvider;
        }

        @Bean
        AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
                return builder.getAuthenticationManager();
        }

}
