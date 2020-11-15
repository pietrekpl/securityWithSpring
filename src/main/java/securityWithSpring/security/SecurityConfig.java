package securityWithSpring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static securityWithSpring.security.UserRoles.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            /*    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()*/
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
                .antMatchers("/api/**").hasRole(EMPLOYEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/departments",true)
                    .passwordParameter("password")
                    .usernameParameter("username")
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                    .rememberMeParameter("remember-me")
                .key("securedPassword")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET")) // when csrf is disabled
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID","remember-me")
                    .logoutSuccessUrl("/login");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userPeter = User.builder()
                .username("peter")
                .password(passwordEncoder.encode("pass"))
                //         .roles(EMPLOYEE.name())
                .authorities(EMPLOYEE.getGrantedAuthorities())
                .build();

        UserDetails adminPaul = User.builder()
                .username("paul")
                .password(passwordEncoder.encode("pass"))
                //        .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails userElvis = User.builder()
                .username("elvis")
                .password(passwordEncoder.encode("pass"))
                //        .roles(ADMINTRAIN.name())
                .authorities(ADMINTRAIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                userPeter,
                userElvis,
                adminPaul
        );
    }
}
