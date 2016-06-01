package oa.bean;

import java.util.HashSet;
import java.util.Set;

//��ʦ��ѧ������
public class StudentPingJiaType {

	private Integer id;
	private String name;
	private double fenzhi;
	private StudentPingJiaProject studentPingJiaProject;

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


	public double getFenzhi() {
		return fenzhi;
	}

	public void setFenzhi(double fenzhi) {
		this.fenzhi = fenzhi;
	}

	public StudentPingJiaProject getStudentPingJiaProject() {
		return studentPingJiaProject;
	}

	public void setStudentPingJiaProject(
			StudentPingJiaProject studentPingJiaProject) {
		this.studentPingJiaProject = studentPingJiaProject;
	}

}
