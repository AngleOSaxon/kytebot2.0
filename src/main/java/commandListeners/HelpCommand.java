package commandListeners;

import botconfigs.IRCBot;
import msg.IRCMsg;

import java.util.ArrayList;

public class HelpCommand extends BaseCommand {
	
	private String helpKey;

    public HelpCommand(IRCBot ircbot) {
        super(ircbot);
	}

	@Override
	public void loadCommandDescription() {
        description = new ArrayList<>();
        description.add( "HELP - usage: ![botnick] HELP [COMMAND]");
		description.add( "  - for a list of commands run ![botnick] LIST");
	}

	@Override
	public boolean listen(IRCMsg msg) {

        log.info("Prefix: " + msg.getPrefix());
        log.info("Command: " + msg.getCommand());
        log.info("Args[0]: " + msg.getArgs()[0]);
        log.info("Trailing: " + msg.getTrailing());

        target = msg.getArgs()[0];
        helpKey = null;
		String[] chunks = msg.getTrailing().split(" ");
		if( chunks.length == 1 && chunks[0].equalsIgnoreCase("HELP") ){
			helpKey = "HELP";
			return true;
		}else if( chunks.length == 2 && chunks[0].equalsIgnoreCase("HELP") ){
			helpKey = chunks[1];
			return true;
		}else{
			return false;	
		} 
	}

	
	@Override
	public void doAction() {
        log.info("Help command called!");

        helpKey = helpKey.toUpperCase();

        ArrayList<String> tmpDescription = ((BaseCommand) ircbot.getBotCommandListeners().get(helpKey)).getCommandDescription();
        for (String line : tmpDescription) {
            outboundMsgQ.add(ircCommands.privmsg(target, line));
        }

        //	Reset target & helpKey once command has executed
		target = null;
		helpKey = null;
	}
}
