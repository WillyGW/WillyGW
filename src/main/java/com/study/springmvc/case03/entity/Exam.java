package com.study.springmvc.case03.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Exam {
	private String STDid; // 學員代號
	private String EXid; // 考試代號
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // 返回日期型態
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 接收日期類型
	private Date examDate; // 考試日期
	private String[] slot; // 考試時段
	private Boolean examPay; // 繳費狀況:true(已繳費)， false(未繳費)
	private String examNote; // 考試備註
	public String getSTDid() {
		return STDid;
	}
	public void setSTDid(String sTDid) {
		STDid = sTDid;
	}
	public String getEXid() {
		return EXid;
	}
	public void setEXid(String eXid) {
		EXid = eXid;
	}
	public String[] getSlot() {
		return slot;
	}
	public void setSlot(String[] slot) {
		this.slot = slot;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public Boolean getExamPay() {
		return examPay;
	}
	public void setExamPay(Boolean examPay) {
		this.examPay = examPay;
	}
	public String getExamNote() {
		return examNote;
	}
	public void setExamNote(String examNote) {
		this.examNote = examNote;
	}
	@Override
	public String toString() {
		return "Exam [STDid=" + STDid + ", EXid=" + EXid + ", slot=" + Arrays.toString(slot) + ", examDate=" + examDate
				+ ", examPay=" + examPay + ", examNote=" + examNote + "]";
	}
	
	
}
