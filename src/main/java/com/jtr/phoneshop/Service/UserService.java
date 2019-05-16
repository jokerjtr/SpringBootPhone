package com.jtr.phoneshop.Service;

import com.jtr.phoneshop.Mapper.userMapper;
import com.jtr.phoneshop.pojo.Address;
import com.jtr.phoneshop.pojo.User;
import com.jtr.phoneshop.pojo.UserAddR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
     @Autowired
     private userMapper userMapper;

     //检查是否登录成功
     public User findUser(User user){
         User loguser = userMapper.findUser(user);
          return loguser;
     }
     //查看用户收获地址
    public List<Address>findUserAddress(Integer userid)
    {
        List<Address> userAddress = userMapper.findUserAddress(userid);
        return userAddress;
    }
    // //注册用户
    public void inserUser(User user){
             userMapper.inserUser(user);
     }
     // //检查用户名是否存在
          public User checkUser(String username){
              User user = userMapper.checkUser(username);
              return user;
          }
     //  //查询用户的身份信息
     public User findUserByUserId(Integer userid){
         User userByUserId = userMapper.findUserByUserId(userid);
         return userByUserId;
     }
     //更新身份信息
    public void UpdateUser(User user){
        userMapper.UpdateUser(user);
    }
    //更新密码
    public void updatePsd(String password,Integer userid,String userpassword){
          userMapper.updatePsd(password,userid,userpassword);
    }
    ////收货地址的删除
         public void deleteUseradd(String addressid){
              userMapper.deleteUseradd(addressid);
         }
    //       @Delete("DELETE FROM address WHERE addressid=#{addressid}")
         public void deleteaddress(String addressid){
              userMapper.deleteaddress(addressid);
         }
         //增加收获地址
    //       @Insert("INSERT INTO address(addressid,addressname,addressphone,addr) VALUES(#{addressid},#{addressname},#{addressphone},#{addr})")
          public void insertaddress(Address address){
              userMapper.insertaddress(address);
          }
    //       @Insert("INSERT INTO useraddr(userid,addressid) VALUES(#{userid},#{addressid})")
        public  void insertuseraddr(UserAddR address){
             userMapper.insertuseraddr(address);
        }
}
