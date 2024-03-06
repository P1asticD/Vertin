package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.SmithOption;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import vertinmod.helpers.ModHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen.HAND_SELECT;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Vertin;
import static vertinmod.relics.The_Spinning_Wheel.Characters;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;

public class The_Suitcase extends CustomRelic implements BetterClickableRelic<The_Suitcase>{
    public static final String ID = ModHelper.makePath("The_Suitcase");
    private static final String IMG_PATH = "ModVertinResources/img/relics/The_Suitcase.png";
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    public static int Count_Ascend = 0;

    public The_Suitcase(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        this.counter = 0;
        this.setDuration(2000).addRightClickActions(
                () -> this.Prepared1()
        );
    }

    public void atTurnStart(){
        this.counter = 0;
    }

    public void atBattleStart(){
        Count_Ascend = 0;
    }

    public void Prepared1(){
        if (this.counter == 0) {
            if (EnergyPanel.getCurrentEnergy() > 0) {
                this.counter = 1;
                addToBot(new LoseEnergyAction(1));
                addToBot(new DrawCardAction(AbstractDungeon.player, 3));
                addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, false));
            }
            else{
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, this.DESCRIPTIONS[4], true));
            }
        }
        else
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, this.DESCRIPTIONS[5], true));
    }

    public void onRefreshHand(){
        if (AbstractDungeon.screen != HAND_SELECT) {
            if (AbstractDungeon.player.hand.group.size() >= 2) {
                for (int i = 0; i < AbstractDungeon.player.hand.group.size() - 1; i++) {
                    if (AbstractDungeon.player.hand.group.get(i).hasTag(Arcanist) && !AbstractDungeon.player.hand.group.get(i).upgraded && !AbstractDungeon.player.hand.group.get(i + 1).upgraded && AbstractDungeon.player.hand.group.get(i).cardID.equals(AbstractDungeon.player.hand.group.get(i + 1).cardID)) {
                        addToTop(new ExhaustSpecificCardAction(AbstractDungeon.player.hand.group.get(i), AbstractDungeon.player.hand));
                        AbstractDungeon.player.hand.group.get(i + 1).upgrade();
                        Count_Ascend++;
                        if (AbstractDungeon.player.hasPower("VertinMod:GrandPower")){
                            AbstractDungeon.player.gainEnergy(AbstractDungeon.player.getPower("VertinMod:GrandPower").amount);
                            addToBot(new DrawCardAction(AbstractDungeon.player, AbstractDungeon.player.getPower("VertinMod:GrandPower").amount));
                        }
                        for(int j = 0; j < Characters.size(); j++){
                            if(AbstractDungeon.player.hand.group.get(i + 1).hasTag(Characters.get(j))){
                                if(Moxie.get(j) != 0 && Moxie.get(j) < The_Spinning_Wheel.Moxie_Max) {
                                    Moxie.set(j, Moxie.get(j) + 1);
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean canUseCampfireOption(AbstractCampfireOption option) {
        if (option instanceof SmithOption && option.getClass().getName().equals(SmithOption.class.getName())) {
            ((SmithOption)option).updateUsability(false);
            return false;
        }
        return true;
    }

    public void onObtainCard(AbstractCard card){
        if(card.hasTag(Arcanist) && !card.upgraded)
            AbstractDungeon.player.masterDeck.addToTop(card.makeStatEquivalentCopy());
    }

    public void onVictory() {
        Count_Ascend = 0;
        ArrayList<AbstractCard> upgradableCards = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade() && c.hasTag(Vertin))
                upgradableCards.add(c);
        }
        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
        if (!upgradableCards.isEmpty()){
            flash();
            (upgradableCards.get(0)).upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
            AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect((upgradableCards.get(0)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F - 20.0F * Settings.scale, Settings.HEIGHT / 2.0F));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2] + this.DESCRIPTIONS[3];
    }

    public AbstractRelic makeCopy() {
        return new The_Suitcase();
    }
}
