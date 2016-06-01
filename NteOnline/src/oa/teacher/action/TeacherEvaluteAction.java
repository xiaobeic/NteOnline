package oa.teacher.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Classes;
import oa.bean.Course;
import oa.bean.Student;
import oa.bean.StudentPingJiaProject;
import oa.bean.StudentPingJiaType;
import oa.bean.StudentPingYu;
import oa.bean.Teacher;

public class TeacherEvaluteAction extends BaseAction<Teacher>implements ServletRequestAware{

	private static final long serialVersionUID = 4258500062734882463L;
	
	private HttpServletRequest request;
	
	private String aspectId;
	private String course;//获取班级课程id信息
	private String stuClass;//获取班级id信息
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getStuClass() {
		return stuClass;
	}
	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
	public String getAspectId() {
		return aspectId;
	}
	public void setAspectId(String aspectId) {
		this.aspectId = aspectId;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String listAspects(){
		System.out.println(aspectId);
		return "evaluate_comment";
	}
	public void rollBackForm(){
	}
	public String teacherSeeStudentInfo(){
		int teacherId = 0;
		List<Course>courseList=new ArrayList<Course>();
		if(ActionContext.getContext().getSession().get("TeacherId")!=null)
			teacherId=(Integer) ActionContext.getContext().getSession().get("TeacherId");
		
		backForm(null, stuClass, course, teacherId);
		return "evaluate_comment";
	}
	@SuppressWarnings("unused")
	public String teacherEvaluting(){
		//从request中取值
		String courseId=request.getParameter("courseId");
		String classId=request.getParameter("classId");
		String stuId=request.getParameter("stuId");
		String evaluteResult=request.getParameter("evaluteResult");//评语
		String aspectCount=request.getParameter("aspectCount");
		String projectId=request.getParameter("projectId");
		int teacherId = 0;
		if(ActionContext.getContext().getSession().get("TeacherId")!=null)
			teacherId=(Integer) ActionContext.getContext().getSession().get("TeacherId");
		
		//判断值有没有
		if(teacherId!=0&&courseId!=null&&classId!=null&&stuId!=null&&evaluteResult!=null&&aspectCount!=null&&projectId!=null){
			//依次获取指定id的对象
			Course course=courseService.getById(Integer.parseInt(courseId));
			Student student=studentService.getById(Integer.parseInt(stuId));
			Teacher teacher=teacherService.getById(teacherId);
			StudentPingJiaProject studentPingJiaProject=studentPingJiaProjectService.getById(Integer.parseInt(projectId));
			//遍历整个type获取各个aspectId和score
			for(int i=0;i<Integer.parseInt(aspectCount);i++){
				String aspectId=request.getParameter("aspectId"+i);
				String score=request.getParameter("score"+i);
				if(aspectId!=null&&score!=null){
					StudentPingJiaType studentPingJiaType=studentPingJiaTypeService.getById(Integer.parseInt(aspectId));
					//保存数据
					teacherService.saveEvaluteScore(student, course, studentPingJiaProject, studentPingJiaType, Integer.parseInt(score));
				}
			}
			//保存评语
			teacherService.savePingyu(student, course, teacher, studentPingJiaProject, evaluteResult);
		}

		//-------------分割线----------------------------
		//下面进行表单回显
		backForm(stuId, classId, courseId, teacherId);
		
		return "evaluate_comment";
	}
	public void backForm(String studentId,String classId,String courseId,int teacherId){
		List<StudentPingJiaProject>listCourseProject=teacherService.getStudentCourseProject();
		request.setAttribute("listCourseProject", listCourseProject);
		if(courseId!=null){
			//获取当前班级下的学生集合
			List<Student>list=teacherService.getStudentsByClassId(Integer.parseInt(classId));
			request.setAttribute("studentsList", list);
			//对学生列表list进行遍历获取当前学生已经评过的课程
			List<List<Integer>>big_project_validate=new ArrayList<List<Integer>>();
			for (int i = 0; i < list.size(); i++) {
				List<Integer>project_validate=new ArrayList<Integer>();
				List<StudentPingYu>spy_list=teacherService.getStudentPingYuByStudentId(list.get(i).getId());
				if(spy_list==null){
					spy_list=new ArrayList<StudentPingYu>();
				}
				//遍历这个评语集合，获取其中已经填写过的projectId
				for (int j = 0; j < spy_list.size(); j++) {
					//添加到project_validate集合
					project_validate.add(spy_list.get(j).getStudentPingJiaProject().getId());
				}
				big_project_validate.add(project_validate);
			}
			//存入验证集合
			request.setAttribute("evalute_validate",big_project_validate );
			//使用参数回显
			//获取courseList的值
			List<Course>courseList=new ArrayList<Course>();
			if(ActionContext.getContext().getSession().get("TeacherId")!=null)
				teacherId=(Integer) ActionContext.getContext().getSession().get("TeacherId");
			if(teacherId!=0)
				courseList=teacherService.getCourseByTeacherId(teacherId);
			//获取ClassList的值
			List<Course>temp=null;
			List<Classes>class_list=new ArrayList<Classes>();
			//做去重的HasheSet
			Set<Classes>class_set=new HashSet<Classes>();
			if(courseId!=null)
				temp=teacherService.getCoursesByCourseId(Integer.parseInt(courseId));
			//遍历list取出里面的学生信息，并把学生信息里面的班级信息给取出来
			for (Course course:temp) {
				//将每门课程的学生给取出来，对这个Set集合进行遍历
				for(Student student:course.getStudents()){
					//将所有学生的班级信息添加到班级的HashSet中，hashSet会自动去重
					class_set.add(student.getClasses());
				}
			}
			class_list.addAll(class_set);
			request.setAttribute("CourseList", courseList);
			request.setAttribute("ClassList", class_list);
			//返回选定状态
			request.setAttribute("courseKey",courseId );
			request.setAttribute("classKey", classId);
		}
		
	}
	
	public void setServletRequest(HttpServletRequest hsr) { 
		this.request=hsr;
	}
	
}
