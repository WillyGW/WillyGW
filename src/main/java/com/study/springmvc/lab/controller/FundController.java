package com.study.springmvc.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.entity.Fundstock;
import com.study.springmvc.lab.repository.FundDao;

@RestController
@RequestMapping("/lab/fund")
public class FundController {
	
	private int pageNumber = -1;
	
	@Autowired
	private FundDao fundDao;
	
	@GetMapping("/")
	public List<Fund> index() {
		List<Fund> funds = fundDao.queryAll();
		// 將沒有成分股的基金內的 Fundstock 更改為 null
		/*for(Fund fund : funds) {
			if(fund.getFundstocks().size() > 0 && fund.getFundstocks().get(0).getFid() == null) {
				fund.setFundstocks(null);
			}
		}*/
		// homework 用 java 8 來處理 (不使用for-loop 能達到上述效果)
		funds.stream()
				.filter(f-> f.getFundstocks() != null)
				.filter(f-> f.getFundstocks().get(0).getFid() == null)
				.forEach(f->f.setFundstocks(null));
		return funds;
	}
	
	@GetMapping("/{fid}")
	public Fund get(@PathVariable("fid") Integer fid) {
		return fundDao.get(fid);
	}
	
	@GetMapping("/page/{pageNumber}")	
	public List<Fund> page(@PathVariable("pageNumber") int pageNumber,@ModelAttribute Fundstock fundstock, Model model){
		this.pageNumber = pageNumber;
		int offset = (pageNumber-1) * FundDao.LIMIT;
		return fundDao.queryPage(offset);
	}
	
	@GetMapping("/totalpagecount")	
	public int pagecount(){		
		return fundDao.count() / FundDao.LIMIT;
	}
	
	@PostMapping("/")
	public int add(@RequestBody Fund fund) {
		return fundDao.add(fund);
	}
	
	@PutMapping("/")
	public int update(@RequestBody Fund fund) {
		return fundDao.update(fund);
	}
	
	@DeleteMapping("/{fid}")
	public int delete(@PathVariable("fid") Integer fid) {
		return fundDao.delete(fid);
	}
	
}