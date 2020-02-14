package com.circle.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.DataVersionDao;
import com.circle.model.ClientModel;
import com.circle.model.DataVersionModel;
import com.circle.service.DataVersionService;
import com.circle.util.CommonConstant;

@Service("dataVersionService")
@Transactional
public class DataVersionServiceImpl implements DataVersionService {

	@Autowired
	private DataVersionDao dataVersionDao;

	@Override
	public DataVersionModel getCurrentDataVersion(ClientModel client) {
		return dataVersionDao.getCurrentDataVersion(client);
	}

	@Override
	public Map<String, Map<String, List<Object>>> getData(ClientModel client, long lastSyncAt,
			List<String> tableNames) {

		Map<String, Map<String, List<Object>>> data = new HashMap<String, Map<String, List<Object>>>();

		Map<String, List<Object>> tableData = new HashMap<String, List<Object>>();

		for (String tableName : tableNames) {
			tableData.put(CommonConstant.KEY_INSERTED_DATA,
					dataVersionDao.getInsertData(client, lastSyncAt, tableName));
			tableData.put(CommonConstant.KEY_UPDATED_DATA, dataVersionDao.getUpdateData(client, lastSyncAt, tableName));
			data.put(tableName, tableData);
		}
		return data;
	}

}
