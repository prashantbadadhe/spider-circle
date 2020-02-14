package com.circle.dao;

import java.util.List;
import java.util.Map;

import com.circle.model.ClientModel;
import com.circle.model.DataVersionModel;

public interface DataVersionDao {

	public DataVersionModel getCurrentDataVersion(ClientModel client);

	public Map<String, Map<String, List<Object>>> getData(ClientModel client, long lastSyncAt);
	
	public List<Object> getInsertData(ClientModel client, long lastSyncAt,String tableName);
	
	public List<Object> getUpdateData(ClientModel client, long lastSyncAt,String tableName);
}
