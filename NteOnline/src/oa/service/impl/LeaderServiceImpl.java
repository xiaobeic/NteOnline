package oa.service.impl;

import org.springframework.stereotype.Service;

import oa.baseDao.BaseDaoImpl;
import oa.bean.Leader;
import oa.service.LeaderService;

@Service
public class LeaderServiceImpl extends BaseDaoImpl<Leader> implements
		LeaderService {

	public Leader login(Leader leader) {

		return (Leader) getSession().createQuery(
				"from Leader where username='" + leader.getUsername()
						+ "' and password='" + leader.getPassword() + "'")
				.uniqueResult();
	}

}
