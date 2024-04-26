package vertinmod.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import vertinmod.actions.MorphingAction;
import vertinmod.helpers.ModHelper;

public class TransformationPotion extends CustomPotion {
    public static final String POTION_ID = ModHelper.makePath(TransformationPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public TransformationPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.WHITE);
        isThrown = false;
    }
    public void initializeData() {
        this.description = String.format(DESCRIPTIONS[0]);
        this.tips.clear();
        this.tips.add(new PowerTip(potionStrings.NAME, this.description));
    }
    public void use(AbstractCreature target) {
        addToBot(new VFXAction(new SmokeBombEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
        addToBot(new MorphingAction());
    }
    public int getPotency(int ascensionLevel) {
        return 0;
    }
    public AbstractPotion makeCopy() {
        return new TransformationPotion();
    }
}
