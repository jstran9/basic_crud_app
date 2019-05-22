package tran.example.basicwebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * WebSecurityConfigurerAdapter: sets up a form base login on all URLs and forces authentication.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFailure authFailure;

    @Autowired
    private AuthSuccess authSuccess;

    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("username").password("{noop}password").roles("USER"); resolving java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
        /*
         * get accounts from our application's database instead of using in-memory database like the above commented code.
         * the registration form will now save accounts to the database.
         * when the user logs in the userDetailService will be used to check entered information against the database.
         */
        authenticationManagerBuilder.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        /*
         * this configuration will ignore some of the defaults set by Spring Security.
         * default behavior is to use a form based login for authentication.
         *
         */
        http.
                csrf().disable()
                .exceptionHandling()
                    .authenticationEntryPoint(entryPointUnauthorizedHandler)
                .and()
                .formLogin() // add form based login.
                    .successHandler(authSuccess) // override default behavior and just send 200 status code.
                    .failureHandler(authFailure) // send back the unauthorized status code.
                .and()
                .authorizeRequests()
                    .antMatchers("/**")
                        .permitAll(); // allow all users.
    }
}
