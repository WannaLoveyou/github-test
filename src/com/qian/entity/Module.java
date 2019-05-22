/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.main.Module.java
 * Class:			Module
 * Date:			2012-8-2
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="security_module")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","operations","roles","menus"})
public class Module  {
	
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

	@Column(name = "name", columnDefinition = ("varchar(225)  default null comment '模块名称'"))
	private String name;
	
	/**
	 * 模块的入口地址
	 */
	@Column(name = "url", columnDefinition = ("varchar(225)  default null comment '模块地址'"))
	private String url;
	
	@Column(name = "description", columnDefinition = ("varchar(225)  default null comment '模块描述'"))
	private String description;
	
	/**
	 * 标志符，用于授权名称（类似module:save）
	 */
	@Column(name = "sn", unique=true, updatable=false,columnDefinition = ("varchar(225)  default null comment '模块标志'"))
	private String sn;
	
	/**
	 * 模块的排序号,越小优先级越高
	 */
	@Column(name = "priority", columnDefinition = ("int(11)  default null comment '优先级'"))
	private Integer priority = 99;

	@ManyToOne
	@JoinColumn(name="parentId")
	private Module parent;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="parent")
	@OrderBy("priority ASC")
	private List<Module> children = new ArrayList<Module>();
	
	
	@OneToMany(mappedBy="module", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Permission> permissions = new ArrayList<Permission>();
	

	/**  
	 * 返回 name 的值   
	 * @return name  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置 name 的值  
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 返回 url 的值   
	 * @return url  
	 */
	public String getUrl() {
		return url;
	}

	/**  
	 * 设置 url 的值  
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**  
	 * 返回 description 的值   
	 * @return description  
	 */
	public String getDescription() {
		return description;
	}

	/**  
	 * 设置 description 的值  
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**  
	 * 返回 priority 的值   
	 * @return priority  
	 */
	public Integer getPriority() {
		return priority;
	}

	/**  
	 * 设置 priority 的值  
	 * @param priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**  
	 * 返回 parent 的值   
	 * @return parent  
	 */
	public Module getParent() {
		return parent;
	}

	/**  
	 * 设置 parent 的值  
	 * @param parent
	 */
	public void setParent(Module parent) {
		this.parent = parent;
	}

	/**  
	 * 返回 children 的值   
	 * @return children  
	 */
	public List<Module> getChildren() {
		return children;
	}

	/**  
	 * 设置 children 的值  
	 * @param children
	 */
	public void setChildren(List<Module> children) {
		this.children = children;
	}

	/**  
	 * 返回 sn 的值   
	 * @return sn  
	 */
	public String getSn() {
		return sn;
	}

	/**  
	 * 设置 sn 的值  
	 * @param sn
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	/**  
	 * 返回 permissions 的值   
	 * @return permissions  
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**  
	 * 设置 permissions 的值  
	 * @param permissions
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}


}
