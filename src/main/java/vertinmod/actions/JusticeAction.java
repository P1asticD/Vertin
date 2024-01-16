package vertinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static vertinmod.relics.The_Spinning_Wheel.Moxie;
import static vertinmod.relics.The_Spinning_Wheel.Moxie_Max;

public class JusticeAction extends AbstractGameAction {
    private int increaseAmount;
    private DamageInfo info;

    public JusticeAction(AbstractCreature target, DamageInfo info, int Amount){
        this.info = info;
        setValues(target, info);
        this.increaseAmount = Amount;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    public void update(){
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HEAVY));
            this.target.damage(this.info);
            if (((this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                AbstractDungeon.player.gainEnergy(increaseAmount);
                addToBot(new DrawCardAction(AbstractDungeon.player, increaseAmount));
                if(Moxie.get(15) > 0){
                    Moxie.set(15, Math.min(Moxie.get(15) + increaseAmount, Moxie_Max));
                }
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        tickDuration();
    }
}
