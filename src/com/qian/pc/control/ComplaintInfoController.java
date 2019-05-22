package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.code.ComplaintInfoStateCode;
import com.qian.entity.ComplaintInfo;
import com.qian.entity.ComplaintInfoState;
import com.qian.entity.User;
import com.qian.service.ComplaintInfoService;
import com.qian.util.TimeUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.Field;

@Controller
@RequestMapping("complaint")
public class ComplaintInfoController {
	
private static final String LIST = "complaint/list";
private static final String DISPATCH_LIST = "order/dispatch_complaint_list";
	
	@Autowired
	private ComplaintInfoService complaintInfoService;
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		
		return LIST;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	}
	
	@RequiresUser
	@RequestMapping(value = "AllList", method = RequestMethod.GET)
	public String Alllist(HttpServletRequest request, Map<String, Object> map) {
		
		return DISPATCH_LIST;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, ComplaintInfo complaintInfo, String complaint_appointment_time11, String complaint_appointment_time22) {
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		complaintInfo.setComplaint_appointment_time1(TimeUtils.formatAppointmentTime(complaint_appointment_time11));
		complaintInfo.setComplaint_appointment_time2(TimeUtils.formatAppointmentTime(complaint_appointment_time22));
		complaintInfo.setOperator(user);
		
		complaintInfoService.add(complaintInfo);
		return "ok";
	}
	
	@RequestMapping(value = "getComplaintInfo", method = RequestMethod.POST)
	public @ResponseBody
	List<ComplaintInfo> getComplaintInfo(HttpServletRequest request, Map<String, Object> map, int clientId){
		List<ComplaintInfo> list= complaintInfoService.getComplaintInfo(clientId);
		return list;
	}
	
	@RequestMapping(value = "getcomplaintInfoAllList", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getcomplaintInfoAllList(HttpServletRequest request, Map<String, Object> map, int page, int rows){
		
		List<String> l = new ArrayList<String>();
		Field filed = new Field();
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}
		
		ToolsBarUtils.getcomplaintInfoSearchCondition(request, l, filed);
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = complaintInfoService.getTotalNum(l, filed);
		List<ComplaintInfo> list = complaintInfoService.getcomplaintPageList(l, filed, page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	@RequestMapping(value = "dispatch", method = RequestMethod.POST)
	public @ResponseBody
	String dispatch(HttpServletRequest request, Map<String, Object> map, Integer complaintId, Integer complaintManId) {

		ComplaintInfo complaint = complaintInfoService.findcomplaintById(complaintId);

		User repairMan = new User();
		repairMan.setId(complaintManId);

		ComplaintInfoState  rs=new ComplaintInfoState();
		rs.setId(ComplaintInfoStateCode.ALREADY_DEAL);

		complaint.setComplaintInfoState(rs);//设置投诉状态为已处理

		complaintInfoService.updateComplaintState(complaint);

		return "ok";
	}
	
	@RequestMapping(value = "checkComplaintState", method = RequestMethod.POST)
	public @ResponseBody
	boolean checkComplaintState(HttpServletRequest request, Map<String, Object> map, int complaintId){
			boolean flag = true;
			ComplaintInfo complaint = complaintInfoService.findcomplaintById(complaintId);
				if (complaint.getComplaintInfoState().getId()==2) {
					flag = false;
					return false;
				}
			return flag;
		}
	@RequestMapping(value="addDealComplaintNote",method=RequestMethod.POST)
	public @ResponseBody
	boolean addDealComplaintNote(HttpServletRequest request,int complaintId,String dealcomplaintNote){
		int i=complaintInfoService.addComplaintNote(complaintId, dealcomplaintNote);
		if(i==0){
			return true;
		}else{
		return false;
		}
		
	}
	
	

}
