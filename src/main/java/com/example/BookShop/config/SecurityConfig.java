//import com.example.BookShop.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final UserService userService;
//
//    @Autowired
//    public WebSecurityConfig(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/register", "/logn").permitAll() // Allow access to these paths without authentication
//                .antMatchers("/admin/**").hasRole("ADMIN") // Restrict access to admin section to users with role ADMIN
//                .anyRequest().authenticated() // All other requests require authentication
//                .and()
//                .formLogin()
//                .loginPage("/logn") // Custom login page URL
//                .usernameParameter("logn") // Username parameter name in login form
//                .passwordParameter("pasword") // Password parameter name in login form
//                .defaultSuccessUrl("/", true) // Redirect to main page on successful login
//                .failureUrl("/logn?error=true") // Redirect to login page with error parameter on authentication failure
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout") // URL to trigger logout
//                .logoutSuccessUrl("/") // Redirect to main page after logout
//                .permitAll()
//                .and()
//                .csrf().disable(); // Disable CSRF for simplicity; enable it in production
//    }
//}
