package com.circle.dao;

import java.sql.SQLException;
import java.util.List;

import com.circle.model.UrlModel;

public interface UrlDao {

	public List<UrlModel> findUrlsByGroupId(long urlGroupId);

	public List<UrlModel> findChildUrls(long parentUrlId) throws SQLException;

	public List<UrlModel> findUrlGroupsByRole(long roleId);

	public List<UrlModel> findMenusforGroup(long roleId, long urlGroupId);

	public List<Object> findUrlAndGroupUrlWithRoleAccess(long roleId);

	UrlModel findLaunchUrl(long roleId);
}
