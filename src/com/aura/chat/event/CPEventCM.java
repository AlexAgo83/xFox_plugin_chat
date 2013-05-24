package com.aura.chat.event;

import com.aura.base.Aura;
import com.aura.base.event.AbstractEvent;
import com.aura.base.manager.event.EventCE;
import com.aura.base.manager.event.EventCM;

public class CPEventCM<A extends Aura> extends EventCM<A> {
	
	public final static int MESSAGE = CURSEUR.nextVal();
	
	public CPEventCM(EventCM<A> selfManager) {
		super(selfManager);
	}
	
	@Override
	protected void implLoad() throws KeyContainerAlreadyUsed {
		register(new EventCE(MESSAGE, "MESSAGE") {@Override public AbstractEvent create(int id) {return new CPEventMessage(getId());}});
	}
}