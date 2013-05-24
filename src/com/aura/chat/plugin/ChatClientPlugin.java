package com.aura.chat.plugin;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aura.base.TypeSide;
import com.aura.base.event.AbstractEvent;
import com.aura.base.event.AuraEventListener;
import com.aura.base.manager.AbstractPacketManager;
import com.aura.base.manager.console.ConsoleCE;
import com.aura.base.packet.AbstractPacket;
import com.aura.base.utils.AuraBaliseParser;
import com.aura.base.utils.AuraLogger;
import com.aura.chat.configuration.CPConfigCM;
import com.aura.chat.console.CPConsoleCM;
import com.aura.chat.event.CPEventCM;
import com.aura.chat.event.CPEventMessage;
import com.aura.chat.packet.CPPacketCM;
import com.aura.chat.packet.CPPacketMessage;
import com.aura.client.AuraClient;

public class ChatClientPlugin extends ChatPlugin<AuraClient> {
	public static SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss");
	
	public ChatClientPlugin(AuraClient client) {
		super(client);
	}
	
	@Override
	public void onSystemLoad() {
		getAura().getEventManager().getDispatchThread().addListener(
			new AuraEventListener(getAura().getEvent(CPEventCM.MESSAGE)) {
			@Override
			protected void implExecute(AbstractEvent event) {
				CPEventMessage m = (CPEventMessage) event;
				AuraLogger.chat(getAura().getSide(), 
					AuraBaliseParser.COLOR.getBalise(
						getAura().getCfgManager().getConfigStringValue(CPConfigCM.CONSOLE_COLOR_CLOCK))
						+"["+SDF.format(new Date(m.getTime())) + "] " +
					AuraBaliseParser.COLOR.getBalise(
						getAura().getCfgManager().getConfigStringValue(CPConfigCM.CONSOLE_COLOR_LOGIN))
						+ m.getLogin() +
					AuraBaliseParser.COLOR.getBalise(
						getAura().getCfgManager().getConfigStringValue(CPConfigCM.CONSOLE_COLOR_CLOCK))
						+ " : " +
					AuraBaliseParser.COLOR.getBalise(
						getAura().getCfgManager().getConfigStringValue(CPConfigCM.CONSOLE_COLOR_MESSAGE))
						+ m.getMessage());
			}
		});
	}
	
	@Override
	public void onPacketReceive(AbstractPacketManager pm, AbstractPacket ap) {
		if (ap.getId() == CPPacketCM.MESSAGE) {
			CPPacketMessage apm = (CPPacketMessage) ap;
			CPEventMessage em = (CPEventMessage) getAura().createEvent(CPEventCM.MESSAGE);
			em.setLogin(apm.getLogin());
			em.setMessage(apm.getMessage());
			em.setTime(apm.getTime());
			getAura().getEventManager().addEvent(em);
		}
	}
	
	@Override
	public void onCommand(String[] cmd) {
		ConsoleCE cp = getAura().getConsoleManager().getContainerManager().dispatcher(TypeSide.CLIENT, cmd, false);
		if (cp == null) 
			return;
		if (cp.getId() == CPConsoleCM.SAY) {
			String message = "";
			for (int i=1; i< cmd.length; i++) 
				message += cmd[i] + " ";
			if (message != null && message.trim().length() > 0) {
				CPPacketMessage apm = (CPPacketMessage) getAura().createPacket(CPPacketCM.MESSAGE);
				apm.setMessage(message.trim());
				getAura().getNetworkManager().envoyerPaquet(apm);
			}
		}
	}
	
	@Override public Class<?>[] onLoadDbClass() { return null; }
}