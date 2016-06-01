package oa.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.CoursePingYu;
import oa.bean.StudentPingYu;
import oa.service.StudentPingYuService;

@Service
public class StudentPingYuServiceImpl extends BaseDaoImpl<StudentPingYu>
		implements StudentPingYuService {

	public List<StudentPingYu> findStuInfo(int studentId) {
		String hql = "FROM StudentPingYu c WHERE c.student.id in (SELECT id FROM Student WHERE id =?)";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, studentId);
		List<StudentPingYu> listScore = (List<StudentPingYu>) query.list();
		return listScore;
	}
	public List<StudentPingYu> findPYByCSP(String courseid, String studentid,
			String projectid) {
		// TODO Auto-generated method stub
		return  getSession().createSQLQuery("select * from StudentPingYu c1 where  c1.courseid='"+courseid+"' and c1.studentid='"+studentid+"' and c1.studentPingJiaProjectid='"+projectid+"'").list();
	}
}
