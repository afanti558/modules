package com.lxf.event;

import java.util.EventObject;

/**
 * 事件监听测试
 */
public class Test {

	/**
	 * 测试方法
	 */
	public static void main(String[] args) {
		Car car = new Car();
		car.setPrice(100);
	}
	

}

class Car{
	private float price;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
		Source source = new Source();
		source.addListener(new MyListener());//注册监听器
		source.broadcast(new MyEvent("汽车降价了"));//事件源发布事件
	}
	
}
