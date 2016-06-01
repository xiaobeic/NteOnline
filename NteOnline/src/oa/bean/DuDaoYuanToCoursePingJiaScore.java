package oa.bean;

public class DuDaoYuanToCoursePingJiaScore {

	private Integer id;
	private double score;
	private DuDaoYuanToCoursePingJiaType duDaoYuanToCoursePingJiaType;
	private DuDaoYuanToCoursePingJiaProject duDaoYuanToCoursePingJiaProject;
	private Course course;
	private Leader leader;

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

	public DuDaoYuanToCoursePingJiaType getDuDaoYuanToCoursePingJiaType() {
		return duDaoYuanToCoursePingJiaType;
	}

	public void setDuDaoYuanToCoursePingJiaType(
			DuDaoYuanToCoursePingJiaType duDaoYuanToCoursePingJiaType) {
		this.duDaoYuanToCoursePingJiaType = duDaoYuanToCoursePingJiaType;
	}

	public Leader getLeader() {
		return leader;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public DuDaoYuanToCoursePingJiaProject getDuDaoYuanToCoursePingJiaProject() {
		return duDaoYuanToCoursePingJiaProject;
	}

	public void setDuDaoYuanToCoursePingJiaProject(
			DuDaoYuanToCoursePingJiaProject duDaoYuanToCoursePingJiaProject) {
		this.duDaoYuanToCoursePingJiaProject = duDaoYuanToCoursePingJiaProject;
	}


}
