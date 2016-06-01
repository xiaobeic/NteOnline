package oa.service;

import oa.baseDao.BaseDao;
import oa.bean.Leader;

public interface LeaderService extends BaseDao<Leader> {

	public Leader login(Leader leader);
}
