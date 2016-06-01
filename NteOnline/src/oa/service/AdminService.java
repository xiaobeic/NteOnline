package oa.service;

import oa.baseDao.BaseDao;
import oa.bean.Admin;

public interface AdminService extends BaseDao<Admin> {

	Admin login(String username, String password);

}
