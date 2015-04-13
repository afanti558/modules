package com.raipeng.event;
import java.util.EventObject;

public class MyListener implements IMyListener {

	@Override
	public void handleEvent(EventObject e) {
        if(e instanceof MyEvent){
            System.out.println("执行触发的监听事件:" + e.getSource());
        }else{
            System.out.println("事件没有发生");
        }
	}

}
