package michaelj.namespace.namespace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService service;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = getPasswordEncoder();

        auth
                .userDetailsService(this.service)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()

                // setup authorized pages
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/test*").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/signup*").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/css/*").permitAll()
                .anyRequest().authenticated()

                // configure login
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?errored=true")

                // configure logout
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
        ;
    }

}
