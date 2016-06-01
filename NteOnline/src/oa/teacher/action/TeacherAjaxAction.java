package oa.teacher.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import oa.baseDao.BaseAction;
import oa.bean.Classes;
import oa.bean.Course;
import oa.bean.CoursePingJiaProject;
import oa.bean.Student;
import oa.bean.StudentPingJiaProject;
import oa.bean.StudentPingJiaScore;
import oa.bean.StudentPingJiaType;
import oa.bean.StudentPingYu;
import oa.bean.Teacher;
import oa.bean.TeacherScore;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TeacherAjaxAction  extends BaseAction<Teacher>implements ServletRequestAware{
	
	private String result;
	private String classId;
	private String linkId;
	private String courseId;
	private String aspectId;
	private String password1;
	private String password2;
	
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getAspectId() {
		return aspectId;
	}
	public void setAspectId(String aspectId) {
		this.aspectId = aspectId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * ajax返回学生的信息
	 * @return
	 * @throws Exception
	 */
	public String listStudents()throws Exception{
		if (classId!=null) {
			List<Student>list=teacherService.getStudentsByClassId(Integer.parseInt(classId));
			//将其set置空，否则序列化时会产生堆栈溢出，下面同上
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCourses(null);
				list.get(i).setClasses(null);
			}
			ObjectMapper mapper =new ObjectMapper();
			result=mapper.writeValueAsString(list);
		}
		return SUCCESS;
	}
	/**
	 * ajax返回环节信息
	 * @return
	 * @throws Exception
	 */
	public String listLinks() throws Exception{
		List<StudentPingJiaProject> list=teacherService.getStudentLinks();
		//将其set置空，否则序列化时会产生堆栈溢出，下面同上
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setStudentPingJiaTypes(null);
			
		}
		ObjectMapper mapper =new ObjectMapper();
		result=mapper.writeValueAsString(list);
		return SUCCESS;
	}
	/**
	 * ajax返回评价的方面信息
	 * @return
	 * @throws Exception
	 */
	public String listAspects()throws Exception{
		if(linkId!=null){
			List<StudentPingJiaType>list=teacherService.getTypeById(Integer.parseInt(linkId));
			//将其set置空，否则序列化时会产生堆栈溢出，下面同上
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setStudentPingJiaProject(null);
			}
			ObjectMapper mapper =new ObjectMapper();
			result=mapper.writeValueAsString(list);
		}
		return SUCCESS;
	}
	/**
	 * ajax返回student评价的环节信息
	 * @return
	 * @throws Exception
	 */
	public String listStudentAspects()throws Exception{
		List<StudentPingJiaProject>list=teacherService.getStudentCourseProject();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setStudentPingJiaTypes(null);
		}
		ObjectMapper mapper =new ObjectMapper();
		result=mapper.writeValueAsString(list);
		return SUCCESS;
	}
	/**
	 * ajax返回教师课程的班级信息
	 * @return
	 * @throws Exception
	 */
	public String listClasses()throws Exception{
		List<Course>list=null;
		List<Classes>class_list=new ArrayList<Classes>();
		//做去重的HasheSet
		Set<Classes>class_set=new HashSet<Classes>();
		if(courseId!=null)
			list=teacherService.getCoursesByCourseId(Integer.parseInt(courseId));
		//遍历list取出里面的学生信息，并把学生信息里面的班级信息给取出来
		for (Course course:list) {
			//将每门课程的学生给取出来，对这个Set集合进行遍历
			for(Student student:course.getStudents()){
				//将所有学生的班级信息添加到班级的HashSet中，hashSet会自动去重
				class_set.add(student.getClasses());
			}
		}
		//对其中的集合类型进行过滤即设空处理
		Iterator<Classes>it=class_set.iterator();
		while(it.hasNext()){
			Classes classes=it.next();
			classes.setLeaders(null);
			classes.setStudents(null);
			class_list.add(classes);
		}
		ObjectMapper mapper =new ObjectMapper();
		result=mapper.writeValueAsString(class_list);
		return SUCCESS;
	}
	/**
	 * 通过ajax返回班级里学生的评价信息汇总
	 * @return
	 * @throws Exception
	 */
	public String listEcharts()throws Exception{
		if(classId!=null&&aspectId!=null){
			List<Double>list=teacherService.getStudentEvaluteScore(Integer.parseInt(classId),Integer.parseInt(aspectId));
			double best=0;//优秀 80以上
			double good=0;//良好 70以上
			double general=0;//一般70以下
			//对分值进行统计
			for (int i = 0; i < list.size(); i++) {
				double score=list.get(i);
				if(score>=80){
					best++;
				}else if(score>=70){
					good++;
				}else{
					general++;
				}
			}
			TeacherScore ts1=new TeacherScore();
			TeacherScore ts2=new TeacherScore();
			TeacherScore ts3=new TeacherScore();
			ts1.setGrade("优秀");ts1.setScore(best);
			ts2.setGrade("良好");ts2.setScore(good);
			ts3.setGrade("一般");ts3.setScore(general);
			List<TeacherScore>ts=new ArrayList<TeacherScore>();
			ts.add(ts1);
			ts.add(ts2);
			ts.add(ts3);
			ObjectMapper mapper =new ObjectMapper();
			result=mapper.writeValueAsString(ts);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("evaluteCount", list.size());
		}
		
		return SUCCESS;
	}
	public String updatePwd()throws Exception{
		int teacherId = 0;
		if(ActionContext.getContext().getSession().get("TeacherId")!=null){
			teacherId=(Integer) ActionContext.getContext().getSession().get("TeacherId");
			if(teacherService.isTruePwd(teacherId, password1)){
				int res=teacherService.updateTeacherPwd(teacherId, password2);
				ObjectMapper mapper =new ObjectMapper();
				result=mapper.writeValueAsString(res);
			}
		}
		return SUCCESS;
	}
	public String showEvaluteInfo()throws Exception{
		String studentId=request.getParameter("studentId");
		String courseId=request.getParameter("courseId");
		String projectId=request.getParameter("projectId");
		if(studentId!=null&&courseId!=null&&projectId!=null){
			//获取指定学生、指定课程、指定project的评语
			List<StudentPingYu>evalute_info_list=teacherService.getEvaluteInfo(Integer.parseInt(studentId), Integer.parseInt(courseId),Integer.parseInt( projectId));
			//去重操作
			for (int i = 0; i < evalute_info_list.size(); i++) {
				evalute_info_list.get(i).setStudent(null);
				evalute_info_list.get(i).setCourse(null);
				evalute_info_list.get(i).setStudentPingJiaProject(null);
				evalute_info_list.get(i).setTeacher(null);
			}
			//依次获取里面的值
			//评语信息
			ObjectMapper mapper =new ObjectMapper();
			result=mapper.writeValueAsString(evalute_info_list);
		}
		return SUCCESS;
	}
	public String showEvaluteScore()throws Exception{
		String studentId=request.getParameter("studentId");
		String courseId=request.getParameter("courseId");
		String projectId=request.getParameter("projectId");
		if(studentId!=null&&courseId!=null&&projectId!=null){
			//获取指定学生、指定课程、指定project的type分值
			List<StudentPingJiaScore>evalute_score_list=teacherService.getEvaluteTypeScore(Integer.parseInt(studentId), Integer.parseInt(courseId),Integer.parseInt( projectId));
			//去重操作
			for (int i = 0; i < evalute_score_list.size(); i++) {
				evalute_score_list.get(i).setCourse(null);
				evalute_score_list.get(i).setStudent(null);
				evalute_score_list.get(i).setStudentPingJiaProject(null);
				evalute_score_list.get(i).setStudentPingJiaType(null);
			}
			//依次获取里面的值
			//评语信息
			ObjectMapper mapper =new ObjectMapper();
			result=mapper.writeValueAsString(evalute_score_list);
		}
		return SUCCESS;
	}
	public void setServletRequest(HttpServletRequest hsr) {
		request=hsr;
	}
}
