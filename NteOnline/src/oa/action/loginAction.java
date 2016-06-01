package oa.action;

import javax.annotation.Resource;

import oa.baseDao.BaseAction;
import oa.bean.Admin;
import oa.bean.Course;
import oa.bean.Leader;
import oa.bean.Student;
import oa.bean.Teacher;
import oa.service.TeacherService;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class loginAction extends BaseAction<Course> {

	public String loginUI() {

		return "login";
	}

	/**
	 * roletype 1代表学生 2代表教师 3代表督导员 4代表管理员
	 * 
	 * @return
	 */
	public String login() {
		if (roletype.equals("1")) {
			Student student=new Student();
			student.setUsername(username);
			student.setPassword(password);
			int  id=studentService.Loginstudent(student);
			if(id!=0){
				ActionContext.getContext().getSession().put("StudentId", id);
				Student student2=studentService.getById(id);
				ActionContext.getContext().getSession().put("name", student2.getName());
			return "studentMain";
			}else
			{
				return "login";
			}
		}
		if (roletype.equals("2")) {
			Teacher teacher=new Teacher();
			teacher.setUsername(username);
			teacher.setPassword(password);
			String teacherName=teacherService.teacher_login(teacher);
			if (teacherName!=null) {
				int teacherId=teacherService.getTeacherIdByName(username);
				ActionContext.getContext().getSession().put("TeacherId", teacherId);
				ActionContext.getContext().getSession().put("TeacherName", teacherName);
				return "teacherMain";
			} else {
				return "login";
			}
		}
		if (roletype.equals("3")) {

			Leader leader = new Leader();
			leader.setUsername(username);
			leader.setPassword(password);
			Leader leader2 = leaderService.login(leader);
			if (leader2 == null)
				return "login";
			ActionContext.getContext().getSession().put("leaderUser", leader2);
			ServletActionContext.getRequest().getSession().setAttribute("user", leader2);
			return "leaderMain";
		}
		if (roletype.equals("4")) {

			Admin admin=new Admin();
			admin.setUsername(username);
			admin.setPassword(password);
			Admin admin2=adminService.login(username,password);
			if(admin2==null)
			{
				
				return "login";
			}
			ActionContext.getContext().getSession().put("user", admin2);
			ServletActionContext.getRequest().getSession().setAttribute("user", admin2);
			return "adminMain";
		}
		return "loginUI";
	}

	// ===========================================================================================================
	private String roletype;
	private String username;
	private String password;
	@Resource
	protected TeacherService teacherService;

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
