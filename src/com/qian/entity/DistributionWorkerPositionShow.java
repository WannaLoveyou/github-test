package com.qian.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qian.vo.DistributionWorkerPositionShowVo;


@Entity
@Table(name = "distribution_worker_position_show")
public class DistributionWorkerPositionShow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "delivery_man_id")
	private User delivery_man;
	
	@Column(nullable = false)
	private Date create_time;
	
	@Column()
	private Double longitude;

	@Column()
	private Double latitude;
	

	public DistributionWorkerPositionShow(DistributionWorkerPositionShowVo dbwpsVo) {
		this.latitude = dbwpsVo.getLatitude();
		this.longitude =dbwpsVo.getLongitude();
		User u = new User();
		u.setId(dbwpsVo.getDelivery_man_id());
		this.setDelivery_man(u);
		this.create_time = new Date();
	}

	public DistributionWorkerPositionShow() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getDelivery_man() {
		return delivery_man;
	}

	public void setDelivery_man(User delivery_man) {
		this.delivery_man = delivery_man;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
}
