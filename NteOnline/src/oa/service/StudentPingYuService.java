package oa.service;

import java.util.List;

import oa.baseDao.BaseDao;
import oa.bean.CoursePingYu;
import oa.bean.StudentPingYu;

public interface StudentPingYuService extends BaseDao<StudentPingYu>{
	public List<StudentPingYu> findStuInfo(int studentId);
	public List<StudentPingYu> findPYByCSP(String courseid, String studentid,	
			String projectid);
}
