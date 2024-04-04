package ng.com.nokt.rentit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    private static final String[] WHITE_LIST = {
            "/",
            "/login",
            "/register",
            "/db-console/**",
            "/static/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .requestMatchers(WHITE_LIST)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/login?error")
                                .permitAll()
                )
                .logout(
                        logout-> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                ).httpBasic(Customizer.withDefaults());

        http.csrf(
                AbstractHttpConfigurer::disable
        );
        http.headers(
                header -> header.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::disable
                ).disable()
        );
        return http.build();
    }
}
