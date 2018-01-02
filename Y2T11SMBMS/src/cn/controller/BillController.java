package cn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pojo.Bill;
import cn.pojo.Provider;
import cn.service.bill.BillService;
import cn.service.provider.*;
import cn.pojo.User;
import cn.tools.Constants;
@Controller
@RequestMapping(value="/bill")
public class BillController {
	@Resource
	private BillService billService;
	public BillService getBillService() {
		return billService;
	}

	public void setBillService(BillService billService) {
		this.billService = billService;
	}
	@Resource
	private ProviderService providerService;
	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	@RequestMapping(value="/billadd.html",method=RequestMethod.GET)
	public String billAdd(){
		return "billadd";
	}
	@RequestMapping(value="/billadd.html",method=RequestMethod.POST)
	public String billAdd(@ModelAttribute(value="bill") Bill bill,HttpSession session){
		Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String s = e.nextElement();
            System.out.println(s + " == " + session.getAttribute(s));
        }
		User createU=(User)session.getAttribute(Constants.USER_SESSION);
		if(createU==null){
			return "redirect:/login.jsp";
		}
		bill.setCreatedBy(createU.getId());
		bill.setCreationDate(new Date());
		boolean flag = false;
		flag = billService.add(bill);
		System.out.println("add flag -- > " + flag);
		if(flag){
			return "redirect:/bill/query.html";
		}
		return "billadd";
	}
	@RequestMapping(value="/query.html")
	public String query(@ModelAttribute(value="bill") Bill bill,Model model){
		List<Provider> providerList = new ArrayList<Provider>();
		Provider provider=new Provider();
		provider.setProName(null);
		provider.setProCode(null);
		providerList = providerService.getProviderList(provider);
		model.addAttribute("providerList", providerList);
		
		List<Bill> billList = new ArrayList<Bill>();
		billList = billService.getBillList(bill);
		model.addAttribute("queryProductName", bill.getProductName());
		model.addAttribute("queryProviderId", bill.getProviderId());
		model.addAttribute("queryIsPayment", bill.getIsPayment());
		model.addAttribute("billList", billList);
		return "billlist";
	}
	@RequestMapping(value="/{billid}/view.html")
	public String query(@PathVariable(value="billid")int billid,Model model){
		List<Bill> billList = new ArrayList<Bill>();
		Bill bill=new Bill();
		bill.setId(billid);
		billList = billService.getBillList(bill);
		if(billList!=null){
			bill=billList.get(0);
		}
		model.addAttribute("bill", bill);
		return "billview";
	}
	@RequestMapping(value="/{billid}/modify.html",method=RequestMethod.GET)
	public String modify(@PathVariable(value="billid")int billid,Model model){
		List<Provider> providerList = new ArrayList<Provider>();
		Provider provider=new Provider();
		provider.setProName(null);
		provider.setProCode(null);
		providerList = providerService.getProviderList(provider);
		model.addAttribute("providerList", providerList);
		
		Bill bill=billService.getBillById(billid);
		model.addAttribute("bill", bill);
		return "billmodify";
	}
	@RequestMapping(value="/modifySave.html",method=RequestMethod.POST)
	public String modifySave(@ModelAttribute(value="bill") Bill bill,Model model,HttpSession session){
		Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String s = e.nextElement();
            System.out.println(s + " == " + session.getAttribute(s));
        }
		User createU=(User)session.getAttribute(Constants.USER_SESSION);
		if(createU==null){
			return "redirect:/login.jsp";
		}
		bill.setModifyBy(createU.getId());
		bill.setModifyDate(new Date());
		boolean flag = false;
		flag = billService.modify(bill);
		System.out.println("add flag -- > " + flag);
		if(flag){
			return "redirect:/bill/query.html";
		}
		return "redirect:"+bill.getId()+"/modify.html";
	}
	@RequestMapping(value="/{billid}/delete")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable(value="billid")int billid,Model model){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean isDel=billService.deleteBillById(billid);
		
		if(isDel){
			resultMap.put("delResult", "true");
		}else{
			resultMap.put("delResult", "false");
		}
		return resultMap;
	}
}
