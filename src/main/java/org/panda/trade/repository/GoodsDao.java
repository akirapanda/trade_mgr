package org.panda.trade.repository;

import java.util.List;

import org.panda.trade.entity.Goods;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoodsDao extends PagingAndSortingRepository<Goods, Long>,
		JpaSpecificationExecutor<Goods> {
	Goods findById(Long id);

	List<Goods> findByLcId(Long lcId);
}
