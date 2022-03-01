package com.study.springmvc.lab.repository;


import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.entity.Fundstock;

@Repository
public class FundstockDaoImpl implements FundstockDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Fundstock> queryAll() {

		return queryAllCase3();
	}

	public List<Fundstock> queryAllCase1() {
		String sql = "select s.sid, s.fid, s.symbol, s.share from fundstock s order by s.sid";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Fundstock>(Fundstock.class));
	}

	private List<Fundstock> queryAllCase3() {
		// fundstocks_sid 其中的 fundstocks 指的是 fund.java 一對多的屬性命名
		String sql = "select s.sid , s.fid, s.symbol , s.share, "
				+ "f.fid as fund_fid, f.fname as fund_fname, f.createtime as fund_createtime "
				+ "from fundstock s left join fund f " + "on f.fid = s.sid order by s.sid";
		ResultSetExtractor<List<Fundstock>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance().addKeys("sid")
				.newResultSetExtractor(Fundstock.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public List<Fundstock> queryPage(int offset) {
		if(offset < 0) {
			return queryAll();
		}
		String sql = "select s.sid , s.fid, s.symbol , s.share, "
				+ "f.fid as fund_fid, f.fname as fund_fname, f.createtime as fund_createtime "
				+ "from fundstock s left join fund f " + "on f.fid = s.sid order by s.sid";
		sql += String.format(" limit %d offset %d" , LIMIT ,offset);
		ResultSetExtractor<List<Fundstock>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance().addKeys("sid")
				.newResultSetExtractor(Fundstock.class);
		return jdbcTemplate.query(sql, resultSetExtractor);		
		
	}

	@Override
	public Fundstock get(Integer sid) {
		String sql = "select s.sid , s.fid, s.symbol , s.share from fundstock s" 				
				+ " where s.sid = ?";			
		Fundstock fundstock = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<Fundstock>(Fundstock.class),
				sid);
		sql = "select f.fid , f.fname, f.createtime from fund f" 				
				+ " where f.fid = ?";
		Fund fund = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<Fund>(Fund.class),
				fundstock.getFid());
		fundstock.setFund(fund);
		return fundstock;
	}

	@Override
	public int add(Fundstock fundstock) {
		String sql = "insert into fundstock(fid, symbol, share) values(?,?,?)";
		int rowcount = jdbcTemplate.update(sql, fundstock.getFid(), fundstock.getSymbol(), fundstock.getShare());
		return rowcount;
	}

	@Override
	public int update(Fundstock fundstock) {
		String sql = "update fundstock set fid=? , symbol=?, share=? where sid=?";
		int rowcount = jdbcTemplate.update(sql, fundstock.getFid(), fundstock.getSymbol(), fundstock.getShare(),fundstock.getSid());
		return rowcount;
	}

	@Override
	public int delete(Integer sid) {
		String sql = "delete from fundstock where sid=?";
		int rowcount = jdbcTemplate.update(sql,sid);
		return rowcount;
	}

	@Override
	public int count() {		
		String sql = "select count(*) from fundstock";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
