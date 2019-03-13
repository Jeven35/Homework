package com.jeven.mycourses.bl.security;

import com.jeven.mycourses.domain.Resource;
import com.jeven.mycourses.domain.Role;
import com.jeven.mycourses.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Created by jeven on 2019/3/12.
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private ResourceService resourceService;

    //接收用户请求的地址，返回访问该地址需要的所有权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        System.out.println("用户请求的地址是：" + requestUrl);

        //如果是登录页面就不需要权限
        if ("/".equals(requestUrl)) {
            return null;
        }

         Resource resource = resourceService.getResourceByUrl(requestUrl);

        //如果没有匹配的url则说明大家都可以访问
        if(resource == null) {
            return SecurityConfig.createList("ROLE_LOGIN");
        }

        List<Role> roles = resourceService.getRoles(resource.getId());

        int size = roles.size();
        System.out.println("匹配的角色数量是"+size);

        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = roles.get(i).getRole_name();
        }
        return SecurityConfig.createList(values);

    }



    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
