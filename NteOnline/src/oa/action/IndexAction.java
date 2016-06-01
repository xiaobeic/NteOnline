package oa.action;

import oa.baseDao.BaseAction;
import oa.bean.Admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction<Admin> {

	public String top() {

		return "top";
	}

	public String left() {

		return "left";
	}

	public String right() {

		return "right";
	}

	public String main() {

		return "main";
	}
}
