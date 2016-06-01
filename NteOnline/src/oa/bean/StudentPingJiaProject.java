package oa.bean;

import java.util.HashSet;
import java.util.Set;

//教师对学生评价
public class StudentPingJiaProject {

	private Integer id;
	private String name;
	private Set<StudentPingJiaType> studentPingJiaTypes = new HashSet<StudentPingJiaType>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<StudentPingJiaType> getStudentPingJiaTypes() {
		return studentPingJiaTypes;
	}

	public void setStudentPingJiaTypes(
			Set<StudentPingJiaType> studentPingJiaTypes) {
		this.studentPingJiaTypes = studentPingJiaTypes;
	}

}
