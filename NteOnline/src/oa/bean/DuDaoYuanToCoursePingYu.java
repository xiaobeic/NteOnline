package oa.bean;

public class DuDaoYuanToCoursePingYu {

	private Integer id;
	private String pingYu;
	private DuDaoYuanToCoursePingJiaProject duDaoYuanToCoursePingJiaProject;
	private Leader leader;
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

	public DuDaoYuanToCoursePingJiaProject getDuDaoYuanToCoursePingJiaProject() {
		return duDaoYuanToCoursePingJiaProject;
	}

	public void setDuDaoYuanToCoursePingJiaProject(
			DuDaoYuanToCoursePingJiaProject duDaoYuanToCoursePingJiaProject) {
		this.duDaoYuanToCoursePingJiaProject = duDaoYuanToCoursePingJiaProject;
	}

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
