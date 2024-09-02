package programmers.baemin.store.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import programmers.baemin.store.domain.Store;

@Mapper
public interface StoreMapper {
	public List<Store> findAll();

	public Store findById(int id);

	public void save(Store store);
}
