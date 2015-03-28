package com.lxf.event;

import java.util.EventListener;

public interface IMyListener extends EventListener{

	public void handleEvent(MyEvent event);
}
