package com.framework.loippi.controller.admin;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.User;
import com.framework.loippi.service.RoleService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;

/**
 * Controlelr - 管理员
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Controller("adminUserController")
@RequestMapping("/admin/user")
public class UserController extends GenericController {

	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;
	
	/**
	 * 检查用户名是否存在
	 */
	@RequestMapping(value = "/check_username", method = RequestMethod.GET)
	public @ResponseBody boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		if (userService.usernameExists(username)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("roles", roleService.findAll());
		return "/admin/user/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(User user, RedirectAttributes redirectAttributes) {
		if (userService.usernameExists(user.getUserName())) {
			return ERROR_VIEW;
		}
		String password = user.getPassword();
		user.setPassword(DigestUtils.md5Hex(password));
		user.setIsLocked(false);
		user.setLoginFailureCount(0);
		user.setIsEnabled(Boolean.TRUE);
		user.setLockedDate(null);
		user.setLoginDate(null);
		user.setLoginIp(null);
		user.setCreateDate(new Date());
		userService.save(user);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("user", userService.find(id));
		return "/admin/user/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(User user, RedirectAttributes redirectAttributes) {
		String password = user.getPassword();
		if (StringUtils.isNotEmpty(password)) {
			user.setPassword(DigestUtils.md5Hex(password));
		}
		user.setUpdateDate(new Date());
		userService.update(user);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequiresPermissions("admin:system:user")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list( HttpServletRequest request,Pageable pageable, ModelMap model) {
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		pageable.setParameter(paramter);
		model.addAttribute("page", userService.findByPage(pageable));
		model.addAttribute("paramter", paramter);
		return "/admin/user/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Long[] ids) {
		for (Long id : ids) {
			userService.delete(id);
		}
		
		return SUCCESS_MESSAGE;
	}
}
