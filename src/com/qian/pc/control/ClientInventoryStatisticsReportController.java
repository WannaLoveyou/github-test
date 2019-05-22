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

import com.qian.entity.User;
import com.qian.service.ClientInventoryInfoService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.ClientInventoryStatisticsVo;
import com.qian.vo.FamilyCheckInfoReportHeads;
import com.qian.vo.Field;

/**
 * @author DUANKK
 * @version Create time：2019年3月6日11:04:05
 * @Description
 */
@Controller
@RequestMapping("/clientInventoryStatistics")
public class ClientInventoryStatisticsReportController {

	private static final String CLIENT_INVENTORY_STATISTICS_REPORT = "clientInventoryStatisticsReport/list";

	@Autowired
	private ClientInventoryInfoService clientInventoryInfoService;

	@RequiresUser
	@RequestMapping(value = "clientInventoryStatisticsReportList", method = RequestMethod.GET)
	public String storeFamilyCheckReportList(HttpServletRequest request, Map<String, Object> map) {

		return CLIENT_INVENTORY_STATISTICS_REPORT;
	}

	@RequestMapping(value = "getClientInventoryStatisticsReport", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getClientInventoryStatisticsReport(HttpServletRequest request,
			Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<ClientInventoryStatisticsVo> ClientInventoryStatisticsVos = getClientInventoryStatisticsVoList(request,map);

		result.put("rows", ClientInventoryStatisticsVos);

		int total = 0;

		for (ClientInventoryStatisticsVo clientInventoryStatisticsVo : ClientInventoryStatisticsVos) {
			total += clientInventoryStatisticsVo.getCount();
		}

		ClientInventoryStatisticsVo clientInventoryStatisticsVo = new ClientInventoryStatisticsVo();
		clientInventoryStatisticsVo.setCount(total);

		result.put("footer", clientInventoryStatisticsVo);

		return result;

	}

	@RequestMapping(value = "exportClientInventoryStatisticsReport", method = RequestMethod.POST)
	public void exportClientInventoryStatisticsReport(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> map)
			throws Exception {

		List<FamilyCheckInfoReportHeads> heads = ExportHeadsUtils.getExportFamilyCheckInfoReportHeads(request);

		List<ClientInventoryStatisticsVo> ClientInventoryStatisticsVos = getClientInventoryStatisticsVoList(request, map);

		ExportUtils.exportExcel(response, heads, ClientInventoryStatisticsVos, "客户库存统计报表.xls",
				"template-ClientInventoryStatisticsReport .xls");

	}

	private List<ClientInventoryStatisticsVo> getClientInventoryStatisticsVoList(HttpServletRequest request,
			Map<String, Object> map) {

		List<String> list = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {
			list.add("clientInfo.secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}

		ToolsBarUtils.getClientInventorySearchCondition(request, list, filed);

		List<ClientInventoryStatisticsVo> result = clientInventoryInfoService.getClientInventoryStatistics(list, filed);
		
		return result;
	}

}
