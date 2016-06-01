package oa.service.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oa.baseDao.BaseDaoImpl;
import oa.bean.Course;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingYu;
import oa.bean.Student;
import oa.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl extends BaseDaoImpl<Student> implements
		StudentService {

	public List<Student> getByStudentCode(String code) {
		// TODO Auto-generated method stub

		return getSession()
				.createQuery("from Student where studentCode like ?")
				.setString(0, "%" + code + "%").list();
	}

	public List<Student> getByClassesid(String code) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Student where classesid = ?")
				.setString(0, code).list();
	}

	public int Loginstudent(Student student) {
		String hql = "from Student where username=? and password=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, student.getUsername());
		query.setString(1, student.getPassword());
		List<Student> list = query.list();
		if (list.size() != 0) {
			return list.get(0).getId();
		}
		return 0;
	}

	// 更新学生个人信息
	public boolean UpdateInfo(Student student) {
		update(student);
		return true;
	}

	// 显示
	public Student personInfo(int id) {
		String hql = "select s from Student s left join s.classes  where s.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		Student stu = (Student) query.uniqueResult();
		if (stu != null) {
			return stu;
		}
		return null;
	}

	// 修改密码
	public boolean ModifyPass(int student, String password) {
		Query query;
		String hql = "update Student set password=? where id=?";
		query = getSession().createQuery(hql);
		query.setString(0, password);
		query.setInteger(1, student);
		int code = query.executeUpdate();
		if (code != 0) {
			System.out.println(code);
			return true;
		}
		return false;
	}

	// 判断原密码是否正确
	public boolean PassIsRight(int id, String oldpass) {
		Query query;
		Student stu = null;
		String search = "from Student where password=? and id=?";
		query = getSession().createQuery(search);
		query.setString(0, oldpass);
		query.setInteger(1, id);
		stu = (Student) query.uniqueResult();
		if (stu != null) {
			return true;
		}
		return false;
	}

	// 显示学生评教信息(需要评教的课程名和老师名)
	public List<Course> PingJiaoInfo() {
		String hql = "select c from" + " Course c left join c.teacher t ";
		Query query = getSession().createQuery(hql);
		List<Course> list = query.list();
		return list;
	}

	// 显示学生选课信息
	public List<Course> XuanKeInfo() {
		String hql = "select c from" + " Course c left join c.teacher t ";
		Query query = getSession().createQuery(hql);
		List<Course> list = query.list();
		return list;
	}

	// 提交学生选课信息
	public boolean XuanKe_Insert(String[] courseId, int studentId) {
		String hql = "insert into student_course  (courseid, studentid) values (?,?)";
		boolean finish = false;
		for (int i = 0; i < courseId.length; i++) {
			Query query = getSession().createSQLQuery(hql);
			query.setInteger(0, Integer.parseInt(courseId[i]));
			query.setInteger(1, studentId);
			query.executeUpdate();
			finish = true;
		}
		if (finish)
			return true;
		return false;
	}

	// 没有选择课程的id
	public List<Integer> CourseNotSelected(int studentId) {
		String sql = "SELECT "
				+ " id FROM	course "
				+ "WHERE id	not in(	SELECT	courseid from student_course where studentid=?)";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger(0, studentId);
		List<Integer> list = query.list();
		Collections.sort(list);
		return list;
	}

	// 显示学生评教信息(需要评教的课程名和老师名)
	public List<Integer> CourseIsSelected(int studentId) {
		String sql = "SELECT "
				+ " id FROM	course "
				+ "WHERE id	 in(	SELECT	courseid from student_course where studentid=?)";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger(0, studentId);
		List<Integer> list = query.list();
		Collections.sort(list);
		return list;
	}

	// 获得课程环节ID
	public List<CoursePingJiaProject> getCourseLinkById() {
		return getSession().createQuery("from CoursePingJiaProject").list();
	}

	// 根据评语来判断课程的环节ID是否被评价过
	public List<CoursePingYu> getCroursePingYuLink(int studentId) {
		String hql = "select c from CoursePingYu c where c.student.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, studentId);
		List<CoursePingYu> list = query.list();
		return list;
	}

	// 评教课程信息
	public List<Course> EvaluateInfo(int studentId) {
		String hql = "select c from Course c inner join fetch c.students s where s.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, studentId);
		List<Course> list = query.list();
		return list;
	}
}
