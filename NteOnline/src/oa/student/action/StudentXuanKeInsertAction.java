package oa.student.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Student;
import oa.service.StudentService;

public class StudentXuanKeInsertAction extends BaseAction<Student> {
	@Resource
	private StudentService studentService;
	private String[] values;
	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}


	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public String XuanKe_Insert() {
		int studentId=(Integer) ActionContext.getContext().getSession().get("StudentId");
		HttpServletRequest request=ServletActionContext.getRequest();
		values=request.getParameterValues("xuanke");
		for(int i=0;i<values.length;i++){
			System.out.println(values[i]);
		}
		boolean temp=studentService.XuanKe_Insert(values,studentId);
		if(temp){
			return "success";
		}
		return "fail";
	}
}
