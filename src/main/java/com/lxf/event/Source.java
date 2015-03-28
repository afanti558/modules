package com.lxf.event;

import java.util.HashSet;
import java.util.Set;

public class Source {
	//监听器列表
	private Set<IMyListener> listenerList = new HashSet<IMyListener>();
	
	// 监听器注册方法
	public void addListener(MyListener listener) {
		listenerList.add(listener);
	}
	
	// 监听器注销方法
	public void removeListener(MyListener listener) {
		listenerList.remove(listener);
	}
	
	//触发MyEvent事件
//	public void fireEvent(){
//		MyEvent event = new MyEvent(this);
//		broadcast(event);
//	}
	
	// 事件广播方法
	public void broadcast(MyEvent event) {
		for(IMyListener listener : listenerList)
			listener.handleEvent(event);
	}
	
	//事件驱动构造事件源对象，调用fireEvent方法，监听器就会执行相应的代码
	
}
