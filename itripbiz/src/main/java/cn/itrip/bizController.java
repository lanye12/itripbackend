package cn.itrip;


import cn.itrip.dao.itripAreaDic.ItripAreaDicMapper;
import cn.itrip.dao.itripLabelDic.ItripLabelDicMapper;
import cn.itrip.pojo.ItripAreaDic;
import cn.itrip.pojo.ItripLabelDic;
import cn.itrip.util.Dto;
import cn.itrip.util.DtoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @date 2022/11/15 16:16
 */
@Controller
public class bizController {

    @Resource
    ItripAreaDicMapper dao;

    @Resource
    ItripLabelDicMapper dao1;

    @Resource
    ItripAreaDicMapper dao2;

    @RequestMapping(value = "/api/hotel/querytradearea/{id}")
    @ResponseBody
    public Dto GetObj1(@PathVariable("id")int id) {

        return DtoUtil.returnDataSuccess(dao2.getarea(id));
    }

    @RequestMapping(value = "/api/hotel/queryhotelfeature")
    @ResponseBody
    public Dto queryhotelfeature(){
        List<ItripLabelDic> list=dao1.getfirsttop();
        return DtoUtil.returnDataSuccess(list);
    }

    @RequestMapping("/api/hotel/queryhotcity/{id}")
    @ResponseBody
    public Dto GetObj(@PathVariable("id")int id){
        List<ItripAreaDic> list=dao.ishot(id);
        return DtoUtil.returnDataSuccess(list);
    }
}
