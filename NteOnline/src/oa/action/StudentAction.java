package oa.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.baseDao.BaseAction;
import oa.bean.Classes;
import oa.bean.Course;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingJiaType;
import oa.bean.CoursePingYu;
import oa.bean.CourseScore;
import oa.bean.Student;
import oa.service.StudentService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.HqlHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> implements RequestAware {
	private Map<String, Object> map;

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public String grid() {
		return "grid";
	}

	public String evaluate_comment() {
		int studentId = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");
		List<Course> list = studentService.EvaluateInfo(studentId);
		if (list != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("courseList", list);
		}
		List<CoursePingYu> list_pingyu = studentService
				.getCroursePingYuLink(studentId);
		List<CoursePingJiaProject> list_project = studentService
				.getCourseLinkById();
		if (list_pingyu != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("pingyuList", list_pingyu);
			request.setAttribute("projectlist", list_project);
		}
		return "evaluate_comment";
	}

	public String selectedCourse() {
		List<Course> list = studentService.PingJiaoInfo();
		int studentId = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");
		List<Integer> course_IsSelected = studentService
				.CourseIsSelected(studentId);
		map.put("Course_IsSelected", course_IsSelected);
		map.put("PingJiao_info", list);
		return "selectedCourse";
	}

	public String personinfo() {
		Integer id = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");
		map.put("student_info", studentService.personInfo(id));
		return "personinfo";
	}

	public String modify() {
		return "modify";
	}

	public void setRequest(Map<String, Object> m) {
		this.map = m;
	}

}
