package oa.admin.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingJiaType;

@Controller
@Scope("prototype")
public class CoursePingJiaProjectAction extends
		BaseAction<CoursePingJiaProject> {

	/**
	 * 获取课程评价环节
	 * 
	 * @return
	 */
	public String list() {
		Map<String, Object> session = ActionContext.getContext().getSession();

		if (session.get("projectid") != null)
			session.remove("projectid");
		List<CoursePingJiaProject> projects = coursePingJiaProjectService
				.findAll();
		ActionContext.getContext().put("STTprojects", projects);
		return "list";
	}

	/**
	 * 获取环节对应方面
	 * 
	 * @return
	 */
	public String type() {
		// 获取环节ID
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("projectid") == null)
			session.put("projectid", getModel().getId());
		// 根据环节ID获取对应方面
		List<CoursePingJiaType> types = coursePingJiaTypeService.findAll();
		List<CoursePingJiaType> newtypes = new ArrayList<CoursePingJiaType>();
		for (int n = 0; n < types.size(); n++) {
			if (types.get(n).getCoursePingJiaProject().getId() == session
					.get("projectid")) {
				newtypes.add(types.get(n));
			}
		}
		ActionContext.getContext().put("STTtypes", newtypes);
		return "typelist";
	}

	/**
	 * 跳转增加新的环节页面
	 * 
	 * @return
	 */
	public String addProjectUI() {

		return "addProject";
	}

	/**
	 * 跳转增加环节对应方面页面
	 * 
	 * @return
	 */
	public String addTypeUI() {

		return "addType";
	}

	/**
	 * 跳转更新环节页面
	 * 
	 * @return
	 */
	public String updateProjectUI() {

		ActionContext.getContext().getValueStack().push(
				coursePingJiaProjectService.getById(getModel().getId()));
		return "updateProject";
	}

	/**
	 *跳转更新环节对应方面页面
	 * 
	 * @return
	 */
	public String updateTypeUI() {

		ActionContext.getContext().getValueStack().push(
				coursePingJiaTypeService.getById(getModel().getId()));
		return "updateType";
	}

	/**
	 * 增加环节
	 * 
	 * @return
	 */
	public String addProject() {

		CoursePingJiaProject coursePingJiaProject = new CoursePingJiaProject();
		coursePingJiaProject.setName(getModel().getName());
		coursePingJiaProjectService.save(coursePingJiaProject);
		return "tolist";
	}

	/**
	 * 更新环节
	 * 
	 * @return
	 */
	public String updateProject() {

		CoursePingJiaProject coursePingJiaProject = coursePingJiaProjectService
				.getById(getModel().getId());
		coursePingJiaProject.setName(getModel().getName());
		coursePingJiaProjectService.update(coursePingJiaProject);
		return "tolist";
	}

	/**
	 * 增加环节对应方面
	 * 
	 * @return
	 */
	public String addType() {

		CoursePingJiaType coursePingJiaType = new CoursePingJiaType();
		coursePingJiaType.setName(getModel().getName());
		coursePingJiaType.setScore(Double.valueOf(score));
		coursePingJiaTypeService.save(coursePingJiaType);
		return "tolist";
	}

	/**
	 * 更新环节对应方面
	 * 
	 * @return
	 */
	public String updateType() {

		CoursePingJiaType coursePingJiaType = coursePingJiaTypeService
				.getById(getModel().getId());
		coursePingJiaType.setName(getModel().getName());
		coursePingJiaType.setScore(Double.valueOf(score));
		coursePingJiaTypeService.update(coursePingJiaType);
		return "totypelist";
	}

	/**
	 * 删除环节
	 * 
	 * @return
	 */
	public String deleteProject() {
		coursePingJiaProjectService.delete(getModel().getId());
		return "tolist";
	}

	/**
	 * 删除环节对应方面
	 * 
	 * @return
	 */
	public String deleteType() {
		coursePingJiaTypeService.delete(getModel().getId());
		return "tolist";
	}

	// =============================================================================================================
	private String score;
	private String projectid;

	public String getScore() {
		return score;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
