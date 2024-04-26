package vertinmod.patches;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import javassist.CtBehavior;

public class CardTitleRenderPatch {
    public static final float SMALLER_SCALE_CHECK = 0.8F;

    public static final float SMALLER_SCALE_APPLY = 0.72F;

    @SpirePatch(clz = AbstractCard.class, method = "<class>")
    public static class Field {
        public static SpireField<Boolean> useSmallerTitleFont = new SpireField(() -> Boolean.valueOf(false));
    }

    @SpirePatch(clz = AbstractCard.class, method = "initializeTitle")
    public static class DrawCardReplace {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(AbstractCard __instance, GlyphLayout ___gl) {
            if (___gl.width > AbstractCard.IMG_WIDTH * 0.8F)
                CardTitleRenderPatch.Field.useSmallerTitleFont.set(__instance, Boolean.valueOf(true));
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(GlyphLayout.class, "reset");
                return LineFinder.findInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderTitle")
    public static class RenderTitle {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(AbstractCard __instance) {
            if (((Boolean)CardTitleRenderPatch.Field.useSmallerTitleFont.get(__instance)).booleanValue())
                FontHelper.cardTitleFont.getData().setScale(__instance.drawScale * 0.72F);
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "upgraded");
                return LineFinder.findInOrder(ctMethodToPatch, (Matcher)fieldAccessMatcher);
            }
        }
    }
}
