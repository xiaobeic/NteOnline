package oa.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.baseDao.BaseDaoImpl;
import oa.bean.CoursePingJiaProject;
import oa.bean.DuDaoYuanToCoursePingJiaProject;
import oa.bean.DuDaoYuanToCoursePingJiaType;
import oa.service.DuDaoYuanToCoursePingJiaProjectService;

@Controller
@Scope("prototype")
public class DuDaoYuanToCoursePingJiaProjectAction extends
		BaseAction<DuDaoYuanToCoursePingJiaProject> {

	// 获取环节
	public String list() {
		Map<String, Object> session = ActionContext.getContext().getSession();

		if (session.get("projectid") != null)
			session.remove("projectid");
		List<DuDaoYuanToCoursePingJiaProject> projects = daoYuanToCoursePingJiaProjectService
				.findAll();
		ActionContext.getContext().put("DTTprojects", projects);
		return "list";
	}

	// 获取环节对应的方面
	public String type() {

		Map<String, Object> session = ActionContext.getContext().getSession();

		if (session.get("projectid") == null)
			session.put("projectid", getModel().getId());

		List<DuDaoYuanToCoursePingJiaType> types = daoYuanToCoursePingJiaTypeService
				.findAll();
		List<DuDaoYuanToCoursePingJiaType> newtypes = new ArrayList<DuDaoYuanToCoursePingJiaType>();

		for (int n = 0; n < types.size(); n++) {

			if (types.get(n).getDuDaoYuanToCoursePingJiaProject().getId() == session
					.get("projectid")) {
				newtypes.add(types.get(n));
			}
		}
		ActionContext.getContext().put("DTTtypes", newtypes);
		return "typelist";
	}

	// 添加环节
	public String addProject() {

		DuDaoYuanToCoursePingJiaProject duDaoYuanToCoursePingJiaProject = new DuDaoYuanToCoursePingJiaProject();

		duDaoYuanToCoursePingJiaProject.setName(getModel().getName());
		daoYuanToCoursePingJiaProjectService
				.save(duDaoYuanToCoursePingJiaProject);
		return "tolist";
	}

	// 添加环节对应的方面
	public String addType() {

		DuDaoYuanToCoursePingJiaType duDaoYuanToCoursePingJiaType = new DuDaoYuanToCoursePingJiaType();

		duDaoYuanToCoursePingJiaType.setName(getModel().getName());
		duDaoYuanToCoursePingJiaType.setScore(Double.valueOf(score));
		duDaoYuanToCoursePingJiaType
				.setDuDaoYuanToCoursePingJiaProject(daoYuanToCoursePingJiaProjectService
						.getById(getModel().getId()));
		daoYuanToCoursePingJiaTypeService.save(duDaoYuanToCoursePingJiaType);
		return "totypelist";
	}

	// 更新环节
	public String updateProject() {

		DuDaoYuanToCoursePingJiaProject duDaoYuanToCoursePingJiaProject = daoYuanToCoursePingJiaProjectService
				.getById(getModel().getId());

		duDaoYuanToCoursePingJiaProject.setName(getModel().getName());
		daoYuanToCoursePingJiaProjectService.update(duDaoYuanToCoursePingJiaProject);
		return "tolist";
	}

	// 更新环节对应的方面
	public String updateType() {

		ActionContext.getContext().getValueStack().push(
				daoYuanToCoursePingJiaProjectService
						.getById(getModel().getId()));
		DuDaoYuanToCoursePingJiaType duDaoYuanToCoursePingJiaType = daoYuanToCoursePingJiaTypeService
				.getById(getModel().getId());

		duDaoYuanToCoursePingJiaType.setName(getModel().getName());
		duDaoYuanToCoursePingJiaType.setScore(Double.valueOf(score));
		daoYuanToCoursePingJiaTypeService.update(duDaoYuanToCoursePingJiaType);
		return "totypelist";
	}

	// 添加环节
	public String addProjectUI() {

		return "addProject";
	}

	// 添加环节对应方面
	public String addTypeUI() {

		ActionContext.getContext().getValueStack().push(
				daoYuanToCoursePingJiaProjectService
						.getById(getModel().getId()));
		return "addType";
	}

	// 更新环节
	public String updateProjectUI() {

		ActionContext.getContext().getValueStack().push(
				daoYuanToCoursePingJiaProjectService
						.getById(getModel().getId()));
		return "updateProject";
	}

	// 更新环节对应方面
	public String updateTypeUI() {

		ActionContext.getContext().getValueStack().push(
				daoYuanToCoursePingJiaTypeService.getById(getModel().getId()));

		return "updateType";
	}

	public String deleteProject() {

		daoYuanToCoursePingJiaProjectService.delete(getModel().getId());
		return "tolist";
	}

	public String deleteType() {

		daoYuanToCoursePingJiaTypeService.delete(getModel().getId());
		return "tolist";
	}

	private String score;
	private String projectid;

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

}
