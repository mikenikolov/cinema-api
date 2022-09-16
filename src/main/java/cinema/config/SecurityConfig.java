package cinema.config;

import cinema.security.jwt.JwtFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/register", "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/cinema-halls", "/movies",
                        "/movie-sessions").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/orders/complete").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/cinema-halls", "/movies",
                        "/movie-sessions").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/orders",
                        "/shopping-carts/by-user").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/users/by-email").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/shopping-carts/movie-sessions").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
