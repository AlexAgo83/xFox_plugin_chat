package com.aura.chat.packet;

import com.aura.base.Aura;
import com.aura.base.manager.packet.PacketCE;
import com.aura.base.manager.packet.PacketCM;
import com.aura.base.packet.AbstractPacket;

public class CPPacketCM<A extends Aura> extends PacketCM<A> {
	
	public static final int MESSAGE = CURSEUR.nextVal();
	
	public CPPacketCM(A aura) {
		super(aura);
	}
	public CPPacketCM(PacketCM<A> selfManager) {
		super(selfManager);
	}
	
	@Override
	protected void implLoad() throws KeyContainerAlreadyUsed {
		register(new PacketCE(MESSAGE, "message") {@Override public AbstractPacket create(int id) {
			return new CPPacketMessage(getId());}});
	}
}