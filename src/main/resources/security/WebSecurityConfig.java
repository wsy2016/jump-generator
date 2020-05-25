package com.jump.generator.config.security.bak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>
 * Your vision will become clear only when you can look into your own heart.
 * 唯有审视内心，你的愿景才会清晰起来。
 * </p>
 * 自定义Security: 认证authenticate+授权Authorize
 *
 * @author wsy
 * @date 2020/4/27
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 用户信息
     */
//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    /**
     * 自定义基于JWT的安全过滤器
     */
//    @Autowired
//    JwtAuthorizationTokenFilter authenticationTokenFilter;

//    @Value("${jwt.header}")
//    private String tokenHeader;

//    @Value("${jwt.auth.path}")
//    private String loginPath;


    /**
     * 校验用户登陆功能
     */
//    @Autowired
//    private CustomAuthenticationProvider customAuthenticationProvider;

//    @Autowired
//    private AuthenticationEntryPoint unauthorizedHandler;


    /**
     * 构建自定义认证策略
     *
     * @param auth
     * @return void
     * @author wsy
     * @date 2020/5/11
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
       // auth.authenticationProvider(customAuthenticationProvider);

    }

    /**
     * 构建自定义授权Authorize策略
     *
     * @param httpSecurity
     * @return void
     * @author wsy
     * @date 2020/5/11
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        //anonymous() 允许匿名用户访问
        //permitAll() 无条件允许访问
//        httpSecurity
//                // 禁用 CSRF(跨站域请求伪造,A挟持了B的请求认证)
//                .csrf().disable()
//                // 授权异常
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                // 不创建会话
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//
//                // 过滤请求
//                .authorizeRequests()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/*.html",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).anonymous()
//                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
//                //login
//                .antMatchers(HttpMethod.POST, "/auth/" + loginPath).anonymous()
//                .antMatchers("/api/file/**").anonymous()
//                .antMatchers("/api/localPictures").anonymous()
//                // swagger start
//                .antMatchers("/swagger-ui.html").anonymous()
//                .antMatchers("/swagger-resources/**").anonymous()
//                .antMatchers("/webjars/**").anonymous()
//                .antMatchers("/*/api-docs").anonymous()
//                // swagger end
//                // 接口限流测试
//                .antMatchers("/test/**").anonymous()
//                //druid
//                .antMatchers("/druid/**").anonymous()
//                // 所有请求都需要认证
//                .anyRequest().authenticated()
//                // 防止iframe 造成跨域
//                .and().headers().frameOptions().disable();
//
//        httpSecurity
//                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return null;
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return false;
//            }
//        };
//    }
}
