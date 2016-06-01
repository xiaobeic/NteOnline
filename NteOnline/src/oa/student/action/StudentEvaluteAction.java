package oa.student.action;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Student;

public class StudentEvaluteAction extends BaseAction<Student> implements
		ServletRequestAware {
	private HttpServletRequest request;

	public String studentEvaluting() {
		int studentId = (Integer) ActionContext.getContext().getSession()
				.get("StudentId");
		String courseId = request.getParameter("courseId");
		String pingyu = request.getParameter("evaluteResult");
		String datalength = request.getParameter("aspectCount");
		String projectId = request.getParameter("projectId");
		for (int i = 0; i < Integer.parseInt(datalength); i++) {
			courseScoreService.InsertScore(
					Double.parseDouble(request.getParameter("score" + i)),
					studentId,
					Integer.parseInt(request.getParameter("aspectId" + i)),
					Integer.parseInt(courseId), Integer.parseInt(projectId));
		}
		coursePingYuService.InsertPingYu(pingyu, studentId,
				Integer.parseInt(courseId), Integer.parseInt(projectId));

		return "evaluate_comment";
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
}
