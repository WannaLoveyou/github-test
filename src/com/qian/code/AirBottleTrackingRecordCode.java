package com.qian.code;

/**
 * @author Lu Kongwen
 * @version Create time：2015-12-18 下午2:30:11
 * @Description 气瓶追踪状态编码
 */
public class AirBottleTrackingRecordCode {

	public static final int EMPTY_BOTTLE_INIT_IN_WAREHOUSE = 100; // 空瓶初始化在仓库

	public static final int HEAVY_BOTTLE_STORAGE_IN_WAREHOUSE = 110; // 仓库重瓶入库

	public static final int WRONG_BOTTLE_STORAGE_IN_WAREHOUSE = 111; // 仓库问题瓶入库

	public static final int HEAVY_BOTTLE_OUT_IN_WAREHOUSE = 120; // 仓库重瓶出库

	public static final int HEAVY_BOTTLE_FILLING_IN_WAREHOUSE = 121; // 仓库自提充装

	public static final int HEAVY_BOTTLE_FORCE_FILLING_IN_WAREHOUSE = 122; // 仓库强制自提充装

	public static final int HEAVY_BOTTLE_OUT_IN_WAREHOUSE_TO_CLIENT = 123; // 仓库自提重瓶出库给用户

	public static final int EMPTY_BOTTLE_STORAGE_IN_WAREHOUSE_FROM_CLIENT = 124; // 仓库自提从用户回收空瓶

	public static final int HEAVY_BOTTLE_STORAGE_IN_WAREHOUSE_FROM_DELIVERYMAN = 125; // 仓库从配送工退回重瓶

	public static final int HEAVY_BOTTLE_STORAGE_IN_SOTRE = 130; // 门店重瓶入库

	public static final int WRONG_BOTTLE_BACK_TO_WH_IN_SOTRE = 131; // 门店问题重瓶退回仓库

	public static final int HEAVY_BOTTLE_PICK_FROM_WAREHOUSE_IN_STORE = 132; // 门店从仓库取重瓶

	public static final int HEAVY_BOTTLE_OUT_SOTRE_TO_DELIVERY_MAN = 140; // 门店重瓶出库给配送工

	public static final int HEAVY_BOTTLE_OUT_SOTRE_TO_CLIENT = 141; // 门店重瓶出库给用户

	public static final int HEAVY_BOTTLE_BACK_FROM_CLIENT_BY_DELIVERY_MAN = 142; // 门店从配送工退回重瓶

	public static final int HEAVY_BOTTLE_STORAGE_IN_CLIENT = 150; // 重瓶到达用户

	public static final int HEAVY_BOTTLE_BACK_FROM_CLIENT_BY_STORE = 151; // 门店从用户退回重瓶

	public static final int HEAVY_BOTTLE_CHANGE_FROM_CLIENT_BY_STORE_TO_STORE = 152; // 门店从用户更换重瓶到门店

	public static final int HEAVY_BOTTLE_CHANGE_FROM_CLIENT_BY_STORE_TO_USER = 153; // 门店从用户更换重瓶到用户

	public static final int EMPTY_BOTTLE_STORAGE_IN_STORE = 160; // 门店空瓶入库

	public static final int EMPTY_BOTTLE_OUT_IN_STORE = 170; // 门店空瓶出库

	public static final int EMPTY_BOTTLE_STORAGE_IN_WAREHOUSE = 180; // 仓库空瓶入库

	public static final int EMPTY_BOTTLE_STORAGE_IN_DELIVERY_MAN = 200; // 配送工确认收到空瓶

	public static final int HEAVY_BOTTLE_STORAGE_IN_DELIVERY_MAN = 210; // 配送工确认收到重瓶

	public static final int EMPTY_BOTTLE_OUT_IN_DELIVERY_MAN = 220; // 配送工确认送出空瓶

	public static final int HEAVY_BOTTLE_OUT_IN_DELIVERY_MAN = 230; // 配送工确认送出重瓶

	public static final int CANCEL_EMPTY_BOTTLE_STORAGE_IN_DELIVERY_MAN = 240; // 配送工撤销收到空瓶

	public static final int CANCEL_HEAVY_BOTTLE_STORAGE_IN_DELIVERY_MAN = 250; // 配送工撤销收到重瓶

	public static final int CANCEL_EMPTY_BOTTLE_OUT_IN_DELIVERY_MAN = 260; // 配送工撤销送出空瓶

	public static final int CANCEL_HEAVY_BOTTLE_OUT_IN_DELIVERY_MAN = 270; // 配送工撤销送出重瓶

	public static final int ALL_BOTTLE_OUT_TO_DELIVERY_MAN_IN_DELIVERY_MAN = 280; // 配送工全部库存转移

	public static final int HEAVY_BOTTLE_BACK_FROM_CLIENT_IN_DELIVERY_MAN = 290; // 配送工从用户退回重瓶

	public static final int BOTTLE_OUT_IN_WAREHOUSE_TO_INSPECTION = 1001; // 仓库送检出库

	public static final int BOTTLE_STORAGE_IN_WAREHOUSE_FROM_INSPECTION = 1002; // 仓库送检入库

	public static final int CANCEL_SCRAP = 9000; // 系统撤销报废

	public static final int INSPECTION_SCRAP = 9998; // 检测站送检报废

	public static final int FORCE_SCRAP_BOTTLE_STORAGE_IN_WAREHOUSE = 9999; // 仓库强制报废入库

	public static final int FORCE_EMPTY_BOTTLE_STORAGE_IN_WAREHOUSE = 10000; // 仓库强制空瓶入库

	public static final int EXCEPTION_RETURN_SUMBIT_IN_STORE = 10001; // 门店异常回瓶申请

	public static final int EXCEPTION_RETURN_PASS_IN_STORE = 10002; // 门店异常回瓶通过

	public static final int EXCEPTION_RETURN_NO_PASS_IN_STORE = 10003; // 门店异常回瓶不通过

	public static final int SYSTEM_RETURN_TO_STORE = 10004; // 系统后台退回到门店

	public static final int SYSTEM_RETURN_TO_CLIENT = 10005; // 系统后台退回到客户

	public static final int SYSTEM_RETURN_HEAVY_BOTTLE_TO_WAREHOUSE = 10006; // 系统后台退回重瓶到仓库

}
