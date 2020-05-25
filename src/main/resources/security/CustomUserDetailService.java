package com.jump.generator.config.security.bak;

import com.jump.commons.utils.exception.BadRequestException;
import com.jump.zeus.system.domain.JwtUser;
import com.jump.zeus.system.domain.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Your vision will become clear only when you can look into your own heart.
 * 唯有审视内心，你的愿景才会清晰起来。
 * </p>
 * UserDetailsService主要是用来我们和数据库做交互用的。
 * 简单来说，就是用户名传过来，这个类负责校验用户名是否存在等业务逻辑
 *
 * @author wsy
 * @date 2020/4/27
 */

@Service("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {
    //uranus dubbo 接口
//    @Autowired
//    private UserApiService userApiService;
//    @Autowired
//    private RoleApiService roleApiService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //  UserApiService user = UserApiService.findByName(username);
        UserDTO user = null;
        if (user == null) {
            throw new BadRequestException("账号不存在");
        } else {
            return createJwtUser(user);
        }
    }

    public UserDetails createJwtUser(UserDTO user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatar(),
                user.getEmail(),
                user.getPhone(),
                user.getEnabled(),
                user.getCreateTime(),
                user.getLastPasswordResetTime()
        );
    }
}

