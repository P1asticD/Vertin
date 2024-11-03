package vertinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import static vertinmod.modcore.VertinMod.Vertin;

public class DissonanceAction extends AbstractGameAction {
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(Vertin))
                c.setCostForTurn(-9);
        }
        this.isDone = true;
    }
}
