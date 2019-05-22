/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.main.CustomPermission.java
 * Class:			CustomPermission
 * Date:			2013-4-16
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          2.0.0
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "security_permission")
public class Permission {
	
	
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

	/**
	 * 操作
	 */
	public final static String PERMISSION_CREATE = "save";

	public final static String PERMISSION_READ = "view";

	public final static String PERMISSION_UPDATE = "edit";

	public final static String PERMISSION_DELETE = "delete";

	@Column(name = "name", columnDefinition = ("varchar(225)  default null comment '权限名称'"))
	private String name;

	@Column(name = "short_name", columnDefinition = ("varchar(225)  default null comment '权限缩写'"))
	private String shortName;

	@Column(name = "description", columnDefinition = ("varchar(255)  default null comment '权限描述'"))
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
	@JsonIgnore private Module module;

	@OneToMany(mappedBy = "permission", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE })
	private List<RolePermission> rolePermissiones = new ArrayList<RolePermission>();


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
	 * 返回 module 的值
	 * 
	 * @return module
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * 设置 module 的值
	 * 
	 * @param module
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	/**
	 * 返回 rolePermissiones 的值
	 * 
	 * @return rolePermissiones
	 */
	public List<RolePermission> getRolePermissiones() {
		return rolePermissiones;
	}

	/**
	 * 设置 rolePermissiones 的值
	 * 
	 * @param rolePermissiones
	 */
	public void setRolePermissiones(List<RolePermission> rolePermissiones) {
		this.rolePermissiones = rolePermissiones;
	}

	/**
	 * 返回 shortName 的值
	 * 
	 * @return shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * 设置 shortName 的值
	 * 
	 * @param shortName
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
