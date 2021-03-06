package com.sist.mar.recipe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.mar.cmn.Message;
import com.sist.mar.recipe.domain.RecipeVO;
import com.sist.mar.recipe.domain.SimpleItemVO;
import com.sist.mar.recipe.service.RecipeServiceImpl;

@Controller
public class RecipeController {
	
//	▼ 변수 ===============================================================
	
	final Logger LOG = LoggerFactory.getLogger(RecipeController.class);
	
	final String VIEW_NAME = "recipe/recipe_reg";
	final String VIEW_NAME2 = "recipe/recipe_detail";
	final String VIEW_NAME3 = "recipe/recipe_mod";
	
	@Autowired
	RecipeServiceImpl recipeService;
	
	
//	▼ 생성자 ==============================================================	
	
	public RecipeController() {}
	
	
//	▼ 메소드 ===============================================================
	
	@RequestMapping(value = "recipe/recipe_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String view(Model model) throws Exception {
		return VIEW_NAME;
	}
	
	@RequestMapping(value = "recipe/recipe_view2.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String view2(Model model, @RequestParam(value = "recipeNo", required = false)String recipeNo) throws Exception {
		
		model.addAttribute("recipeNo", recipeNo);
		return VIEW_NAME2;
	}
	
	@RequestMapping(value = "recipe/recipe_view3.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String view3(Model model, @RequestParam(value = "recipeNo", required = false)String recipeNo) throws Exception {
		
		model.addAttribute("recipeNo", recipeNo);
		return VIEW_NAME3;
	}
	
	@RequestMapping(value = "recipe/do_insert.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(RecipeVO recipeVO) throws Exception {
		
		LOG.debug("doInsert");
		Message message = new Message();
		message.setMsgId(Integer.toString(recipeService.upRegisterRecipe(recipeVO)));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("레시피 등록이 완료되었습니다.");
		else message.setMsgContents("레시피 등록에 실패하였습니다.");
		
		Gson gson = new Gson();
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
		
	}
	
	@RequestMapping(value = "recipe/do_delete.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(RecipeVO recipeVO) throws Exception {
		
		Message message = new Message();
		message.setMsgId(Integer.toString(recipeService.upDeleteRecipe(recipeVO)));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("레시피 삭제가 완료되었습니다.");
		else message.setMsgContents("레시피 삭제에 실패하였습니다.");
		
		Gson gson = new Gson();
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
		
	}
	
	@RequestMapping(value = "recipe/do_update.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(RecipeVO recipeVO) throws Exception {
		
		Message message = new Message();
		message.setMsgId(Integer.toString(recipeService.upUpdateRecipe(recipeVO)));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("레시피 수정이 완료되었습니다.");
		else message.setMsgContents("레시피 수정에 실패하였습니다.");
		
		Gson gson = new Gson();
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
		
	}
	
	@RequestMapping(value = "recipe/do_select.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public RecipeVO doSelect(RecipeVO recipeVO) throws Exception {
		return recipeService.doSelectRecipe(recipeVO);
	}
	
	
	@RequestMapping(value = "recipe/do_show_relevant_item.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doShowRelevantItem(RecipeVO recipeVO) throws Exception {
			
		List<SimpleItemVO> list = recipeService.doShowRelevantItem(recipeVO);
		Gson gson = new Gson();
		return gson.toJson(list.toArray());
		
	}
	
}
