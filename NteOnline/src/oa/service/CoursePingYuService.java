package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.CoursePingYu;

public interface CoursePingYuService extends BaseDao<CoursePingYu> {
	public boolean InsertPingYu(String pingyu, int studentId, int courseId,int projectId);

	public List<CoursePingYu> findPYByCSP(String courseid, String studentid,	
			String projectid);
}
