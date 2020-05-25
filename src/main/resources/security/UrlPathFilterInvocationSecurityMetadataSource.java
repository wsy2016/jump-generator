package com.jump.generator.config.security.bak;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * <p>
 * Your vision will become clear only when you can look into your own heart.
 * 唯有审视内心，你的愿景才会清晰起来。
 * </p>
 * 它的作用是用来处理当前用户是否拥有此接口的权限。
 *
 * @author wsy
 * @date 2020/4/27
 */
@Component
public class UrlPathFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

//
//    @Autowired
//    private SysMenuDAO sysMenuDAO;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        FilterInvocation filterInvocation = (FilterInvocation) object;
//        String requestUrl = filterInvocation.getRequestUrl();
//        // 因为菜单一般随着开发完成，变动不大，此处可以使用缓存，这里为了演示，就直接查库,菜单对应角色需要动态情缓存，如变更菜单和角色关系，需清除缓存
//        List<SysMenu> all = sysMenuDAO.findAll();
//        for (SysMenu menu : all) {
//            if (menu.getRoles().size() != 0 && antPathMatcher.match(menu.getUrlPath(), requestUrl)) {
//                List<SysRole> roles = menu.getRoles();
//                int size = roles.size();
//                String[] values = new String[size];
//                for (int i = 0; i < size; i++) {
//                    values[i] = roles.get(i).getRoleName();
//                }
//                return SecurityConfig.createList(values);
//            }
//        }
//        return SecurityConfig.createList("ROLE_LOGIN");
//    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}



