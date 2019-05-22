/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.service.UserRoleService.java
 * Class:			UserRoleService
 * Date:			2012-8-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.qian.service;

import java.util.List;

import com.qian.entity.UserRole;

public interface UserRoleService {

	/**
	 * 根据userId，找到已分配的角色。 描述
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRole> find(int userId);

}
