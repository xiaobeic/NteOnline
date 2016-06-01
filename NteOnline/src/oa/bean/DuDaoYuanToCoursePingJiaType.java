package oa.bean;

import java.util.HashSet;
import java.util.Set;

public class DuDaoYuanToCoursePingJiaType {

	private Integer id;
	private String name;
	private double score;
	private double fenzhi;
	private DuDaoYuanToCoursePingJiaProject duDaoYuanToCoursePingJiaProject;
	private Set<DuDaoYuanToCoursePingJiaScore> duDaoYuanToCoursePingJiaScores = new HashSet<DuDaoYuanToCoursePingJiaScore>();

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

	public double getScore() {
		return score;
	}

	public double getFenzhi() {
		return fenzhi;
	}

	public void setFenzhi(double fenzhi) {
		this.fenzhi = fenzhi;
	}

	public void setScore(double score) {
		this.score = score;
		fenzhi=score;
	}

	public DuDaoYuanToCoursePingJiaProject getDuDaoYuanToCoursePingJiaProject() {
		return duDaoYuanToCoursePingJiaProject;
	}

	public void setDuDaoYuanToCoursePingJiaProject(
			DuDaoYuanToCoursePingJiaProject duDaoYuanToCoursePingJiaProject) {
		this.duDaoYuanToCoursePingJiaProject = duDaoYuanToCoursePingJiaProject;
	}

	public Set<DuDaoYuanToCoursePingJiaScore> getDuDaoYuanToCoursePingJiaScores() {
		return duDaoYuanToCoursePingJiaScores;
	}

	public void setDuDaoYuanToCoursePingJiaScores(
			Set<DuDaoYuanToCoursePingJiaScore> duDaoYuanToCoursePingJiaScores) {
		this.duDaoYuanToCoursePingJiaScores = duDaoYuanToCoursePingJiaScores;
	}

}
