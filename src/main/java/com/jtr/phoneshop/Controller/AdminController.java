package com.jtr.phoneshop.Controller;

import com.jtr.phoneshop.Service.AdminService;
import com.jtr.phoneshop.Service.GoodService;
import com.jtr.phoneshop.Service.OrderService;
import com.jtr.phoneshop.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller

public class AdminController {
        //去登录界面
    @Autowired
    private AdminService adminService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private  Good good;
    private  pageBean pageBean;
    @Autowired
    private OrderCustor orderCustor;
    @Autowired
    private OrderService orderService;
    @RequestMapping("/admin/tologin")
    public ModelAndView tologin(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/login");
        return modelAndView;
    }
    @PostMapping("/admin/toindex")
    public ModelAndView tohead(admin admin,HttpServletRequest request){
        com.jtr.phoneshop.pojo.admin adminLogin = adminService.findAdminLogin(admin);
        ModelAndView modelAndView=new ModelAndView();
        if(adminLogin!=null){
            modelAndView.setViewName("/admin/head");
            request.getSession().setAttribute("adminlogin",adminLogin);
        }else
            modelAndView.setViewName("/admin/login");

           return  modelAndView;
    }
    @GetMapping("/admin/toindexx")
    public ModelAndView toindex(HttpServletRequest request){
        Object adminlogin = request.getSession().getAttribute("adminlogin");
        ModelAndView modelAndView=new ModelAndView();
        if(adminlogin!=null){
            modelAndView.setViewName("admin/head");
        }else modelAndView.setViewName("redirect:/admin/tologin");
        return modelAndView;
    }
    @PostMapping("/admin/updatename")
    public ModelAndView upname(admin admin, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        admin adminlogin = (admin)request.getSession().getAttribute("adminlogin");
        admin.setId(adminlogin.getId());
        adminService.updatename(admin);
        request.getSession().removeAttribute("adminlogin");
        modelAndView.setViewName("redirect:/admin/tologin");
        return modelAndView;
    }
    //
    @PostMapping("/admin/updatepsd")
    public ModelAndView updatepsd(admin admin,HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        admin adminlogin = (admin)request.getSession().getAttribute("adminlogin");
        admin.setId(adminlogin.getId());
        adminService.updatepsd(admin);
        request.getSession().removeAttribute("adminlogin");
        modelAndView.setViewName("redirect:/admin/tologin");
        return  modelAndView;
    }
    @GetMapping("/admin/exit")
    public ModelAndView eixt(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/admin/tologin");
        request.getSession().removeAttribute("adminlogin");
        return modelAndView;
    }
    //商品展示
    @GetMapping("/admin/show/{bandid}/{pageNum}")
    public ModelAndView showgood(@PathVariable("bandid") Integer bandid,@PathVariable("pageNum") Integer pageNum){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin/showgood");
        if(bandid==0){
            //表示所有产品
            Integer count = goodService.findCount();
           pageBean pageBean=new pageBean(pageNum,12,count);
            good.setPageBean(pageBean);
            List<Good> listGoodLimit = goodService.findListGoodLimit(good);
            pageBean.setShop(listGoodLimit);
            modelAndView.addObject("goodlimit",pageBean);
            modelAndView.addObject("pageNum",pageNum);

        }else{
            //表示品牌分页显示
            Integer countByBandId = goodService.findCountByBandId(bandid);
            pageBean pageBean=new pageBean(pageNum,12,countByBandId);
            good.setPageBean(pageBean);
            good.setSearch(bandid);
            List<Good> listGoodByBandNameLimit = goodService.findListGoodByBandNameLimit(good);
            pageBean.setShop(listGoodByBandNameLimit);
            modelAndView.addObject("goodlimit",pageBean);
            modelAndView.addObject("pageNum",pageNum);
        }
        List<Band> bandList = goodService.findBandList();
        modelAndView.addObject("bandlist",bandList);
        modelAndView.addObject("bandid",bandid);
        modelAndView.addObject("mod","band");
        return  modelAndView;
    }
    @GetMapping("/admin/toband")
    public String toband(){
        return "/admin/bandfile";
    }
    @GetMapping("/admin/togood")
    public ModelAndView togood(){
        ModelAndView modelAndView=new ModelAndView();
        List<Band> bandList = goodService.findBandList();
        modelAndView.addObject("bandlist",bandList);
        modelAndView.setViewName("/admin/shopfile");
        return modelAndView;
    }
    //手机品牌上架
    @PostMapping("/admin/addband")
    public ModelAndView addband(Band band){
          goodService.inserBand(band);
          ModelAndView modelAndView=new ModelAndView();
          modelAndView.setViewName("/admin/bandfile");
          return  modelAndView;
    }
    @PostMapping("/admin/addgood")
    public ModelAndView addgood(Good gd,@RequestParam(value="file") MultipartFile file,HttpServletRequest request) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filefix=originalFilename.substring(originalFilename.lastIndexOf("."));
        originalFilename= UUID.randomUUID().toString()+filefix;
        String path="F:/phoneshop/src/main/resources/static/image/phone/other/";
        file.transferTo(new File(path,originalFilename));
        gd.setGoodimg("image/phone/other/"+originalFilename);
        goodService.insergood(gd);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/shopfile");
        return  modelAndView;
    }
    //销售统计
    @RequestMapping("/admin/orderoper/{bandid}/{pageNum}/{asc}")
    public ModelAndView orderoper(@PathVariable("bandid") Integer bandid,@PathVariable("pageNum")Integer pageNum,@PathVariable("asc") Integer asc){
        ModelAndView modelAndView=new ModelAndView();
        List<Band> bandList = goodService.findBandList();
        modelAndView.addObject("bandlist",bandList);

        if(asc==1){
            //升序
            if(bandid==0){
                //所有手机
                Integer count = goodService.findCount();
                pageBean=new pageBean(pageNum,10,count);
                 orderCustor.setPageBean(pageBean);

                List<OrderCustor> orderOperByGood = goodService.findOrderOperByGood(orderCustor);
                pageBean.setShop(orderOperByGood);
                modelAndView.addObject("goodlist",pageBean);
                modelAndView.addObject("bandid",bandid);
                modelAndView.addObject("pageNum",pageNum);
                modelAndView.addObject("asc",asc);
            }else{
                Integer countByBandId = goodService.findCountByBandId(bandid);
                pageBean=new pageBean(pageNum,10,countByBandId);
                orderCustor.setPageBean(pageBean);
                orderCustor.setBandid(bandid);
                List<OrderCustor> orderOperByBand = goodService.findOrderOperByBand(orderCustor);
                pageBean.setShop(orderOperByBand);
                modelAndView.addObject("goodlist",pageBean);
                modelAndView.addObject("bandid",bandid);
                modelAndView.addObject("pageNum",pageNum);
                modelAndView.addObject("asc",asc);

            }
        }else{
            if(bandid==0){
                //所有手机
                Integer count = goodService.findCount();
                pageBean=new pageBean(pageNum,10,count);
                orderCustor.setPageBean(pageBean);

                List<OrderCustor> orderOperByGood = goodService.findOrderOperByGoodASC(orderCustor);
                pageBean.setShop(orderOperByGood);
                modelAndView.addObject("goodlist",pageBean);
                modelAndView.addObject("bandid",bandid);
                modelAndView.addObject("pageNum",pageNum);
                modelAndView.addObject("asc",asc);
            }else{
                Integer countByBandId = goodService.findCountByBandId(bandid);
                pageBean=new pageBean(pageNum,10,countByBandId);
                orderCustor.setPageBean(pageBean);
                orderCustor.setBandid(bandid);
                List<OrderCustor> orderOperByBand = goodService.findOrderOperByBandASC(orderCustor);
                pageBean.setShop(orderOperByBand);
                modelAndView.addObject("goodlist",pageBean);
                modelAndView.addObject("bandid",bandid);
                modelAndView.addObject("pageNum",pageNum);
                modelAndView.addObject("asc",asc);

            }
        }
        modelAndView.addObject("mods",1);
        modelAndView.setViewName("/admin/orderoper");
        return  modelAndView;
    }
    @RequestMapping("/admin/toorderoper")
    public ModelAndView toorderoper(){
        ModelAndView modelAndView=new ModelAndView();
        List<OrderCustor> ordersOperator = orderService.findOrdersOperator();
        modelAndView.addObject("orderlist",ordersOperator);
        modelAndView.setViewName("/admin/ordersp");
        return modelAndView;

    }
   @RequestMapping("/admin/check/{orderid}/{goodtotal}/{goodid}/{orderstatus}")
    public  ModelAndView checkorder(@PathVariable("orderid")String orderid,@PathVariable("goodtotal")Integer goodtotal,@PathVariable("goodid")Integer goodid,@PathVariable("orderstatus")String orderstatus){
        ModelAndView modelAndView=new ModelAndView();
        orderService.updteorderstatus(orderstatus,orderid);
        if(orderstatus.equals("1"))
        {goodService.updategoodnumber(goodtotal,goodid);}
        modelAndView.setViewName("redirect:/admin/toorderoper");
        return  modelAndView;
    }
    //手机产品下架
    @RequestMapping("/admin/deletegood/{goodid}")
    public ModelAndView deletegood(@PathVariable("goodid") Integer goodid){
           goodService.deletegood(goodid);
           ModelAndView modelAndView=new ModelAndView();
           modelAndView.setViewName("redirect:/admin/show/0/1");
           return  modelAndView;
    }
   @RequestMapping("/admin/findByTimeASC/{datatime}")
    public ModelAndView findbyTimeasc(@PathVariable("datatime")Integer datatime){
       ModelAndView modelAndView=new ModelAndView();
       List<OrderCustor> byTimeASC = goodService.findByTimeASC(datatime);
       List<Band> bandList = goodService.findBandList();
       modelAndView.addObject("bandlist",bandList);
       pageBean=new pageBean(1,1000,100);
       pageBean.setShop(byTimeASC);
       modelAndView.addObject("goodlist",pageBean);
       modelAndView.addObject("mods",0);

       modelAndView.setViewName("/admin/orderoper");
       return modelAndView;

   }

}
