package com.qianfeng.domain;

import java.util.Date;
import java.util.List;


public class Order {
	private String id;  //������id��
	private double money;//��������Ǯ��
	private String receiverAddress;//�ռ��˵ĵ�ַ
	private String receiverName;//�ռ��˵�����
	private String receiverPhone;//�ռ��˵ĵ绰
	private int paystate;//������״̬(��֧��/δ֧��)
	private Date ordertime;//�µ�ʱ��
	private User user;//�������û�
	private List<OrderItem> list;//ʢװ��ǰ���������еĶ�����
	
	
	
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", money=" + money + ", receiverAddress=" + receiverAddress + ", receiverName="
				+ receiverName + ", receiverPhone=" + receiverPhone + ", paystate=" + paystate + ", ordertime="
				+ ordertime + ", user=" + user + ", list=" + list + "]";
	}
	
	
	
	
}
