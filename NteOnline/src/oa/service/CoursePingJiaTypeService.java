package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingJiaType;

public interface CoursePingJiaTypeService extends BaseDao<CoursePingJiaType> {
	// 查询每个项目里面，每个类别的信息
	public List<CoursePingJiaType> CourseTypeInfo(int projectId);
}
