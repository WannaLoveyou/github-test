package com.qian.pc.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.service.CloudDataService;
import com.qian.vo.BaseDto;


/**
 * 云数据上传controller
 * @author sc
 *
 */
@Controller
@RequestMapping("/cloudData")
public class CloudDataController {

	@Autowired
	private CloudDataService cloudDataService;

	/**
	 * 上传钢瓶数据
	 * 
	 * url
	 * http://127.0.0.1:8084/energymanage/cloudData/upGas
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "upGas", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody BaseDto<String> upGas(
			HttpServletRequest request,Map<String, Object> map) {

		Map<String, String> resMap = cloudDataService.findGasCylinderDataSendToCloud();
		String msg = resMap.get("msg");
		return BaseDto.getSuccessResult(msg);
	}
	
	/**
	 * 
	 * 上传检测记录
	 * 
	 * url
	 * http://127.0.0.1:8084/energymanage/cloudData/upCheck
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "upCheck", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody BaseDto<String> upCheck(
			HttpServletRequest request,Map<String, Object> map) {

		Map<String, String> resMap = cloudDataService.findCheckDataSendToCloud();
		String code = resMap.get("code");
		String msg = "";
		if ("0".equals(code)) {
			msg = resMap.get("msg");
		}else{
			msg = resMap.get("msg");
		}
		
		return BaseDto.getSuccessResult(msg);
	}
	
	
	/**
	 * 
	 * 上传充装记录
	 * 
	 * url
	 * http://127.0.0.1:8084/energymanage/cloudData/upFilling
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "upFilling", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody BaseDto<String> upFilling(
			HttpServletRequest request,Map<String, Object> map) {
		
		Map<String, String> resMap = cloudDataService.findFillingDataSendToCloud();
		String code = resMap.get("code");
		String msg = "";
		if ("0".equals(code)) {
			msg = resMap.get("msg");
		}else{
			msg = resMap.get("msg");
		}
		
		return BaseDto.getSuccessResult(msg);
	}
	
	/**
	 * 
	 * 测试登录
	 * 
	 * url
	 * http://127.0.0.1:8084/energymanage/cloudData/checkLogin
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "checkLogin", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody BaseDto<String> checkLogin(HttpServletRequest request,Map<String, Object> map) {
		
		String val = cloudDataService.authentication();
		
		return BaseDto.getSuccessResult(val);
	}
	
	/**
	 * 上传钢瓶数据
	 * 
	 * url
	 * http://127.0.0.1:8084/energymanage/cloudData/upGas2
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "upGas2", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody BaseDto<String> upGas2(
			HttpServletRequest request,Map<String, Object> map,String userName, String unitName, String appKey, String dataStr) {

		dataStr = "[{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"张家港中集圣达因低温装备有限公司\",\"MAKENO\":\"12P450-01-15\",\"MAKEDATE\":\"2012-02-01\",\"JARNO\":\"JBJQ001\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"车用液化天然气焊接绝热气瓶\",\"JARMODEL\":\"CDPW600-450-1.37型\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"30\",\"WORKPRESSURE\":\"1.37\",\"DESIGNTHICKNESS\":\"2.88\",\"DIMENSION\":\"450\",\"WEIGHT\":\"249\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-03-27\",\"NEXTDATE\":\"2020-03-22\",\"LASTUSEDATE\":\"2027-02-01\",\"EndUSEDATE\":\"2027-02-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"广东省特种设备检测研究院梅州检测院\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"张家港中集圣达因低温装备有限公司\",\"ReformDate\":\"2012-02\",\"ReformTestUNIT\":\"江苏特种设备安全监督检验研究院\",\"ReformTestDate\":\"2012-04\",\"PlateNo\":\"粤M04429\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"张家港中集圣达因低温装备有限公司\",\"MAKENO\":\"12P450-01-18\",\"MAKEDATE\":\"2012-02-01\",\"JARNO\":\"JBJQ002\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"车用液化天然气焊接绝热气瓶\",\"JARMODEL\":\"CDPW600-450-1.37型\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"30\",\"WORKPRESSURE\":\"1.37\",\"DESIGNTHICKNESS\":\"2.88\",\"DIMENSION\":\"450\",\"WEIGHT\":\"249\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-03-27\",\"NEXTDATE\":\"2020-03-22\",\"LASTUSEDATE\":\"2027-02-01\",\"EndUSEDATE\":\"2027-02-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"广东省特种设备检测研究院梅州检测院\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"张家港中集圣达因低温装备有限公司\",\"ReformDate\":\"2012-02\",\"ReformTestUNIT\":\"江苏特种设备安全监督检验研究院\",\"ReformTestDate\":\"2012-04\",\"PlateNo\":\"粤M04429\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"安徽绿动能源有限公司\",\"MAKENO\":\"LDDRD054054\",\"MAKEDATE\":\"2017-05-01\",\"JARNO\":\"JBJQ006\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"车用压缩天然气\",\"JARMODEL\":\"CNG2-G-356-70-20B\",\"JARMEDIA\":\"CNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"30\",\"WORKPRESSURE\":\"20\",\"DESIGNTHICKNESS\":\"5.5\",\"DIMENSION\":\"70.4\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-08-02\",\"NEXTDATE\":\"2020-07-05\",\"LASTUSEDATE\":\"2032-05-01\",\"EndUSEDATE\":\"2032-05-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"梅州市质量技术监督局\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"梅州市三俊新能源科技有限公司\",\"ReformDate\":\"2017-06\",\"ReformTestUNIT\":\"广东省特种设备检测研究院梅州检测院\",\"ReformTestDate\":\"2017-07\",\"PlateNo\":\"粤MVS002\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-01\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-01\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-02\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-02\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-03\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-03\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-04\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-04\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-05\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-05\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-06\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-06\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-08\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-07\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-09\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-08\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-10\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-09\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-11\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-10\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-12\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-11\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-13\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-12\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-14\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-13\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-15\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-14\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-16\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-15\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-17\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-16\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-18\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-17\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"},{\"TokenKey\":\"\",\"ID\":\"\",\"MAKER\":\"浙江省普阳深冷装备股份有限公司\",\"MAKENO\":\"CDPL450M-1707-20\",\"MAKEDATE\":\"2017-11-01\",\"JARNO\":\"JBJQ-BL-18\",\"FILLINGUNIT\":\"蕉岭县金碧天然气汽车加气有限公司\",\"FILLINGCODE\":\"MNE\",\"OWNFLAG\":\"自有\",\"OWNUNIT\":\"\",\"JARUUID\":\"\",\"SBTYPE\":\"气瓶\",\"JARTYPE\":\"焊接绝热气瓶\",\"JARMODEL\":\"DPL700-450-1.4I\",\"JARMEDIA\":\"LNG\",\"USESTATUS\":\"01\",\"TESTPRESSURE\":\"4.0\",\"WORKPRESSURE\":\"2.0\",\"DESIGNTHICKNESS\":\"5.67\",\"DIMENSION\":\"450\",\"WEIGHT\":\"54.3\",\"DESIGNUSEYEARS\":\"15\",\"LASTDATE\":\"2017-11-22\",\"NEXTDATE\":\"2020-11-22\",\"LASTUSEDATE\":\"2032-11-01\",\"EndUSEDATE\":\"2032-11-01\",\"TESTRESULT\":\"合格\",\"TESTUNIT\":\"金华市特种设备检测中心\",\"SUPERUNIT\":\"梅州市质量技术监督局\",\"ReformUNIT\":\"浙江省普阳深冷装备股份有限公司\",\"ReformDate\":\"2017-11\",\"ReformTestUNIT\":\"金华市特种设备检测中心\",\"ReformTestDate\":\"2017-11\",\"PlateNo\":\"\",\"BUSISTATUS\":\"01\",\"TAGCODE\":\"\",\"TAGNO\":\"\",\"TAGPASTEDATE\":\"\",\"DATAFROM\":\"蕉岭县金碧天然气管理系统\",\"CITY\":\"梅州\",\"AREA\":\"\",\"JClassP\":\"档案库\"}]";
		
		Map<String, String> resMap = cloudDataService.sendCylinderDataToCloud(userName, unitName, appKey, dataStr);
		String code = resMap.get("code");
		String msg = "";
		if ("0".equals(code)) {
			msg = resMap.get("msg");
		}else{
			msg = resMap.get("msg");
		}
		
		return BaseDto.getSuccessResult(msg);
	}
	
	

}
