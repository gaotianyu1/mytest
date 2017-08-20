package cn.itheima.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.itheima.ssm.po.Item;
import cn.itheima.ssm.po.ItemVo;
import cn.itheima.ssm.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	/*@RequestMapping("/queryItem.do")
	public ModelAndView queryItem(){
		
		ModelAndView mav = new ModelAndView();
		
		List<Item>itemList=new ArrayList<Item>();

		List<Item> queryItemList = this.itemService.queryItemList();

		mav.addObject("itemList", queryItemList);
		
		mav.setViewName("item/itemList");
		
		return mav;
	}*/
	
	@RequestMapping("/queryItemById.do")
	public String queryItemById(Model model, Integer id){
		
		/*String id = request.getParameter("id");
		Item item = this.itemService.queryItemById(Integer.parseInt(id));*/
		Item item = this.itemService.queryItemById(id);
		model.addAttribute("item",item);
		
		return "item/itemEdit";
		
	}
	
	@RequestMapping("/updateItem.do")
	public String update(Item item){

		try {
			this.itemService.updateByPrimaryKeySelective(item);
		} catch (Exception e) {
			return "common/failure";
		}
		return "common/success";
	}
	
	@RequestMapping("/queryItem.do")
	public ModelAndView queryItem(ItemVo itemVo){

		ModelAndView mav =new ModelAndView();
		
		List<Item> itemList = this.itemService.queryItemList();
		
		mav.addObject("itemList", itemList);
		
		mav.setViewName("item/itemList");
		
		return mav;
	
	}
}
