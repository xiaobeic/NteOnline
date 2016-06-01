package oa.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;


import oa.baseDao.BaseDaoImpl;
import oa.bean.Course;
import oa.bean.CoursePingJiaProject;
import oa.bean.CourseScore;
import oa.bean.Student;
import oa.bean.StudentPingJiaProject;
import oa.bean.StudentPingJiaScore;
import oa.bean.StudentPingJiaType;
import oa.bean.StudentPingYu;
import oa.bean.Teacher;
import oa.service.StudentService;
import oa.service.TeacherService;
@Service
public class TeacherServiceImpl extends BaseDaoImpl<Teacher> implements TeacherService{

	public String teacher_login(Teacher teacher) {
		String hql="from Teacher where username=? and password=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, teacher.getUsername());
		query.setString(1, teacher.getPassword());
		List<Teacher> list=query.list();
		if(query.list().size()==0)
			return null;
		else 
			return list.get(0).getTeacherName();
	}

	public List<StudentPingJiaProject> getStudentLinks() {
		return getSession().createQuery("from StudentPingJiaProject").list();
	}

	public List<StudentPingJiaType> getTypeById(int id) {
		
		return getSession().createQuery("from StudentPingJiaType spt where spt.studentPingJiaProject.id="+id).list();
	}

	public List<Student> getStudentsByClassId(int id) {
		return getSession().createQuery("from Student s where s.classes.id="+id).list();
	}

	public int getTeacherIdByName(String username) {
		List<Teacher>list=getSession().createQuery("from Teacher t where t.username='"+username+"'").list();
		return list.get(0).getId();
	}

	public List<Course> getCourseByTeacherId(int teacherId) {
		return getSession().createQuery("from Course c where c.teacher.id="+teacherId).list();
	}

	public List<Course> getCoursesByCourseId(int courseId) {
		return getSession().createQuery("from Course c where  c.id="+courseId).list();
	}

	public List<Double> getStudentEvaluteScore(int classId,int projectId) {
		//List list= getSession().createQuery("select sum(score) from CourseScore cs where cs.student.classes.id="+classId+" and cs.coursePingJiaProject.id="+projectId).list();
		//List<Double> list= getSession().createQuery("select sum(score) from CourseScore cs group by cs.student.classes.id,cs.coursePingJiaProject.id having cs.student.classes.id="+classId+" and cs.coursePingJiaProject.id="+projectId).list();
		List<Double> list= getSession().createQuery("select sum(score) from CourseScore cs group by cs.student,cs.student.classes.id,cs.coursePingJiaProject.id having cs.student.classes.id="+classId+" and cs.coursePingJiaProject.id="+projectId).list();
		return list;
	}

	public List<StudentPingJiaProject> getStudentCourseProject() {
		return getSession().createQuery("from StudentPingJiaProject").list();
	}

	public boolean isTruePwd(int teacherId,String password) {
		Object o=getSession().createQuery("from Teacher t where t.id="+teacherId+" and t.password='"+password+"'").uniqueResult();
		if(o!=null)
			return true;
		return false;
	}

	public int updateTeacherPwd(int teacherId,String password) {
		return getSession().createQuery("update Teacher t set t.password='"+password+"' where id="+teacherId).executeUpdate();
	}
	
	public List<StudentPingYu> getStudentPingYuByStudentId(int studentId) {
		return getSession().createQuery("from StudentPingYu spy where spy.student="+studentId).list();
	}

	public void saveEvaluteScore(Student student, Course course,
			StudentPingJiaProject studentPingJiaProject,
			StudentPingJiaType studentPingJiaType, double score) {
		StudentPingJiaScore spjs=new StudentPingJiaScore();
		spjs.setCourse(course);
		spjs.setScore(score);
		spjs.setStudent(student);
		spjs.setStudentPingJiaProject(studentPingJiaProject);
		spjs.setStudentPingJiaType(studentPingJiaType);
		getSession().save(spjs);
	}

	public void savePingyu(Student student, Course course, Teacher teacher,
			StudentPingJiaProject studentPingJiaProject, String pingYu) {
		StudentPingYu spy=new StudentPingYu();
		spy.setCourse(course);
		spy.setPingYu(pingYu);
		spy.setStudent(student);
		spy.setStudentPingJiaProject(studentPingJiaProject);
		spy.setTeacher(teacher);
		getSession().save(spy);
	}

	public List<StudentPingYu> getEvaluteInfo(int studentId,
			int courseId, int projectId) {
		return getSession().createQuery("from StudentPingYu sy where sy.student.id="+studentId+" and sy.course.id="+courseId+" and sy.studentPingJiaProject.id="+projectId).list();
	}

	public List<StudentPingJiaScore> getEvaluteTypeScore(int studentId,
			int courseId, int projectId) {
		return getSession().createQuery("from StudentPingJiaScore sps where sps.student.id="+studentId+" and sps.course.id="+courseId+" and sps.studentPingJiaProject.id="+projectId).list();
	}

	
}
