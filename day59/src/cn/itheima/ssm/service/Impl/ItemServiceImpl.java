package cn.itheima.ssm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.ssm.mapper.ItemMapper;
import cn.itheima.ssm.po.Item;
import cn.itheima.ssm.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	public List<Item> queryItemList() {
		
		List<Item> list = itemMapper.selectByExample(null);
		return list;
	}

	public Item queryItemById(Integer id) {
		
		Item item = itemMapper.selectByPrimaryKey(id);
		
		return item;
	}

	
	public void updateByPrimaryKeySelective(Item item) {
		this.itemMapper.updateByPrimaryKeySelective(item);
	}
}
