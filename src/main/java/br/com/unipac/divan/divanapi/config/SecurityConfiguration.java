package br.com.unipac.divan.divanapi.config;

import br.com.unipac.divan.divanapi.security.filter.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The type Web security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * The Constant AUTH_WHITELIST.
     */
    private static final String[] WHITELIST = {
            // -- swagger ui
            "/webjars/**",
            "/v1/auth",
            "/v1/register",
            "/actuator/**"
    };

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v1/auth/login",
            "/v1/auth/register"
    };

    /**
     * The encoder.
     */
    private PasswordEncoder encoder;

    /**
     * The custom user details service.
     */
    private UserDetailsService customUserDetailsService;


    public SecurityConfiguration(UserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(HttpMethod.POST, "/v1/auth/signup").permitAll()
                                .requestMatchers(HttpMethod.POST, "/v1/auth/signin").permitAll()
                                .requestMatchers(HttpMethod.POST, "/v1/auth/refreshtoken").permitAll()
                                .anyRequest().authenticated()
                                //.anyRequest().permitAll()
                )
                .requestCache().disable()
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
     * annotation.web.builders.HttpSecurity)
     */
    // @Bean
    // protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    //     return http
    //            .csrf().disable()
    //             .authorizeHttpRequests( auth -> auth
    //                  .requestMatchers(AUTH_WHITELIST).permitAll()
    //                 .anyRequest().authenticated()
    //            )
    //            .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
    //.addFilterAfter(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
    ////     .build();

       /* http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.OPTIONS).permitAll() // allow CORS option calls for Swagger UI
                        .requestMatchers("/v1/register").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);*/

       /* http
                .csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize
                                .anyRequest().authenticated()
                                .requestMatchers("/v1/register").permitAll()
                                .requestMatchers(AUTH_WHITELIST).permitAll()   // this response is 403 forbidden, expect 404 Not Found because set permitAll
//                        .requestMatchers("/**").permitAll()     // if you release this comment, "/data" response is 404 Not Found
                )
                //.authorizeHttpRequests()
                //.requestMatchers("/v1/register").permitAll()
                //.requestMatchers(AUTH_WHITELIST).permitAll()
                //.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //.anyRequest().authenticated()
                //.and()
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);*/

    //return http.build();
    //}

   /* @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( new AntPathRequestMatcher("swagger-ui/**")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("v3/api-docs/**")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                        .anyRequest().authenticated())
                .httpBasic();
        return httpSecurity.build();
    }*/

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/v1/register"
        );
    }

    /**
     * Configure global.
     *
     * @param auth the auth
     * @throws Exception the exception
     */
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        if (encoder == null) {
            encoder = new BCryptPasswordEncoder();
        }

        return encoder;
    }
}

