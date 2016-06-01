package oa.student.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import oa.baseDao.BaseAction;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingJiaType;
import oa.bean.Student;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class StudentAjaxAction extends BaseAction<Student> {
	private String result;
	private String linkId;
	private String courseId;
	private String aspectId;

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(String aspectId) {
		this.aspectId = aspectId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String listAspects() throws Exception {
		if (linkId != null) {
			List<CoursePingJiaType> list = coursePingJiaTypeService
					.CourseTypeInfo(Integer.parseInt(linkId));
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCoursePingJiaProject(null);
			}
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(list);
		}

		return SUCCESS;
	}

}
