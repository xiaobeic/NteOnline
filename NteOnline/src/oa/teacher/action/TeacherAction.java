package oa.teacher.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;


import oa.baseDao.BaseAction;
import oa.bean.Classes;
import oa.bean.Course;
import oa.bean.Teacher;

@Controller
@Scope("prototype")
public class TeacherAction extends BaseAction<Teacher>{

	private static final long serialVersionUID = 2894430639666572584L;

	public String teacherInfo() {

		return "teacherInfo";
	}
	public String class_evaluate(){
		int teacherId = 0;
		List<Course>list=null;
		if(ActionContext.getContext().getSession().get("TeacherId")!=null)
			teacherId=(Integer) ActionContext.getContext().getSession().get("TeacherId");
		if(teacherId!=0)
			list=teacherService.getCourseByTeacherId(teacherId);
		if(list!=null){
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("CourseList", list);
		}
		return "class_evaluate";
	}
	public String evaluate_comment(){
		int teacherId = 0;
		List<Course>list=null;
		if(ActionContext.getContext().getSession().get("TeacherId")!=null)
			teacherId=(Integer) ActionContext.getContext().getSession().get("TeacherId");
		if(teacherId!=0)
			list=teacherService.getCourseByTeacherId(teacherId);
		if(list!=null){
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("CourseList", list);
			request.setAttribute("ClassList", new ArrayList<Classes>());
		}
		return "evaluate_comment";
	}
}




