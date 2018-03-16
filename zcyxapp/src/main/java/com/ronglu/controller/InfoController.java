package com.ronglu.controller;

import java.util.ArrayList;
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
@RequestMapping("/info")
public class InfoController {
	@Autowired
	JdbcTemplate db;
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	@ResponseBody
	public List<String> list(@RequestParam String username){
		String sql = "select i.biaoti title from s_info i where username='"+username+"' order by updatetime desc ";
		List<Map<String, Object>> list = db.queryForList(sql);
		List<String> titles = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			titles.add(map.get("title").toString());
		}
		if(list.size()>0){
			return titles;
		}
	    return null;
	}
}
