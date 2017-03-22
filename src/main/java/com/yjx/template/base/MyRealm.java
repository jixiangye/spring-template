package com.yjx.template.base;

import com.yjx.template.dao.RolePermissionsMapper;
import com.yjx.template.dao.UserMapper;
import com.yjx.template.dao.UserRolesMapper;
import com.yjx.template.pojo.RolePermissions;
import com.yjx.template.pojo.User;
import com.yjx.template.pojo.UserRoles;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private RolePermissionsMapper rolePermissionsMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) getAvailablePrincipal(principals);
        List<UserRoles> userRolesList = userRolesMapper.getRolesByUser(username);
        Set<String> permissions = new HashSet<String>();
        for (UserRoles userRoles : userRolesList) {
            List<RolePermissions> rolePermissionsList = rolePermissionsMapper.getPermissionsByRole(userRoles.getRoleCode());
            for (RolePermissions rolePermissions : rolePermissionsList) {
                permissions.add(rolePermissions.getPermissionCode());
            }
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userMapper.getByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }

}
