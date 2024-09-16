package bot;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class HourEqualBot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
    	
    	
        String token = "MTI4NTI1NzUyNzc3MjE4ODc1NA.G4-Pap.TAFZg4ax9mCEeVYfxAKAO60oimPNnwwItkeOJk"; 

        JDABuilder jdaBuilder = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT);
        jdaBuilder.addEventListeners(new HourEqualBot());
        jdaBuilder.build();
        
        agendarMensagemQuandoHorasForemIguais();
    }

    public static void agendarMensagemQuandoHorasForemIguais() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            LocalTime now = LocalTime.now();
            int hour = now.getHour();
            int minute = now.getMinute();

            if (hour == minute) {
                enviarMensagem(hour, minute);
            }
        }, 0, 1, TimeUnit.MINUTES); 
    }
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw(); 
        MessageChannel channel = event.getChannel();
        User author = event.getAuthor();
        
        if (author.isBot()) {
            return;
        }

        if (content.equalsIgnoreCase("!amor")) {
            channel.sendMessage("Oi amor, tudo bem?").queue(); 
        }
        if(content.equalsIgnoreCase("!teamo")) {
        	channel.sendMessage("Amo vc tbm").queue();
        }
        if(content.equalsIgnoreCase("!hm")) {
        	channel.sendMessage("hm").queue();
        }
        if(content.equalsIgnoreCase("!musga")) {
        	channel.sendMessage("https://open.spotify.com/intl-pt/track/1w6jyI7LNYjjdqhY5rwf3W?si=6a7b787abb094dac").queue();
        }

        if (content.equalsIgnoreCase("!oi")) {
            channel.sendMessage("Oi, " + author.getAsMention() + "!").queue();
        }
        
    }

    public static void enviarMensagem(int hour, int minute) {
        String token = "MTI4NTI1NzUyNzc3MjE4ODc1NA.G4-Pap.TAFZg4ax9mCEeVYfxAKAO60oimPNnwwItkeOJk"; 
        var jda = JDABuilder.createDefault(token).build();

		TextChannel channel = jda.getTextChannelById("1285069275488583740"); 

		if (channel != null) {
		    String message = String.format("Agora são %02d:%02d! o amor está no ar!!!", hour, minute);
		    channel.sendMessage(message).queue();
		}
    }
    
}
