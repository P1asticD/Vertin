package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

import static vertinmod.relics.The_Spinning_Wheel.Moxie;
import static vertinmod.relics.The_Spinning_Wheel.Moxie_Max;

public class Goggles extends CustomRelic {
    public static final String ID = ModHelper.makePath("Goggles");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Goggles.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private boolean gainMoxie = false;

    public Goggles(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void atPreBattle() {
        flash();
        this.gainMoxie = true;
        if (!this.pulse) {
            beginPulse();
            this.pulse = true;
        }
    }

    public void onPlayerEndTurn() {
        beginPulse();
        this.pulse = true;
        if (this.gainMoxie) {
            flash();
            for (int i = 0; i < Moxie.size(); i++){
                if (Moxie.get(i) > 0){
                    Moxie.set(i, Math.min(Moxie_Max, Moxie.get(i) + 1));
                }
            }
        }
        this.gainMoxie = true;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.gainMoxie = false;
            this.pulse = false;
        }
    }

    public void onVictory() {
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Goggles();
    }
}
