package com.aura.chat.console;

import com.aura.base.Aura;
import com.aura.base.TypeSide;
import com.aura.base.manager.console.ConsoleCE;
import com.aura.base.manager.console.ConsoleCM;

public class CPConsoleCM<A extends Aura> extends ConsoleCM<A> {
	
	public final static int SAY = CURSEUR.nextVal();
	
	public CPConsoleCM(ConsoleCM<A> selfManager) {
		super(selfManager);
	}

	@Override
	protected void implLoad() throws KeyContainerAlreadyUsed {
		register(new ConsoleCE(SAY, "SAY", "say", TypeSide.CLIENT, 1, "Ex: /say <message>"));
	}
}