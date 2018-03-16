package com.ronglu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ronglu.entity.Menulog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/menuLog")
public class MenuLogController {
    @Autowired
    JdbcTemplate db;

    @RequestMapping(value = "/clickMenu",method = RequestMethod.GET)
    public void clickMenuLog(@RequestParam String menucode,@RequestParam String city){

        Map<String,String> map = new HashMap<String,String>();
        map.put("11","首页");
        map.put("12","学校概况");
        map.put("13","师资力量");
        map.put("14","特色培训");
        map.put("15","出国留学");
        map.put("16","招生信息");
        map.put("17","在线报名");
        map.put("18","传统培训");
        map.put("19","校园风光");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String sql = "insert into menulog values('"+uuid+"','"+menucode+"','"+map.get(menucode)+"',now(),'"+city+"','','','1',now())";
        db.update(sql);

    }
    
    @RequestMapping("/selectMenuLog")
    @ResponseBody
    public List<Menulog> selectMenuLog(){
    	List<Menulog> logs = new ArrayList();
    	String sql = "select menucode,menuname , count(logid) clicknum from menulog group by menucode,menuname order by clicknum desc ";
    	List<Map<String,Object>> list = db.queryForList(sql);
    	for(Map<String,Object> map : list){
    		Menulog log = new Menulog();
    		log.setMenucode(map.get("menucode").toString());
    		log.setMenuname(map.get("menuname").toString());
    		log.setClicknum(Long.parseLong(map.get("clicknum").toString()));
    		logs.add(log);;
    	}
    	return logs;
    }
    @RequestMapping("/selectCity")
    @ResponseBody
    public Map<String,String> selectCity(){
    	Map<String,String> citymap = new HashMap<String, String>();
    	String sql = "select city,count(logid) clicknum from menulog group by city order by clicknum desc";
    	List<Map<String,Object>> list = db.queryForList(sql);
    	for(Map<String,Object> map : list){
    		citymap.put(map.get("city").toString(), map.get("clicknum").toString());
    	}
    	return citymap;
    }
}
