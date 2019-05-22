package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.app.entity.AppAddOrder;
import com.qian.entity.ClientInfo;
import com.qian.entity.OrderInfo;
import com.qian.entity.PartFeeInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.DeliveryManDeliveryFeeInfoVo;
import com.qian.vo.Field;
import com.qian.vo.OrderCycleInfoVo;
import com.qian.vo.SaleDetailReportInfoVo;
import com.qian.vo.SaleReportInfoSummaryByDifferentPrice;
import com.qian.vo.SaleReportInfoVo;
import com.qian.vo.SimpleOrderInfo;

public interface OrderService {
	public List<OrderInfo> getOrderInfoByLatest(int clientId);

	public List<OrderInfo> getNewOrderInfo(int clientId);

	public OrderInfo addOrderInfo(OrderInfo orderInfo, List<PartFeeInfo> partFeeInfos, User user);

	public OrderInfo updateOrderInfo(OrderInfo orderInfo);

	public BaseDto<Object> cancelOrderInfo(int orderId, String cancelMsg, User user);

	public OrderInfo findOrderInfoById(int orderId);
	
	public OrderInfo findOrderInfoByOrderCode(String orderCode);

	public List<OrderInfo> getDispatchPageList(int page, int rows);

	public List<OrderInfo> getOrderInfoPageList(List<String> list, Field filed, int page, int rows);

	public long getTotalNum();

	public long getTotalNum(List<String> list, Field filed);

	public List<MobileOrderInfo> getOrderInfoPageList(List<String> list, Field filed);

	public boolean fillHeavyBottleInOrder(String bottleCodes, int orderId, User user);

	public boolean fillEmptyBottleInOrder(int orderId, String bottleCodes, User user);

	public boolean fillHeavyBottleByPickUpInSotres(String bottleCodes, int orderId, User user);

	public boolean cancelDeliveryOrder(int orderId);

	public boolean fillEmptyBottleByPickUpInSotres(String bottleCodes, int orderId, User user);

	public boolean fillEmptyBottlePhotoByPickUpInSotres(int orderId, List<String> urls, User user);

	public boolean fillEmptyBottlePhotoInOrder(int orderId, List<String> urls, User user);

	public Boolean addWeixinOrder(ClientInfo clientInfo, OrderInfo orderInfo);

	public List<SaleReportInfoVo> getAllOrderInfo(List<String> l, Field filed);

	public CommonCode heavyBottleBackFromClient(String bottleCodes, User user);

	public CommonCode mobileAddStoreOrder(String bottleCodes, User user, ClientInfo clientInfo, int bottleTypeId, OrderInfo orderInfo);

	public List<SaleDetailReportInfoVo> getAllOrderInfoByDate(List<String> orderInfoSearchConditionList, Field orderInfoSearchConditionFiled);

	public Map<String, Integer> getOrderInfoStateTotalNum(List<String> l, Field filed);

	public boolean addRemark(Integer orderId, String remark);

	public List<DeliveryManDeliveryFeeInfoVo> getAllDeliveryManDeliveryFeeInfo(List<String> l, Field filed);

	public Integer getOrderBottleNum(List<String> l, Field filed);

	public OrderInfo addAppOrderInfo(AppAddOrder appAddOrder);

	public CommonCode savePrepayId(int orderId, String prepayId);

	public boolean weixinPaySuccess(Map<String, Object> resultMap);

	public CommonCode cancelAppOrder(int orderId, String remark);

	public OrderInfo getLastOrderInfoByClientIdAndOrderId(int clientId, int orderId);

	public CommonCode addPrintTime(Integer orderId);

	public CommonCode heavyBottleChangeFromClient(String oldBottleCode, String newBottleCode, User user);

	public CommonCode addNonQrcodeHeavyBottle(Integer orderId, String bottlecodes);

	public CommonCode addNonQrcodeEmptyBottle(Integer orderId, String bottlecodes);

	public CommonCode speedUpOrder(Integer orderId);

	public CommonCode cancelSpeedUpOrder(Integer orderId);

	public List<SimpleOrderInfo> getSimpleOrderInfoPageList(List<String> l, Field filed, int page, int rows,int export, String sort, String order);

	public CommonCode rongXinTongPay();

	public BaseDto<Object> forceCacnel(Integer orderId, User user);

	public int getPirntTimes(Integer orderId);

	public CommonCode printCancel(Integer orderId, String printNo, User user);

	public List<OrderCycleInfoVo> getOrderCycleInfo(List<String> orderInfoSearchConditionList, Field orderInfoSearchConditionFiled);

	public Integer getPickUpInSotreNum(List<String> l, Field filed);

	public Integer getTelephoneOrderNum(List<String> l, Field filed);

	public Integer getWeiXinOrderNum(List<String> l, Field filed);

	public int getTodayOrderNumByClinetId(int clientId);

	public CommonCode finishPartsOrder(Integer orderId);

	public CommonCode editOrderStore(Integer orderId, Integer sotreId);

	public CommonCode returnQRcode(Integer orderId, User user);

	public CommonCode invoice(Integer orderId, User user);

	public CommonCode cancelInvoice(Integer orderId, String remark, User user);

	public BaseDto<Object> fillHeavyBottleByPickUpInWarehouse(String bottleCodes, int orderId, User user);

	public BaseDto<Object> fillEmptyBottleByPickUpInWarehouse(String bottleCodes, int orderId, User user);

	public BaseDto<Object> cancelPickupInWH(Integer orderId, User user);

	public CommonCode checkDispatch(Integer orderId);
	
	public List<SaleReportInfoSummaryByDifferentPrice> getSubtotalList(List<String> l, Field filed);

	public OrderInfo addMobileOrderInfo(AppAddOrder appAddOrder);

	public BaseDto<Object> addMobileDispatch(int orderId, int deliveryManId);

}
