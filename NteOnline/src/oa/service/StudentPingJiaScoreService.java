package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.CourseScore;
import oa.bean.StudentPingJiaScore;

public interface StudentPingJiaScoreService extends
		BaseDao<StudentPingJiaScore> {

	public List<StudentPingJiaScore> findObjectsByCourse(String courseid);

	public List<StudentPingJiaScore> findObjectsByCourseCount(String courseid, Integer integer);
	
	public List<StudentPingJiaScore> findObjectsByCourse(List<Integer> courseid);
	public List<StudentPingJiaScore> findObjectsByCourseCount(List<Integer> courseid);
	public List<StudentPingJiaScore> findSumGrid(int courseId);
	public List<StudentPingJiaScore> findGridInfo(int courseId);
	List<StudentPingJiaScore> findbyCourseAndStudent(String courseid,String studentid,String projectid);
}
