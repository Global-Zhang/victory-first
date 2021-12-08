package com.hitTheRoad.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hitTheRoad.server.AdminUtils;
import com.hitTheRoad.server.config.security.JwtTokenUtil;
import com.hitTheRoad.server.mapper.AdminRoleMapper;
import com.hitTheRoad.server.mapper.RoleMapper;
import com.hitTheRoad.server.pojo.*;
import com.hitTheRoad.server.mapper.AdminMapper;
import com.hitTheRoad.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LaoZhang
 * @since 2021-11-28
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private  AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>()
                                                        .eq("username",username)
                                                        .eq("enabled",true));
    }

    @Override
    public RespBean login(String name, String password, String code,HttpServletRequest request) {
        String captcha =(String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码错误，请重新获取");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if (null == userDetails || !passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()){
            return RespBean.error("账号禁用，请联系管理员");
        }

        //更新security对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return RespBean.success("登陆成功",tokenMap);
    }

    /*
     * 根据用户id查询角色列表
     * */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /*
    * 获取所有操作员
    * */
    @Override
    public List<Admin> getAllAdmins(String keywords) {

        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(),keywords);
    }

    @Override
    //涉及到单个以上操作
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));

        return rids.length == adminRoleMapper.addAdminRole(adminId,rids) ? RespBean.success("更新成功") : RespBean.error("更新失败");
    }

    //更新用户密码
    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPass,admin.getPassword())){
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if (1 == result){
                return RespBean.success("更新成功");
            }
        }
        return RespBean.error("更新失败");
    }


}
