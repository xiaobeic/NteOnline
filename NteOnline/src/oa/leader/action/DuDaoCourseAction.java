package oa.leader.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.dao.support.DaoSupport;
import org.springframework.web.HttpRequestHandler;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Course;
import oa.bean.DuDaoYuanToCoursePingJiaProject;
import oa.bean.DuDaoYuanToCoursePingJiaScore;
import oa.bean.DuDaoYuanToCoursePingJiaType;
import oa.bean.DuDaoYuanToCoursePingYu;
import oa.bean.Leader;

public class DuDaoCourseAction extends BaseAction<Leader> {

	/**
	 * 获取督导课程
	 * @return
	 */
	public String courseList() {
		Leader leader = (Leader) ActionContext.getContext().getSession().get(
				"leaderUser");
		if (leader == null) {
			return "login";
		}
		Set<Course> courses = leader.getCourses();
		System.out.println(courses.size());
		List<DuDaoYuanToCoursePingJiaProject> projects = daoYuanToCoursePingJiaProjectService
				.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		for(DuDaoYuanToCoursePingJiaProject p:projects)
		{
			
		}
		request.setAttribute("projects", projects);
		ActionContext.getContext().put("courselist", courses);
		ActionContext.getContext().put("projects", projects);
		return "courselist";
	}

	/**
	 * 点击环节进行评价的时候，获取环节对应的方面
	 * @return
	 */
	public String type() {

		types = daoYuanToCoursePingJiaProjectService
				.getByIdTotype(getModel().getId());
		ActionContext.getContext().put("types", types);
		return "success";
	}

	/**
	 * 评价过后点击环节获取对应方面的成绩
	 * @return
	 */
	public String score() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String course = request.getParameter("courseid");
		String project = request.getParameter("projectid");
		scores = daoYuanToCoursePingJiaScoreService.findByCourseProject(course,
				project);
		return "score";
	}

	/**
	 * 获取环节对应的评语
	 * @return
	 */
	public String py() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String course = request.getParameter("courseid");
		String project = request.getParameter("projectid");
		py = daoYuanToCoursePingYuService
				.findByCourseProject(course, project);
		return "py";
	}

	/**
	 * 评价
	 * @return
	 */
	public String addscore() {

		/**
		 * 获取课程和环节ID，存储成绩的时候需要这两个参数，
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		String courseid = request.getParameter("courseid");
		String projectid = request.getParameter("projectid");
		/**
		 * 获取该环节下对应的方面，前台传递的用户输入的成绩的name属性是score+方面的ID，所以在这里获取输入成绩的时候
		 *  先要获取所有的环节对应的方面，然后request.getParameter("score"+方面的ID)来获取成绩
		 */
		Set<DuDaoYuanToCoursePingJiaType> coursePingJiaTypes = daoYuanToCoursePingJiaProjectService
				.getById(Integer.parseInt(projectid))
				.getDuDaoYuanToCoursePingJiaTypes();
		Iterator<DuDaoYuanToCoursePingJiaType> iterator = coursePingJiaTypes
				.iterator();
		//遍历
		while (iterator.hasNext()) {
			DuDaoYuanToCoursePingJiaType coursePingJiaType = iterator.next();
			//获取该方面评价的成绩
			String score = request.getParameter("score"
					+ coursePingJiaType.getId());
			//创建成绩对象
			DuDaoYuanToCoursePingJiaScore coursePingJiaScore = new DuDaoYuanToCoursePingJiaScore();
			coursePingJiaScore.setCourse(courseService.getById(Integer
					.parseInt(courseid)));
			coursePingJiaScore
					.setDuDaoYuanToCoursePingJiaProject(daoYuanToCoursePingJiaProjectService
							.getById(Integer.parseInt(projectid)));
			coursePingJiaScore
					.setDuDaoYuanToCoursePingJiaType(coursePingJiaType);
			coursePingJiaScore.setLeader((Leader) ActionContext.getContext()
					.getSession().get("leaderUser"));
			coursePingJiaScore.setScore(Double.parseDouble(score));
			daoYuanToCoursePingJiaScoreService.save(coursePingJiaScore);
		}
		//添加评语
		DuDaoYuanToCoursePingYu coursePingYu = new DuDaoYuanToCoursePingYu();
		coursePingYu.setCourse(courseService
				.getById(Integer.parseInt(courseid)));
		coursePingYu
				.setDuDaoYuanToCoursePingJiaProject(daoYuanToCoursePingJiaProjectService
						.getById(Integer.parseInt(projectid)));
		coursePingYu.setPingYu(request.getParameter("project_yu"));
		coursePingYu.setLeader((Leader) ActionContext.getContext().getSession()
				.get("leaderUser"));
		daoYuanToCoursePingYuService.save(coursePingYu);
		return "tocourselist";

	}
	/**
	 * 更改密码，展示个人信息
	 */
	
	public String updatepasswordUI()
	{
		
		return "updatepassword";
	}
	public String updatepassword()
	{
		
		Leader leader=(Leader) ServletActionContext.getRequest().getSession().getAttribute("user");
		leader.setPassword(getModel().getPassword());
		leaderService.update(leader);
		return "login";
	}

	private List<DuDaoYuanToCoursePingJiaType> types = new ArrayList<DuDaoYuanToCoursePingJiaType>();
	private List<DuDaoYuanToCoursePingJiaScore> scores = new ArrayList<DuDaoYuanToCoursePingJiaScore>();
	private List<DuDaoYuanToCoursePingYu> py = new ArrayList<DuDaoYuanToCoursePingYu>();

	public List<DuDaoYuanToCoursePingJiaScore> getScores() {
		return scores;
	}

	public void setScores(List<DuDaoYuanToCoursePingJiaScore> scores) {
		this.scores = scores;
	}

	public List<DuDaoYuanToCoursePingJiaType> getTypes() {
		return types;
	}

	public void setTypes(List<DuDaoYuanToCoursePingJiaType> types) {
		this.types = types;
	}

	public List<DuDaoYuanToCoursePingYu> getPy() {
		return py;
	}

	public void setPy(List<DuDaoYuanToCoursePingYu> py) {
		this.py = py;
	}

}
