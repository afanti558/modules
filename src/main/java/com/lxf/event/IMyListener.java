package com.lxf.event;

import java.awt.*;
import java.util.EventListener;
import java.util.EventObject;

public interface IMyListener extends EventListener{

    //监听事件，实现类中具体化事件
	public void handleEvent(EventObject event);
}
