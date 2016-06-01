package oa.student.action;

import java.util.Map;

import javax.annotation.Resource;

import oa.baseDao.BaseAction;
import oa.bean.Admin;
import oa.bean.Student;
import oa.service.StudentService;

import com.opensymphony.xwork2.ActionContext;

public class StudentPassModifyAction extends BaseAction<Student> {
	@Resource
	private StudentService studentService;
	private String oldpass;

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	private String password1;
	private String password2;

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public String modify() {
		int id = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");
		if (studentService.PassIsRight(id, oldpass))
			if (password1.equals(password2)) {
				boolean code = studentService.ModifyPass(id, password1);
				if (code)
					return "modifysuccess";
			}
		return "fail";
	}
}
