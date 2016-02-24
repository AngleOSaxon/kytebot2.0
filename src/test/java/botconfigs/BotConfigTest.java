package botconfigs;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import core.BotConstants;

public class BotConfigTest {

	@Test
	public void testBotConfigsAgainstDefaultProperties(){
		String configFilePath = BotConstants.TEST_DEFAULT;
		BotConfigs configs = BotConfigFactory.createBotConfigs(configFilePath);
		
		Assert.assertNotNull( configs );
		Assert.assertTrue( defaultConfigsAreValid(configs) );
	}
	
	private boolean defaultConfigsAreValid(BotConfigs configs) {
		//	Assert Core configs
		Assert.assertEquals("botnick", configs.getBotnick() );
		Assert.assertEquals("passwd", configs.getBotpasswd() );

		Assert.assertEquals(120000, configs.getHeartbeat() );

		//	Assert IRC Connection Configs
		Assert.assertEquals(6667, configs.getIrcport() );
		Assert.assertEquals("irc.server.net", configs.getIrcserver() );

		//	Assert Starting channels and ajoins
		Assert.assertEquals("#startchan", configs.getStartChan() );
		Assert.assertEquals(true, assertAjoins(configs.getAjoins()) );

		//	Assert Security Configurations
		Assert.assertEquals("yournick", configs.getAdmin() );
		Assert.assertEquals(true, assertTrustedUsers(configs.getTrustedUsers()) );

		//	Assert Action Configurations
		Assert.assertEquals(true, assertGreetingChannels(configs.getGreetingChans()) );
		Assert.assertEquals(true, assertFarewellChannels(configs.getFarewellChans()) );
		Assert.assertEquals(true, assertStoryChannels(configs.getStoryChans()) );
		return true;
	}

	private boolean assertAjoins( ArrayList<String> ajoins){
		if( ajoins.contains("#chan1") && 
				ajoins.contains("#chan2") && 
				ajoins.contains("#chan3") ){
			return true;
		}else{
			return false;
		}
	}

	private boolean assertTrustedUsers( HashSet<String> trustedUsers ){
		if( trustedUsers.contains("tr1") && 
				trustedUsers.contains("tr2") ){
			return true;
		}else{
			return false;
		}
	}

	
	private boolean assertStoryChannels( HashSet<String> storyChans ){
		if( storyChans.contains("#chan1") && 
				storyChans.contains("#chan4") ){
			return true;
		}else{
			return false;
		}
	}

	private boolean assertGreetingChannels( HashSet<String> greetChans ){
		if( greetChans.contains("#chan1") && 
				greetChans.contains("#chan2") && 
				greetChans.contains("#chan3") ){
			return true;
		}else{
			return false;
		}
	}

	private boolean assertFarewellChannels( HashSet<String> farewellChans ){
		if( farewellChans.contains("#chan1") && 
				farewellChans.contains("#chan2") && 
				farewellChans.contains("#chan3") ){
			return true;
		}else{
			return false;
		}
	}
}