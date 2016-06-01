package oa.admin.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Admin;
import oa.bean.Course;
import oa.bean.Leader;
import util.HqlHelper;

@Controller
@Scope("prototype")
public class DuDaoCourseManagerAction extends BaseAction<Course> {

	// 课程列表
	public String list() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("courseid") != null)
			session.remove("courseid");
		new HqlHelper(Course.class, "course").addWhereCondition(
				getModel().getCourseName() != null
						&& !getModel().getCourseName().equals(""),
				"courseName like ?", "%" + getModel().getCourseName() + "%")
				.addWhereCondition(
						teacherName != null && !teacherName.equals(""),
						"teacher.teacherName like ?", "%" + teacherName + "%")
				.preparePageBeanForStruts2(1, 10, courseService);
		return "list";
	}

	public String updatepasswordUI()
	{
		
		if(ServletActionContext.getRequest().getSession().getAttribute("user")==null)
		{
			return "login";
		}
		return "updatepassword";
	}
	public String updatepassword()
	{
		
		Admin admin=(Admin) ServletActionContext.getRequest().getSession().getAttribute("user");
		admin.setPassword(password);
		adminService.update(admin);
		return "login";
	}
	// 课程对应督导员列表
	public String dudaocourselist() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("courseid") == null)
			session.put("courseid", getModel().getId());
		ActionContext.getContext().put(
				"dudaocourse",
				courseService.getById(
						Integer.parseInt(String
								.valueOf(session.get("courseid"))))
						.getLeaders());
		return "dudaocourselist";
	}

	// 为课程增加督导员
	public String adddudaoUI() {

		List<Leader> leaders = leaderService.findAll();
		ActionContext.getContext().put("leaderlist", leaders);
		return "adddudao";
	}

	// 为课程增加督导员
	public String adddudao() {

		Map<String, Object> session = ActionContext.getContext().getSession();
		Leader leader = leaderService.getById(getModel().getId());
		Course course = courseService.getById(Integer.parseInt(String
				.valueOf(session.get("courseid"))));
		course.getLeaders().add(leader);
		courseService.update(course);
		return "todudaocourselist";
	}

	public String deletedudao() {

		Map<String, Object> session = ActionContext.getContext().getSession();

		Course course = courseService.getById(Integer.parseInt(String
				.valueOf(session.get("courseid"))));
		Leader leader = leaderService.getById(getModel().getId());
		course.getLeaders().remove(leader);
		courseService.update(course);
		return "todudaocourselist";
	}

	private String teacherName;
	
	private String password;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
