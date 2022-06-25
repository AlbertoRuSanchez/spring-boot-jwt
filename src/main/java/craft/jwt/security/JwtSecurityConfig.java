package craft.jwt.security;

import craft.jwt.jwt.JwtConfig;
import craft.jwt.jwt.JwtTokenVerifier;
import craft.jwt.jwt.JwtUserAndPasswordAuthFilter;
import craft.jwt.model.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity auth) throws Exception {
        auth
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUserAndPasswordAuthFilter(authenticationManager(), jwtConfig))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig), JwtUserAndPasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.CUSTOMER.toString())
                .anyRequest()
                .authenticated();
    }
}
