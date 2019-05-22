package com.qian.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.qian.entity.Permission;
import com.qian.entity.Role;
import com.qian.entity.RolePermission;
import com.qian.entity.User;
import com.qian.entity.UserRole;
import com.qian.service.UserRoleService;
import com.qian.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private UserService userSerivce;

	@Autowired
	private UserRoleService userRoleSerivce;

	public ShiroDbRealm() {
		super();
	}
	

	/**
	 * 认证回调函数, 登录时调用.
	 */
	// TODO 对认证进行缓存处理
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		User user = userSerivce.findByUserName(upToken.getUsername());
		if (user == null) {
			throw new UnknownAccountException("No account found for user ["
					+ user.getUsername() + "]");

		}

		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Collection<?> collection = principals.fromRealm(getName());
		if (CollectionUtils.isEmpty(collection)) {
			return null;
		}
		User user = (User) principals.fromRealm(getName()).iterator().next();
		List<UserRole> userRoles = userRoleSerivce.find(user.getId());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(makePermissions(userRoles, user));
		return info;
	}

	private Collection<String> makePermissions(List<UserRole> userRoles,
			User user) {

		List<Role> roles = new ArrayList<Role>(0);
		for (UserRole userRole : userRoles) {
			roles.add(userRole.getRole());
		}

		Collection<String> stringPermissions = new ArrayList<String>();
		for (Role role : roles) {
			List<RolePermission> rolePermissions = role.getRolePermissions();
			for (RolePermission rolePermission : rolePermissions) {
				Permission permission = rolePermission.getPermission();
				if (permission.getModule() != null) {// 避免脏数据
					stringPermissions.add(permission.getModule().getSn() + ":"
							+ permission.getShortName());
				}
			}
		}

		return stringPermissions;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {

		private static final long serialVersionUID = -1748602382963711884L;
		private Long id;
		private String loginName;
		private String ipAddress;

		public ShiroUser() {

		}

		/**
		 * 构造函数
		 * 
		 * @param id
		 * @param loginName
		 * @param email
		 * @param createTime
		 * @param status
		 */
		public ShiroUser(Long id, String loginName) {
			this.id = id;
			this.loginName = loginName;
		}

		/**
		 * 返回 id 的值
		 * 
		 * @return id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * 返回 loginName 的值
		 * 
		 * @return loginName
		 */
		public String getLoginName() {
			return loginName;
		}

		public String getIpAddress() {
			return ipAddress;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		/**
		 * 返回 user 的值
		 * 
		 * @return user
		 */		
		
		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}
	}

}
