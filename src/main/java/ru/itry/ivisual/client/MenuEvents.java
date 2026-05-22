package ru.itry.ivisual.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "ivisual",
        value = Dist.CLIENT
)
public class MenuEvents {

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {

        if (event.getScreen() instanceof TitleScreen) {

            AbstractWidget singleplayerButton = null;

            for (var r : event.getListenersList()) {
                if (r instanceof AbstractWidget widget) {
                    if (widget.getMessage().getContents() instanceof TranslatableContents translatable
                            && translatable.getKey().equals("menu.singleplayer")) {
                        singleplayerButton = widget;
                        break;
                    }
                }
            }

            if (singleplayerButton != null) {

                singleplayerButton.setWidth(98);

                event.addListener(
                        Button.builder(
                                Component.literal("I.visual"),
                                button -> Minecraft.getInstance().setScreen(new VisualMenuScreen())
                        ).bounds(
                                singleplayerButton.getX() + 102,
                                singleplayerButton.getY(),
                                98,
                                20
                        ).build()
                );
            } else {
                event.addListener(
                        Button.builder(
                                Component.literal("I.visual"),
                                button -> Minecraft.getInstance().setScreen(new VisualMenuScreen())
                        ).bounds(10, 10, 98, 20).build()
                );
            }
        }

        if (event.getScreen() instanceof PauseScreen) {
            event.addListener(
                    Button.builder(
                            Component.literal("I.visual"),
                            button -> Minecraft.getInstance().setScreen(new VisualMenuScreen())
                    ).bounds(10, 10, 100, 20).build()
            );
        }
    }
}
