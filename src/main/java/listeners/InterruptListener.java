package listeners;

import botconfigs.IRCBot;
import botconfigs.IRCCommands;
import msg.IRCMsg;

/**
 * Created by JKyte on 3/12/2016.
 * <p>
 * An InterruptListener is used as a short duration listener that is a component of an Action Chain.
 * <p>
 * An example action chain for creating a channel
 * 1. query chanserv to see if a channel name is available
 * 2. if ( channel.isAvailable() ) then ( register channel )
 */
public class InterruptListener extends BaseListener {

    //  The maximum allowed duration for an InterruptListener
    private final long MAX_DURATION = 5 * 60 * 1000;   //  5 minutes * 60 seconds * 1000 millis

    //  The duration in SECONDS for this InterruptListener
    private long duration;

    //  The time in millis
    private long start;

    /**
     * @param duration in SECONDS for this InterruptListener
     */
    public InterruptListener(IRCBot ircbot, IRCCommands ircCommands, long duration) {
        super(ircbot, ircCommands);
        this.start = System.currentTimeMillis();
        this.duration = duration * 1000;    //  Convert the input SECONDS to MILLIS
        if (this.duration > MAX_DURATION) {
            this.duration = MAX_DURATION;
        }
    }

    @Override
    public boolean listen(IRCMsg msg) {
        return false;
    }

    @Override
    public void doAction() {

    }

    @Override
    public boolean listenerFinished() {

        long currentDuration = System.currentTimeMillis() - start;

        return currentDuration > duration;
    }
}
