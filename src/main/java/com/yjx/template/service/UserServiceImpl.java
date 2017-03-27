package com.yjx.template.service;

import com.yjx.template.base.BizException;
import com.yjx.template.dao.RoleMapper;
import com.yjx.template.dao.UserMapper;
import com.yjx.template.dao.UserRolesMapper;
import com.yjx.template.pojo.AssignRoleDTO;
import com.yjx.template.pojo.Role;
import com.yjx.template.pojo.User;
import com.yjx.template.pojo.UserRoles;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private RoleMapper roleMapper;

    public int register(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            throw new BizException("username.required", "用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new BizException("password.required", "密码不能为空");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new BizException("email.required", "邮箱不能为空");
        }
        String salt = generateSalt();
        String encryptPassword = encryptPassword(user.getPassword(), salt);
        user.setPassword(encryptPassword);
        user.setSalt(salt);
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        user.setStatus("1");
        return userMapper.insert(user);
    }

    private String generateSalt() {
        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        return generator.nextBytes().toHex();
    }

    private String encryptPassword(String password, String salt) {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }

        digest.reset();
        digest.update(salt.getBytes());
        byte[] hashed = digest.digest(password.getBytes());
        // iterate remaining number:
        for (int i = 0; i < 2; i++) {
            digest.reset();
            hashed = digest.digest(hashed);
        }
        return Hex.encodeToString(hashed);
    }

    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    public void updatePassword(User user) {
        String salt = generateSalt();
        String password = encryptPassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(password);
        user.setModifyTime(new Date());
        userMapper.updateByUsername(user);
    }

    public List<User> listUser() {
        return userMapper.listUser();
    }

    public List<Role> userRoleList(String username) {
        List<Role> roleList = new ArrayList<Role>();
        List<UserRoles> rolesByUser = userRolesMapper.getRolesByUser(username);
        for (UserRoles userRoles : rolesByUser) {
            roleList.add(roleMapper.selectByPrimaryKey(userRoles.getRoleCode()));
        }
        return roleList;
    }

    public void saveUserRole(AssignRoleDTO assignRoleDTO) {
        List<UserRoles> rolesByUser = userRolesMapper.getRolesByUser(assignRoleDTO.getUsername());
        for (UserRoles userRoles : rolesByUser) {
            userRolesMapper.deleteByPrimaryKey(userRoles.getId());
        }
        List<String> roleCodeList = assignRoleDTO.getRoleCodeList();
        for (String s : roleCodeList) {
            UserRoles record = new UserRoles();
            record.setUsername(assignRoleDTO.getUsername());
            record.setRoleCode(s);
            userRolesMapper.insertSelective(record);
        }
    }
}
