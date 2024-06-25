package vertinmod.ui.button;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import vertinmod.effect.CampfireSummonEffect;
import vertinmod.helpers.ModHelper;

public class CampfireSummonButton extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ModHelper.makePath("SummonButton"));

    public static final String[] TEXT = uiStrings.TEXT;

    private static final String buttonImg = "ModVertinResources/img/UI/button/Summon.png";

    public CampfireSummonButton(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = TEXT[1];
        this.img = ImageMaster.loadImage(buttonImg);
    }

    public void useOption() {
        if (this.usable)
            AbstractDungeon.effectList.add(new CampfireSummonEffect());
    }
}
