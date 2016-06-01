package oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.CoursePingJiaProject;
import oa.service.CoursePingJiaProjectService;

@Service
public class CoursePingJiaProjectServiceImpl extends
		BaseDaoImpl<CoursePingJiaProject> implements
		CoursePingJiaProjectService {

	public List<CoursePingJiaProject> SelectedProjectId() {
		String hql = "from CoursePingJiaProject";
		Query query = getSession().createQuery(hql);
		List<CoursePingJiaProject> list = query.list();
		return list;
	}

	public List<CoursePingJiaProject> NotPingJiaProject() {
		String hql = "from CoursePingJiaProject c where c.id not in (select c.coursePingJiaProject.id from CourseScore c)";
		Query query = getSession().createQuery(hql);
		List<CoursePingJiaProject> list = query.list();
		return list;
	}

	public List<CoursePingJiaProject> IsPingJiaoProject() {
		String hql = "from CoursePingJiaProject c where c.id in (select c.coursePingJiaProject.id from CourseScore c)";
		Query query = getSession().createQuery(hql);
		List<CoursePingJiaProject> list = query.list();
		return list;
	}

}
