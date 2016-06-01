package oa.student.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Course;
import oa.bean.Student;
import oa.service.StudentService;

public class StudentXuanKeInfoAction extends BaseAction<Student> implements RequestAware{
	private Map<String,Object> map;
	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	//显示选课信息
	public String XuanKe_Info(){
		List<Course> list=studentService.XuanKeInfo();
		int studentId=(Integer) ActionContext.getContext().getSession().get("StudentId");
		List<Integer> course_NotSelected=studentService.CourseNotSelected(studentId);
		map.put("Course_NotSelected",course_NotSelected);
		map.put("XuanKe_info", list);
		return "xuanke";
	}

	public void setRequest(Map<String, Object> arg0) {
		this.map=arg0;
	}

}
