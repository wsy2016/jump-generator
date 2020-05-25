package com.jump.generator.config.security.bak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * Your vision will become clear only when you can look into your own heart.
 * 唯有审视内心，你的愿景才会清晰起来。
 * </p>
 * 它的作用是用来校验用户密码等功能，
 * 当然如短信验证或要第三方验证，也可以实现这个接口，在本文中是用密码校验。
 * 前面也说到userDetailService会传一个用户的基本信息。它的主要作用就是为该接口服务的
 *
 * @author wsy
 * @date 2020/4/27
 */
@Service("authenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单用户名
        String username = (String) authentication.getPrincipal();
        // 获取表单用户填写的密码
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String password1 = userDetails.getPassword();
        if (!Objects.equals(password, password1)) {
            throw new BadCredentialsException("用户名或密码不正确");
        }
        //登录验证通过,保存登陆信息
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
