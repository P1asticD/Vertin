package vertinmod.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import vertinmod.helpers.ModHelper;

public class SRegenPotion extends CustomPotion {
    public static final String POTION_ID = ModHelper.makePath(SRegenPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public SRegenPotion(){
        super(potionStrings.NAME, POTION_ID, PotionRarity.RARE, PotionSize.FAIRY, PotionColor.WHITE);
        isThrown = false;
    }

    public void initializeData() {
        this.potency = getPotency();
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(

                TipHelper.capitalize(GameDictionary.REGEN.NAMES[0]), GameDictionary.keywords
                .get(GameDictionary.REGEN.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, this.potency), this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 10;
    }

    public AbstractPotion makeCopy() {
        return new SRegenPotion();
    }
}
