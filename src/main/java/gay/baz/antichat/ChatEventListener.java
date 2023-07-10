package gay.baz.antichat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ChatEventListener {
  String[] bannedComms = { "me", "message", "msg", "say", "teammsg", "tell", "tm", "w", "whisper" };
  
  @SubscribeEvent
  public void onChatMessage(ClientChatEvent event) {
    sendMessage("Chatting is disabled!");

    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onCommand(CommandEvent event) {
    try {
      String command = event.getParseResults().getReader().getString().split(" ")[0];

      for (String comm : bannedComms) {
        if (command.equals(comm)) {
          event.setCanceled(true);

          sendMessage("You are not allowed to use this command.");
          return;
        }
      }
    } catch (Exception e) {
      // we don't want the error to crash the game. who cares if a message here and
      // there happens to get through?
      System.out.println(e);
    }
  }

  void sendMessage(String message) {
    LocalPlayer player = Minecraft.getInstance().player;

    if (player != null) {
      player.sendSystemMessage(Component.literal(message).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xff0000))));
    }
  }
}
