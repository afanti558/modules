package com.lxf.event;


public class MyListener implements IMyListener {

	@Override
	public void handleEvent(MyEvent e) {
		System.out.println("执行触发的监听事件" + e.getSource());
	}

	
}
