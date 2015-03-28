package com.lxf.event;

import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

public class Source {
	//监听器列表,这里存放自定义的监听器
	private Set<IMyListener> listenerList = new HashSet<IMyListener>();
	
	// 监听器注册方法
	public void addListener(MyListener listener) {
		listenerList.add(listener);
	}
	
	// 监听器注销方法
	public void removeListener(MyListener listener) {
		listenerList.remove(listener);
	}
	
	// 事件广播方法
	public void broadcast(EventObject event) {
		for(IMyListener listener : listenerList)
			listener.handleEvent(event);
	}
	
}
