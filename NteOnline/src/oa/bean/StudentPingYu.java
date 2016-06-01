package oa.bean;

//教师对学生评价
public class StudentPingYu {

	private Integer id;
	private Student student;
	private String pingYu;
	private Teacher teacher;
	private Course course;
	private StudentPingJiaProject studentPingJiaProject;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getPingYu() {
		return pingYu;
	}

	public void setPingYu(String pingYu) {
		this.pingYu = pingYu;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public StudentPingJiaProject getStudentPingJiaProject() {
		return studentPingJiaProject;
	}

	public void setStudentPingJiaProject(
			StudentPingJiaProject studentPingJiaProject) {
		this.studentPingJiaProject = studentPingJiaProject;
	}

}
