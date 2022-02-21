package ru.dmitruk.library.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12)
//        {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String hashedPassword) {
//                String hashedPassword2 = encode(rawPassword); // hash your rawPassword here
//                return hashedPassword2.equals(hashedPassword);
//            }
//        }
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/about").permitAll()
//                .anyRequest()
//                .authenticated()
                .and()
                .formLogin()
//                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/success_login")
                .and()
                .logout()
                .logoutSuccessUrl("/success_logout");
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    //    @Override
//    protected UserDetailsService userDetailsService() {
////        System.out.println(bCryp.antMatchers(HttpMethod.POST, "/registrationClient").permitAll()tPasswordEncoder().encode("aaaaa"));
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(bCryptPasswordEncoder().encode("aaaaa"))
//                        .authorities(Role.ADMIN.getAuthorities())
//                        .build()

//
//        );
//    }

    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }


}
