package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.Course;
import oa.bean.CoursePingJiaProject;
import oa.bean.CourseScore;
import oa.bean.Student;
import oa.bean.StudentPingJiaProject;
import oa.bean.StudentPingJiaScore;
import oa.bean.StudentPingJiaType;
import oa.bean.StudentPingYu;
import oa.bean.Teacher;

public interface TeacherService extends BaseDao<Teacher>{
	/**
	 * 获取登录信息
	 * @param teacher
	 * @return
	 */
	public String teacher_login(Teacher teacher);
	/**
	 * 根据teacherId验证密码是否正确
	 * @param teacherId
	 * @return
	 */
	public boolean isTruePwd(int teacherId,String password);
	/**
	 * 根据指定教师Id重置教师密码
	 * @param password
	 * @return
	 */
	public int updateTeacherPwd(int teacherId,String password);
	/**
	 * 通过username找id
	 * @return
	 */
	public int getTeacherIdByName(String username);
	/**
	 * 通过教师id获取教师的课程信息
	 * @return
	 */
	public List<Course> getCourseByTeacherId(int teacherId);
	/**
	 * 通过课程id获取学生
	 * @param id
	 * @return
	 */
	public List<Student> getStudentsByClassId(int id);
	/**
	 * 获取学生的评价环节
	 * @return
	 */
	public List<StudentPingJiaProject> getStudentLinks();
	/**
	 * 通过环节id获取环节的方面type
	 * @return
	 */
	public List<StudentPingJiaType> getTypeById(int id);
	/**
	 * 通过课程id获取其指定的课程信息
	 * @param courseId
	 * @return
	 */
	public List<Course> getCoursesByCourseId(int courseId);
	/**
	 * 通过班级号classId获取学生的评价分值
	 * @param classId
	 * @return
	 */
	public List<Double>getStudentEvaluteScore(int classId,int projectId);
	
	/**
	 * 获取学生的课程的环节信息
	 * @return
	 */
	public List<StudentPingJiaProject>getStudentCourseProject();
	/**
	 * 通过学生id获取评语
	 * @return
	 */
	public List<StudentPingYu> getStudentPingYuByStudentId(int studentId);
	/**
	 * 保存评价信息分数
	 * @param student
	 * @param course
	 * @param studentPingJiaProject
	 * @param studentPingJiaType
	 * @param score
	 */
	public void saveEvaluteScore(Student student,Course course,StudentPingJiaProject studentPingJiaProject,StudentPingJiaType studentPingJiaType,double score);
	/**
	 * 保存评语
	 * @param student
	 * @param course
	 * @param teacher
	 * @param studentPingJiaProject
	 * @param pingYu
	 */
	public void savePingyu(Student student,Course course,Teacher teacher,StudentPingJiaProject studentPingJiaProject,String pingYu);
	/**
	 * 获取评语信息
	 * @param studentId
	 * @param courseId
	 * @param projectId
	 * @return
	 */
	public List<StudentPingYu>getEvaluteInfo(int studentId,int courseId,int projectId);
	/**
	 * 获取评价的分值信息
	 * @param studentId
	 * @param courseId
	 * @param projectId
	 * @return
	 */
	public List<StudentPingJiaScore>getEvaluteTypeScore(int studentId,int courseId,int projectId);
}
