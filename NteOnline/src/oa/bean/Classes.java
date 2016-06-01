package oa.bean;

import java.util.HashSet;
import java.util.Set;

public class Classes {

	private Integer id;
	private String className;
	private Set<Student> students = new HashSet<Student>();
	private Set<Leader> leaders = new HashSet<Leader>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Leader> getLeaders() {
		return leaders;
	}

	public void setLeaders(Set<Leader> leaders) {
		this.leaders = leaders;
	}

}
