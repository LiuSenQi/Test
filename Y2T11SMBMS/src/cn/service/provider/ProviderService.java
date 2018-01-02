package cn.service.provider;

import java.util.List;

import cn.pojo.Provider;

public interface ProviderService {

	/**
	 * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
	 * @param proName
	 * @return
	 */
	public List<Provider> getProviderList(Provider provider);
}
