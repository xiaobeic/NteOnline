package oa.student.action;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Student;
import oa.bean.StudentPingJiaScore;
import oa.bean.StudentPingYu;
import oa.service.StudentPingJiaScoreService;
import oa.service.StudentPingYuService;

public class StudentGridInfoAction extends BaseAction<Student> implements
		RequestAware {
	private Map<String, Object> map;

	public StudentPingYuService getStudentPingYuService() {
		return studentPingYuService;
	}

	public void setStudentPingYuService(
			StudentPingYuService studentPingYuService) {
		this.studentPingYuService = studentPingYuService;
	}

	public StudentPingJiaScoreService getStudentPingJiaScoreService() {
		return studentPingJiaScoreService;
	}

	public void setStudentPingJiaScoreService(
			StudentPingJiaScoreService studentPingJiaScoreService) {
		this.studentPingJiaScoreService = studentPingJiaScoreService;
	}

	public void setRequest(Map<String, Object> arg0) {
		this.map = arg0;
	}

	public String type() {
		int studentId = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");
		List<StudentPingJiaScore> list = studentPingJiaScoreService
				.findGridInfo(studentId);
		map.put("Grid_StuInfo", list);
		return "type";
	}

	public String sum() {
		int studentId = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");
		List<StudentPingJiaScore> list_sum = studentPingJiaScoreService
				.findSumGrid(studentId);
		map.put("SumGridProject", list_sum);
		return "sum";
	}

	public String pingyu() {
		int studentId = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");
		List<StudentPingYu> list_pingyu = studentPingYuService
				.findStuInfo(studentId);
		map.put("StudentPingYu", list_pingyu);
		return "pingyu";
	}

//	public String ShowGrid_StuInfo() {
//		int studentId = (Integer) ActionContext.getContext().getSession()
//				.get("StudentId");
//		List<StudentPingJiaScore> list = studentPingJiaScoreService
//				.findGridInfo(studentId);
//		List<StudentPingYu> list_pingyu = studentPingYuService
//				.findStuInfo(studentId);
//		List<StudentPingJiaScore> list_sum = studentPingJiaScoreService
//				.findSumGrid(studentId);
//		map.put("SumGridProject", list_sum);
//		map.put("Grid_StuInfo", list);
//		map.put("StudentPingYu", list_pingyu);
//		return "show";
//	}

}
