package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;
import vertinmod.patches.TokenCardField;

public class Fractal_Geometry extends CustomRelic {
    public static final String ID = ModHelper.makePath("Fractal_Geometry");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Fractal_Geometry.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Fractal_Geometry(){super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);}

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStart() {
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if ((TokenCardField.isToken.get(card)).booleanValue()) {
            this.counter++;
            if (this.counter % 3 == 0) {
                flash();
                this.counter = 0;
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(7, true), DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.LIGHTNING));
            }
        }
    }

    public void onVictory() {
        this.counter = -1;
    }

    public AbstractRelic makeCopy() {
        return new Fractal_Geometry();
    }
}
