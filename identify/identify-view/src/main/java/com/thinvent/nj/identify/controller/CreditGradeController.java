package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.identify.service.CreditGradeService;
import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.identify.entity.CreditGrade;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 企业信用级别 Controller
 *
 * @author wangwj
 * @date 2018-10-31
 */
@Controller
@RequestMapping(path = "/setting/credit/grades")
public class CreditGradeController extends BaseViewController {

	@Autowired
	private CreditGradeService creditGradeService;

	@RequiresPermissions("credit:view")
	@RequestMapping(method = RequestMethod.GET)
	public String toHtml() {
		return "creditGradeList";


	}


	@RequestMapping(path = "/search/page", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
		Page<CreditGrade> target = creditGradeService.findPage(getQueryParameter(params), getQueryMsg(params));
		return ResponseEntity.ok(target);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity get(@PathVariable("id") String id) {
		return ResponseEntity.ok(creditGradeService.get(id));
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity insert(@RequestBody Map<String, Object> params) {
		CreditGrade target = new CreditGrade();
		MapperUtil.copy(params, target);

		creditGradeService.insert(target);

		return ResponseEntity.ok();
	}


	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity update(@PathVariable("id") String id, @RequestBody Map<String, Object> params) {
		CreditGrade target = creditGradeService.get(id);
		MapperUtil.copy(params, target);

		creditGradeService.update(target);

		return ResponseEntity.ok();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity del(@PathVariable("id") String id) {
		creditGradeService.delete(id);
		return ResponseEntity.ok();
	}

	@RequestMapping(value = "/saveData")
	@ResponseBody
	public ResponseEntity saveData(@RequestBody Map<String, Object> map) {


		creditGradeService.updateFrom(map);
		return ResponseEntity.ok();

	}

}
