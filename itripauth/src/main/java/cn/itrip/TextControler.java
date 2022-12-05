package cn.itrip;

import cn.itrip.dao.itripHotel.ItripHotelMapper;
import cn.itrip.dao.itripUser.ItripUserMapper;
import cn.itrip.pojo.ItripHotel;
import cn.itrip.pojo.ItripUser;
import cn.itrip.pojo.ItripUserVO;
import cn.itrip.util.*;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Random;

@Controller
public class TextControler {
    @Resource
    ItripHotelMapper dao;
    @Resource
    ItripUserMapper dao1;
    @Resource
    RedisHelp redisHelp;
    @Resource
    TokenBiz biz;


    @RequestMapping("/api/validatephone")
    @ResponseBody
    public Dto validatephone(String user,String code){
        String rcode=redisHelp.getkey(user);
        if(rcode.equals(code)){
            dao1.jh(user);
            return DtoUtil.returnSuccess("激活成功");
        }

        return DtoUtil.returnFail("激活失败","1000");
    }

    @RequestMapping("/api/registerbyphone")
    @ResponseBody
    public Dto Getlist(@RequestBody ItripUserVO vo, HttpServletRequest request) throws Exception {
        ItripUser user=new ItripUser();
        user.setUserCode(vo.getUserCode());
        user.setUserName(vo.getUserName());
        user.setUserPassword(vo.getUserPassword());
        dao1.insertItripUser(user);
        Random random=new Random();
        String code=""+random.nextInt(9999);
        redisHelp.setKey(vo.getUserCode(),code,7200);
        SMS_Sent.SetSms(user.getUserCode(),code);

        return DtoUtil.returnSuccess("注册成功");
    }


    @RequestMapping("/api/dologin")
    @ResponseBody
    public Dto Getlist(String name, String password, HttpServletRequest request) throws Exception {
        String Agent=request.getHeader("User-Agent");
        ItripUser pojo=dao1.login(name,password);
        String token=biz.generateToken(Agent,pojo);
        //redis 存储字符传
        redisHelp.setKey(token, JSONArray.toJSONString(pojo),50);
        ItripTokenVO vo=new ItripTokenVO(token, Calendar.getInstance().getTimeInMillis()+7200,Calendar.getInstance().getTimeInMillis());
        return DtoUtil.returnDataSuccess(vo);
    }
    @RequestMapping("/list")
    @ResponseBody
    public Object Getlist() throws Exception {
        return dao.getItripHotelById(1L);
    }

}
