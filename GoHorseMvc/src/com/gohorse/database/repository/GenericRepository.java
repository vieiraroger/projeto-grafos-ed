package com.gohorse.database.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.gohorse.database.model.Entity;
import com.gohorse.database.service.CrudInterface;


public class GenericRepository<T extends Entity> implements CrudInterface<T> {

	private final StoreData<T> storeData;

	public GenericRepository(Class<T> type) {
		this.storeData = new StoreData<T>(type);
	}

	@Override
	public T find(Integer id) {
		T obj = null;
		Map<Integer, T> map = storeData.deserialize();
		if (map != null) {
			Collection<T> col = map.values();
			for (T objCol : col) {
				if (objCol.getId().intValue() == id) {
					obj = objCol;
					continue;
				}
			}
		}
		return obj;
	}

	@Override
	public T save(T obj) {
		Integer id = 1;
		Map<Integer, T> map = storeData.deserialize();
		if (map != null) {
			Collection<T> col = map.values();
			for (T objCol : col) {
				id = objCol.getId();
			}
			id++;
			obj.setId(id);
			map.put(id, obj);
		} else {
			map = new HashMap<Integer, T>();
			obj.setId(id);
			map.put(id, obj);
		}
		storeData.serialize(map);
		return obj;
	}

	@Override
	public T update(T obj) {
		Integer id = obj.getId();
		T objMap = null;
		Map<Integer, T> map = storeData.deserialize();
		if (map != null) {
			objMap = map.get(id);
			if (objMap != null) {
				map.put(id, obj);
			}
		}
		storeData.serialize(map);
		return objMap;
	}

	@Override
	public void delete(Integer id) {
		T objMap = null;
		Map<Integer, T> map = storeData.deserialize();
		if (map != null) {
			objMap = map.get(id);
			if (objMap != null) {
				map.remove(id);
			}
		}
		storeData.serialize(map);
	}

	@Override
	public Collection<T> findAll() {
		Collection<T> col = null;
		Map<Integer, T> map = storeData.deserialize();
		if (map != null) {
			col = map.values();
		}
		return col;
	}

}
