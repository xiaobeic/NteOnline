package oa.bean;

//学生对教师评价类型
public class CoursePingJiaType {

	private Integer id;
	private String name;// 类型名称
	private double score;// 所占分值
	private double fenzhi;
	private CoursePingJiaProject coursePingJiaProject;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getFenzhi() {
		return fenzhi;
	}

	public void setFenzhi(double fenzhi) {
		this.fenzhi = fenzhi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
		fenzhi=score;
	}

	public CoursePingJiaProject getCoursePingJiaProject() {
		return coursePingJiaProject;
	}

	public void setCoursePingJiaProject(
			CoursePingJiaProject coursePingJiaProject) {
		this.coursePingJiaProject = coursePingJiaProject;
	}

}
