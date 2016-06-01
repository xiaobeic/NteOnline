package oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.DuDaoYuanToCoursePingJiaScore;
import oa.service.DuDaoYuanToCoursePingJiaScoreService;

@Service
public class DuDaoYuanToCoursePingJiaScoreServiceImpl extends
		BaseDaoImpl<DuDaoYuanToCoursePingJiaScore> implements
		DuDaoYuanToCoursePingJiaScoreService {

	public List<DuDaoYuanToCoursePingJiaScore> findByCourseProject(
			String course, String project) {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery(
				" select * from DuDaoYuanToCoursePingJiaScore s left join dudaoyuantocoursepingjiatype t on s.dudaoyuantocoursepingjiatypeid=t.id where s.courseid='" + course
						+ "' and s.duDaoYuanToCoursePingJiaProjectid='"
						+ project + "'").list();
	}

}
