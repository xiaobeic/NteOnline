package oa.service.impl;

import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.Admin;
import oa.service.AdminService;

@Service
public class AdminServiceImpl extends BaseDaoImpl<Admin> implements
		AdminService {

	public Admin login(String username, String password) {
		// TODO Auto-generated method stub
		return (Admin) getSession().createQuery("from Admin a where a.username='"+username+"' and password='"+password+"'").uniqueResult();
	}

}
