package listenerFactories;

import botconfigs.IRCBot;
import botconfigs.IRCCommands;
import commandListeners.*;
import listeners.Listeners;

public class BotCommandListenerFactory {

	public static Listeners createEventListeners(IRCBot ircbot, IRCCommands ircCommands){
		Listeners listeners = new Listeners();
		
		listeners.put("HELP", new HelpCommand(ircbot, ircCommands));
		listeners.put("LIST", new ListCommand(ircbot, ircCommands));
		listeners.put("TRADE", new TradeCommand(ircbot, ircCommands));
		listeners.put("JOIN", new JoinCommand(ircbot, ircCommands));
		listeners.put("FLIRT", new FlirtCommand(ircbot, ircCommands));
		
		return listeners;
	}
}
