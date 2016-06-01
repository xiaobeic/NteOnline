package oa.admin.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import oa.baseDao.BaseAction;
import oa.bean.Course;
import oa.bean.Teacher;
import util.HqlHelper;

@Controller
@Scope("prototype")
public class CourseManagerAction extends BaseAction<Course> {

	/**
	 * 获取课程
	 * 
	 * @return
	 */
	public String list() {

		new HqlHelper(Course.class, "course").addWhereCondition(
				getModel().getCourseName() != null
						&& !getModel().getCourseName().equals(""),
				"courseName like ?", "%" + getModel().getCourseName() + "%")
				.addWhereCondition(
						teacherName != null && !teacherName.equals(""),
						"teacher.teacherName like ?", "%" + teacherName + "%")
				.preparePageBeanForStruts2(1, 10, courseService);
		return "list";
	}

	/**
	 * 
	 * 跳转添加课程页面
	 * 
	 * @return
	 */
	public String addCourseUI() {

		List<Teacher> teachers = teacherService.findAll();
		ActionContext.getContext().put("teachers", teachers);
		return "addcourse";
	}

	/**
	 * 添加课程
	 * 
	 * @return
	 */
	public String addCourse() {

		Course course = new Course();
		course.setCourseName(getModel().getCourseName());
		course.setTeacher(teacherService.getById(getModel().getId()));
		courseService.save(course);
		return "tolist";
	}

	/**
	 * 跳转到更新课程页面
	 * 
	 * @return
	 */
	public String updateCourseUI() {

		ActionContext.getContext().getValueStack().push(
				courseService.getById(getModel().getId()));
		List<Teacher> teachers = teacherService.findAll();
		ActionContext.getContext().put("teachers", teachers);
		return "updateCourse";
	}

	/**
	 * 更新课程
	 * 
	 * @return
	 */
	public String updateCourse() {

		Course course = courseService.getById(getModel().getId());
		course.setCourseName(getModel().getCourseName());
		course.setTeacher(teacherService.getById(teacherid));
		return "tolist";
	}

	/**
	 * 删除课程
	 * 
	 * @return
	 */
	public String deleteCourse() {

		courseService.delete(getModel().getId());
		return "tolist";
	}

	private String teacherName;
	private Integer teacherid;

	public Integer getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}
