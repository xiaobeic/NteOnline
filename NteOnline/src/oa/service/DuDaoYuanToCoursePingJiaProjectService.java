package oa.service;

import java.util.List;
import java.util.Set;

import oa.baseDao.BaseDao;
import oa.bean.DuDaoYuanToCoursePingJiaProject;
import oa.bean.DuDaoYuanToCoursePingJiaType;

public interface DuDaoYuanToCoursePingJiaProjectService extends
		BaseDao<DuDaoYuanToCoursePingJiaProject> {

	List<DuDaoYuanToCoursePingJiaType> getByIdTotype(Integer id);

}
