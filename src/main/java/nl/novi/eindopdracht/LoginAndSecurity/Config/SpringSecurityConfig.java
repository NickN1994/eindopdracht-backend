package nl.novi.eindopdracht.LoginAndSecurity.Config;

import nl.novi.eindopdracht.LoginAndSecurity.Filter.JwtRequestFilter;
import nl.novi.eindopdracht.LoginAndSecurity.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }

    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        //JWT token authentication
        // hieronder ook goed kijken naar de permissions
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                                auth
                                        // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.
//                .requestMatchers("/**").permitAll()
//                                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                                        //Inloggen
                                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                                        //profiel informatie ophalen
                                        .requestMatchers(HttpMethod.GET,"/users/{username}").hasAnyRole()
                                        //Hele lijst users ophalen
                                        .requestMatchers(HttpMethod.GET,"/users/users").hasRole("ADMIN")
                                        //Profiel informatie wijzigen
                                        .requestMatchers(HttpMethod.PUT,"/users/{username}").hasAnyRole()
                                        //Verwijderen users
                                        .requestMatchers(HttpMethod.DELETE, "/users/{username}").hasRole("ADMIN")
                                        //addUserAuthority
                                        .requestMatchers(HttpMethod.POST, "/users/{username}/authorities").hasRole("ADMIN")
                                        //deleteUserAuthority
                                        .requestMatchers(HttpMethod.DELETE, "/users/{username}/authorities/{authority}").hasRole("ADMIN")
                                        //Toevoegen van activiteit
                                        .requestMatchers(HttpMethod.POST, "/add-activity").hasRole("ADMIN")
                                        //Aanpassen van activiteit
                                        .requestMatchers(HttpMethod.PUT, "/edit-activities/{id}").hasRole("ADMIN")
                                        // verwijderen van activiteit
                                        .requestMatchers(HttpMethod.DELETE, "/edit-activities/{id}").hasRole("ADMIN")
                                        //Ophalen van alle activiteiten
                                        .requestMatchers(HttpMethod.GET,"/activities").hasAnyRole()
                                        //Ophalen van activity met id
                                        .requestMatchers(HttpMethod.GET,"/activities/{id}").hasAnyRole()
                                        //toevoegen van informatie aan de game course
                                        .requestMatchers(HttpMethod.POST, "/post-information").hasRole("ADMIN")
                                        //Aanpassen van informatie van game course
                                        .requestMatchers(HttpMethod.PUT, "/information/{id}").hasRole("ADMIN")
                                        // verwijderen van informatie van game course
                                        .requestMatchers(HttpMethod.DELETE, "/information/{id}").hasRole("ADMIN")
                                        //ophalen van alle content van course
                                        .requestMatchers(HttpMethod.GET,"/information").hasAnyRole()
                                        //versturen bericht contact formulier
                                        .requestMatchers(HttpMethod.POST, "/send-email").hasRole("USER")


                                        .requestMatchers("/authenticated").authenticated()
                                        .requestMatchers("/authenticate").permitAll()/*alleen dit punt mag toegankelijk zijn voor niet ingelogde gebruikers*/
                                        .anyRequest().denyAll() /*Deze voeg je altijd als laatste toe, om een default beveiliging te hebben voor eventuele vergeten endpoints of endpoints die je later toevoegd. */
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
