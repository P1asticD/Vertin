package vertinmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DiscardAtEndOfTurnActionPatch {
    @SpirePatch(clz = DiscardAtEndOfTurnAction.class, method = "update")
    public static class updatePatch {
        @SpirePrefixPatch
        public static void updatePatch(DiscardAtEndOfTurnAction __instance) {
            for (int i = 0; i < AbstractDungeon.player.hand.size(); i++) {
                if (AbstractDungeon.player.hand.group.get(i) instanceof vertinmod.cards.Incantations.Self_Focusing) {
                    if (i - 1 >= 0 && !((AbstractCard)AbstractDungeon.player.hand.group.get(i - 1)).isEthereal) {
                        ((AbstractCard) AbstractDungeon.player.hand.group.get(i - 1)).retain = true;
                        ((AbstractCard) AbstractDungeon.player.hand.group.get(i - 1)).upgrade();
                    }
                    if(AbstractDungeon.player.hand.group.get(i).upgraded) {
                        if (i + 1 < AbstractDungeon.player.hand.group.size() && !((AbstractCard) AbstractDungeon.player.hand.group.get(i + 1)).isEthereal) {
                            ((AbstractCard) AbstractDungeon.player.hand.group.get(i + 1)).retain = true;
                            ((AbstractCard) AbstractDungeon.player.hand.group.get(i + 1)).upgrade();
                        }
                    }
                }
            }
        }
    }
}
