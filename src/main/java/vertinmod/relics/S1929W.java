package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

public class S1929W extends CustomRelic {
    public static final String ID = ModHelper.makePath("S192-9W");
    private static final String IMG_PATH = "ModVertinResources/img/relics/S192-9W.png";
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public S1929W() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void onPlayerEndTurn(){
        boolean f = false;
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
            int num_DEBUFF = 0;
            for (AbstractPower power:mo.powers)
                if(power.type == AbstractPower.PowerType.DEBUFF && !power.ID.equals("Shackled"))
                    num_DEBUFF++;
            if (num_DEBUFF >= 2) {
                f = true;
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, 12, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
            }
        }
        if (f)
            flash();
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new S1929W();
    }

}
