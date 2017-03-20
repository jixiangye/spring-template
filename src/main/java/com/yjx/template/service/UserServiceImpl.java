package com.yjx.template.service;

import com.yjx.template.base.BizException;
import com.yjx.template.dao.UserMapper;
import com.yjx.template.pojo.User;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

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
            e.printStackTrace();
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
}
