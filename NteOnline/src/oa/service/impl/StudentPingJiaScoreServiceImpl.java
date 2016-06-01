package oa.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.CourseScore;
import oa.bean.StudentPingJiaScore;
import oa.service.StudentPingJiaScoreService;

@Service
public class StudentPingJiaScoreServiceImpl extends
		BaseDaoImpl<StudentPingJiaScore> implements StudentPingJiaScoreService {

	public List<StudentPingJiaScore> findObjectsByCourse(String courseid) {
		return getSession().createQuery(
				"from StudentPingJiaScore where courseid=? order by courseid")
				.setString(0, courseid).list();
	}

	public List<StudentPingJiaScore> findObjectsByCourseCount(String courseid,Integer student) {

		return getSession()
				.createQuery(
						"select new StudentPingJiaScore(c.id,sum(coalesce(c.score,0)),c.studentPingJiaType,c.studentPingJiaProject,c.course ,c.student) from StudentPingJiaScore c where   c.student.id="+student+" and c.course.id=? group by c.studentPingJiaProject.id")
				.setString(0, courseid).list();
	}

	
	public List<StudentPingJiaScore> findObjectsByCourse(List<Integer> courseid) {
		return getSession()
				.createQuery(
						"from StudentPingJiaScore where courseid in(:alist) order by courseid")
				.setParameter("alist", courseid).list();
	}

	public List<StudentPingJiaScore> findObjectsByCourseCount(
			List<Integer> courseid) {
		return getSession()
				.createQuery(
						"select id as id,count(score) as score , courseid,studentid ,studentPingJiaTypeid from StudentPingJiaScore where  courseid in (:alist) group by studentPingJiaProjectid")
				.setParameter("alist", courseid).list();
	}

	// 学生查询个人成绩
	public List<StudentPingJiaScore> findSumGrid(int courseId) {
//		String	hql = "FROM StudentPingJiaScore c WHERE c.student.id in (SELECT id FROM Student WHERE id =?) group by c.studentPingJiaProject.id";
		String hql = "select new StudentPingJiaScore"
				+ "(c.id,sum(coalesce(c.score,0)),c.student,c.studentPingJiaType,"
				+ "c.studentPingJiaProject,c.course) from StudentPingJiaScore c "
				+ "where  c.student.id=? group by c.studentPingJiaProject.id";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, courseId);
		List<StudentPingJiaScore> listScore = (List<StudentPingJiaScore>) query
				.list();
		return listScore;
	}

	public List<StudentPingJiaScore> findGridInfo(int studentId) {
		String hql = "FROM StudentPingJiaScore c WHERE c.student.id in (SELECT id FROM Student WHERE id =?) order by c.studentPingJiaProject.id asc";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, studentId);
		List<StudentPingJiaScore> listScore = (List<StudentPingJiaScore>) query
				.list();
		return listScore;
	}
	public List<StudentPingJiaScore> findbyCourseAndStudent(String courseid,String studentid,String projectid) {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("select * from StudentPingJiaScore c left join studentpingjiatype t on c.studentpingjiatypeid=t.id where c.courseid='"+courseid+"' and c.studentid='"+studentid+"' and c.studentPingJiaProjectid='"+projectid+"'").list();
	}
}
