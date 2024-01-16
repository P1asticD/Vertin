package vertinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class AD778Action extends AbstractGameAction {
    private DamageInfo info;

    public AD778Action(AbstractCreature target, DamageInfo info, int Amount){
        this.info = info;
        setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.amount = Amount;
        this.duration = 0.1F;
    }

    public void update(){
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HEAVY));
            int va = this.info.output - this.target.currentHealth - this.target.currentBlock;
            this.target.damage(this.info);
            if (((this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                AbstractDungeon.player.addPower(new VigorPower(AbstractDungeon.player, va));
                AbstractDungeon.player.gainEnergy(1);
                addToBot(new DrawCardAction(AbstractDungeon.player, 1));
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        tickDuration();
    }
}
