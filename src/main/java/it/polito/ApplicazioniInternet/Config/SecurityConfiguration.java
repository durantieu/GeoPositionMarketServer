package it.polito.ApplicazioniInternet.Config;

import com.google.common.collect.ImmutableList;
import it.polito.ApplicazioniInternet.Filter.SimpleCORSFilter;
import it.polito.ApplicazioniInternet.Model.User;
import it.polito.ApplicazioniInternet.Service.UserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService; //Acchiappa l'User (CustomUser) a partire dall'username

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public PasswordEncoder encoder (){
        return new BCryptPasswordEncoder(); //abilita l'oggetto che crea una password hashata con SHA-1
    }

  //Devo indicare la cifratura delle credenziale
  //Sostanzialmente dico che sulle password non è applicato nessun DIGEST
    /*@SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }*/

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }


    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //qui esponiamo determinate url a determinati ruoli

        http //disabilita Cross Site Request Forgery
                .cors().and()
                .csrf().ignoringAntMatchers("/registration")
                .and()
                .addFilterBefore(new SimpleCORSFilter(), ChannelProcessingFilter.class)
                .authorizeRequests()
                .antMatchers("/user/getMyPositions","/user/getMyPositions_").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/insertPositions","/user/insertPositions_").hasRole("USER")
                .antMatchers("/user/deletePositions","/user/deletePositions_").hasRole("USER")
                .antMatchers("/customer/getBoughtPositions", "/customer/getBoughtPositions_").hasRole("CUSTOMER")
                .antMatchers("/customer/buyPositionsByTime", "/customer/buyPositionsByTime_").hasRole("CUSTOMER")
                .antMatchers("/customer/buyPositions", "/customer/buyPositions_").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers("/customer/confirm", "/customer/confirm_").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers("/customer/getShoppingCart", "/customer/getShoppingCart_").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers("/customer/shoppingCart", "/customer/shoppingCart_").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers("/customer/getUsers", "/customer/getUsers_").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers("/getRole", "/getRole_").hasAnyRole("ADMIN", "CUSTOMER", "USER")
                .antMatchers("/customer/info", "/customer/info_").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers("/customer/download", "/customer/download_").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers("/user/getMyPositions", "/user/getMyPositions_").hasAnyRole("ADMIN", "USER")
                .antMatchers("/user/getMyArchives", "/user/getMyArchives_").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers("/user/getMyInfo", "/user/getMyInfo_").hasAnyRole("ADMIN", "USER")
                .and().formLogin().permitAll()
                .and().logout().permitAll();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "OPTIONS", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token", "xsrf-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
