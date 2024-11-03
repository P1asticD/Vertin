package vertinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import vertinmod.cards.Ver_CustomCard;

public class UpgradeAllAction extends AbstractGameAction {

    public UpgradeAllAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.canUpgrade()) {
                c.upgrade();
                c.superFlash();
                c.applyPowers();
            }
            else if (c instanceof Ver_CustomCard){
                c.upgrade();
                c.superFlash();
                c.applyPowers();
            }
        }
        this.isDone = true;
    }
}
