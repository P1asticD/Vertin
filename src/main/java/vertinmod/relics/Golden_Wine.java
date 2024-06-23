package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

public class Golden_Wine extends CustomRelic implements CustomSavable<int[]> {
    public static final String ID = ModHelper.makePath("Golden_Wine");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Golden_Wine.png";
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private int last_gold;

    public Golden_Wine(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void onEquip(){
        last_gold = AbstractDungeon.player.gold;
    }

    public void onGainGold() {
        flash();
        int heal_amount = AbstractDungeon.player.gold - last_gold;
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.player.heal(heal_amount, true);
        last_gold = AbstractDungeon.player.gold;
        if(AbstractDungeon.player.maxHealth > 10)
            AbstractDungeon.player.decreaseMaxHealth(1);
    }

    public void onSpendGold(){
        last_gold = AbstractDungeon.player.gold;
    }

    public void onLoseGold(){
        last_gold = AbstractDungeon.player.gold;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Golden_Wine();
    }

    @Override
    public int[] onSave() {
        return new int[] {last_gold};
    }

    @Override
    public void onLoad(int[] g){
        last_gold = g[0];
    }
}
