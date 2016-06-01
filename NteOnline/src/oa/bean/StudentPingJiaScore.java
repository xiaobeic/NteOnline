package oa.bean;

//��ʦ��ѧ������
public class StudentPingJiaScore {

	private Integer id;
	private double score;

	private StudentPingJiaType studentPingJiaType;
	private StudentPingJiaProject studentPingJiaProject;
	private Course course;
	private Student student;

	public StudentPingJiaScore() {
	}

	public StudentPingJiaScore(Integer id, double score,
			StudentPingJiaType studentPingJiaType,
			StudentPingJiaProject studentPingJiaProject, Course course,
			Student student) {
		this.id = id;
		this.score = score;
		this.studentPingJiaType = studentPingJiaType;
		this.studentPingJiaProject = studentPingJiaProject;
		this.course = course;
		this.student = student;
	}

	
	public StudentPingJiaScore(Integer id, double score, Student student,
			StudentPingJiaType studentPingJiaType,
			StudentPingJiaProject studentPingJiaProject, Course course) {
		super();
		this.id = id;
		this.score = score;
		this.student = student;
		this.studentPingJiaType = studentPingJiaType;
		this.studentPingJiaProject = studentPingJiaProject;
		this.course = course;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentPingJiaType getStudentPingJiaType() {
		return studentPingJiaType;
	}

	public void setStudentPingJiaType(StudentPingJiaType studentPingJiaType) {
		this.studentPingJiaType = studentPingJiaType;
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
