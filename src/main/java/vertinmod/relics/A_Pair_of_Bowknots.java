package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

public class A_Pair_of_Bowknots extends CustomRelic implements OnApplyPowerRelic {
    public static final String ID = ModHelper.makePath("A_Pair_of_Bowknots");
    private static final String IMG_PATH = "ModVertinResources/img/relics/A_Pair_of_Bowknots.png";
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public A_Pair_of_Bowknots(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public boolean onApplyPower(AbstractPower toApply, AbstractCreature target, AbstractCreature source) {
        if ((toApply.ID.equals("Weakened") || toApply.ID.equals("Vulnerable") || toApply.ID.equals("BlockReturnPower") || toApply.ID.equals("Poison")
        || toApply.ID.equals("Choked") || toApply.ID.equals("PathToVictoryPower") || toApply.ID.equals("Shackled"))
                && target != AbstractDungeon.player && source == AbstractDungeon.player)
            toApply.amount++;
        else if (toApply.ID.equals("Strength") && target != AbstractDungeon.player && source == AbstractDungeon.player){
            if (toApply.amount < 0)
                toApply.amount--;
        }
        return true;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new A_Pair_of_Bowknots();
    }
}
