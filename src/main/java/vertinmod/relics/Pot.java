package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

public class Pot extends CustomRelic {
    public static final String ID = ModHelper.makePath("Pot");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Pot.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private int MAX_Dmg = 0;
    private AbstractMonster targetMonster = null;

    public Pot(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStart(){
        flash();
        MAX_Dmg = 0;
        targetMonster = AbstractDungeon.getCurrRoom().monsters.monsters.get(0);
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
            if(mo.getIntentBaseDmg() >= MAX_Dmg){
                MAX_Dmg = mo.getIntentBaseDmg();
                targetMonster = mo;
            }
        }
        addToBot(new ApplyPowerAction(targetMonster, AbstractDungeon.player, new VulnerablePower(targetMonster, 1, false), 1, true));
        addToBot(new ApplyPowerAction(targetMonster, AbstractDungeon.player, new WeakPower(targetMonster, 1, false), 1, true));
    }

    public AbstractRelic makeCopy(){
        return new Pot();
    }
}
