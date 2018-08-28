package com.framework.loippi.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.AclDao;
import com.framework.loippi.dao.RoleAclDao;
import com.framework.loippi.entity.Acl;
import com.framework.loippi.service.AclService;

/**
 * Service - ACL
 * 
 * @author LinkCity Team
 * @version 3.0
 */
@Service("aclServiceImpl")
public class AclServiceImpl extends GenericServiceImpl<Acl, Long> implements AclService {
	
	@Autowired
	private AclDao aclDao;
	
	@Autowired
	private RoleAclDao roleAclDao;
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(aclDao);
	}
	
	
	public Long delete(Long id){
		roleAclDao.deleteByAclId(id);
		return aclDao.delete(id);
	}


	public List<Acl> findRoots() {
		return aclDao.findRoots();
	}

	public List<Acl> findChildrens(Long id) {
		return aclDao.findChildrens(id);
	}


	@Override
	public List<Acl> findByParams(Object parameter) {
		// TODO Auto-generated method stub
		return aclDao.findByParams(parameter);
	}
	private static Set<Pattern> freeSet = new HashSet<Pattern>();
	
	
	public static void main(String[] args) {
		freeSet.add(Pattern.compile("/api/app/auth/*"));
		String str ="/api/app/auth/";
		for (Pattern pattern : freeSet) {
			System.out.println("pattern = " + pattern.matcher(str).find());

		}
	}
	
	
	
}
