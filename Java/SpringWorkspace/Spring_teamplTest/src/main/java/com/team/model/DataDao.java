package com.team.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public void saveData(String contentid, String contenttypeid, String cat1, String cat2, String cat3, 
							String title, String tel, String addr1, String addr2, String firstimage, 
							String mapx, String mapy) {
        String sql = "INSERT INTO content (contentid, contenttypeid, cat1, cat2, cat3, title, tel, "
        									+ "addr1, addr2, firstimage, mapx, mapy) "
        									+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, contentid, contenttypeid, cat1, cat2, cat3, title, tel, addr1, addr2, firstimage, mapx, mapy);
    }
	
	/*public void cat1(String cat1, String cat1_name) {
        String sql = "INSERT INTO CAT1 (cat1, cat1_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, cat1, cat1_name);
    }*/

}
