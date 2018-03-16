package com.ronglu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	JdbcTemplate db;
	
	@RequestMapping("/")
	public String loginhtml(){
	    return "login.html";
	}
	
	@RequestMapping(value="/logindb",method = RequestMethod.GET)
	@ResponseBody
	public String logindb(@RequestParam String username, @RequestParam String userpwd){
		String sql = "select * from s_user where username='"+username+"'and userpwd='"+userpwd+"'";
		List<Map<String, Object>> list = db.queryForList(sql);
		if(list.size()>0){
			return "1";
		}
	    return "0";
	}
	
	@RequestMapping(value="/fabuInfo",method = RequestMethod.GET)
	@ResponseBody
	public int fabuInfo(@RequestParam String biaoti,@RequestParam String biaoqian,@RequestParam String chenghu,@RequestParam String fangshi,@RequestParam String miaoshu,@RequestParam String username){
		Integer idnum =  db.queryForObject("select max(id) from s_info", java.lang.Integer.class);
		String sql = "insert into s_info values("+(idnum+1)+",'"+biaoti+"','"+biaoqian+"','"+chenghu+"','"+fangshi+"','"+miaoshu+"','1','','"+username+"',now())";
		return db.update(sql);
	}
	
	

}
