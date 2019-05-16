package com.jtr.phoneshop.Controller;

import com.jtr.phoneshop.Service.GoodService;
import com.jtr.phoneshop.Service.OrderService;
import com.jtr.phoneshop.Service.UserService;
import com.jtr.phoneshop.pojo.Address;
import com.jtr.phoneshop.pojo.Orders;
import com.jtr.phoneshop.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class orderController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private Orders order;
    @Autowired
    private OrderService orderService;
    @GetMapping("/toorder")
    public ModelAndView toorder(HttpServletRequest request, HttpServletResponse response)
    {    String goodname=request.getParameter("goodname");
         String goodcolor=request.getParameter("goodcolor");
         String goodram=request.getParameter("goodram");
         String goodnumber=request.getParameter("goodnumber");
         Integer number=Integer.parseInt(goodnumber);//手机台数
         String money=request.getParameter("goodprice");
         String goodimg=request.getParameter("imgurl");
         Double goodprice=Double.parseDouble(money);
         String casu=goodcolor+"+"+goodram;//手机参数
         Double totalmoney=goodprice*number;//商品总价
        //查询用户的收获地址列表
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        List<Address> userAddress = userService.findUserAddress(loginUser.getUserid());//用户的收获地址列表

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("goodname",goodname);
        modelAndView.addObject("casu",casu);
        modelAndView.addObject("goodnumber",number);
        modelAndView.addObject("totalmoney",totalmoney);
        modelAndView.addObject("addresslist",userAddress);
         modelAndView.addObject("goodimg",goodimg);
        modelAndView.setViewName("orderof");
        return modelAndView;
    }
    @PostMapping("/productorder")
    public ModelAndView productorder(HttpServletRequest request,HttpServletResponse response){
         String goodname=request.getParameter("goodname");
         String mess=request.getParameter("message");//手机参数
         String []casu=mess.split("\\+");
         String goodcolor=casu[0];
         String goodram=casu[1];
        Integer goodid = goodService.findGoodIdByGoodnameAndGoodRamAndGoodcolor(goodname, goodram, goodcolor);
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        Integer userid=loginUser.getUserid();
        String addressid=request.getParameter("addressid");
        String orderid= UUID.randomUUID().toString();
        Integer total=Integer.parseInt(request.getParameter("totalnumber"));
        Double money=Double.parseDouble(request.getParameter("totalmoney"));
        String sendtype=request.getParameter("sendtype");
        order.setOrderid(orderid);
        order.setUserid(userid);
        order.setGoodid(goodid);
        order.setGoodtotal(total);
        order.setTotalmoney(money);
        order.setAddressid(addressid);
        order.setOrdertext(mess);
        order.setOrderstatus("0");
        order.setSendtype(sendtype);
        orderService.insertorder(order);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/toindex");
        return modelAndView;

    }
}
