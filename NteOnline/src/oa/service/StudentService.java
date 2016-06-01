package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.Course;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingYu;
import oa.bean.Student;

public interface StudentService extends BaseDao<Student> {

	public List<Student> getByStudentCode(String code);

	public List<Student> getByClassesid(String code);
	
	// 学生登陆
	public int Loginstudent(Student student);

	// 学生基本信息
	public Student personInfo(int id);

	// 学生密码修改
	public boolean ModifyPass(int id, String pass);

	// 判断原学生的密码是否正确
	public boolean PassIsRight(int id, String pass);

	// 学生基本信息修改是否成功
	public boolean UpdateInfo(Student student);

	// 学生评教信息查看(已选课程名和老师名)
	public List<Course> PingJiaoInfo();

	// 学生已经选择的课程且需要评教的课程
	public List<Integer> CourseIsSelected(int studentId);

	// 学生需要选课信息
	public List<Course> XuanKeInfo();

	// 没有选择的课程id
	public List<Integer> CourseNotSelected(int studentId);

	// 学生选课信息提交是否成功
	public boolean XuanKe_Insert(String[] courseid, int studentId);

	// 获取Course的评价环节
	public List<CoursePingJiaProject> getCourseLinkById();

	public List<Course> EvaluateInfo(int studentId);

	public List<CoursePingYu> getCroursePingYuLink(int studentId);
}
