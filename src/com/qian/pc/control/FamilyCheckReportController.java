package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.code.FamilyCheckInfoStateCode;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.service.ClientInfoService;
import com.qian.service.FamilyCheckInfoService;
import com.qian.service.SecondCategoryService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.CheckOperatorFamilyCheckInfoVo;
import com.qian.vo.FamilyCheckInfoReportHeads;
import com.qian.vo.Field;
import com.qian.vo.StoreFamilyCheckInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-12 下午4:40:12
 * @Description
 */
@Controller
@RequestMapping("/familyCheckReport")
public class FamilyCheckReportController {

	private static final String STORE_FAMILY_CHECK_REPORT_LIST = "familyCheckReport/store_family_check_report_list";

	private static final String CHECK_OPERATOR_FAMILY_CHECK_REPORT_LIST = "familyCheckReport/check_operator_family_check_report_list";

	@Autowired
	private ClientInfoService clientInfoService;

	@Autowired
	private SecondCategoryService secondCategoryService;

	@Autowired
	private FamilyCheckInfoService familyCheckInfoService;

	@RequiresUser
	@RequestMapping(value = "storeFamilyCheckReportList", method = RequestMethod.GET)
	public String storeFamilyCheckReportList(HttpServletRequest request, Map<String, Object> map) {

		return STORE_FAMILY_CHECK_REPORT_LIST;
	}

	@RequiresUser
	@RequestMapping(value = "checkOperatorFamilyCheckReportList", method = RequestMethod.GET)
	public String checkOperatorFamilyCheckReportList(HttpServletRequest request, Map<String, Object> map) {

		return CHECK_OPERATOR_FAMILY_CHECK_REPORT_LIST;
	}

	@RequestMapping(value = "getStoreFamilyCheckInfo", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getStoreFamilyCheckInfo(HttpServletRequest request,
			Map<String, Object> map, String family_check_time_begin_time, String family_check_time_end_time) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<StoreFamilyCheckInfoVo> storeFamilyCheckInfoVoList = getStoreFamilyCheckInfoVoList(request, map,
				family_check_time_begin_time, family_check_time_end_time);

		result.put("rows", storeFamilyCheckInfoVoList);

		int check_client_num = 0;
		int total_client_num = 0;

		for (StoreFamilyCheckInfoVo storeFamilyCheckInfoVo : storeFamilyCheckInfoVoList) {
			check_client_num += storeFamilyCheckInfoVo.getCheck_client_num();
			total_client_num += storeFamilyCheckInfoVo.getTotal_client_num();
		}

		StoreFamilyCheckInfoVo storeFamilyCheckInfoVoTotal = new StoreFamilyCheckInfoVo();
		storeFamilyCheckInfoVoTotal.setCheck_client_num(check_client_num);
		storeFamilyCheckInfoVoTotal.setTotal_client_num(total_client_num);
		storeFamilyCheckInfoVoTotal.setCheck_per(1.0 * check_client_num / total_client_num);

		result.put("footer", storeFamilyCheckInfoVoTotal);

		return result;

	}

	@RequestMapping(value = "exportStoreFamilyCheckInfo", method = RequestMethod.POST)
	public void exportStoreFamilyCheckInfo(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> map, String family_check_time_begin_time, String family_check_time_end_time)
			throws Exception {

		List<FamilyCheckInfoReportHeads> heads = ExportHeadsUtils.getExportFamilyCheckInfoReportHeads(request);

		List<StoreFamilyCheckInfoVo> storeFamilyCheckInfoVos = getStoreFamilyCheckInfoVoList(request, map,
				family_check_time_begin_time, family_check_time_end_time);

		ExportUtils.exportExcel(response, heads, storeFamilyCheckInfoVos, "门店入户安检统计.xls",
				"template-StoreFamilyCheckInfo.xls");

	}

	private List<StoreFamilyCheckInfoVo> getStoreFamilyCheckInfoVoList(HttpServletRequest request,
			Map<String, Object> map, String family_check_time_begin_time, String family_check_time_end_time) {

		List<String> list = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {
			list.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}

		ToolsBarUtils.getClientInfoSearchCondition(request, list, filed);

		Map<Integer, Integer> checkClientNumMap = clientInfoService.getFamilyCheckClientNumByStore(list, filed,
				family_check_time_begin_time, family_check_time_end_time);

		Map<Integer, Integer> totalClientNumMap = clientInfoService.getTotalClientNumByStore(list, filed);

		List<StoreFamilyCheckInfoVo> result = new ArrayList<StoreFamilyCheckInfoVo>();

		List<SecondCategory> secondCategorys = secondCategoryService.getAllList();

		for (SecondCategory secondCategory : secondCategorys) {

			if (totalClientNumMap.get(secondCategory.getId()) == null
					|| totalClientNumMap.get(secondCategory.getId()) == 0) {
				continue;
			}

			StoreFamilyCheckInfoVo storeFamilyCheckInfoVo = new StoreFamilyCheckInfoVo();
			storeFamilyCheckInfoVo.setSecond_category_id(secondCategory.getId());
			storeFamilyCheckInfoVo.setSecond_category_name(secondCategory.getSecond_category_name());

			if (checkClientNumMap.get(secondCategory.getId()) != null) {
				storeFamilyCheckInfoVo.setCheck_client_num(checkClientNumMap.get(secondCategory.getId()));
			}

			storeFamilyCheckInfoVo.setTotal_client_num(totalClientNumMap.get(secondCategory.getId()));

			storeFamilyCheckInfoVo.setCheck_per(
					1.0 * storeFamilyCheckInfoVo.getCheck_client_num() / storeFamilyCheckInfoVo.getTotal_client_num());

			result.add(storeFamilyCheckInfoVo);
		}

		return result;
	}

	@RequestMapping(value = "getCheckOperatorFamilyCheckInfo", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCheckOperatorFamilyCheckInfo(HttpServletRequest request,
			Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<CheckOperatorFamilyCheckInfoVo> checkOperatorFamilyCheckInfoVoList = getCheckOperatorFamilyCheckInfoList(
				request, map);

		result.put("rows", checkOperatorFamilyCheckInfoVoList);

		int check_order_num = 0;
		int check_client_num = 0;

		for (CheckOperatorFamilyCheckInfoVo checkOperatorFamilyCheckInfo : checkOperatorFamilyCheckInfoVoList) {
			check_order_num += checkOperatorFamilyCheckInfo.getCheck_order_num();
			check_client_num += checkOperatorFamilyCheckInfo.getCheck_client_num();
		}

		CheckOperatorFamilyCheckInfoVo checkOperatorFamilyCheckInfoTotal = new CheckOperatorFamilyCheckInfoVo();
		checkOperatorFamilyCheckInfoTotal.setCheck_client_num(check_client_num);
		checkOperatorFamilyCheckInfoTotal.setCheck_order_num(check_order_num);

		result.put("footer", checkOperatorFamilyCheckInfoTotal);

		return result;

	}

	private List<CheckOperatorFamilyCheckInfoVo> getCheckOperatorFamilyCheckInfoList(HttpServletRequest request,
			Map<String, Object> map) {

		List<String> list = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {
			list.add("check_operator.secondCategory.id  = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}

		list.add("familyCheckInfoState.id  = ?");
		filed.addInt(FamilyCheckInfoStateCode.HAS_CHECK); // 取对应门店

		ToolsBarUtils.getFamilyCheckInfoSearchCondition(request, list, filed);

		List<CheckOperatorFamilyCheckInfoVo> checkOperatorFamilyCheckInfoVos = familyCheckInfoService
				.getFamilyCheckClientNumByStore(list, filed);

		return checkOperatorFamilyCheckInfoVos;
	}

	@RequestMapping(value = "exportCheckOperatorFamilyCheckInfo", method = RequestMethod.POST)
	public void exportCheckOperatorFamilyCheckInfo(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> map) throws Exception {

		List<FamilyCheckInfoReportHeads> heads = ExportHeadsUtils.getExportFamilyCheckInfoReportHeads(request);

		List<CheckOperatorFamilyCheckInfoVo> checkOperatorFamilyCheckInfoVos = getCheckOperatorFamilyCheckInfoList(
				request, map);

		ExportUtils.exportExcel(response, heads, checkOperatorFamilyCheckInfoVos, "安检员入户安检统计.xls",
				"template-CheckOperatorFamilyCheckInfo.xls");

	}
}
