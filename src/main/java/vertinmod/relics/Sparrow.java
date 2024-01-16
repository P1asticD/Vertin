package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import vertinmod.helpers.ModHelper;

public class Sparrow extends CustomRelic {
    public static final String ID = ModHelper.makePath("Sparrow");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Sparrow.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private boolean DexActive = false;
    private boolean StrActive = false;
    public Sparrow(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void atBattleStart() {
        this.DexActive = false;
        this.StrActive = false;
        addToBot(new AbstractGameAction() {
            public void update() {
                if (!Sparrow.this.DexActive && AbstractDungeon.player.isBloodied) {
                    Sparrow.this.flash();
                    Sparrow.this.pulse = true;
                    AbstractDungeon.player.addPower(new DexterityPower(AbstractDungeon.player, 2));
                    addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, Sparrow.this));
                    Sparrow.this.DexActive = true;
                    AbstractDungeon.onModifyPower();
                }
                else if (!Sparrow.this.StrActive && !AbstractDungeon.player.isBloodied) {
                    Sparrow.this.flash();
                    Sparrow.this.pulse = true;
                    AbstractDungeon.player.addPower(new StrengthPower(AbstractDungeon.player, 2));
                    addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, Sparrow.this));
                    Sparrow.this.StrActive = true;
                    AbstractDungeon.onModifyPower();
                }
                this.isDone = true;
            }
        });
    }

    public void onBloodied() {
        flash();
        this.pulse = true;
        if (!this.DexActive && this.StrActive && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractPlayer p = AbstractDungeon.player;
            addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, -2), -2));
            addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.DexActive = true;
            this.StrActive = false;
            AbstractDungeon.player.hand.applyPowers();
        }
    }

    public void onNotBloodied() {
        if (this.DexActive && !this.StrActive && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractPlayer p = AbstractDungeon.player;
            addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
            addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, -2), -2));
        }
        stopPulse();
        this.DexActive = false;
        this.StrActive = true;
        AbstractDungeon.player.hand.applyPowers();
    }

    public void onVictory() {
        this.pulse = false;
        this.DexActive = false;
        this.StrActive = false;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Sparrow();
    }
}
