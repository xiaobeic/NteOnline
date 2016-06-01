package oa.student.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingJiaType;
import oa.bean.CourseScore;
import oa.service.CoursePingJiaTypeService;
import oa.service.CoursePingYuService;
import oa.service.CourseScoreService;

public class CoursePingJiaoType_InsAction extends BaseAction<CoursePingJiaType>
		implements RequestAware {
	private Map<String, Object> map;
	private String typeId;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	private int courseId;
	private int projectId;
	private String score_type;
	private String pingyu;

	public String getPingyu() {
		return pingyu;
	}

	public void setPingyu(String pingyu) {
		this.pingyu = pingyu;
	}

	public CourseScoreService getCourseScoreService() {
		return courseScoreService;
	}

	public void setCourseScoreService(CourseScoreService courseScoreService) {
		this.courseScoreService = courseScoreService;
	}

	public CoursePingYuService getCoursePingYuService() {
		return coursePingYuService;
	}

	public void setCoursePingYuService(CoursePingYuService coursePingYuService) {
		this.coursePingYuService = coursePingYuService;
	}

	public String getScore_type() {
		return score_type;
	}

	public void setScore_type(String score_type) {
		this.score_type = score_type;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String show() {
		return "show";
	}

	public String Insert_Type() {
		String c = "";
		for (int i = 0; i < score_type.length(); i++) {
			if (score_type.charAt(i) != ' ') {
				c += score_type.charAt(i);
			}
		}

		String[] score = c.split(",");

		int s_id = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");

		boolean insert_course = false;
		String b = "";
		for (int i = 0; i < typeId.length(); i++) {
			if (typeId.charAt(i) != ' ') {
				b += typeId.charAt(i);
			}
		}

		String[] type = b.split(",");

		for (int i = 0; i < type.length; i++) {
			insert_course = courseScoreService.InsertScore(
					Integer.parseInt(score[i]), s_id,
					Integer.parseInt(type[i]), courseId, projectId);
		}

		boolean insert_pingyu = coursePingYuService.InsertPingYu(pingyu, s_id,
				courseId, projectId);

		map.put("courseId", courseId);
		if (insert_course && insert_pingyu)
			return "success";
		return "fail";
	}

	public void setRequest(Map<String, Object> arg0) {
		this.map = arg0;
	}

}
