package vertinmod.patches;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import vertinmod.cards.Ver_CustomCard;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SingleCardViewPatch {
    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderTips")
    public static class RenderTipsPatch {
        public static void Postfix(SingleCardViewPopup _instance, SpriteBatch sb) {
            try {
                Field cardField = SingleCardViewPopup.class.getDeclaredField("card");
                cardField.setAccessible(true);
                AbstractCard card = (AbstractCard) cardField.get(_instance);
                if (card instanceof Ver_CustomCard) {
                    float BODY_TEXT_WIDTH = 280.0F * Settings.scale;
                    float TIP_DESC_LINE_SPACING = 26.0F * Settings.scale;
                    String title = "Tips";
                    String body = ((Ver_CustomCard)card).FLAVOR_TEXT;
                    if (body.isEmpty())
                        return;
                    Field textHeight = TipHelper.class.getDeclaredField("textHeight");
                    textHeight.setAccessible(true);
                    Method renderTipBox = TipHelper.class.getDeclaredMethod("renderTipBox", new Class[]{float.class, float.class, SpriteBatch.class, String.class, String.class});
                    renderTipBox.setAccessible(true);
                    float height = -FontHelper.getSmartHeight(FontHelper.tipBodyFont, body, BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING);
                    textHeight.setFloat(null, height - 7.0F * Settings.scale);
                    renderTipBox.invoke(null, new Object[]{Float.valueOf(Settings.WIDTH / 2.0F - 650.0F * Settings.scale), Float.valueOf(400.0F * Settings.scale), sb, title, body});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}