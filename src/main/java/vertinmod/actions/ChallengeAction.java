package vertinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class ChallengeAction extends AbstractGameAction {
    private DamageInfo info;

    public ChallengeAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public void update() {
        if (shouldCancelAction()) {
            this.isDone = true;
            return;
        }
        tickDuration();
        if (this.isDone) {
            this.target.damage(this.info);
            if (this.target.lastDamageTaken > 0) {
                addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, this.target.lastDamageTaken), this.target.lastDamageTaken));
            }
        }
    }
}
