package oa.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Course;
import oa.bean.CourseScore;
import oa.bean.Student;
import oa.bean.StudentPingJiaProject;
import oa.bean.StudentPingJiaScore;
import oa.bean.StudentPingJiaType;
import oa.bean.StudentPingYu;
import oa.service.StudentPingJiaScoreService;

@Controller
@Scope("prototype")
public class StudentPingJiaInfoAction extends BaseAction<StudentPingJiaScore> {

	// 查看某一门课程
	public String studentPingJiaMain() {

		if (courseid == null || courseid.equals("")) {
			List<Course> courses = courseService.findAll();
			ActionContext.getContext().put("courses", courses);
			List<StudentPingJiaProject> studentPingJiaProjects = studentPingJiaProjectService
					.findAll();
			ActionContext.getContext().put("projects", studentPingJiaProjects);
			return "studentPingJiaMain";
		} else {
			List<StudentPingJiaProject> studentPingJiaProjects = studentPingJiaProjectService
					.findAll();
			ActionContext.getContext().put("projects", studentPingJiaProjects);
			List<StudentPingJiaScore> studentPingJiaScoresCount = new ArrayList<StudentPingJiaScore>();
			// 获取所有学生
			
			Course course=courseService.getById(Integer.parseInt(courseid));
			
			List<Student> students =new ArrayList<Student>(course.getStudents());
			// 按照每个评价项目统计
		
			List<Map<String, Object>> projectScoreList = new ArrayList<Map<String, Object>>();

		
			// 按照每个评价项目对应一个总成绩来显示
			for (int studentNum = 0; studentNum < students.size(); studentNum++) {
				int scorenum = 0;
				Map<String, Object> projectScoreMap = new HashMap<String, Object>();
				List<StudentPingJiaScore> sum_score=new ArrayList<StudentPingJiaScore>();
				for (int n = 0; n < studentPingJiaProjects.size(); n++) {

					studentPingJiaScoresCount = studentPingJiaScoreService
					.findObjectsByCourseCount(courseid,students.get(studentNum).getId());
					for (int m = 0; m < studentPingJiaScoresCount.size(); m++) {
						if (students.get(studentNum).getId() == studentPingJiaScoresCount
								.get(m).getStudent().getId()
								&& studentPingJiaScoresCount.get(m)
										.getStudentPingJiaType()
										.getStudentPingJiaProject().getId() == studentPingJiaProjects
										.get(n).getId()) {
							StudentPingJiaScore studentScore=new StudentPingJiaScore();
							studentScore.setCourse(courseService.getById(Integer.parseInt(courseid)));
							studentScore.setStudentPingJiaProject(studentPingJiaScoresCount.get(m).getStudentPingJiaProject());
							studentScore.setStudent(studentService.getById(students.get(studentNum).getId()));
							studentScore.setScore(studentPingJiaScoresCount.get(m).getScore());
							sum_score.add(studentScore);
							projectScoreMap.put("teacherName",
									course.getTeacher()
											.getTeacherName());
						}
					}

				}
				projectScoreMap.put("teacherName",
						course.getTeacher()
								.getTeacherName());
				projectScoreMap.put("sum_score", sum_score);
				projectScoreMap.put("projects_score", studentPingJiaProjects);
				projectScoreMap.put("studentName", students.get(studentNum)
						.getName());

				projectScoreList.add(projectScoreMap);
			}
			ActionContext.getContext().put("scores", projectScoreList);
			List<Course> courses = courseService.findAll();
			ActionContext.getContext().put("courses", courses);
		}

		return "studentPingJiaMain";
	}

	public String score_()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		scores=studentPingJiaScoreService.findbyCourseAndStudent(request.getParameter("courseid"),request.getParameter("studentid"),request.getParameter("projectid"));
		return "score";
	}
	public String py()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		py=studentPingYuService.findPYByCSP(request.getParameter("courseid"),request.getParameter("studentid"),request.getParameter("projectid"));
		return "py";
	}
	// ========================================================================================
	private String studentid;
	private String courseid;
	
	private List<StudentPingJiaScore> scores=new ArrayList<StudentPingJiaScore>();
	private List<StudentPingYu> py=new ArrayList<StudentPingYu>();
	

	public List<StudentPingJiaScore> getScores() {
		return scores;
	}

	public void setScores(List<StudentPingJiaScore> scores) {
		this.scores = scores;
	}

	public List<StudentPingYu> getPy() {
		return py;
	}

	public void setPy(List<StudentPingYu> py) {
		this.py = py;
	}

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

}
