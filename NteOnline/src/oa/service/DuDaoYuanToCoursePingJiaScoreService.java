package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.DuDaoYuanToCoursePingJiaScore;

public interface DuDaoYuanToCoursePingJiaScoreService extends
		BaseDao<DuDaoYuanToCoursePingJiaScore> {

	List<DuDaoYuanToCoursePingJiaScore> findByCourseProject(String course,
			String project);

}
