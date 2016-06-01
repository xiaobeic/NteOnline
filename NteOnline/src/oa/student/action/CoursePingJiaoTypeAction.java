package oa.student.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oa.baseDao.BaseAction;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingJiaType;
import oa.service.CoursePingJiaTypeService;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;

public class CoursePingJiaoTypeAction extends BaseAction<CoursePingJiaoAction>
		implements RequestAware {
	private int courseId;
	private int projectId;

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

	private Map<String, Object> map;

	public void setRequest(Map<String, Object> arg0) {
		this.map = arg0;
	}

	public CoursePingJiaTypeService getCoursePingJiaTypeService() {
		return coursePingJiaTypeService;
	}

	public void setCoursePingJiaTypeService(
			CoursePingJiaTypeService coursePingJiaTypeService) {
		this.coursePingJiaTypeService = coursePingJiaTypeService;
	}

	public String Show_Type() {
		List<CoursePingJiaType> list_type = coursePingJiaTypeService
				.CourseTypeInfo(projectId);
		map.put("ListType", list_type);
		map.put("courseId", projectId);
		map.put("project", courseId);
		return "type";
	}

}
