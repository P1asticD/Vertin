package vertinmod.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import vertinmod.characters.Vertin;
import vertinmod.ui.SkinSelectScreen;

public class SkinSelectPatch {
    public static boolean isyuzuSelected() {
        return (CardCrawlGame.chosenCharacter == Vertin.Enums.VERTIN && ((Boolean)ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.charSelectScreen, CharacterSelectScreen.class, "anySelected")).booleanValue());
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
    public static class RenderButtonPatch {
        public static void Postfix(CharacterSelectScreen _inst, SpriteBatch sb) {
            if (SkinSelectPatch.isyuzuSelected())
                SkinSelectScreen.Inst.render(sb);
        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "update")
    public static class UpdateButtonPatch {
        public static void Prefix(CharacterSelectScreen _inst) {
            if (SkinSelectPatch.isyuzuSelected())
                SkinSelectScreen.Inst.update();
        }
    }
}
