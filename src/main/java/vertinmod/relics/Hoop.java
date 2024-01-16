package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import vertinmod.helpers.ModHelper;

import java.util.ArrayList;

public class Hoop extends CustomRelic implements CustomSavable<int[]> {
    public static final String ID = ModHelper.makePath("Hoop");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Hoop.png";
    private static final AbstractRelic.RelicTier RELIC_TIER = RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound LANDING_SOUND = AbstractRelic.LandingSound.FLAT;
    public int Count_A;
    public int Count_S;
    public int Count_C;

    public Hoop(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        Count_A = 0;
        Count_S = 0;
        Count_C = 0;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1] + DESCRIPTIONS[2] + DESCRIPTIONS[3] + DESCRIPTIONS[4];
    }

    public void onEquip() {
        for (AbstractCard card: AbstractDungeon.player.masterDeck.group){
            if (!card.upgraded){
                float x = MathUtils.random(0.1F, 0.9F) * Settings.WIDTH;
                float y = MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT;
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(card
                        .makeStatEquivalentCopy(), x, y));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
                card.upgrade();
                AbstractDungeon.player.bottledCardUpgradeCheck(card);
            }
        }
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : (AbstractDungeon.player.masterDeck.getPurgeableCards()).group)
            tmp.addToTop(card);
        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, AbstractDungeon.player.masterDeck.size(), this.DESCRIPTIONS[10], false, false, true, false);
        AbstractDungeon.overlayMenu.cancelButton.show(DESCRIPTIONS[11]);
        this.refreshDesc();
    }

    public void onUnequip() {
        for(int i = 0; i < Count_C; i++)
            AbstractDungeon.player.energy.energyMaster--;
    }

    public void update() {
        super.update();
        deleteCards(AbstractDungeon.gridSelectScreen.selectedCards);
    }

    private void refreshDesc(){
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + DESCRIPTIONS[2] + DESCRIPTIONS[3] + DESCRIPTIONS[4];
        if (Count_A != 0 || Count_S != 0)
            this.description = this.description + DESCRIPTIONS[5];
        if (Count_A != 0)
            this.description = this.description + 2 * Count_A + DESCRIPTIONS[6];
        if (Count_S != 0)
            this.description = this.description + 2 * Count_S + DESCRIPTIONS[7];
        if (Count_C != 0)
            this.description = this.description + DESCRIPTIONS[8] + Count_C + DESCRIPTIONS[9];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    public void deleteCards(ArrayList<AbstractCard> group) {
        float displayCount = 0.0F;
        for (AbstractCard card : group) {
            card.untip();
            card.unhover();
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, Settings.WIDTH / 3.0F + displayCount, Settings.HEIGHT / 2.0F));
            displayCount += Settings.WIDTH / 6.0F;
            if (card.type == AbstractCard.CardType.ATTACK)
                Count_A++;
            else if (card.type == AbstractCard.CardType.SKILL)
                Count_S++;
            else if (card.type == AbstractCard.CardType.POWER)
                AbstractDungeon.player.increaseMaxHp(10, true);
            else if (card.type == AbstractCard.CardType.CURSE) {
                AbstractDungeon.player.energy.energyMaster++;
                Count_C++;
            }
            this.refreshDesc();
            AbstractDungeon.player.masterDeck.group.remove(card);
        }
        AbstractDungeon.gridSelectScreen.selectedCards.clear();
    }

    public void atBattleStart() {
        flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2 * Count_A), 2 * Count_A));
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2 * Count_S), 2 * Count_S));
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public AbstractRelic makeCopy() {
        return new Hoop();
    }

    @Override
    public int[] onSave() {
        return new int[]{Count_A, Count_S, Count_C};
    }

    @Override
    public void onLoad(int[] cards){
        Count_A = cards[0];
        Count_S = cards[1];
        Count_C = cards[2];
        this.refreshDesc();
    }
}
