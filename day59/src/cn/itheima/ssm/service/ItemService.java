package cn.itheima.ssm.service;

import java.util.List;

import cn.itheima.ssm.po.Item;

public interface ItemService {

	public List<Item> queryItemList();
	
	public Item queryItemById(Integer id);
	
	public void updateByPrimaryKeySelective(Item item);
}
