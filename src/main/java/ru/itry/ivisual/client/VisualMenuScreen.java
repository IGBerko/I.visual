package ru.itry.ivisual.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.Util;

public class VisualMenuScreen extends Screen {

    private Button espButton;
    private Button minifireButton;
    private Button lowShieldButton;
    private Button noPumpkinButton;
    private Button noSnowButton;
    private Button noSkyButton;
    private Button curseButton; // Кнопка CurseForge
    private Button closeButton;

    public VisualMenuScreen() {
        super(Component.literal("I.visual"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // ЛЕВЫЙ СТОЛБЕЦ
        espButton = Button.builder(getEspText(), button -> {
            ESP.enabled = !ESP.enabled;
            button.setMessage(getEspText());
        }).bounds(centerX - 105, centerY - 35, 100, 20).build();

        minifireButton = Button.builder(getMinifireText(), button -> {
            Minifire.enabled = !Minifire.enabled;
            button.setMessage(getMinifireText());
        }).bounds(centerX - 105, centerY - 10, 100, 20).build();

        lowShieldButton = Button.builder(getLowShieldText(), button -> {
            VisualTweaks.lowShield = !VisualTweaks.lowShield;
            button.setMessage(getLowShieldText());
        }).bounds(centerX - 105, centerY + 15, 100, 20).build();

        curseButton = Button.builder(Component.literal("CurseForge"), button -> {
            // Открывает ссылку в браузере ПК
            Util.getPlatform().openUri("https://www.curseforge.com/minecraft/mc-mods/i-visual");
        }).bounds(centerX - 105, centerY + 40, 100, 20).build();

        // ПРАВЫЙ СТОЛБЕЦ
        noPumpkinButton = Button.builder(getNoPumpkinText(), button -> {
            VisualTweaks.noPumpkin = !VisualTweaks.noPumpkin;
            button.setMessage(getNoPumpkinText());
        }).bounds(centerX + 5, centerY - 35, 100, 20).build();

        noSnowButton = Button.builder(getNoSnowText(), button -> {
            VisualTweaks.noSnowBlur = !VisualTweaks.noSnowBlur;
            button.setMessage(getNoSnowText());
        }).bounds(centerX + 5, centerY - 10, 100, 20).build();

        noSkyButton = Button.builder(getNoSkyText(), button -> {
            VisualTweaks.noSkyElements = !VisualTweaks.noSkyElements;
            button.setMessage(getNoSkyText());
        }).bounds(centerX + 5, centerY + 15, 100, 20).build();

        // НИЖНЯЯ КНОПКА
        closeButton = Button.builder(Component.literal("Close Menu"), button -> this.onClose())
                .bounds(centerX - 105, centerY + 70, 210, 20).build();

        // Прозрачность
        espButton.setAlpha(0.0F);
        minifireButton.setAlpha(0.0F);
        lowShieldButton.setAlpha(0.0F);
        curseButton.setAlpha(0.0F);
        noPumpkinButton.setAlpha(0.0F);
        noSnowButton.setAlpha(0.0F);
        noSkyButton.setAlpha(0.0F);
        closeButton.setAlpha(0.0F);

        addRenderableWidget(espButton);
        addRenderableWidget(minifireButton);
        addRenderableWidget(lowShieldButton);
        addRenderableWidget(curseButton);
        addRenderableWidget(noPumpkinButton);
        addRenderableWidget(noSnowButton);
        addRenderableWidget(noSkyButton);
        addRenderableWidget(closeButton);
    }

    private Component getEspText() {
        return Component.literal("ESP: " + (ESP.enabled ? "ON" : "OFF"));
    }

    private Component getMinifireText() {
        return Component.literal("Minifire: " + (Minifire.enabled ? "ON" : "OFF"));
    }

    private Component getLowShieldText() {
        return Component.literal("Low Shield: " + (VisualTweaks.lowShield ? "ON" : "OFF"));
    }

    private Component getNoPumpkinText() {
        return Component.literal("No Pumpkin: " + (VisualTweaks.noPumpkin ? "ON" : "OFF"));
    }

    private Component getNoSnowText() {
        return Component.literal("No Snow: " + (VisualTweaks.noSnowBlur ? "ON" : "OFF"));
    }

    private Component getNoSkyText() {
        return Component.literal("No Sky: " + (VisualTweaks.noSkyElements ? "ON" : "OFF"));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        graphics.fill(centerX - 115, centerY - 70, centerX + 115, centerY + 100, 0x99101010);
        renderOutline(graphics, centerX - 115, centerY - 70, centerX + 115, centerY + 100, 0xFF5B5BFF);

        graphics.drawCenteredString(this.font, "I.VISUAL TWEAKS", centerX, centerY - 58, 0xFF5B5BFF);
        graphics.fill(centerX - 100, centerY - 45, centerX + 100, centerY - 44, 0x55FFFFFF);

        drawCustomButton(graphics, espButton, mouseX, mouseY, ESP.enabled ? 0xFF2ECC71 : 0xFFE74C3C);
        drawCustomButton(graphics, minifireButton, mouseX, mouseY, Minifire.enabled ? 0xFF2ECC71 : 0xFFE74C3C);
        drawCustomButton(graphics, lowShieldButton, mouseX, mouseY, VisualTweaks.lowShield ? 0xFF2ECC71 : 0xFFE74C3C);
        drawCustomButton(graphics, curseButton, mouseX, mouseY, 0xFF3498DB); // Синий акцент для ссылки

        drawCustomButton(graphics, noPumpkinButton, mouseX, mouseY, VisualTweaks.noPumpkin ? 0xFF2ECC71 : 0xFFE74C3C);
        drawCustomButton(graphics, noSnowButton, mouseX, mouseY, VisualTweaks.noSnowBlur ? 0xFF2ECC71 : 0xFFE74C3C);
        drawCustomButton(graphics, noSkyButton, mouseX, mouseY, VisualTweaks.noSkyElements ? 0xFF2ECC71 : 0xFFE74C3C);
        drawCustomButton(graphics, closeButton, mouseX, mouseY, 0xFF95A5A6);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void drawCustomButton(GuiGraphics graphics, Button button, int mouseX, int mouseY, int statusColor) {
        boolean hovered = mouseX >= button.getX() && mouseY >= button.getY() && mouseX < button.getX() + button.getWidth() && mouseY < button.getY() + button.getHeight();
        int backgroundColor = hovered ? 0xDD202020 : 0xBB151515;
        int outlineColor = hovered ? statusColor : 0x44FFFFFF;

        graphics.fill(button.getX(), button.getY(), button.getX() + button.getWidth(), button.getY() + button.getHeight(), backgroundColor);
        renderOutline(graphics, button.getX(), button.getY(), button.getX() + button.getWidth(), button.getY() + button.getHeight(), outlineColor);

        int textColor = hovered ? 0xFFFFFFFF : 0xFFCCCCCC;
        graphics.drawCenteredString(this.font, button.getMessage(), button.getX() + button.getWidth() / 2, button.getY() + (button.getHeight() - 8) / 2, textColor);
    }

    private void renderOutline(GuiGraphics graphics, int x1, int y1, int x2, int y2, int color) {
        graphics.fill(x1, y1, x2, y1 + 1, color);
        graphics.fill(x1, y1 + 1, x1 + 1, y2, color);
        graphics.fill(x2 - 1, y1 + 1, x2, y2, color);
        graphics.fill(x1, y2 - 1, x2, y2, color);
    }
}
