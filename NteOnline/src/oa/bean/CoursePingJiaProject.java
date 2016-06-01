package oa.bean;

import java.util.HashSet;
import java.util.Set;

//学生对课程||教师评价
public class CoursePingJiaProject {

	private Integer id;
	private String name;
	private Set<CoursePingJiaType> coursePingJiaTypes = new HashSet<CoursePingJiaType>();

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

	public Set<CoursePingJiaType> getCoursePingJiaTypes() {
		return coursePingJiaTypes;
	}

	public void setCoursePingJiaTypes(Set<CoursePingJiaType> coursePingJiaTypes) {
		this.coursePingJiaTypes = coursePingJiaTypes;
	}

}
