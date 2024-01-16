package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

public class Critters extends CustomRelic {
    public static final String ID = ModHelper.makePath("Critters");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Critters.png";
    private static final AbstractRelic.RelicTier RELIC_TIER = RelicTier.RARE;
    private static final AbstractRelic.LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Critters(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 1) {
            if (AbstractDungeon.player.hasPower("Thorns")) {
                if(damageAmount <= AbstractDungeon.player.getPower("Thorns").amount) {
                    flash();
                    addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    return 1;
                }
            }
        }
        return damageAmount;
    }

    public void atBattleStart() {
        flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, 2), 2));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Critters();
    }
}
