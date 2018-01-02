package cn.service.bill;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.dao.bill.BillMapper;
import cn.pojo.Bill;
@Service
public class BillServiceImpl implements BillService{
	@Resource
	private BillMapper billMapper;
	public BillMapper getBillMapper() {
		return billMapper;
	}
	public void setBillMapper(BillMapper billMapper) {
		this.billMapper = billMapper;
	}
	@Override
	public boolean add(Bill bill) {
		boolean flag = false;
		if(billMapper.add(bill) > 0)
			flag = true;
		return flag;				
	}
	@Override
	public List<Bill> getBillList(Bill bill) {
		List<Bill> billList = null;
		billList = billMapper.getBillList(bill);
		return billList;				
	}
	@Override
	public boolean deleteBillById(Integer delId) {
		boolean ismodify=false;
		int intm=billMapper.deleteBillById(delId);
		if(intm>0){
			ismodify=true;
		}
		return ismodify;
	}
	@Override
	public Bill getBillById(int id) {
		List<Bill> billList = null;
		Bill bill=new Bill();
		bill.setId(id);
		billList = billMapper.getBillList(bill);
		return billList.get(0);
	}
	@Override
	public boolean modify(Bill bill) {
		boolean ismodify=false;
		int intm=billMapper.modify(bill);
		if(intm>0){
			ismodify=true;
		}
		return ismodify;
	}
	

}
