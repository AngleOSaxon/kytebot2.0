package commandListeners;

import botconfigs.IRCBot;
import msg.IRCMsg;

import java.util.ArrayList;

public class JoinCommand extends AdminCommand {

	private String targetChannel;

    public JoinCommand(IRCBot ircbot) {
        super(ircbot);
    }

	@Override
	public boolean listen(IRCMsg msg) {
		//	Ensure an admin is making this call
		if( !super.listen(msg) ){
			return false;
		}
		
		String[] chunks = msg.getTrailing().split(" ");
		if( chunks.length == 2 && chunks[0].equalsIgnoreCase("JOIN") ){
			targetChannel = chunks[1];
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void doAction() {
		outboundMsgQ.add( ircCommands.joinChannel(targetChannel));	
	}

	@Override
	public void loadCommandDescription() {
        description = new ArrayList<>();
        description.add( "JOIN - usage: ![botnick] JOIN [#channel]");
		description.add( "  - orders the bot to join a channel.");	
	}
}
