package com.enormouz.cpsmod.features;

import com.enormouz.cpsmod.CPSMod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

public class CPSCounter {

    private final ArrayList<Long> leftClickList = new ArrayList<>();
    private final ArrayList<Long> rightClickList = new ArrayList<>();

    @SubscribeEvent
    public void onMouseClick(InputEvent.MouseInputEvent event) {
        if (CPSMod.mc.thePlayer == null || CPSMod.mc.theWorld == null) {
            return;
        }
        int leftClickButton = CPSMod.mc.gameSettings.keyBindAttack.getKeyCode();
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == leftClickButton + 100) {
            leftClickList.add(System.currentTimeMillis());
        }

        int rightClickButton = CPSMod.mc.gameSettings.keyBindUseItem.getKeyCode();
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == rightClickButton + 100) {
            rightClickList.add(System.currentTimeMillis());
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (CPSMod.mc.thePlayer == null || CPSMod.mc.theWorld == null) {
            return;
        }
        String name = getCPS() + " CPS";
        CPSMod.mc.fontRendererObj.drawString(name, 50, 50, 0xFFFFFF);
    }

    int getCPS() {
        long time = System.currentTimeMillis();
        leftClickList.removeIf((o) -> o + 1000L < time);
        rightClickList.removeIf((o) -> o + 1000L < time);
        return leftClickList.size() + rightClickList.size();
    }
}
