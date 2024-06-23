package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

public class Scripture extends CustomRelic implements OnApplyPowerRelic {
    public static final String ID = ModHelper.makePath("Scripture");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Scripture.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private boolean BuffThisTurn = false;
    private boolean DebuffThisTurn = false;

    public Scripture(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void atTurnStart() {
        this.BuffThisTurn = false;
        this.DebuffThisTurn = false;
    }

    public boolean onApplyPower(AbstractPower toApply, AbstractCreature target, AbstractCreature source) {
        if(!this.DebuffThisTurn) {
            if (toApply.ID.equals("Strength") && target != AbstractDungeon.player && source == AbstractDungeon.player && !target.hasPower("Artifact")) {
                if (toApply.amount < 0) {
                    this.DebuffThisTurn = true;
                    flash();
                    addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    addToBot(new GainEnergyAction(1));
                }
            }
            else if(toApply.type.equals(AbstractPower.PowerType.DEBUFF) && target != AbstractDungeon.player && source == AbstractDungeon.player && !target.hasPower("Artifact")){
                this.DebuffThisTurn = true;
                flash();
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new GainEnergyAction(1));
            }
        }
        if(!this.BuffThisTurn){
            if(toApply.type.equals(AbstractPower.PowerType.BUFF) && target == AbstractDungeon.player && source == AbstractDungeon.player){
                this.BuffThisTurn = true;
                flash();
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new GainEnergyAction(1));
            }
        }
        return true;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Scripture();
    }
}
