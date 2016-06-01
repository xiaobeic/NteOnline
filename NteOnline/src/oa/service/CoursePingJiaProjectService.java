package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.CoursePingJiaProject;
import oa.student.action.CoursePingJiaoProjectAction;

public interface CoursePingJiaProjectService extends
		BaseDao<CoursePingJiaProject> {
	// 显示评选的是哪个课程的四个项目
	public List<CoursePingJiaProject> SelectedProjectId();

	public List<CoursePingJiaProject> NotPingJiaProject();

	

}
