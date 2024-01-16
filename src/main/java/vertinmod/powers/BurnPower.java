package vertinmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import vertinmod.actions.BurnLoseHpAction;
import vertinmod.helpers.ModHelper;

public class BurnPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makePath("BurnPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCreature source;

    public BurnPower(AbstractCreature owner, AbstractCreature source, int burnAmt){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.amount = burnAmt;
        if (this.amount >= 9999)
            this.amount = 9999;
        String path128 = "ModVertinResources/img/powers/Burn84.png";
        String path48 = "ModVertinResources/img/powers/Burn32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
        this.type = AbstractPower.PowerType.DEBUFF;
    }


    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("ATTACK_FIRE", 0.05F);
    }

    public void updateDescription() {
        if (this.owner == null || this.owner.isPlayer) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[1];
        }
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    public void atEndOfTurn(boolean isPlayer) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flashWithoutSound();
            addToBot(new BurnLoseHpAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.FIRE));
        }
    }
}
