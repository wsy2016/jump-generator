package com.jump.generator.config.security.bak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 安全配置
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigxia extends WebSecurityConfigurerAdapter {

    @Autowired
//	UserDetailsServiceImpl userService;
//
//	@Autowired
//	CustomAccessDeniedHandler customAccessDeniedHandler;
//
//	@Autowired
//	CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
//
//	@Autowired
//	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
//
//	@Autowired
//	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//
//	@Autowired
//	CustomLogoutSuccessHandler customLogoutSuccessHandler;
//
//	@Autowired
//	CustomSessionInformationExpiredStrategy customSessionInformationExpiredStrategy;

    /**
     * 用来配置拦截保护的请求(配置认证和授权的url,登录url,退出url,session管理)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//
//    	http
//    	.csrf().disable()
//    	.headers().frameOptions().disable().and()
//    	.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
//  	  	.authenticationEntryPoint(customAuthenticationEntryPoint)
//  	  	.and().authorizeRequests()
//  	  		//登录页面  以/noauth开头url 获取图片放行，不需要登录和权限
//        	.antMatchers("/noauth/**").permitAll()
//        	//匹配到url必须具有权限才能访问
//            .anyRequest().authenticated()
//        .and().formLogin()
//            	  .successHandler(customAuthenticationSuccessHandler)
//            	  .failureHandler(customAuthenticationFailureHandler)
//            	  .loginProcessingUrl("/login")
//            	  .usernameParameter("account")
//            	  .passwordParameter("password")
//            	  .permitAll()
//        .and().logout()
//        		  .logoutUrl("/auth/logout")
//        		  .logoutSuccessHandler(customLogoutSuccessHandler)
//        		  .permitAll()
//       // .and().rememberMe().rememberMeServices(rememberMeServices())
//        .and().sessionManagement()
//        	 .maximumSessions(2)
//        	 .expiredSessionStrategy(customSessionInformationExpiredStrategy);


    }

    /**
     * 用来配置用户签名服务，主要user—details机制，可以给用户赋予权限
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        //auth.authenticationProvider(getDaoAuthenticationProvider());
    }

//    @Bean
//    RememberMeServices rememberMeServices() {
//        SpringSessionRememberMeServices rememberMeServices =
//                new SpringSessionRememberMeServices();
//        rememberMeServices.setAlwaysRemember(true);
//        return rememberMeServices;
//    }

//    @Bean
//    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
//    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//    	provider.setUserDetailsService(userService);
//    	provider.setHideUserNotFoundExceptions(false);
//    	provider.setPasswordEncoder(passwordEncoder());
//    	return provider;
//    }

//    @Bean
//    public CookieSerializer cookieSerializer() {
//            DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//            serializer.setCookieName("JSESSIONID");
//            serializer.setCookiePath("/");
//            serializer.setCookieMaxAge(1* 24 * 60 * 60*100);
//            serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
//            return serializer;
//    }

    /**
     * 密码加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");
    }


}