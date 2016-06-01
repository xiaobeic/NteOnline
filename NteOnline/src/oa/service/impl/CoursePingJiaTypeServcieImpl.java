package oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.CoursePingJiaType;
import oa.service.CoursePingJiaTypeService;

@Service
public class CoursePingJiaTypeServcieImpl extends
		BaseDaoImpl<CoursePingJiaType> implements CoursePingJiaTypeService {

	public List<CoursePingJiaType> CourseTypeInfo(int projectId) {
		String hql = "from CoursePingJiaType c where c.coursePingJiaProject.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, projectId);
		List<CoursePingJiaType> list_type = query.list();
		return list_type;
	}

}
