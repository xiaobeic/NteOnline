package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.CourseScore;
import oa.bean.StudentPingJiaScore;

public interface CourseScoreService extends BaseDao<CourseScore> {

	public List<CourseScore> findObjectsByCourse(String courseid);

	public List<CourseScore> findObjectsByCourseCount(String courseid, Integer integer);
	public boolean InsertScore(double score, int studentId, int typeId,
			int courseId, int projectId);

	public List<CourseScore> EvaluateFinish();
	List<CourseScore> findbyCourseAndStudent(String courseid,String studentid,String projectid);

}
