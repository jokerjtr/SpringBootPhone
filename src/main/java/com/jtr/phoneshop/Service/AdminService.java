package com.jtr.phoneshop.Service;


import com.jtr.phoneshop.pojo.admin;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminService {
    @Autowired
    private com.jtr.phoneshop.Mapper.adminMapper adminMapper;
    //验证密码是否正确

    public admin findAdminLogin(admin admin){
        return adminMapper.findAdminLogin(admin);
    }
    //更行账号

    public void updatename(admin admin){
        adminMapper.updatename(admin);
    }

    //更新密码
   public void updatepsd(admin admin){
        adminMapper.updatepsd(admin);
   }
}
