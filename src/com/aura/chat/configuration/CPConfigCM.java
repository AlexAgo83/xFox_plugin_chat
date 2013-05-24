package com.aura.chat.configuration;

import java.util.HashMap;
import java.util.Map;

import com.aura.base.Aura;
import com.aura.base.TypeSide;
import com.aura.base.configuration.PropertiesLoader.PropertiesLoaderException;
import com.aura.base.manager.configuration.ConfigCE;
import com.aura.base.manager.configuration.ConfigCEString;
import com.aura.base.manager.configuration.ConfigCM;
import com.aura.chat.plugin.ChatPlugin;

public class CPConfigCM<A extends Aura> extends ConfigCM<A> {
	
	public static final int CONSOLE_COLOR_CLOCK = CURSEUR.nextVal();
	public static final int CONSOLE_COLOR_LOGIN = CURSEUR.nextVal();
	public static final int CONSOLE_COLOR_SEPARATOR = CURSEUR.nextVal();
	public static final int CONSOLE_COLOR_MESSAGE = CURSEUR.nextVal();
	
	private ChatPlugin<?> plugin;
	public CPConfigCM(ChatPlugin<?> plugin, ConfigCM<A> selfManager) {
		super(selfManager);
		this.plugin = plugin;
	}
	
	@Override
	protected void implLoad() throws KeyContainerAlreadyUsed {
		Map<Integer, ConfigCE<?>> ces = new HashMap<Integer, ConfigCE<?>>();
		ces.put(CONSOLE_COLOR_CLOCK, new ConfigCEString(CONSOLE_COLOR_CLOCK, plugin.getTag()+".console.clock", TypeSide.CLIENT, "GREEN"));
		ces.put(CONSOLE_COLOR_LOGIN, new ConfigCEString(CONSOLE_COLOR_LOGIN, plugin.getTag()+".console.login", TypeSide.CLIENT, "WHITE"));
		ces.put(CONSOLE_COLOR_SEPARATOR, new ConfigCEString(CONSOLE_COLOR_SEPARATOR, plugin.getTag()+".console.separator", TypeSide.CLIENT, "GREEN"));
		ces.put(CONSOLE_COLOR_MESSAGE, new ConfigCEString(CONSOLE_COLOR_MESSAGE, plugin.getTag()+".console.message", TypeSide.CLIENT, "WHITE"));
		
		for (Integer id: ces.keySet()) {
			ConfigCE<?> ce = ces.get(id);
			try {
				// Obligé de charger soit même, car déjà parse par le noyau.
				if (ce.getSide() == getAura().getSide()) {
					ce.load(getAura().getCfgManager().getLoader());
					register(ce);
				}
			} catch (PropertiesLoaderException e) {
				e.printStackTrace();
			}
		}
	}
}