package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

public class Sputnik extends CustomRelic implements OnApplyPowerRelic {
    public static final String ID = ModHelper.makePath("Sputnik");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Sputnik.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Sputnik(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public boolean onApplyPower(AbstractPower toApply, AbstractCreature target, AbstractCreature source) {
        if ((toApply.ID.equals("Thorns") && target == AbstractDungeon.player && source == AbstractDungeon.player)) {
            flash();
            AbstractDungeon.player.addPower(new DexterityPower(AbstractDungeon.player, toApply.amount));
        }
        return true;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Sputnik();
    }
}
