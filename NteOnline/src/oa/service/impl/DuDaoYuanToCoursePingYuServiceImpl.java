package oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.DuDaoYuanToCoursePingJiaScore;
import oa.bean.DuDaoYuanToCoursePingYu;
import oa.service.DuDaoYuanToCoursePingYuService;

@Service
public class DuDaoYuanToCoursePingYuServiceImpl extends
		BaseDaoImpl<DuDaoYuanToCoursePingYu> implements
		DuDaoYuanToCoursePingYuService {

	public List<DuDaoYuanToCoursePingYu> findByCourseProject(String course,
			String project) {
		// TODO Auto-generated method stub
		return  getSession().createSQLQuery(
				"select * from DuDaoYuanToCoursePingYu where courseid='" + course
						+ "' and duDaoYuanToCoursePingJiaProjectid='"
						+ project + "'").list();
	}

}
