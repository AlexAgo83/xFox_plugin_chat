package com.aura.chat.plugin;

import com.aura.base.AbstractAuraPlugin;
import com.aura.base.Aura;
import com.aura.base.container.AbstractContainerManager.KeyContainerAlreadyUsed;
import com.aura.chat.configuration.CPConfigCM;
import com.aura.chat.console.CPConsoleCM;
import com.aura.chat.event.CPEventCM;
import com.aura.chat.packet.CPPacketCM;

public abstract class ChatPlugin<E extends Aura> extends AbstractAuraPlugin<E> {
	public final static int PLUGIN_ID = -3;
	public final static String PLUGIN_NAME = "chat";
	
	public ChatPlugin(E aura) {
		super(PLUGIN_ID, PLUGIN_NAME, aura);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected boolean implLoad() throws KeyContainerAlreadyUsed {
		new CPConsoleCM(getAura().getConsoleManager().getContainerManager()).load();
		new CPPacketCM(getAura().getNetworkManager().getContainerManager()).load();
		new CPEventCM(getAura().getEventManager().getContainerManager()).load();
		new CPConfigCM<E>(this, getAura().getCfgManager().getContainerManager()).load();
		return true;
	}
}