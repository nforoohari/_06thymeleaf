package ir.digixo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("admin")
                .password("1234")
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("nima")
                .password("1234")
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers("/config").hasRole("ADMIN")
                            .requestMatchers("/system").hasRole("MANAGER")
                            .anyRequest().authenticated();
                }
        ).formLogin(
                httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/showMyLoginForm")
                            .loginProcessingUrl("/loginProcess")
                            .permitAll();
                }
        ).logout(
                httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.permitAll()
        );

        return http.build();
    }
}
