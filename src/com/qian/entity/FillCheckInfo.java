package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fill_check_info")
public class FillCheckInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String fill_check_code;

	@Column(length = 255)
	private String fill_check_description;

	// 0 before, 1 after 参考com.tgb.code.BeforeOrAfterFillCode
	@Column()
	private int before_or_after_fill;

	public FillCheckInfo(){
		
	}
	
	public FillCheckInfo(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFill_check_code() {
		return fill_check_code;
	}

	public void setFill_check_code(String fill_check_code) {
		this.fill_check_code = fill_check_code;
	}

	public String getFill_check_description() {
		return fill_check_description;
	}

	public void setFill_check_description(String fill_check_description) {
		this.fill_check_description = fill_check_description;
	}

	public int getBefore_or_after_fill() {
		return before_or_after_fill;
	}

	public void setBefore_or_after_fill(int before_or_after_fill) {
		this.before_or_after_fill = before_or_after_fill;
	}

}
