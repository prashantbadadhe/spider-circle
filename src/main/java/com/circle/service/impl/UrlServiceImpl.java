package com.circle.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.UrlDao;
import com.circle.model.UrlModel;
import com.circle.service.UrlService;

@Service("urlService")
@Transactional
public class UrlServiceImpl implements UrlService{
	
	@Autowired
	private UrlDao urlDao;

	@Override
	public List<UrlModel> findUrlsByGroupId(long urlGroupId) {
		return urlDao.findUrlsByGroupId(urlGroupId);
	}

	@Override
	public List<UrlModel> findChildUrls(long parentUrlId) throws SQLException {
		return findChildUrls(parentUrlId);
	}

	@Override
	public List<UrlModel> findUrlGroupsByRole(long roleId) {
		return findUrlGroupsByRole(roleId);
	}

	@Override
	public List<UrlModel> findMenusforGroup(long roleId, long urlGroupId) {
		return findMenusforGroup(roleId, urlGroupId);
	}

	@Override
	public List<Object> findUrlAndGroupUrlWithRoleAccess(long roleId) {
		return findUrlAndGroupUrlWithRoleAccess(roleId);
	}

	@Override
	public UrlModel findLaunchUrl(long roleId) {
		return findLaunchUrl(roleId);
	}


	 
}
