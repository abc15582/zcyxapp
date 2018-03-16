package com.ronglu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mydb")
public class DBController {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

}
