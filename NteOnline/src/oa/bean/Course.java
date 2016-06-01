package oa.bean;

import java.util.HashSet;
import java.util.Set;

public class Course {

	private Integer id;
	private String courseName;
	private Teacher teacher;

	private Set<Student> students = new HashSet<Student>();
	private Set<Leader> leaders = new HashSet<Leader>();
	private Set<StudentPingJiaScore> studentPingJiaScores = new HashSet<StudentPingJiaScore>();
	private Set<DuDaoYuanToCoursePingJiaScore> duDaoYuanToCoursePingJiaScores = new HashSet<DuDaoYuanToCoursePingJiaScore>();
	private Set<StudentPingYu> studentPingYus = new HashSet<StudentPingYu>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public Set<DuDaoYuanToCoursePingJiaScore> getDuDaoYuanToCoursePingJiaScores() {
		return duDaoYuanToCoursePingJiaScores;
	}

	public void setDuDaoYuanToCoursePingJiaScores(
			Set<DuDaoYuanToCoursePingJiaScore> duDaoYuanToCoursePingJiaScores) {
		this.duDaoYuanToCoursePingJiaScores = duDaoYuanToCoursePingJiaScores;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Set<Leader> getLeaders() {
		return leaders;
	}

	public void setLeaders(Set<Leader> leaders) {
		this.leaders = leaders;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<StudentPingJiaScore> getStudentPingJiaScores() {
		return studentPingJiaScores;
	}

	public void setStudentPingJiaScores(
			Set<StudentPingJiaScore> studentPingJiaScores) {
		this.studentPingJiaScores = studentPingJiaScores;
	}

	public Set<StudentPingYu> getStudentPingYus() {
		return studentPingYus;
	}

	public void setStudentPingYus(Set<StudentPingYu> studentPingYus) {
		this.studentPingYus = studentPingYus;
	}

}
