package oa.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.CourseScore;
import oa.bean.StudentPingJiaScore;
import oa.service.CourseScoreService;

@Service
public class CourseScoreServiceImpl extends BaseDaoImpl<CourseScore> implements
		CourseScoreService {

	public List<CourseScore> findObjectsByCourse(String courseid) {
		return getSession().createQuery(
				"from CourseScore c where c.course.id=? order by c.course.id")
				.setString(0, courseid).list();
	}

	public List<CourseScore> findObjectsByCourseCount(String courseid,Integer student) {

		return getSession()
				.createQuery(
						"select new CourseScore(c.id,sum(coalesce(c.score,0)),c.course ,c.student,c.coursePingJiaType,c.coursePingJiaProject) from CourseScore c where  c.student.id="+student+" and c.course.id=? group by c.coursePingJiaProject.id")
				.setString(0, courseid).list();
	}
	public List<CourseScore> findbyCourseAndStudent(String courseid,String studentid,String projectid) {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("select *,t.score as scoreMax from CourseScore c left join coursepingjiatype t on (c.coursepingjiatypeid=t.id) where c.courseid='"+courseid+"' and c.studentid='"+studentid+"' and c.coursePingJiaProjectid='"+projectid+"'").list();
	}

	public boolean InsertScore(double score, int studentId, int typeId,
			int courseId, int projectId) {
		System.out.println("成绩");
		String sql = "insert into CourseScore "
				+ "(score,courseid,studentid,coursePingJiaTypeid,coursePingJiaProjectid) "
				+ "values(?,?,?,?,?)";
		Query query = getSession().createSQLQuery(sql);
		query.setDouble(0, score);
		query.setInteger(1, courseId);
		query.setInteger(2, studentId);
		query.setInteger(3, typeId);
		query.setInteger(4, projectId);
		int a = query.executeUpdate();
		System.out.println("成绩程序完成");
		if (a != 0) {
			return true;
		}
		return false;
	}

	// 已经评价课程信息
	public List<CourseScore> EvaluateFinish() {
		String hql = "from CourseScore group by coursePingJiaProject";
		Query query = getSession().createQuery(hql);
		List<CourseScore> list = query.list();
		return list;
	}
}
