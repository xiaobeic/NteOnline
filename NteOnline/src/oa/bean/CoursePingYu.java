package oa.bean;

public class CoursePingYu {

	private Integer id;
	private String pingYu;
	private Student student;
	private CoursePingJiaProject coursePingJiaProject;
	private Course course;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPingYu() {
		return pingYu;
	}

	public void setPingYu(String pingYu) {
		this.pingYu = pingYu;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CoursePingJiaProject getCoursePingJiaProject() {
		return coursePingJiaProject;
	}

	public void setCoursePingJiaProject(
			CoursePingJiaProject coursePingJiaProject) {
		this.coursePingJiaProject = coursePingJiaProject;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
