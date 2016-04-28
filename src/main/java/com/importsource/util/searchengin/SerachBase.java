package com.importsource.util.searchengin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SerachBase {
	// details 存储搜素对象的详细信息，其中key作为区分Object的唯一标识
	private HashMap<String, Object> details = new HashMap<String, Object>();
	// 对于参与搜索的关键词，这里采用的稀疏数组存储，也可以采用HashMap来存储，定义格式如下
	// private static HashMap<Integer, HashSet<String>> keySearch = new
	// HashMap<Integer, HashSet<String>>();
	// HashMap中额key值相当于稀疏数组中的下标，value相当于稀疏数组在该位置的值
	private final static int maxLength = Character.MAX_VALUE;
	@SuppressWarnings("unchecked")
	private HashSet<String>[] keySearch = new HashSet[maxLength];

	/**
	 * @Description: 实现单例模式，采用Initialization on Demand Holder加载
	 * @Version:1.1.0
	 */
	private static class lazyLoadSerachBase {
		private static final SerachBase serachBase = new SerachBase();
	}

	/**
	 * 这里把构造方法设置成私有为的是单例模式
	 */
	private SerachBase() {

	}

	/**
	 * @return
	 * @Description: 获取单例
	 */
	public static SerachBase getSerachBase() {
		return lazyLoadSerachBase.serachBase;
	}

	/**
	 * @param id
	 * @return
	 * @Description: 根据id获取详细
	 */
	public Object getObject(String id) {
		return details.get(id);
	}

	/**
	 * @param ids
	 * @return
	 * @Description: 根据ids获取详细，id之间用","隔开
	 */
	public List<Object> getObjects(String ids) {
		if (ids == null || "".equals(ids)) {
			return null;
		}
		List<Object> objs = new ArrayList<Object>();
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			objs.add(getObject(id));
		}
		return objs;
	}

	/**
	 * @param key
	 * @return
	 * @Description: 根据搜索词查找对应的id，id之间用","分割
	 */
	public String getIds(String key) {
		if (key == null || "".equals(key)) {
			return null;
		}
		// 查找
		// idTimes存储搜索词每个字符在id中是否出现
		HashMap<String, Integer> idTimes = new HashMap<String, Integer>();
		// ids存储出现搜索词中的字符的id
		HashSet<String> ids = new HashSet<String>();

		// 从搜索库中去查找
		for (int i = 0; i < key.length(); i++) {
			int at = key.charAt(i);
			// 搜索词库中没有对应的字符，则进行下一个字符的匹配
			if (keySearch[at] == null) {
				continue;
			}
			for (Object obj : keySearch[at].toArray()) {
				String id = (String) obj;
				int times = 1;
				if (ids.contains(id)) {
					times += idTimes.get(id);
					idTimes.put(id, times);
				} else {
					ids.add(id);
					idTimes.put(id, times);
				}
			}
		}

		// 使用数组排序
		List<SortBean> sortBeans = new ArrayList<SortBean>();
		for (String id : ids) {
			SortBean sortBean = new SortBean();
			sortBeans.add(sortBean);
			sortBean.setId(id);
			sortBean.setTimes(idTimes.get(id));
		}
		Collections.sort(sortBeans, new Comparator<SortBean>() {
			public int compare(SortBean o1, SortBean o2) {
				return o2.getTimes() - o1.getTimes();
			}
		});

		// 构建返回字符串
		StringBuffer sb = new StringBuffer();
		for (SortBean sortBean : sortBeans) {
			sb.append(sortBean.getId());
			sb.append(",");
		}

		// 释放资源
		idTimes.clear();
		idTimes = null;
		ids.clear();
		ids = null;
		sortBeans.clear();
		sortBeans = null;

		// 返回
		return sb.toString();
	}

	/**
	 * @param id
	 * @param searchKey
	 * @param obj
	 * @Description: 添加搜索记录
	 */
	public void add(String id, String searchKey, Object obj) {
		// 参数有部分为空，不加载
		if (id == null || searchKey == null || obj == null) {
			return;
		}
		// 保存对象
		details.put(id, obj);
		// 保存搜索词
		addSearchKey(id, searchKey);
	}

	/**
	 * @param id
	 * @param searchKey
	 * @Description: 将搜索词加入到搜索域中
	 */
	private void addSearchKey(String id, String searchKey) {
		// 参数有部分为空，不加载
		// 这里是私有方法，可以不做如下判断，但为了设计规范，还是加上
		if (id == null || searchKey == null) {
			return;
		}
		// 下面采用的是字符分词，这里也可以使用现在成熟的其他分词器
		for (int i = 0; i < searchKey.length(); i++) {
			// at值相当于是数组的下标，id组成的HashSet相当于数组的值
			int at = searchKey.charAt(i);
			if (keySearch[at] == null) {
				HashSet<String> value = new HashSet<String>();
				keySearch[at] = value;
			}
			keySearch[at].add(id);
		}
	}

}