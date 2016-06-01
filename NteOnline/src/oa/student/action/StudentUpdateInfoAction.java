package oa.student.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.ui.Model;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import oa.baseDao.BaseAction;
import oa.bean.Admin;
import oa.bean.Student;
import oa.service.StudentService;

public class StudentUpdateInfoAction extends BaseAction<Student> implements
		ModelDriven<Student>, Preparable {
	public void prepare() throws Exception {
	}

	/**
	 * 
	 */

	public StudentService getStudentService() {
		return studentService;
	}

	private Student student;

	@Override
	public Student getModel() {
		return student;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void prepareUpdateInfo() {
		int id = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");
		student = studentService.personInfo(id);
	}

	public String updateInfo() {
		if (studentService.UpdateInfo(student))
			return "success";
		return "fail";
	}

}
