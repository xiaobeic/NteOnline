package oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.DuDaoYuanToCoursePingJiaProject;
import oa.bean.DuDaoYuanToCoursePingJiaType;
import oa.service.DuDaoYuanToCoursePingJiaProjectService;

@Service
public class DuDaoYuanToCoursePingJiaProjectServiceImpl extends
		BaseDaoImpl<DuDaoYuanToCoursePingJiaProject> implements
		DuDaoYuanToCoursePingJiaProjectService {

	public List<DuDaoYuanToCoursePingJiaType> getByIdTotype(Integer id) {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("select * from dudaoyuantocoursepingjiatype t where t.dudaoyuantocoursepingjiaprojectid="+id+"").list();
	}

}
