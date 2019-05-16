package com.jtr.phoneshop.Controller;

import com.jtr.phoneshop.Service.GoodService;
import com.jtr.phoneshop.Service.OrderService;
import com.jtr.phoneshop.Service.UserService;
import com.jtr.phoneshop.pojo.Address;
import com.jtr.phoneshop.pojo.OrderCustor;
import com.jtr.phoneshop.pojo.User;
import com.jtr.phoneshop.pojo.UserAddR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class userController {
   @Autowired
   private UserService userService;
   @Autowired
   private User user;
   @Autowired
   private UserAddR useraddr;
   @Autowired
   private OrderService orderService;
   @Autowired
   private GoodService goodService;
    //登录检查
   @PostMapping("/tologin")
    @ResponseBody
    public String checkUser(@RequestParam(value = "id")String name, @RequestParam(value = "password") String passoword, HttpServletRequest request)
   {     user.setUsername(name);
         user.setUserpassword(passoword);
       User user1 = userService.findUser(user);
        if(user1!=null){
         request.getSession().setAttribute("loginUser",user1);
         return "success";
        }else{
            return "error";
        }
   }
   @GetMapping("/toregist")
    public String toregist(){
          return "regist";
   }
   @PostMapping("/checkusername")
   @ResponseBody
    public String checkusername(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("username");
       User user = userService.checkUser(username);
       if(user!=null)return "用户名不可用";
       else return "用户名可用";
   }
   //注册
   @PostMapping("/regist")
    public ModelAndView regist(User user)
   {

       userService.inserUser(user);
       ModelAndView modelAndView=new ModelAndView();
       modelAndView.setViewName("index");
       return modelAndView;
   }
   //去会员中兴界面
    @GetMapping("/tovip")
    public ModelAndView tovip(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        ModelAndView modelAndView=new ModelAndView();
        User userByUserId = userService.findUserByUserId(loginUser.getUserid());
        //查找用户的收货地址列表
        List<Address> userAddress = userService.findUserAddress(loginUser.getUserid());
        modelAndView.addObject("addresslist",userAddress);
        //订单相关操作
        List<OrderCustor> ordersList = orderService.findOrdersList(loginUser.getUserid());
        modelAndView.addObject("ordercustor",ordersList);
        modelAndView.setViewName("vippeople");
        modelAndView.addObject("user",userByUserId);
        return modelAndView;
    }
    @PostMapping("/toupdateUser")
    public String toupdateUser(User user){
           userService.UpdateUser(user);
           return  "redirect:/tovip";
    }
   @PostMapping("/toupadatepsd")
    public String toupdatepsd(HttpServletRequest request){
     User loginUser =(User) request.getSession().getAttribute("loginUser");
     String psd=request.getParameter("password");
     String repsd=request.getParameter("repassword");
     userService.updatePsd(repsd,loginUser.getUserid(),psd);
     request.getSession().removeAttribute("loginUser");
     return "index";
   }
   @GetMapping("/deleteaddress/{addressid}")
    public ModelAndView deleteAddress(@PathVariable String addressid){
       ModelAndView modelAndView=new ModelAndView();
       userService.deleteUseradd(addressid);
       userService.deleteaddress(addressid);
       modelAndView.setViewName("redirect:/tovip");
       return modelAndView;
   }
   @PostMapping("/insertaddr")
    public ModelAndView inseraddr(Address address,HttpServletRequest request){
       HttpSession session = request.getSession();
       User loginUser =(User) session.getAttribute("loginUser");
       String uuid=UUID.randomUUID().toString();
       address.setAddressid(uuid);
       useraddr.setAddressid(address.getAddressid());
       useraddr.setUserid(loginUser.getUserid());
       userService.insertaddress(address);
       userService.insertuseraddr(useraddr);
       ModelAndView modelAndView=new ModelAndView();
       modelAndView.setViewName("redirect:/tovip");
       return modelAndView;
   }
   @GetMapping("/exit")
    public ModelAndView exit(HttpServletRequest request){
       ModelAndView modelAndView=new ModelAndView();
       request.getSession().removeAttribute("loginUser");
       modelAndView.setViewName("redirect:/toindex");
       return  modelAndView;
   }

}
