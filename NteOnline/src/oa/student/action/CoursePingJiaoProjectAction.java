package oa.student.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import oa.baseDao.BaseAction;
import oa.bean.CoursePingJiaProject;
import oa.service.CoursePingJiaProjectService;
import oa.service.CoursePingJiaTypeService;

public class CoursePingJiaoProjectAction extends
		BaseAction<CoursePingJiaProject> implements RequestAware {
	private Map<String, Object> map;

	public CoursePingJiaProjectService getCoursePingJiaProjectService() {
		return coursePingJiaProjectService;
	}

	public void setCoursePingJiaProjectService(
			CoursePingJiaProjectService coursePingJiaProjectService) {
		this.coursePingJiaProjectService = coursePingJiaProjectService;
	}

	public String PingJiaoInfo_Project() {
		String[] courseId = (String[]) ActionContext.getContext()
				.getParameters().get("courseId");
		List<CoursePingJiaProject> list_project = coursePingJiaProjectService
				.NotPingJiaProject();
		for (CoursePingJiaProject c : list_project) {
			System.out.println("Project "+c.getId());
		}
		int id = Integer.parseInt(courseId[0]);
		map.put("courseId", id);
		map.put("Project", list_project);
		return "project";
	}

	public void setRequest(Map<String, Object> arg0) {
		this.map = arg0;

	}

}
