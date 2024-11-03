package vertinmod.helpers;

import basemod.abstracts.CustomMultiPageFtue;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.ui.FtueTip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ModHelper {
    public static String makePath(String id) {
        return "VertinMod:" + id;
    }

    private static TutorialStrings generated = null;
    public static String ENABLE_SPELL_CARD_SIGN_DISPLAY = "enableSpellCardSignDisplay";

    public static SpireConfig conf = null;
    public static void generateTutorial() {
        TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString(makePath("Raw"));
        ArrayList<String> res = new ArrayList<>();
        for (String key : tutorialStrings.TEXT) {
            String s = key;
            res.add(s);
        }
        generated = new TutorialStrings();
        generated.TEXT = res.<String>toArray(new String[0]);
    }
    public static void openTutorial() {
        if (generated == null)
            generateTutorial();
        Texture[] images = new Texture[generated.TEXT.length];
        for (int i = 0; i < images.length; i++)
            images[i] = ImageMaster.loadImage("ModVertinResources/img/tutorial/" + i + ".png");
        AbstractDungeon.ftue = (FtueTip)new CustomMultiPageFtue(images, generated.TEXT);
    }

    public static void loadConfig() {
        try {
            Properties defaults = new Properties();
            defaults.setProperty(ENABLE_SPELL_CARD_SIGN_DISPLAY, "true");
            conf = new SpireConfig("VertinMod", "config", defaults);
        } catch (IOException e) {
            ;
        }
    }
    public static boolean enableSpellCardSignDisplay() {
        return conf.getBool(ENABLE_SPELL_CARD_SIGN_DISPLAY);
    }

    public static void setEnableSpellCardSignDisplay(boolean enableSpellCardSignDisplay) {
        conf.setBool(ENABLE_SPELL_CARD_SIGN_DISPLAY, enableSpellCardSignDisplay);
    }

}