package org.projet_integre.online_book.securite;


import org.projet_integre.online_book.services.OurUserDetailsService;
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
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private OurUserDetailsService ourUserDetailsService;
    @Autowired
    private JWTAuthFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll();
                    
                    auth.requestMatchers(HttpMethod.GET,"/books/**").hasAnyAuthority("USER","ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/books/**").hasAnyAuthority("USER","ADMIN");
                    auth.requestMatchers(HttpMethod.GET,"/categories/**").hasAnyAuthority("USER","ADMIN");
                    auth.requestMatchers(HttpMethod.GET,"/emprunts/client/**").hasAnyAuthority("USER","ADMIN");
                    auth.requestMatchers(HttpMethod.POST,"/emprunts/**").hasAnyAuthority("USER","ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/emprunts/annuler/**").hasAnyAuthority("USER","ADMIN");

                    auth.requestMatchers("/admin/**").hasAnyAuthority("ADMIN");
                    auth.requestMatchers("/books/**").hasAnyAuthority("ADMIN");
                    auth.requestMatchers("/categories/**").hasAnyAuthority("ADMIN");
                    auth.requestMatchers("/emprunts/**").hasAnyAuthority("ADMIN");

                })
                .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(ourUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}