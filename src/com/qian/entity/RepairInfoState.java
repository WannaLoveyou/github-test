package com.qian.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "repair_info_state")
// 维修状态
public class RepairInfoState {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String repair_state_description;

	@OneToMany(mappedBy = "repairInfoState")
	@JsonIgnore
	private List<RepairInfo> repairInfo = new ArrayList<RepairInfo>();

	public RepairInfoState() {

	}

	public RepairInfoState(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getrepair_state_description() {
		return repair_state_description;
	}

	public void setrepair_state_description(String repair_state_description) {
		this.repair_state_description = repair_state_description;
	}

	public List<RepairInfo> getRepairInfo() {
		return repairInfo;
	}

	public void setRepairInfo(List<RepairInfo> repairInfo) {
		this.repairInfo = repairInfo;
	}

}
