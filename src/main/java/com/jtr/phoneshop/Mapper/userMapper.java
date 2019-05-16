package com.jtr.phoneshop.Mapper;

import com.jtr.phoneshop.pojo.Address;
import com.jtr.phoneshop.pojo.User;
import com.jtr.phoneshop.pojo.UserAddR;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface userMapper {
      //登录用户检查
       @Select("select * from user where username=#{username} and userpassword=#{userpassword} ")
        User findUser(User user);
       //检查用户的收获地址
       @Select("SELECT address.addressid,address.addressname,address.addressphone,address.addr FROM user,useraddr,address WHERE useraddr.userid=user.userid and useraddr.addressid=address.addressid and `user`.userid=#{userid}"
       )
       public List<Address> findUserAddress(Integer userid);
       //注册用户
       @Insert("INSERT INTO user(username,userpassword,uname,sex,email,phone,idcard,address)VALUES(#{username},#{userpassword},#{uname},#{sex},#{email},#{phone},#{idcard},#{address})")
       public void inserUser(User user);
       //检查用户名是否存在
       @Select("SELECT * FROM `user` WHERE `user`.username=#{username}")
       public User checkUser(String username);
       //查询用户的身份信息
       @Select("SELECT * FROM `user` WHERE user.userid=#{userid}")
       public User findUserByUserId(Integer userid);
       //更新身份信息
       @Update("UPDATE  user SET username=#{username},sex=#{sex},email=#{email},phone=#{phone},address=#{address} WHERE userid=#{userid}")
       public void UpdateUser(User user);
       //更新密码
        @Update("UPDATE user SET userpassword=#{password} WHERE userid=#{userid} and userpassword=#{userpassword}")
      public void updatePsd(@Param("password") String password, @Param("userid") Integer userid, @Param("userpassword") String userpassword);
        //收货地址的删除
       @Delete("DELETE FROM useraddr WHERE addressid=#{addressid}")
       public void deleteUseradd(String addressid);
       @Delete("DELETE FROM address WHERE addressid=#{addressid}")
       public void deleteaddress(String addressid);

       //增加收获地址
       @Insert("INSERT INTO address(addressid,addressname,addressphone,addr) VALUES(#{addressid},#{addressname},#{addressphone},#{addr})")
       public void insertaddress(Address address);
       @Insert("INSERT INTO useraddr(userid,addressid) VALUES(#{userid},#{addressid})")
       public  void insertuseraddr(UserAddR address);
}
