package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.DuDaoYuanToCoursePingJiaScore;
import oa.bean.DuDaoYuanToCoursePingYu;

public interface DuDaoYuanToCoursePingYuService extends
		BaseDao<DuDaoYuanToCoursePingYu> {

	List<DuDaoYuanToCoursePingYu> findByCourseProject(String course, String project);

}
