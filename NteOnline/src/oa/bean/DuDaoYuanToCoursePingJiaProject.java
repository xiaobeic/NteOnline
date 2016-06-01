package oa.bean;

import java.util.HashSet;
import java.util.Set;

public class DuDaoYuanToCoursePingJiaProject {

	private Integer id;
	private String name;
	private Set<DuDaoYuanToCoursePingJiaType> duDaoYuanToCoursePingJiaTypes = new HashSet<DuDaoYuanToCoursePingJiaType>();
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

	public Set<DuDaoYuanToCoursePingJiaType> getDuDaoYuanToCoursePingJiaTypes() {
		return duDaoYuanToCoursePingJiaTypes;
	}

	public void setDuDaoYuanToCoursePingJiaTypes(
			Set<DuDaoYuanToCoursePingJiaType> duDaoYuanToCoursePingJiaTypes) {
		this.duDaoYuanToCoursePingJiaTypes = duDaoYuanToCoursePingJiaTypes;
	}

	public Set<DuDaoYuanToCoursePingJiaScore> getDuDaoYuanToCoursePingJiaScores() {
		return duDaoYuanToCoursePingJiaScores;
	}

	public void setDuDaoYuanToCoursePingJiaScores(
			Set<DuDaoYuanToCoursePingJiaScore> duDaoYuanToCoursePingJiaScores) {
		this.duDaoYuanToCoursePingJiaScores = duDaoYuanToCoursePingJiaScores;
	}

}
