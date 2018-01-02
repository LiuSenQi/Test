package cn.service.bill;

import java.util.List;

import cn.pojo.Bill;

public interface BillService {
	/**
	 * 增加订单
	 * @param bill
	 * @return
	 */
	public boolean add(Bill bill);


	/**
	 * 通过条件获取订单列表-模糊查询-billList
	 * @param bill
	 * @return
	 */
	public List<Bill> getBillList(Bill bill);
	
	/**
	 * 通过billId删除Bill
	 * @param delId
	 * @return
	 */
	public boolean deleteBillById(Integer delId);
	
	
	/**
	 * 通过billId获取Bill
	 * @param billid
	 * @return
	 */
	public Bill getBillById(int billid);
	
	/**
	 * 修改订单信息
	 * @param bill
	 * @return
	 */
	public boolean modify(Bill bill);
}
