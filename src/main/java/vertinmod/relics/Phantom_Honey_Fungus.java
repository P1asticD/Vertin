package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

public class Phantom_Honey_Fungus extends CustomRelic implements OnReceivePowerRelic {
    public static final String ID = ModHelper.makePath("Phantom_Honey_Fungus");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Phantom_Honey_Fungus.png";
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public Phantom_Honey_Fungus(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public boolean onReceivePower(AbstractPower beingApplied, AbstractCreature source) {
        if (beingApplied.ID.equals("Vulnerable")) {
            flash();
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            return false;
        }
        return true;
    }

    public AbstractRelic makeCopy() {
        return new Phantom_Honey_Fungus();
    }
}
