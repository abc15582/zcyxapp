package com.ronglu;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ronglu.config.IndexSettings;

@RestController
@SpringBootApplication
public class RongluApplication extends SpringBootServletInitializer  {
	
	@Autowired
	private IndexSettings indexSet;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/")
	public String index(){
		String sql = "select name from t1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		
		return "template/login";
	}
	public static void main(String[] args) {
		SpringApplication.run(RongluApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RongluApplication.class);
    }

	
}
