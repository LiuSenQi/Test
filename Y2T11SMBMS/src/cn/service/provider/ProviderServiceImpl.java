package cn.service.provider;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.dao.bill.BillMapper;
import cn.dao.provider.ProviderMapper;
import cn.pojo.Provider;
@Service
public class ProviderServiceImpl implements ProviderService {
	@Resource
	private ProviderMapper providerMapper;
	public ProviderMapper getProviderMapper() {
		return providerMapper;
	}
	public void setProviderMapper(ProviderMapper providerMapper) {
		this.providerMapper = providerMapper;
	}
	@Resource
	private BillMapper billMapper;
	public BillMapper getBillMapper() {
		return billMapper;
	}
	public void setBillMapper(BillMapper billMapper) {
		this.billMapper = billMapper;
	}
	@Override
	public List<Provider> getProviderList(Provider provider) {
		List<Provider> providerList = null;
		providerList = providerMapper.getProviderList(provider);
		return providerList;
	}

	
	

}
