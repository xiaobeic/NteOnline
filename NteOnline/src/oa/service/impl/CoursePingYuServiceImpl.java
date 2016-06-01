package oa.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.CoursePingYu;
import oa.service.CoursePingYuService;

@Service
public class CoursePingYuServiceImpl extends BaseDaoImpl<CoursePingYu>
		implements CoursePingYuService {
	// 插入学生对教学课程的评语
	public boolean InsertPingYu(String pingyu, int studentId, int courseId,
			int projectId) {
		System.out.println("评语");
		String sql = "insert into coursepingyu (pingYu,studentid,courseid,coursePingJiaProjectid)"
				+ " values(?,?,?,?)";
		Query query = getSession().createSQLQuery(sql);
		query.setString(0, pingyu);
		query.setInteger(1, studentId);
		query.setInteger(2, courseId);
		query.setInteger(3, projectId);
		int a = query.executeUpdate();
		if (a != 0)
			return true;
		return false;
	}

	public List<CoursePingYu> findPYByCSP(String courseid, String studentid,
			String projectid) {
		// TODO Auto-generated method stub
		return  getSession().createSQLQuery("select * from CoursePingYu c1 where  c1.courseid='"+courseid+"' and c1.studentid='"+studentid+"' and c1.coursePingJiaProjectid='"+projectid+"'").list();
	}

}
