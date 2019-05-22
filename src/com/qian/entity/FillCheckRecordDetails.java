package com.qian.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2018-10-23 下午8:36:34
 * @Description
 */
@Entity
@Table(name = "fill_check_record_details")
public class FillCheckRecordDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "fill_check_record_id")
	private FillCheckRecord fillCheckRecord;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "fill_check_info_id")
	private FillCheckInfo fillCheckInfo;

	public FillCheckRecordDetails(){
		
	}
	
	public FillCheckRecordDetails(FillCheckRecord fillCheckRecord,FillCheckInfo fillCheckInfo){
		this.fillCheckRecord = fillCheckRecord;
		this.fillCheckInfo = fillCheckInfo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FillCheckRecord getFillCheckRecord() {
		return fillCheckRecord;
	}

	public void setFillCheckRecord(FillCheckRecord fillCheckRecord) {
		this.fillCheckRecord = fillCheckRecord;
	}

	public FillCheckInfo getFillCheckInfo() {
		return fillCheckInfo;
	}

	public void setFillCheckInfo(FillCheckInfo fillCheckInfo) {
		this.fillCheckInfo = fillCheckInfo;
	}

}
