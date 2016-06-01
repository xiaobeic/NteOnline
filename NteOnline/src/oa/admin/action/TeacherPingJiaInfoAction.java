package oa.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Course;
import oa.bean.CoursePingJiaProject;
import oa.bean.CoursePingJiaType;
import oa.bean.CoursePingYu;
import oa.bean.CourseScore;
import oa.bean.DuDaoYuanToCoursePingJiaScore;
import oa.bean.Student;
import oa.bean.StudentPingJiaProject;
import oa.bean.StudentPingJiaScore;
import oa.bean.StudentPingJiaType;
import oa.service.StudentPingJiaScoreService;

@Controller
@Scope("prototype")
public class TeacherPingJiaInfoAction extends BaseAction<CourseScore> {

	
	public String score_()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		scores_=courseScoreService.findbyCourseAndStudent(request.getParameter("courseid"),request.getParameter("studentid"),request.getParameter("projectid"));
		return "score";
	}
	public String py()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		py_=coursePingYuService.findPYByCSP(request.getParameter("courseid"),request.getParameter("studentid"),request.getParameter("projectid"));
		return "py_";
	}
	// 查看某一门课程
	public String teacherPingJiaMain() {

		if (courseid == null || courseid.equals("")) {
			List<Course> courses = courseService.findAll();
			ActionContext.getContext().put("courses", courses);
			List<CoursePingJiaProject> coursePingJiaProjects = coursePingJiaProjectService
					.findAll();
			ActionContext.getContext().put("projects", coursePingJiaProjects);
			return "teacherPingJiaMain";
		} else {
			List<CourseScore> courseScores = courseScoreService
					.findObjectsByCourse(courseid);
			List<CoursePingJiaProject> coursePingJiaProjects = coursePingJiaProjectService
					.findAll();
			ActionContext.getContext().put("projects", coursePingJiaProjects);
			List<CoursePingJiaType> coursePingJiaTypes = coursePingJiaTypeService
					.findAll();
			List<CourseScore> coursePingJiaScoresCount = new ArrayList<CourseScore>();
			// 获取所有学生
			Course course=courseService.getById(Integer.parseInt(courseid));
			
			List<Student> students =new ArrayList<Student>(course.getStudents());
			// 按照每个评价项目统计
			List<Map<String, Object>> projectScoreList = new ArrayList<Map<String, Object>>();
			
	

			// 按照每个评价项目对应一个总成绩来显示
			for (int studentNum = 0; studentNum < students.size(); studentNum++) {
				int scorenum = 0;
				Map<String, Object> projectScoreMap = new HashMap<String, Object>();
				List<CourseScore> sum_score=new ArrayList<CourseScore>();
				for (int n = 0; n < coursePingJiaProjects.size(); n++) {

					coursePingJiaScoresCount = courseScoreService
					.findObjectsByCourseCount(courseid,students.get(studentNum).getId());
					for (int m = 0; m < coursePingJiaScoresCount.size(); m++) {
						if (students.get(studentNum).getId() == coursePingJiaScoresCount
								.get(m).getStudent().getId()
								&& coursePingJiaScoresCount.get(m)
										.getCoursePingJiaType()
										.getCoursePingJiaProject().getId() == coursePingJiaProjects
										.get(n).getId()) {
							CourseScore courseScore=new CourseScore();
							courseScore.setCourse(courseService.getById(Integer.parseInt(courseid)));
							courseScore.setCoursePingJiaProject(coursePingJiaScoresCount.get(m).getCoursePingJiaProject());
							courseScore.setStudent(studentService.getById(students.get(studentNum).getId()));
							courseScore.setScore(coursePingJiaScoresCount.get(m).getScore());
							sum_score.add(courseScore);
							projectScoreMap.put("teacherName",
									coursePingJiaScoresCount.get(m).getCourse()
											.getTeacher().getTeacherName());
						}
					}

				}
				projectScoreMap.put("teacherName",
						course.getTeacher()
								.getTeacherName());
				projectScoreMap.put("sum_score", sum_score);
				projectScoreMap.put("projects_score", coursePingJiaProjects);
				projectScoreMap.put("studentName", students.get(studentNum)
						.getName());

				projectScoreList.add(projectScoreMap);
			}
			ActionContext.getContext().put("scores", projectScoreList);
			List<Course> courses = courseService.findAll();
			ActionContext.getContext().put("courses", courses);
		}

		return "teacherPingJiaMain";
	}

	// ========================================================================================
	private String studentid;
	private String courseid;

	
	private List<CourseScore> scores_=new ArrayList<CourseScore>();
	private List<CoursePingYu> py_=new ArrayList<CoursePingYu>();
	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	public List<CourseScore> getScores_() {
		return scores_;
	}
	public void setScores_(List<CourseScore> scores) {
		scores_ = scores;
	}
	public List<CoursePingYu> getPy_() {
		return py_;
	}
	public void setPy_(List<CoursePingYu> py) {
		py_ = py;
	}


}
