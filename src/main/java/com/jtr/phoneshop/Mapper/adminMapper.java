package com.jtr.phoneshop.Mapper;

import com.jtr.phoneshop.pojo.admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface adminMapper {
    //验证密码是否正确
    @Select("SELECT * FROM admins WHERE adminname=#{adminname} and password=#{password}")
    public admin findAdminLogin(admin admin);
    //更行账号
    @Update("UPDATE admins SET adminname=#{adminname},name=#{name},phone=#{phone},email=#{email} WHERE id=#{id}")
    public void updatename(admin admin);
    //更新密码
    @Update("UPDATE admins SET password=#{name} WHERE id=#{id} AND password=#{password}")
    public void updatepsd(admin admin);
}
