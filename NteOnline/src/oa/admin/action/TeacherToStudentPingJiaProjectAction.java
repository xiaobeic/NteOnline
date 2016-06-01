package oa.admin.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.baseDao.BaseDaoImpl;
import oa.bean.CoursePingJiaProject;
import oa.bean.DuDaoYuanToCoursePingJiaProject;
import oa.bean.DuDaoYuanToCoursePingJiaType;
import oa.bean.StudentPingJiaProject;
import oa.bean.StudentPingJiaType;
import oa.service.DuDaoYuanToCoursePingJiaProjectService;

@Controller
@Scope("prototype")
public class TeacherToStudentPingJiaProjectAction extends
		BaseAction<StudentPingJiaProject> {

	public String list() {
		Map<String, Object> session = ActionContext.getContext().getSession();

		if (session.get("projectid") != null)
			session.remove("projectid");
		List<StudentPingJiaProject> projects = studentPingJiaProjectService
				.findAll();
		ActionContext.getContext().put("TTSprojects", projects);
		return "list";
	}

	public String type() {

		Map<String, Object> session = ActionContext.getContext().getSession();

		if (session.get("projectid") == null)
			session.put("projectid", getModel().getId());

		List<StudentPingJiaType> types = studentPingJiaTypeService.findAll();
		List<StudentPingJiaType> newtypes = new ArrayList<StudentPingJiaType>();

		for (int n = 0; n < types.size(); n++) {

			if (types.get(n).getStudentPingJiaProject().getId() == session
					.get("projectid")) {
				newtypes.add(types.get(n));
			}
		}
		ActionContext.getContext().put("TTStypes", newtypes);
		return "typelist";
	}

	public String addProjectUI() {

		return "addProject";
	}

	public String addTypeUI() {

		return "addType";
	}

	public String updateProjectUI() {
		ActionContext.getContext().getValueStack().push(
				studentPingJiaProjectService.getById(getModel().getId()));
		return "updateProject";
	}

	public String updateTypeUI() {

		ActionContext.getContext().getValueStack().push(
				studentPingJiaTypeService.getById(getModel().getId()));
		return "updateType";
	}

	public String addProject() {

		StudentPingJiaProject studentPingJiaProject = new StudentPingJiaProject();

		studentPingJiaProject.setName(getModel().getName());
		studentPingJiaProjectService.save(studentPingJiaProject);
		return "tolist";
	}

	public String addType() {

		StudentPingJiaType studentPingJiaType = new StudentPingJiaType();

		studentPingJiaType.setName(getModel().getName());
		HttpServletRequest request=ServletActionContext.getRequest();
		double score=Double.parseDouble(request.getParameter("score"));
		studentPingJiaType.setFenzhi(score);
		studentPingJiaType.setStudentPingJiaProject(studentPingJiaProjectService.getById(Integer.parseInt(String.valueOf(ActionContext.getContext().getSession().get("projectid")))));
		studentPingJiaTypeService.save(studentPingJiaType);
		return "totype";
	}

	public String updateProject() {

		StudentPingJiaProject studentPingJiaProject = studentPingJiaProjectService
				.getById(getModel().getId());

		studentPingJiaProject.setName(getModel().getName());
		studentPingJiaProjectService.update(studentPingJiaProject);
		return "tolist";
	}

	public String updateType() {

		StudentPingJiaType studentPingJiaType = studentPingJiaTypeService
				.getById(getModel().getId());

		studentPingJiaType.setName(getModel().getName());
		HttpServletRequest request=ServletActionContext.getRequest();
		double score=Double.parseDouble(request.getParameter("score"));
		studentPingJiaType.setFenzhi(score);
		studentPingJiaTypeService.update(studentPingJiaType);
		return "totype";
	}

	public String deleteProject() {

		studentPingJiaProjectService.delete(getModel().getId());
		return "tolist";
	}

	public String deleteType() {

		studentPingJiaTypeService.delete(getModel().getId());
		return "totype";
	}

}
