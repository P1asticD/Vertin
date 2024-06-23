package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

public class Apple extends CustomRelic {
    public static final String ID = ModHelper.makePath("Apple");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Apple.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Apple() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void onEquip(){
        AbstractDungeon.player.increaseMaxHp(10, true);
    }

    public void atBattleStart() {
        int HEAL_AMOUNT = 1;
        if (AbstractDungeon.player.hasRelic("Strawberry"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("Meat on the Bone"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("Pear"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("Ginger"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("Ice Cream"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("Mango"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("Turnip"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("Odd Mushroom"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("Lee's Waffle"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("Orange Pellets"))
            HEAL_AMOUNT++;
        if (AbstractDungeon.player.hasRelic("VertinMod:Carrot"))
            HEAL_AMOUNT++;
        flash();
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, HEAL_AMOUNT, 0.0F));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Apple();
    }
}
