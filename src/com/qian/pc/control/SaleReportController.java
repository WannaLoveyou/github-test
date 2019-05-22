package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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

import com.qian.code.OrderInfoStateCode;
import com.qian.entity.AirBottleType;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.service.AirBottleTrackingRecordService;
import com.qian.service.AirBottleTypeService;
import com.qian.service.OrderService;
import com.qian.service.SecondCategoryService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.ExportOrderInfoReportHeads;
import com.qian.vo.Field;
import com.qian.vo.ReturnBottleInfoVo;
import com.qian.vo.SaleReportInfoSummary;
import com.qian.vo.SaleReportInfoSummaryByAirBottleType;
import com.qian.vo.SaleReportInfoSummaryByDifferentPrice;
import com.qian.vo.SaleReportInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-24 下午4:18:45
 * @Description
 */
@Controller
@RequestMapping("/saleReport")
public class SaleReportController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private SecondCategoryService secondCategoryService;

	@Autowired
	private AirBottleTypeService airBottleTypeService;

	@Autowired
	private AirBottleTrackingRecordService airBottleTrackingRecordService;

	private static final String STORE_SALE_REPORT_LIST = "saleReport/store_sale_report_list";
	private static final String STORE_SALE_DETAIL_REPORT_LIST = "saleReport/store_sale_detail_report_list";

	private static final String STORE_SALE_REPORT_BY_AIR_BOTTLE_TYPE_LIST = "saleReport/store_sale_report_by_air_bottle_type_list";
	private static final String STORE_SALE_DETAIL_REPORT_BY_AIR_BOTTLE_TYPE_LIST = "saleReport/store_sale_detail_report_by_air_bottle_type_list";

	private static final String STORE_SALE_REPORT_BY_DIFFERENT_PRICE_LIST = "saleReport/store_sale_report_by_different_price_list";
	private static final String STORE_SALE_DETAIL_REPORT_BY_BY_DIFFERENT_PRICE_LIST = "saleReport/store_sale_detail_report_by_different_price_list";
	
	private static final String STORE_SALE_REPORT_BY_SUBTOTAL_LIST = "saleReport/store_sale_report_by_subtotal_list";

	@RequiresUser
	@RequestMapping(value = "storeSaleReportList", method = RequestMethod.GET)
	public String storeSaleReportList(HttpServletRequest request, Map<String, Object> map) {

		return STORE_SALE_REPORT_LIST;
	}

	@RequiresUser
	@RequestMapping(value = "storeSaleDetailReportList", method = RequestMethod.GET)
	public String storeSaleDetailReportList(HttpServletRequest request, Map<String, Object> map) {

		return STORE_SALE_DETAIL_REPORT_LIST;
	}

	@RequiresUser
	@RequestMapping(value = "storeSaleReportByAirBottleTypeList", method = RequestMethod.GET)
	public String storeSaleReportByAirBottleTypeList(HttpServletRequest request, Map<String, Object> map) {

		return STORE_SALE_REPORT_BY_AIR_BOTTLE_TYPE_LIST;
	}

	@RequiresUser
	@RequestMapping(value = "storeSaleDetailReportByAirBottleTypeList", method = RequestMethod.GET)
	public String storeSaleDetailReportByAirBottleTypeList(HttpServletRequest request, Map<String, Object> map) {

		return STORE_SALE_DETAIL_REPORT_BY_AIR_BOTTLE_TYPE_LIST;
	}

	@RequiresUser
	@RequestMapping(value = "storeSaleReportByDifferentPriceList", method = RequestMethod.GET)
	public String storeSaleReportByDifferentPriceList(HttpServletRequest request, Map<String, Object> map) {

		return STORE_SALE_REPORT_BY_DIFFERENT_PRICE_LIST;
	}

	@RequiresUser
	@RequestMapping(value = "storeSaleDetailReportByDifferentPriceList", method = RequestMethod.GET)
	public String storeSaleDetailReportByDifferentPriceList(HttpServletRequest request, Map<String, Object> map) {

		return STORE_SALE_DETAIL_REPORT_BY_BY_DIFFERENT_PRICE_LIST;
	}
	
	@RequiresUser
	@RequestMapping(value = "storeSaleDetailReportBySubtotalList", method = RequestMethod.GET)
	public String storeSaleDetailReportBySubtotalList(HttpServletRequest request, Map<String, Object> map) {
		
		return STORE_SALE_REPORT_BY_SUBTOTAL_LIST;
	}

	@RequestMapping(value = "getSaleReportInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getSaleReportInfo(HttpServletRequest request, Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<SaleReportInfoSummary> saleReportInfoSummaryList = getSaleReportInfoSummaryList(request, map);

		result.put("rows", saleReportInfoSummaryList);

		int warehouseOrderBottleNumber = 0;
		int orderNumber = 0;
		int orderBottleNumber = 0;
		double total_amount = 0;

		for (SaleReportInfoSummary saleReportInfoSummary : saleReportInfoSummaryList) {
			warehouseOrderBottleNumber += saleReportInfoSummary.getWarehouseOrderBottleNumber();
			orderNumber += saleReportInfoSummary.getOrderNumber();
			orderBottleNumber += saleReportInfoSummary.getOrderBottleNumber();
			total_amount += saleReportInfoSummary.getTotal_amount();
		}

		SaleReportInfoSummary saleReportInfoSummaryTotal = new SaleReportInfoSummary();
		saleReportInfoSummaryTotal.setWarehouseOrderBottleNumber(warehouseOrderBottleNumber);
		saleReportInfoSummaryTotal.setOrderNumber(orderNumber);
		saleReportInfoSummaryTotal.setOrderBottleNumber(orderBottleNumber);
		saleReportInfoSummaryTotal.setTotal_amount(total_amount);

		result.put("footer", saleReportInfoSummaryTotal);

		return result;

	}

	@RequestMapping(value = "exportSaleReportInfo", method = RequestMethod.POST)
	public void exportSaleReportInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);

		List<SaleReportInfoSummary> saleReportInfoSummaryList = getSaleReportInfoSummaryList(request, map);

		String secondCategoryId = request.getParameter("secondCategory.id");
		String secondCategoryName = "";
		if (StringUtils.nonEmpty(secondCategoryId)) {
			secondCategoryName = secondCategoryService.findById(Integer.parseInt(secondCategoryId)).getSecond_category_name();
		}

		StringBuffer exportName = new StringBuffer();
		exportName.append("门店销售汇总报表-");
		exportName.append(secondCategoryName);
		exportName.append(".xls");

		ExportUtils.exportExcel(response, heads, saleReportInfoSummaryList, exportName.toString(), "template-SaleReportInfo.xls");

	}

	@RequestMapping(value = "getSaleDetailReportInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getSaleDetailReportInfo(HttpServletRequest request, Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<SaleReportInfoSummary> saleReportInfoSummaryList = getSaleReportInfoDetailSummaryList(request, map);

		result.put("rows", saleReportInfoSummaryList);

		int warehouseOrderBottleNumber = 0;
		int orderNumber = 0;
		int orderBottleNumber = 0;
		double total_amount = 0;

		for (SaleReportInfoSummary saleReportInfoSummary : saleReportInfoSummaryList) {
			warehouseOrderBottleNumber += saleReportInfoSummary.getWarehouseOrderBottleNumber();
			orderNumber += saleReportInfoSummary.getOrderNumber();
			orderBottleNumber += saleReportInfoSummary.getOrderBottleNumber();
			total_amount += saleReportInfoSummary.getTotal_amount();
		}

		SaleReportInfoSummary saleReportInfoSummaryTotal = new SaleReportInfoSummary();
		saleReportInfoSummaryTotal.setWarehouseOrderBottleNumber(warehouseOrderBottleNumber);
		saleReportInfoSummaryTotal.setOrderNumber(orderNumber);
		saleReportInfoSummaryTotal.setOrderBottleNumber(orderBottleNumber);
		saleReportInfoSummaryTotal.setTotal_amount(total_amount);

		result.put("footer", saleReportInfoSummaryTotal);

		return result;

	}

	@RequestMapping(value = "exportSaleDetailReportInfo", method = RequestMethod.POST)
	public void exportSaleDetailReportInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);

		List<SaleReportInfoSummary> saleReportInfoSummaryList = getSaleReportInfoDetailSummaryList(request, map);

		String secondCategoryId = request.getParameter("secondCategory.id");
		String secondCategoryName = "";
		if (StringUtils.nonEmpty(secondCategoryId)) {
			secondCategoryName = secondCategoryService.findById(Integer.parseInt(secondCategoryId)).getSecond_category_name();
		}

		StringBuffer exportName = new StringBuffer();
		exportName.append("门店销售明细报表-");
		exportName.append(secondCategoryName);
		exportName.append(".xls");

		ExportUtils.exportExcel(response, heads, saleReportInfoSummaryList, exportName.toString(), "template-SaleDetailReportInfo.xls");

	}

	@RequestMapping(value = "getSaleReportInfoByAirBottleTypeInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> getSaleReportInfoByAirBottleType(HttpServletRequest request, Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<SaleReportInfoSummaryByAirBottleType> saleReportInfoSummaryByAirBottleTypeList = getSaleReportInfoSummaryByAirBottleTypeList(request, map);

		result.put("rows", saleReportInfoSummaryByAirBottleTypeList);

		Integer num_for_5KG = 0;
		Integer num_for_15KG = 0;
		Integer num_for_50KG = 0;
		Integer num_for_2KG = 0;
		Integer num_for_total = 0;

		for (SaleReportInfoSummaryByAirBottleType saleReportInfoSummaryByAirBottleType : saleReportInfoSummaryByAirBottleTypeList) {

			num_for_5KG += saleReportInfoSummaryByAirBottleType.getNum_for_5KG();
			num_for_15KG += saleReportInfoSummaryByAirBottleType.getNum_for_15KG();
			num_for_50KG += saleReportInfoSummaryByAirBottleType.getNum_for_50KG();
			num_for_2KG += saleReportInfoSummaryByAirBottleType.getNum_for_2KG();
			num_for_total += saleReportInfoSummaryByAirBottleType.getNum_for_total();

		}

		SaleReportInfoSummaryByAirBottleType saleReportInfoSummaryByAirBottleTypeTotal = new SaleReportInfoSummaryByAirBottleType();
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_5KG(num_for_5KG);
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_15KG(num_for_15KG);
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_50KG(num_for_50KG);
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_2KG(num_for_2KG);
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_total(num_for_total);

		result.put("footer", saleReportInfoSummaryByAirBottleTypeTotal);

		return result;

	}

	@RequestMapping(value = "exportSaleReportInfoByAirBottleTypeInfo", method = RequestMethod.POST)
	public void exportSaleReportInfoByAirBottleTypeInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);

		List<SaleReportInfoSummaryByAirBottleType> saleReportInfoSummaryByAirBottleTypeList = getSaleReportInfoSummaryByAirBottleTypeList(request, map);

		String secondCategoryId = request.getParameter("secondCategory.id");
		String secondCategoryName = "";
		if (StringUtils.nonEmpty(secondCategoryId)) {
			secondCategoryName = secondCategoryService.findById(Integer.parseInt(secondCategoryId)).getSecond_category_name();
		}

		StringBuffer exportName = new StringBuffer();
		exportName.append("各气瓶类型销售统计表-");
		exportName.append(secondCategoryName);
		exportName.append(".xls");

		ExportUtils.exportExcel(response, heads, saleReportInfoSummaryByAirBottleTypeList, exportName.toString(),
				"template-SaleReportInfoByAirBottleTypeInfo.xls");

	}

	@RequestMapping(value = "getSaleDetailReportInfoByAirBottleTypeInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getSaleDetailReportInfoByAirBottleType(HttpServletRequest request, Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<SaleReportInfoSummaryByAirBottleType> saleDetailReportInfoSummaryByAirBottleTypeList = getSaleDetailReportInfoSummaryByAirBottleTypeList(request,
				map);

		result.put("rows", saleDetailReportInfoSummaryByAirBottleTypeList);

		Integer num_for_5KG = 0;
		Integer num_for_15KG = 0;
		Integer num_for_50KG = 0;
		Integer num_for_2KG = 0;
		Integer num_for_total = 0;

		for (SaleReportInfoSummaryByAirBottleType saleReportInfoSummaryByAirBottleType : saleDetailReportInfoSummaryByAirBottleTypeList) {

			num_for_5KG += saleReportInfoSummaryByAirBottleType.getNum_for_5KG();
			num_for_15KG += saleReportInfoSummaryByAirBottleType.getNum_for_15KG();
			num_for_50KG += saleReportInfoSummaryByAirBottleType.getNum_for_50KG();
			num_for_2KG += saleReportInfoSummaryByAirBottleType.getNum_for_2KG();
			num_for_total += saleReportInfoSummaryByAirBottleType.getNum_for_total();
		}

		SaleReportInfoSummaryByAirBottleType saleReportInfoSummaryByAirBottleTypeTotal = new SaleReportInfoSummaryByAirBottleType();
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_5KG(num_for_5KG);
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_15KG(num_for_15KG);
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_50KG(num_for_50KG);
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_2KG(num_for_2KG);
		saleReportInfoSummaryByAirBottleTypeTotal.setNum_for_total(num_for_total);

		result.put("footer", saleReportInfoSummaryByAirBottleTypeTotal);

		return result;

	}

	@RequestMapping(value = "exportSaleDetailReportInfoByAirBottleTypeInfo", method = RequestMethod.POST)
	public void exportSaleDetailReportInfoByAirBottleTypeInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
			throws Exception {

		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);

		List<SaleReportInfoSummaryByAirBottleType> saleDetailReportInfoSummaryByAirBottleTypeList = getSaleDetailReportInfoSummaryByAirBottleTypeList(request,
				map);

		String secondCategoryId = request.getParameter("secondCategory.id");
		String secondCategoryName = "";
		if (StringUtils.nonEmpty(secondCategoryId)) {
			secondCategoryName = secondCategoryService.findById(Integer.parseInt(secondCategoryId)).getSecond_category_name();
		}

		StringBuffer exportName = new StringBuffer();
		exportName.append("各气瓶类型销售明细统计表-");
		exportName.append(secondCategoryName);
		exportName.append(".xls");

		ExportUtils.exportExcel(response, heads, saleDetailReportInfoSummaryByAirBottleTypeList, secondCategoryName.toString(),
				"template-SaleDetailReportInfoByAirBottleTypeInfo.xls");

	}

	@RequestMapping(value = "getSaleReportInfoByDifferentPrice", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getSaleReportInfoByDifferentPrice(HttpServletRequest request, Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<SaleReportInfoSummaryByDifferentPrice> saleReportInfoSummaryList = getSaleReportInfoSummaryListByDifferentPrice(request, map);

		result.put("rows", saleReportInfoSummaryList);

		int orderNumber = 0;
		int orderBottleNumber = 0;
		double total_amount = 0;

		for (SaleReportInfoSummaryByDifferentPrice saleReportInfoSummaryByDifferentPrice : saleReportInfoSummaryList) {
			orderNumber += saleReportInfoSummaryByDifferentPrice.getOrderNumber();
			orderBottleNumber += saleReportInfoSummaryByDifferentPrice.getOrderBottleNumber();
			total_amount += saleReportInfoSummaryByDifferentPrice.getTotal_amount();
		}

		SaleReportInfoSummaryByDifferentPrice saleReportInfoSummaryByDifferentPriceTotal = new SaleReportInfoSummaryByDifferentPrice();
		saleReportInfoSummaryByDifferentPriceTotal.setOrderNumber(orderNumber);
		saleReportInfoSummaryByDifferentPriceTotal.setOrderBottleNumber(orderBottleNumber);
		saleReportInfoSummaryByDifferentPriceTotal.setTotal_amount(total_amount);

		result.put("footer", saleReportInfoSummaryByDifferentPriceTotal);

		return result;

	}
	

	private List<SaleReportInfoSummaryByDifferentPrice> getSaleReportInfoSummaryListByDifferentPrice(HttpServletRequest request, Map<String, Object> map) {

		List<String> orderInfoSearchConditionList = new ArrayList<String>();
		Field orderInfoSearchConditionFiled = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {

			orderInfoSearchConditionList.add("secondCategory.id = ?");
			orderInfoSearchConditionFiled.addInt(user.getSecondCategory().getId()); // 取对应门店

		}

		// 已派送的订单
		orderInfoSearchConditionList.add("state.id = ?");
		orderInfoSearchConditionFiled.addInt(OrderInfoStateCode.ALREADY_FINISH);

		ToolsBarUtils.getOrderInfoSearchCondition(request, orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		List<SaleReportInfoVo> saleReportInfoVoList = orderService.getAllOrderInfo(orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		List<SaleReportInfoSummaryByDifferentPrice> saleReportInfoSummaryList = new ArrayList<SaleReportInfoSummaryByDifferentPrice>();

		Map<String, SaleReportInfoSummaryByDifferentPrice> saleReportInfoSummaryByDifferentPriceMap = new TreeMap<String, SaleReportInfoSummaryByDifferentPrice>();

		Set<Double> priceSet = new HashSet<Double>();

		StringBuffer keySb = new StringBuffer();

		for (SaleReportInfoVo saleReportInfoVo : saleReportInfoVoList) {

			keySb.setLength(0);
			keySb.append(saleReportInfoVo.getSecondCategoryId());
			keySb.append("_");
			keySb.append(saleReportInfoVo.getAirBottleTypeId());
			keySb.append("_");
			keySb.append(saleReportInfoVo.getPrice());

			priceSet.add(saleReportInfoVo.getPrice());

			String key = keySb.toString();

			SaleReportInfoSummaryByDifferentPrice saleReportInfoSummaryByDifferentPrice = saleReportInfoSummaryByDifferentPriceMap.get(key);

			if (saleReportInfoSummaryByDifferentPrice == null) {

				saleReportInfoSummaryByDifferentPrice = new SaleReportInfoSummaryByDifferentPrice();
				saleReportInfoSummaryByDifferentPrice.setPrice(saleReportInfoVo.getPrice());
				saleReportInfoSummaryByDifferentPriceMap.put(key, saleReportInfoSummaryByDifferentPrice);
			}

			saleReportInfoSummaryByDifferentPrice.setOrderNumber(saleReportInfoSummaryByDifferentPrice.getOrderNumber() + 1);
			saleReportInfoSummaryByDifferentPrice.setOrderBottleNumber(saleReportInfoSummaryByDifferentPrice.getOrderBottleNumber()
					+ saleReportInfoVo.getOrderNumber());
			saleReportInfoSummaryByDifferentPrice.setTotal_amount(saleReportInfoSummaryByDifferentPrice.getTotal_amount() + saleReportInfoVo.getTotal_amount());

		}

		List<SecondCategory> secondCategorys = secondCategoryService.getAllList();

		List<AirBottleType> airBottleTypes = airBottleTypeService.getAllList();

		for (Double price : priceSet) {
			for (SecondCategory secondCategory : secondCategorys) {
				for (AirBottleType airBottleType : airBottleTypes) {

					keySb.setLength(0);
					keySb.append(secondCategory.getId());
					keySb.append("_");
					keySb.append(airBottleType.getId());
					keySb.append("_");
					keySb.append(price);

					String key = keySb.toString();

					SaleReportInfoSummaryByDifferentPrice saleReportInfoSummaryByDifferentPrice = saleReportInfoSummaryByDifferentPriceMap.get(key);

					if (saleReportInfoSummaryByDifferentPrice != null) {

						saleReportInfoSummaryByDifferentPrice.setSecond_category_name(secondCategory.getSecond_category_name());

						saleReportInfoSummaryByDifferentPrice.setAir_bottle_specifications(airBottleType.getAir_bottle_specifications());

						saleReportInfoSummaryList.add(saleReportInfoSummaryByDifferentPrice);
					}
				}
			}
		}

		return saleReportInfoSummaryList;
	}
	

	@RequestMapping(value = "getSaleReportInfoBySubtotal", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getSaleReportInfoBySubtotal(HttpServletRequest request, Map<String, Object> map) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<SaleReportInfoSummaryByDifferentPrice> saleReportInfoSummaryList = getSaleReportInfoSummaryListBySubtotal(request, map);
		
		result.put("rows", saleReportInfoSummaryList);
		
		int orderNumber = 0;
		int orderBottleNumber = 0;
		double total_amount = 0;
		
		for (SaleReportInfoSummaryByDifferentPrice saleReportInfoSummaryByDifferentPrice : saleReportInfoSummaryList) {
			orderNumber += saleReportInfoSummaryByDifferentPrice.getOrderNumber();
			orderBottleNumber += saleReportInfoSummaryByDifferentPrice.getOrderBottleNumber();
			total_amount += saleReportInfoSummaryByDifferentPrice.getTotal_amount();
		}
		
		SaleReportInfoSummaryByDifferentPrice saleReportInfoSummaryByDifferentPriceTotal = new SaleReportInfoSummaryByDifferentPrice();
		saleReportInfoSummaryByDifferentPriceTotal.setOrderNumber(orderNumber);
		saleReportInfoSummaryByDifferentPriceTotal.setOrderBottleNumber(orderBottleNumber);
		saleReportInfoSummaryByDifferentPriceTotal.setTotal_amount(total_amount);
		
		result.put("footer", saleReportInfoSummaryByDifferentPriceTotal);
		
		return result;
		
	}

	@RequestMapping(value = "exportSaleReportInfoByDifferentPrice", method = RequestMethod.POST)
	public void exportSaleReportInfoByDifferentPrice(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);

		List<SaleReportInfoSummaryByDifferentPrice> saleReportInfoSummaryList = getSaleReportInfoSummaryListBySubtotal(request, map);

		StringBuffer exportName = new StringBuffer();
		exportName.append("分类汇总报表.xls");
		
		ExportUtils.exportExcel(response, heads, saleReportInfoSummaryList, exportName.toString(), "template-SaleReportInfoBySubtotal.xls");

	}
	
	private List<SaleReportInfoSummaryByDifferentPrice> getSaleReportInfoSummaryListBySubtotal(HttpServletRequest request, Map<String, Object> map) {
		
		List<String> orderInfoSearchConditionList = new ArrayList<String>();
		Field orderInfoSearchConditionFiled = new Field();
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		if (user.getSecondCategory() != null) {
			
			orderInfoSearchConditionList.add("secondCategory.id = ?");
			orderInfoSearchConditionFiled.addInt(user.getSecondCategory().getId()); // 取对应门店
			
		}
		
		// 已派送的订单
		orderInfoSearchConditionList.add("state.id = ?");
		orderInfoSearchConditionFiled.addInt(OrderInfoStateCode.ALREADY_FINISH);
		
		ToolsBarUtils.getOrderInfoSearchCondition(request, orderInfoSearchConditionList, orderInfoSearchConditionFiled);
		
		List<SaleReportInfoSummaryByDifferentPrice> saleReportInfoSummaryList = 
				orderService.getSubtotalList(orderInfoSearchConditionList, orderInfoSearchConditionFiled);
		
		
		return saleReportInfoSummaryList;
	}

	private List<SaleReportInfoSummaryByAirBottleType> getSaleDetailReportInfoSummaryByAirBottleTypeList(HttpServletRequest request, Map<String, Object> map) {

		List<SaleReportInfoSummary> saleReportInfoSummaryList = getSaleReportInfoDetailSummaryList(request, map);

		List<SaleReportInfoSummaryByAirBottleType> result = new ArrayList<SaleReportInfoSummaryByAirBottleType>();

		Map<String, SaleReportInfoSummaryByAirBottleType> saleReportInfoSummaryByAirBottleTypeMap = new TreeMap<String, SaleReportInfoSummaryByAirBottleType>();

		StringBuffer keySb = new StringBuffer();

		for (SaleReportInfoSummary saleReportInfoSummary : saleReportInfoSummaryList) {

			keySb.setLength(0);
			keySb.append(saleReportInfoSummary.getSecond_category_name());
			keySb.append("_");
			keySb.append(saleReportInfoSummary.getOrder_date());

			String key = keySb.toString();

			SaleReportInfoSummaryByAirBottleType saleReportInfoSummaryByAirBottleType = saleReportInfoSummaryByAirBottleTypeMap.get(key);

			if (saleReportInfoSummaryByAirBottleType == null) {

				saleReportInfoSummaryByAirBottleType = new SaleReportInfoSummaryByAirBottleType();
				saleReportInfoSummaryByAirBottleType.setOrder_date(saleReportInfoSummary.getOrder_date());
				saleReportInfoSummaryByAirBottleType.setSecond_category_name(saleReportInfoSummary.getSecond_category_name());
				saleReportInfoSummaryByAirBottleType.setX_coordinate(saleReportInfoSummary.getX_coordinate());
				saleReportInfoSummaryByAirBottleType.setY_coordinate(saleReportInfoSummary.getY_coordinate());
				saleReportInfoSummaryByAirBottleTypeMap.put(key, saleReportInfoSummaryByAirBottleType);
				result.add(saleReportInfoSummaryByAirBottleType);
			}

			if (saleReportInfoSummary.getAir_bottle_specifications().equals("2KG")) {
				saleReportInfoSummaryByAirBottleType.setNum_for_2KG(saleReportInfoSummaryByAirBottleType.getNum_for_2KG()
						+ saleReportInfoSummary.getOrderBottleNumber());
			}

			if (saleReportInfoSummary.getAir_bottle_specifications().equals("5KG")) {
				saleReportInfoSummaryByAirBottleType.setNum_for_5KG(saleReportInfoSummaryByAirBottleType.getNum_for_5KG()
						+ saleReportInfoSummary.getOrderBottleNumber());
			}

			if (saleReportInfoSummary.getAir_bottle_specifications().equals("15KG")) {
				saleReportInfoSummaryByAirBottleType.setNum_for_15KG(saleReportInfoSummaryByAirBottleType.getNum_for_15KG()
						+ saleReportInfoSummary.getOrderBottleNumber());
			}

			if (saleReportInfoSummary.getAir_bottle_specifications().equals("50KG")) {
				saleReportInfoSummaryByAirBottleType.setNum_for_50KG(saleReportInfoSummaryByAirBottleType.getNum_for_50KG()
						+ saleReportInfoSummary.getOrderBottleNumber());
			}

			saleReportInfoSummaryByAirBottleType.setNum_for_total(saleReportInfoSummaryByAirBottleType.getNum_for_total()
					+ saleReportInfoSummary.getOrderBottleNumber());

		}

		return result;
	}

	private List<SaleReportInfoSummaryByAirBottleType> getSaleReportInfoSummaryByAirBottleTypeList(HttpServletRequest request, Map<String, Object> map) {

		List<SaleReportInfoSummary> saleReportInfoSummaryList = getSaleReportInfoSummaryList(request, map);

		List<SaleReportInfoSummaryByAirBottleType> result = new ArrayList<SaleReportInfoSummaryByAirBottleType>();

		Map<String, SaleReportInfoSummaryByAirBottleType> saleReportInfoSummaryByAirBottleTypeMap = new TreeMap<String, SaleReportInfoSummaryByAirBottleType>();

		StringBuffer keySb = new StringBuffer();

		for (SaleReportInfoSummary saleReportInfoSummary : saleReportInfoSummaryList) {

			keySb.setLength(0);
			keySb.append(saleReportInfoSummary.getSecond_category_name());

			String key = keySb.toString();

			SaleReportInfoSummaryByAirBottleType saleReportInfoSummaryByAirBottleType = saleReportInfoSummaryByAirBottleTypeMap.get(key);

			if (saleReportInfoSummaryByAirBottleType == null) {

				saleReportInfoSummaryByAirBottleType = new SaleReportInfoSummaryByAirBottleType();

				saleReportInfoSummaryByAirBottleType.setSecond_category_name(saleReportInfoSummary.getSecond_category_name());
				saleReportInfoSummaryByAirBottleTypeMap.put(key, saleReportInfoSummaryByAirBottleType);
				result.add(saleReportInfoSummaryByAirBottleType);
			}

			if (saleReportInfoSummary.getAir_bottle_specifications().equals("2KG")) {
				saleReportInfoSummaryByAirBottleType.setNum_for_2KG(saleReportInfoSummaryByAirBottleType.getNum_for_2KG()
						+ saleReportInfoSummary.getOrderBottleNumber());
			}

			if (saleReportInfoSummary.getAir_bottle_specifications().equals("5KG")) {
				saleReportInfoSummaryByAirBottleType.setNum_for_5KG(saleReportInfoSummaryByAirBottleType.getNum_for_5KG()
						+ saleReportInfoSummary.getOrderBottleNumber());
			}

			if (saleReportInfoSummary.getAir_bottle_specifications().equals("15KG")) {
				saleReportInfoSummaryByAirBottleType.setNum_for_15KG(saleReportInfoSummaryByAirBottleType.getNum_for_15KG()
						+ saleReportInfoSummary.getOrderBottleNumber());
			}

			if (saleReportInfoSummary.getAir_bottle_specifications().equals("50KG")) {
				saleReportInfoSummaryByAirBottleType.setNum_for_50KG(saleReportInfoSummaryByAirBottleType.getNum_for_50KG()
						+ saleReportInfoSummary.getOrderBottleNumber());
			}

			saleReportInfoSummaryByAirBottleType.setNum_for_total(saleReportInfoSummaryByAirBottleType.getNum_for_total()
					+ saleReportInfoSummary.getOrderBottleNumber());

		}

		return result;
	}

	private List<SaleReportInfoSummary> getSaleReportInfoSummaryList(HttpServletRequest request, Map<String, Object> map) {

		List<String> orderInfoSearchConditionList = new ArrayList<String>();
		Field orderInfoSearchConditionFiled = new Field();

		List<String> storeGetBottleNumSearchConditionList = new ArrayList<String>();
		Field storeGetBottleNumSearchConditionFiled = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {

			orderInfoSearchConditionList.add("secondCategory.id = ?");
			orderInfoSearchConditionFiled.addInt(user.getSecondCategory().getId()); // 取对应门店

			storeGetBottleNumSearchConditionList.add("secondCategory.id = ?");
			storeGetBottleNumSearchConditionFiled.addInt(user.getSecondCategory().getId());
		}

		// 已派送的订单
		orderInfoSearchConditionList.add("state.id = ?");
		orderInfoSearchConditionFiled.addInt(OrderInfoStateCode.ALREADY_FINISH);

		ToolsBarUtils.getOrderInfoSearchCondition(request, orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		ToolsBarUtils.getAirBottleTrackingRecordSearchCondition(request, storeGetBottleNumSearchConditionList, storeGetBottleNumSearchConditionFiled);

		List<SaleReportInfoVo> saleReportInfoVoList = orderService.getAllOrderInfo(orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		List<ReturnBottleInfoVo> storeGetBottleNumList = airBottleTrackingRecordService.getStoreGetBottleNum(storeGetBottleNumSearchConditionList,
				storeGetBottleNumSearchConditionFiled);

		List<SaleReportInfoSummary> saleReportInfoSummaryList = new ArrayList<SaleReportInfoSummary>();

		Map<String, SaleReportInfoSummary> saleReportInfoSummaryMap = new TreeMap<String, SaleReportInfoSummary>();

		StringBuffer keySb = new StringBuffer();

		for (SaleReportInfoVo saleReportInfoVo : saleReportInfoVoList) {

			keySb.setLength(0);
			keySb.append(saleReportInfoVo.getSecondCategoryId());
			keySb.append("_");
			keySb.append(saleReportInfoVo.getAirBottleTypeId());

			String key = keySb.toString();

			SaleReportInfoSummary saleReportInfoSummary = saleReportInfoSummaryMap.get(key);

			if (saleReportInfoSummary == null) {

				saleReportInfoSummary = new SaleReportInfoSummary();
				saleReportInfoSummaryMap.put(key, saleReportInfoSummary);
			}

			saleReportInfoSummary.setOrderNumber(saleReportInfoSummary.getOrderNumber() + 1);
			saleReportInfoSummary.setOrderBottleNumber(saleReportInfoSummary.getOrderBottleNumber() + saleReportInfoVo.getOrderNumber());
			saleReportInfoSummary.setTotal_amount(saleReportInfoSummary.getTotal_amount() + saleReportInfoVo.getTotal_amount());

		}

		for (ReturnBottleInfoVo returnBottleInfoVo : storeGetBottleNumList) {

			keySb.setLength(0);
			keySb.append(returnBottleInfoVo.getSecondCategoryId());
			keySb.append("_");
			keySb.append(returnBottleInfoVo.getAirBottleTypeId());

			String key = keySb.toString();

			SaleReportInfoSummary saleReportInfoSummary = saleReportInfoSummaryMap.get(key);

			if (saleReportInfoSummary == null) {

				saleReportInfoSummary = new SaleReportInfoSummary();
				saleReportInfoSummaryMap.put(key, saleReportInfoSummary);
			}

			saleReportInfoSummary.setWarehouseOrderBottleNumber(returnBottleInfoVo.getGetBottleNum());
		}

		List<SecondCategory> secondCategorys = secondCategoryService.getAllList();

		List<AirBottleType> airBottleTypes = airBottleTypeService.getAllList();

		for (SecondCategory secondCategory : secondCategorys) {
			for (AirBottleType airBottleType : airBottleTypes) {

				keySb.setLength(0);
				keySb.append(secondCategory.getId());
				keySb.append("_");
				keySb.append(airBottleType.getId());

				String key = keySb.toString();

				SaleReportInfoSummary saleReportInfoSummary = saleReportInfoSummaryMap.get(key);

				if (saleReportInfoSummary != null) {

					saleReportInfoSummary.setSecond_category_name(secondCategory.getSecond_category_name());

					saleReportInfoSummary.setAir_bottle_specifications(airBottleType.getAir_bottle_specifications());

					saleReportInfoSummaryList.add(saleReportInfoSummary);
				}
			}
		}

		return saleReportInfoSummaryList;
	}

	private List<SaleReportInfoSummary> getSaleReportInfoDetailSummaryList(HttpServletRequest request, Map<String, Object> map) {

		List<String> orderInfoSearchConditionList = new ArrayList<String>();
		Field orderInfoSearchConditionFiled = new Field();

		List<String> storeGetBottleNumSearchConditionList = new ArrayList<String>();
		Field storeGetBottleNumSearchConditionFiled = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {

			orderInfoSearchConditionList.add("secondCategory.id = ?");
			orderInfoSearchConditionFiled.addInt(user.getSecondCategory().getId()); // 取对应门店

			storeGetBottleNumSearchConditionList.add("secondCategory.id = ?");
			storeGetBottleNumSearchConditionFiled.addInt(user.getSecondCategory().getId());
		}

		// 已完成的订单
		orderInfoSearchConditionList.add("state.id = ?");
		orderInfoSearchConditionFiled.addInt(OrderInfoStateCode.ALREADY_FINISH);

		ToolsBarUtils.getOrderInfoSearchCondition(request, orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		ToolsBarUtils.getAirBottleTrackingRecordSearchCondition(request, storeGetBottleNumSearchConditionList, storeGetBottleNumSearchConditionFiled);

		List<SaleReportInfoVo> saleDetailReportInfoVoList = orderService.getAllOrderInfo(orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		List<ReturnBottleInfoVo> storeGetBottleNumList = airBottleTrackingRecordService.getStoreGetBottleDetailNum(storeGetBottleNumSearchConditionList,
				storeGetBottleNumSearchConditionFiled);

		List<SaleReportInfoSummary> saleReportInfoSummaryList = new ArrayList<SaleReportInfoSummary>();

		Map<String, SaleReportInfoSummary> saleReportInfoSummaryMap = new TreeMap<String, SaleReportInfoSummary>();

		Set<String> OrderTimeSet = new TreeSet<String>();

		StringBuffer keySb = new StringBuffer();

		for (SaleReportInfoVo saleReportInfoVo : saleDetailReportInfoVoList) {

			keySb.setLength(0);
			keySb.append(saleReportInfoVo.getSecondCategoryId());
			keySb.append("_");
			keySb.append(saleReportInfoVo.getAirBottleTypeId());

			String OrderTimeTemp = TimeUtils.getyyyyMMddStringByDate(saleReportInfoVo.getOrder_time());
			keySb.append("_");
			keySb.append(OrderTimeTemp);

			OrderTimeSet.add(OrderTimeTemp);

			String key = keySb.toString();

			SaleReportInfoSummary saleReportInfoSummary = saleReportInfoSummaryMap.get(key);

			if (saleReportInfoSummary == null) {

				saleReportInfoSummary = new SaleReportInfoSummary();
				saleReportInfoSummaryMap.put(key, saleReportInfoSummary);
			}

			saleReportInfoSummary.setOrderNumber(saleReportInfoSummary.getOrderNumber() + 1);
			saleReportInfoSummary.setOrderBottleNumber(saleReportInfoSummary.getOrderBottleNumber() + saleReportInfoVo.getOrderNumber());
			saleReportInfoSummary.setTotal_amount(saleReportInfoSummary.getTotal_amount() + saleReportInfoVo.getTotal_amount());

		}

		for (ReturnBottleInfoVo returnBottleInfoVo : storeGetBottleNumList) {

			keySb.setLength(0);
			keySb.append(returnBottleInfoVo.getSecondCategoryId());
			keySb.append("_");
			keySb.append(returnBottleInfoVo.getAirBottleTypeId());

			keySb.append("_");
			keySb.append(returnBottleInfoVo.getDate());

			OrderTimeSet.add(returnBottleInfoVo.getDate());

			String key = keySb.toString();

			SaleReportInfoSummary saleReportInfoSummary = saleReportInfoSummaryMap.get(key);

			if (saleReportInfoSummary == null) {

				saleReportInfoSummary = new SaleReportInfoSummary();
				saleReportInfoSummaryMap.put(key, saleReportInfoSummary);
			}

			saleReportInfoSummary.setWarehouseOrderBottleNumber(saleReportInfoSummary.getWarehouseOrderBottleNumber() + returnBottleInfoVo.getGetBottleNum());
		}

		List<SecondCategory> secondCategorys = secondCategoryService.getAllList();

		List<AirBottleType> airBottleTypes = airBottleTypeService.getAllList();

		for (SecondCategory secondCategory : secondCategorys) {
			for (AirBottleType airBottleType : airBottleTypes) {
				for (String orderTime : OrderTimeSet) {
					keySb.setLength(0);
					keySb.append(secondCategory.getId());
					keySb.append("_");
					keySb.append(airBottleType.getId());
					keySb.append("_");
					keySb.append(orderTime);

					String key = keySb.toString();

					SaleReportInfoSummary saleReportInfoSummary = saleReportInfoSummaryMap.get(key);

					if (saleReportInfoSummary != null) {

						saleReportInfoSummary.setOrder_date(orderTime);

						saleReportInfoSummary.setSecond_category_name(secondCategory.getSecond_category_name());
						saleReportInfoSummary.setX_coordinate(secondCategory.getX_coordinate());
						saleReportInfoSummary.setY_coordinate(secondCategory.getY_coordinate());

						saleReportInfoSummary.setAir_bottle_specifications(airBottleType.getAir_bottle_specifications());

						saleReportInfoSummaryList.add(saleReportInfoSummary);
					}
				}
			}
		}

		return saleReportInfoSummaryList;
	}
}
