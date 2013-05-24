package com.aura.chat.plugin;

import com.aura.base.TypeSide;
import com.aura.base.manager.AbstractPacketManager;
import com.aura.base.manager.console.ConsoleCE;
import com.aura.base.packet.AbstractPacket;
import com.aura.chat.packet.CPPacketCM;
import com.aura.chat.packet.CPPacketMessage;
import com.aura.server.AuraServer;

public class ChatServerPlugin extends ChatPlugin<AuraServer> {
	public ChatServerPlugin(AuraServer server) {
		super(server);
	}
	@Override
	public void onSystemLoad() {}
	
	@Override
	public void onPacketReceive(AbstractPacketManager pm, AbstractPacket ap) {
		if (ap.getId() == CPPacketCM.MESSAGE) {
			CPPacketMessage apm = (CPPacketMessage) ap;
			apm.setLogin(pm.getUser());
			apm.setTime(System.currentTimeMillis());
			getAura().getNetworkManager().getTransactionThread().aBroadCast(apm);
		}
	}
	@Override
	public void onCommand(String[] cmd) {
		ConsoleCE cp = getAura().getConsoleManager().getContainerManager().dispatcher(TypeSide.SERVER, cmd, false);
		if (cp == null)
			return;
	}
	
	@Override public Class<?>[] onLoadDbClass() { return null; }
}