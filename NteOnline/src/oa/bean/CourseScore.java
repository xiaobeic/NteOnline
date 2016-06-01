package oa.bean;

//ѧ��Կγ����۱�
public class CourseScore {

	private Integer id;
	private double score;
	private Course course;
	private Student student;
	private CoursePingJiaType coursePingJiaType;
	private CoursePingJiaProject coursePingJiaProject;

	public CourseScore() {
		// TODO Auto-generated constructor stub
	}

	public CourseScore(Integer id, double score, Course course,
			Student student, CoursePingJiaType coursePingJiaType,
			CoursePingJiaProject coursePingJiaProject) {
		this.id = id;
		this.score = score;
		this.course = course;
		this.student = student;
		this.coursePingJiaType = coursePingJiaType;
		this.coursePingJiaProject = coursePingJiaProject;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CoursePingJiaType getCoursePingJiaType() {
		return coursePingJiaType;
	}

	public void setCoursePingJiaType(CoursePingJiaType coursePingJiaType) {
		this.coursePingJiaType = coursePingJiaType;
	}

	public CoursePingJiaProject getCoursePingJiaProject() {
		return coursePingJiaProject;
	}

	public void setCoursePingJiaProject(
			CoursePingJiaProject coursePingJiaProject) {
		this.coursePingJiaProject = coursePingJiaProject;
	}

}
