package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import vertinmod.helpers.ModHelper;

import java.util.Iterator;

public class SPDM_Rules extends CustomRelic {
    public static final String ID = ModHelper.makePath("SPDM_Rules");
    private static final String IMG_PATH = "ModVertinResources/img/relics/SPDM_Rules.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public SPDM_Rules(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        for (Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator(); i.hasNext(); ) {
            AbstractCard e = i.next();
            if (e.hasTag(AbstractCard.CardTags.STARTER_DEFEND) || e.hasTag(AbstractCard.CardTags.STARTER_STRIKE))
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(e.makeStatEquivalentCopy(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        }
        AbstractDungeon.player.energy.energyMaster++;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

    public AbstractRelic makeCopy(){
        return new SPDM_Rules();
    }
}
