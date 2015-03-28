package com.lxf.event;

import java.util.EventObject;

public class MyEvent extends EventObject {

	private static final long serialVersionUID = 7253673262227969369L;

    //父类中的事件源
	public MyEvent(Object source) {
		super(source);
	}

}
