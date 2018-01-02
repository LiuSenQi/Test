package cn.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pojo.Provider;
import cn.service.provider.ProviderService;
@Controller
@RequestMapping(value="/provider")
public class ProviderController {
	@Resource
	private ProviderService providerService;
	
	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	@RequestMapping(value="/getproviderlist",method=RequestMethod.GET)
	@ResponseBody
	public List<Provider> getProviderList(){
		List<Provider> list=providerService.getProviderList(null);
		return list;
	}
}
