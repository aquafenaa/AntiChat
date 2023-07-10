package gay.baz.antichat;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(AntiChat.MODID)
public class AntiChat {
    public static final String MODID = "antichat";

     public AntiChat() {
        MinecraftForge.EVENT_BUS.register(this);
        
        MinecraftForge.EVENT_BUS.register(new ChatEventListener());
    }
}
