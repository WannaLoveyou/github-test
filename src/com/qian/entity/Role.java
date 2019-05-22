/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.Role.java
 * Class:			Role
 * Date:			2012-6-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.qian.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "security_role")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "operations", "roles", "menus" })
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",columnDefinition=("int(11) comment '主键ID'"))
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", columnDefinition = ("varchar(225)  default null comment '角色名称'"))
	private String name;

	@Column(name = "description", columnDefinition = ("varchar(225)  default null comment '角色描述'"))
	private String description;

	@OneToMany(mappedBy = "role", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@OrderBy("priority ASC")
	@JsonIgnore
	private List<UserRole> userRoles = new ArrayList<UserRole>(0);

	@OneToMany(mappedBy = "role")
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private List<RolePermission> rolePermissions = new ArrayList<RolePermission>();

	/**
	 * 返回 name 的值
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name 的值
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 description 的值
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 description 的值
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 返回 userRoles 的值
	 * 
	 * @return userRoles
	 */
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	/**
	 * 设置 userRoles 的值
	 * 
	 * @param userRoles
	 */
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * 返回 rolePermissions 的值
	 * 
	 * @return rolePermissions
	 */
	public List<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	/**
	 * 设置 rolePermissions 的值
	 * 
	 * @param rolePermissions
	 */
	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

}
