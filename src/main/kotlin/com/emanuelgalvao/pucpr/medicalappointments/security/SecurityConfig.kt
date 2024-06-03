package com.emanuelgalvao.pucpr.medicalappointments.security

import com.emanuelgalvao.pucpr.medicalappointments.user.UserRole
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN
import org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Configuration
@EnableMethodSecurity
@SecurityScheme(
    name = "JWT",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
class SecurityConfig(
    private val jwtTokenFilter: JwtTokenFilter
) {

    @Bean
    fun mvc(introspector: HandlerMappingIntrospector) =
        MvcRequestMatcher.Builder(introspector)

    @Bean
    fun filterChain(security: HttpSecurity, mvc: MvcRequestMatcher.Builder): DefaultSecurityFilterChain =
        security.cors(Customizer.withDefaults())
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling {
                it.authenticationEntryPoint { _, response, exception ->
                    response.sendError(
                        SC_FORBIDDEN,
                        if (exception.message.isNullOrEmpty()) HttpStatus.FORBIDDEN.name else exception.message
                    )
                }
            }
            .headers { it.frameOptions { fo -> fo.disable() } }
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**").permitAll()
                    .requestMatchers(toH2Console()).permitAll()
                    .requestMatchers(mvc.pattern(HttpMethod.POST, "/users/login")).permitAll()
                    .requestMatchers(mvc.pattern(HttpMethod.POST, "/users")).hasRole(UserRole.ADMIN.name)
                    .requestMatchers(mvc.pattern(HttpMethod.DELETE, "/**")).hasRole(UserRole.ADMIN.name)
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtTokenFilter, BasicAuthenticationFilter::class.java)
            .build()

    @Bean
    fun corsFilter() = CorsConfiguration().apply {
        addAllowedHeader("*")
        addAllowedOrigin("*")
        addAllowedMethod("*")
    }.let {
        UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", it)
        }
    }.let { CorsFilter(it) }
}