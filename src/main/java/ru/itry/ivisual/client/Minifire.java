package ru.itry.ivisual.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderBlockScreenEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "ivisual",
        value = Dist.CLIENT
)
public class Minifire {

    public static boolean enabled = true;
    public static double fireHeight = -0.3;

    @SubscribeEvent
    public static void onRenderFireOverlay(RenderBlockScreenEffectEvent event) {
        if (enabled && event.getOverlayType() == RenderBlockScreenEffectEvent.OverlayType.FIRE) {

            event.getPoseStack().translate(0.0D, fireHeight, 0.0D);
        }
    }
}
