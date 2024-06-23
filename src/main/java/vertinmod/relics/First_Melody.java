package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import vertinmod.helpers.ModHelper;

public class First_Melody extends CustomRelic implements BetterClickableRelic<First_Melody>{
    public static final String ID = ModHelper.makePath("First_Melody");
    private static final String IMG_PATH = "ModVertinResources/img/relics/The_Spinning_Wheel.png";
    public static AbstractRoom.RoomPhase phase;
    private static AbstractDungeon.CurrentScreen pre;
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public First_Melody(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        this.counter = 0;
        this.setDuration(2000).addRightClickActions(
                () -> this.Prepared1()
        );
    }

    public void atTurnStart(){
        this.counter = 0;
    }

    public void Prepared1() {
        if (this.counter == 0) {
            this.counter = 1;
            addToBot(new DrawCardAction(1));
            addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, false));
        } else
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, this.DESCRIPTIONS[1], true));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new First_Melody();
    }

}
