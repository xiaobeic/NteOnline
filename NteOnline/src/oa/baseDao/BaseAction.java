package oa.baseDao;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import oa.bean.Classes;
import oa.bean.StudentPingJiaProject;
import oa.bean.StudentPingJiaType;
import oa.bean.StudentPingYu;
import oa.service.AdminService;
import oa.service.ClassesService;
import oa.service.CoursePingJiaProjectService;
import oa.service.CoursePingJiaTypeService;
import oa.service.CoursePingYuService;
import oa.service.CourseScoreService;
import oa.service.CourseService;
import oa.service.DuDaoYuanToCoursePingJiaProjectService;
import oa.service.DuDaoYuanToCoursePingJiaScoreService;
import oa.service.DuDaoYuanToCoursePingJiaTypeService;
import oa.service.DuDaoYuanToCoursePingYuService;
import oa.service.LeaderService;
import oa.service.StudentPingJiaProjectService;
import oa.service.StudentPingJiaScoreService;
import oa.service.StudentPingJiaTypeService;
import oa.service.StudentPingYuService;
import oa.service.StudentService;
import oa.service.TeacherService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {

	@Resource
	protected StudentService studentService;
	@Resource
	protected AdminService adminService;
	@Resource
	protected ClassesService classesService;
	@Resource
	protected CoursePingJiaProjectService coursePingJiaProjectService;
	@Resource
	protected CoursePingJiaTypeService coursePingJiaTypeService;
	@Resource
	protected CoursePingYuService coursePingYuService;
	@Resource
	protected CourseScoreService courseScoreService;
	@Resource
	protected CourseService courseService;
	@Resource
	protected DuDaoYuanToCoursePingJiaProjectService daoYuanToCoursePingJiaProjectService;
	@Resource
	protected DuDaoYuanToCoursePingJiaScoreService daoYuanToCoursePingJiaScoreService;
	@Resource
	protected DuDaoYuanToCoursePingJiaTypeService daoYuanToCoursePingJiaTypeService;
	@Resource
	protected DuDaoYuanToCoursePingYuService daoYuanToCoursePingYuService;
	@Resource
	protected LeaderService leaderService;
	@Resource
	protected StudentPingJiaProjectService studentPingJiaProjectService;
	@Resource
	protected StudentPingJiaTypeService studentPingJiaTypeService;
	@Resource
	protected StudentPingJiaScoreService studentPingJiaScoreService;
	@Resource
	protected StudentPingYuService studentPingYuService;
	@Resource
	protected TeacherService teacherService;

	protected T model;

	public BaseAction() {
		try {
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[0];

			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}
}
