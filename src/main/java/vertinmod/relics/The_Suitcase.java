package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import vertinmod.helpers.ModHelper;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen.HAND_SELECT;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.relics.The_Spinning_Wheel.Characters;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;

public class The_Suitcase extends CustomRelic implements ClickableRelic {
    public static final String ID = ModHelper.makePath("The_Suitcase");
    private static final String IMG_PATH = "ModVertinResources/img/relics/The_Suitcase.png";
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    public static int Count_Ascend = 0;
    public static int MorningStarCount = 0;

    public The_Suitcase(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void atBattleStart(){
        Count_Ascend = 0;
        MorningStarCount = 0;
    }

    public void onRefreshHand(){
        if (AbstractDungeon.screen != HAND_SELECT) {
            if (AbstractDungeon.player.hand.group.size() >= 2) {
                for (int i = 0; i < AbstractDungeon.player.hand.group.size() - 1; i++) {
                    if(AbstractDungeon.player.hand.group.get(i).cardID.equals("VertinMod:Clockwork_Rats") && !AbstractDungeon.player.hand.group.get(i).upgraded){
                        for(int j = i + 1; j < AbstractDungeon.player.hand.group.size(); j++){
                            if(AbstractDungeon.player.hand.group.get(j).cardID.equals("VertinMod:Clockwork_Rats") && !AbstractDungeon.player.hand.group.get(j).upgraded){
                                addToTop(new ExhaustSpecificCardAction(AbstractDungeon.player.hand.group.get(i), AbstractDungeon.player.hand));
                                AbstractDungeon.player.hand.group.get(j).upgrade();
                                Count_Ascend++;
                                if (AbstractDungeon.player.hasPower("VertinMod:GrandPower")){
                                    AbstractDungeon.player.gainEnergy(AbstractDungeon.player.getPower("VertinMod:GrandPower").amount);
                                    addToBot(new DrawCardAction(AbstractDungeon.player, AbstractDungeon.player.getPower("VertinMod:GrandPower").amount));
                                }
                                if(Moxie.get(26) != 0 && Moxie.get(26) < The_Spinning_Wheel.Moxie_Max) {
                                    Moxie.set(26, Moxie.get(26) + 1);
                                }
                            }
                        }
                    }
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

    /*public boolean canUseCampfireOption(AbstractCampfireOption option) {
        if (option instanceof SmithOption && option.getClass().getName().equals(SmithOption.class.getName())) {
            ((SmithOption)option).updateUsability(false);
            return false;
        }
        return true;
    }
    */
    public void onEquip() {
        for (RewardItem reward : AbstractDungeon.combatRewardScreen.rewards) {
            if (reward.cards != null)
                for (AbstractCard c : reward.cards)
                    onPreviewObtainCard(c);
        }
    }

    public void onPreviewObtainCard(AbstractCard c) {
        if(MorningStarCount == 1)
            onObtainCard(c);
    }

    public void onObtainCard(AbstractCard card){
        if(MorningStarCount == 0) {
            if (card.hasTag(Arcanist) && !card.upgraded && card.type != AbstractCard.CardType.POWER)
                AbstractDungeon.player.masterDeck.addToTop(card.makeStatEquivalentCopy());
        }
        else{
            if (card.canUpgrade() && !card.upgraded)
                card.upgrade();
        }
    }


    public void onEnterRoom(AbstractRoom room) {
        MorningStarCount = 0;
    }

    /*public void onVictory() {
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
    }*/

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
    }

    public void onRightClick() {
        if (CardCrawlGame.dungeon != null && AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.currMapNode != null)
            if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
                ModHelper.openTutorial();
    }
    public AbstractRelic makeCopy() {
        return new The_Suitcase();
    }
}
