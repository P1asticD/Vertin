package vertinmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class ScryActionPatch {
    public static int num;

    @SpirePatch(clz = ScryAction.class, method = "update")
    public static class updatePatch {
        @SpireInsertPatch(rloc = 27)
        public static void Insert(ScryAction action) {
            ScryActionPatch.num = AbstractDungeon.gridSelectScreen.selectedCards.size();
            if (action.amount == AbstractDungeon.gridSelectScreen.selectedCards.size()) {
                AbstractPower power = AbstractDungeon.player.getPower("Paranoia:TheCouragePower");
                if (power != null) {
                    power.flash();
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, power.amount));
                }
            }
            AbstractPower power2 = AbstractDungeon.player.getPower("Paranoia:HintPower");
            if (power2 != null) {
                for (int i = 0; i < AbstractDungeon.gridSelectScreen.selectedCards.size(); i++)
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, power2.amount));
                if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())
                    power2.flash();
            }
            if (((Boolean)ScryActionPatch.AddFieldPatch.playOne.get(action)).booleanValue() &&
                    !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                int tmp = Math.min(AbstractDungeon.player.drawPile.size(), action.amount);
                if (!AbstractDungeon.player.drawPile.isEmpty() && tmp <= AbstractDungeon.gridSelectScreen.selectedCards.size() * 2) {
                    final AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(AbstractDungeon.cardRandomRng.random(AbstractDungeon.gridSelectScreen.selectedCards.size() - 1));
                    AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                        public void update() {
                            this.isDone = true;
                            if (AbstractDungeon.player.discardPile.group.contains(c)) {
                                AbstractDungeon.player.discardPile.group.remove(c);
                                (AbstractDungeon.getCurrRoom()).souls.remove(c);
                                addToBot((AbstractGameAction)new NewQueueCardAction(c, true, false, true));
                            } else if (AbstractDungeon.player.exhaustPile.group.contains(c)) {
                                c.unfadeOut();
                                AbstractDungeon.player.exhaustPile.group.remove(c);
                                (AbstractDungeon.getCurrRoom()).souls.remove(c);
                                addToBot((AbstractGameAction)new NewQueueCardAction(c, true, false, true));
                            } else if (AbstractDungeon.player.hand.group.contains(c)) {
                                AbstractDungeon.player.hand.group.remove(c);
                                (AbstractDungeon.getCurrRoom()).souls.remove(c);
                                addToBot((AbstractGameAction)new NewQueueCardAction(c, true, false, true));
                            } else if (AbstractDungeon.player.drawPile.group.contains(c)) {
                                AbstractDungeon.player.drawPile.group.remove(c);
                                (AbstractDungeon.getCurrRoom()).souls.remove(c);
                                addToBot((AbstractGameAction)new NewQueueCardAction(c, true, false, true));
                            }
                        }
                    });
                }
            }
        }

        @SpireInsertPatch(rloc = 6)
        public static void Insert2(ScryAction action) {
            ScryActionPatch.num = 0;
        }

        @SpireInsertPatch(rloc = 1)
        public static void Insert3(ScryAction action) {
            ScryActionPatch.num = 0;
        }

        @SpireInstrumentPatch
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                public void edit(MethodCall m) throws CannotCompileException {
                    if (isMethodCalled(m))
                        m.replace("{if(" + ScryActionPatch.updatePatch.class.getName() + ".check(this)){" + ScryActionPatch.updatePatch.class.getName() + ".cal($1);}else{$_=$proceed($$);}}");
                }

                private boolean isMethodCalled(MethodCall m) {
                    return "moveToDiscardPile".equals(m.getMethodName());
                }
            };
        }

        public static boolean check(ScryAction action) {
            return ((Boolean)ScryActionPatch.AddFieldPatch.exhaust.get(action)).booleanValue();
        }

        public static void cal(AbstractCard c) {
            AbstractDungeon.player.drawPile.moveToExhaustPile(c);
        }
    }

    @SpirePatch(clz = ScryAction.class, method = "<class>")
    public static class AddFieldPatch {
        public static SpireField<Boolean> exhaust = new SpireField(() -> Boolean.FALSE);

        public static SpireField<Boolean> playOne = new SpireField(() -> Boolean.FALSE);
    }
}