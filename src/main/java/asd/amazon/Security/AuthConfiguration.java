package asd.amazon.Security;

/*@Configuration
@EnableWebSecurity*/
public class AuthConfiguration /*extends WebSecurityConfigurerAdapter*/ {

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        System.out.println("configure 1");
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("{noop}1234")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
<<<<<<< HEAD
        http.cors().and().csrf().disable()
=======
        System.out.println("configure 2");
        http.csrf().disable()
                .cors().configurationSource(r -> new CorsConfiguration().applyPermitDefaultValues()).and()
>>>>>>> b7301f2859513bd69223698efeb7107f97de3999
                .authorizeRequests()
                .antMatchers( "/*").permitAll()
//                .antMatchers("/*").permitAll();
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        //http.cors().and().csrf().disable().authorizeRequests().and().httpBasic().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://localhost:4200","*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT","PATCH"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

     */


}
