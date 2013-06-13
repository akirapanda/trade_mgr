package org.panda.trade.service.lc;

import java.util.List;

import org.panda.trade.entity.Goods;
import org.panda.trade.repository.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class GoodsService {
	@Autowired
	private GoodsDao goodsDao;

	public List<Goods> getGoodsByLcId(Long lcId) {
		return goodsDao.findByLcId(lcId);
	}

	public Goods getGoodsById(Long id) {
		return goodsDao.findById(id);
	}

	@Transactional(readOnly = false)
	public void save(Goods goods) {
		goodsDao.save(goods);
	}
}
