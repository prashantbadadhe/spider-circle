package com.circle.service;

import java.util.List;
import java.util.Map;

import com.circle.model.ClientModel;
import com.circle.model.DataVersionModel;

public interface DataVersionService {

	public DataVersionModel getCurrentDataVersion(ClientModel client);

	public Map<String, Map<String, List<Object>>> getData(ClientModel client, long lastSyncAt, List<String> tableNames);
}
