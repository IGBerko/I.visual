package ru.itry.ivisual.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "ivisual",
        value = Dist.CLIENT
)
public class VisualTweaks {

    public static boolean noPumpkin = true;
    public static boolean noSnowBlur = true;
    public static boolean lowShield = true;
    public static boolean noSkyElements = true;

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
        String overlayPath = event.getOverlay().id().getPath().toLowerCase();

        if (noPumpkin && overlayPath.contains("pumpkin")) {
            event.setCanceled(true);
        }

        if (noSnowBlur && (overlayPath.contains("frostbite") || overlayPath.contains("snow"))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        if (lowShield && event.getItemStack().is(Items.SHIELD)) {
            event.getPoseStack().translate(0.0D, -0.35D, 0.1D);
        }
    }

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (noSkyElements && event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            mc.options.cloudStatus().set(net.minecraft.client.CloudStatus.OFF);
        }
    }
}
