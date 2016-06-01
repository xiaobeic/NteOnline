package oa.student.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Course;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingJiaType;
import oa.bean.Student;
import oa.service.CoursePingJiaProjectService;
import oa.service.CoursePingJiaTypeService;
import oa.service.CoursePingYuService;
import oa.service.StudentService;

public class CoursePingJiaoAction extends BaseAction<Student>
		implements RequestAware {
	private Map<String,Object> map;
	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setRequest(Map<String, Object> arg0) {
		this.map=arg0;
	}
	//学生只能对已选课程，进行评教
	public String PingJiaoInfo_Show(){
		List<Course> list=studentService.PingJiaoInfo();
		int studentId=(Integer) ActionContext.getContext().getSession().get("StudentId");
		List<Integer> course_IsSelected=studentService.CourseIsSelected(studentId);
		map.put("Course_IsSelected",course_IsSelected);
		map.put("PingJiao_info", list);
		return "show";
	}

}
