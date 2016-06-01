package oa.student.action;

import oa.baseDao.BaseAction;
import oa.bean.Student;
import oa.service.StudentService;

public class StudentIndexAction extends BaseAction<Student> {

	public String top() {

		return "top";
	}

	public String left() {

		return "left";
	}

	public String right() {

		return "right";
	}

	public String main() {
		return "main";
	}
}
