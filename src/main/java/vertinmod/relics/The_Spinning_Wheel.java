package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import vertinmod.cards.Incantations.*;
import vertinmod.cards.temporary.*;
import vertinmod.helpers.ModHelper;
import vertinmod.ui.button.CampfireSummonButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static vertinmod.modcore.VertinMod.*;

public class The_Spinning_Wheel extends CustomRelic implements BetterClickableRelic<The_Spinning_Wheel>, CustomSavable<ArrayList<Integer>> {
    public static final String ID = ModHelper.makePath("The_Spinning_Wheel");
    private static final String IMG_PATH = "ModVertinResources/img/relics/The_Spinning_Wheel.png";
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;
    private boolean cardSelected = true;
    public static RoomPhase phase;
    private static CurrentScreen pre;
    public static int Moxie_Max = 6;
    public static ArrayList<Integer> Moxie = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    public static final ArrayList<AbstractCard.CardTags> Characters = new ArrayList<>(Arrays.asList(Fool, JohnTitor, Eagle, Pavia, Mondlicht, Poltergeist, BabyBlue, Dikke, Sonetto, Balloon, Necrologist, Tennant, Diggers, Ulu, Lilya, Knight, Sotheby, Regulus, Centurion, Voyager, NewBabel, BlackDwarf, Ezra, DruvisIII, Jessica, Kanjira, Melania, Pickles, Mercuria));
    public static final ArrayList<AbstractCard> Ult = new ArrayList<>(Arrays.asList(new Improvised_Show(), new Bytes_65536(), new Superficiality_And_Reality(), new Noisy_Wolves(), new Hunting_Wolves(), new Not_Gentle_Sun(), new Tea_Party(), new Maverick_Judge(), new Unrestricted_Chant(), new Balloon_Party(), new Whispers_of_Deceased_1(), new Beautiful_Lie(), new Sweet_Dreams(), new Indestructible_Fire(), new Soaring_Witch(), new After_AD_778(), new Mix_All(), new Sleepless_Rave(), new RealityShow_Premiere(), new Strings_Galaxy(), new Future_Is_Near(), new Lunar_Divination(), new Comprehensive_Care(), new Silent_Woods(), new Gaze_From_the_Forest(), new Song_of_Wandering(), new Ridiculous_Testimony(), new Thus_Spoke_Pickles(), new Morning_Star_for_the_Night()));
    public static int Count_Melania = 0;

    public The_Spinning_Wheel(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        this.counter = 0;
        this.setDuration(2000).addRightClickActions(
                () -> this.card()
        );
        for(int i = 0; i < Moxie.size(); i++){
            Moxie.set(i, 0);
        }
        Moxie.set(17, 1);
    }

    public void atBattleStart(){
        this.counter = 0;
        Count_Melania = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    private void refreshDesc(){
        this.description = this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2] + this.DESCRIPTIONS[3];
        for(int i = 0; i < Moxie.size(); i++){
            if(Moxie.get(i) != 0){
                int Now_Moxie = Moxie.get(i) - 1;
                int Now_Max = Moxie_Max - 1;
                this.description = this.description + DESCRIPTIONS[i + 6] + Now_Moxie + "/" + Now_Max;
            }
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    public AbstractRelic makeCopy() {
        return new The_Spinning_Wheel();
    }

    /*public void onObtainCard(AbstractCard c){
        for(int i = 0; i < Characters.size(); i++){
            if(c.hasTag((Characters.get(i)))){
                if(Moxie.get(i) == 0){
                    Moxie.set(i, 1);
                    this.refreshDesc();
                }
                break;
            }
        }
    }*/

    public void onUseCard(AbstractCard card, UseCardAction action) {
        for(int i = 0; i < Characters.size(); i++){
            if(card.hasTag(Characters.get(i))){
                if(Moxie.get(i) != 0 && Moxie.get(i) < Moxie_Max) {
                    Moxie.set(i, Moxie.get(i) + 1);
                    this.refreshDesc();
                }
                break;
            }
        }
    }

    public void onRefreshHand(){
        this.refreshDesc();
    }

    private void card() {
        int Ult_Count = 1;
        if (AbstractDungeon.player.hasPower("VertinMod:PigeonPower"))
            Ult_Count += AbstractDungeon.player.getPower("VertinMod:PigeonPower").amount;
        if (this.counter < Ult_Count) {
            this.counter++;
            if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                pre = AbstractDungeon.screen;
                if (AbstractDungeon.isScreenUp) {
                    AbstractDungeon.dynamicBanner.hide();
                    AbstractDungeon.previousScreen = AbstractDungeon.screen;
                }
                phase = AbstractDungeon.getCurrRoom().phase;
                AbstractDungeon.getCurrRoom().phase = RoomPhase.INCOMPLETE;
                this.cardSelected = false;
                CardGroup g = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                ArrayList<AbstractCard> list = new ArrayList<>();
                for (int i = 0; i < Moxie.size(); i++) {
                    if (Moxie.get(i) == Moxie_Max) {
                        list.add(Ult.get(i));
                    }
                }
                g.group = list.stream().collect(Collectors.toCollection(ArrayList::new));
                g.shuffle();
                if(g.group.size() > 0)
                    AbstractDungeon.gridSelectScreen.open(g, 1, DESCRIPTIONS[4], false, false, false, false);
                else {
                    counter--;
                    AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, this.DESCRIPTIONS[35], true));
                }
            }
        }
        else if(Moxie.get(26) == Moxie_Max){
            counter++;
            if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                pre = AbstractDungeon.screen;
                if (AbstractDungeon.isScreenUp) {
                    AbstractDungeon.dynamicBanner.hide();
                    AbstractDungeon.previousScreen = AbstractDungeon.screen;
                }
                phase = AbstractDungeon.getCurrRoom().phase;
                AbstractDungeon.getCurrRoom().phase = RoomPhase.INCOMPLETE;
                this.cardSelected = false;
                CardGroup g = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                ArrayList<AbstractCard> list = new ArrayList<>();
                if(Moxie.get(26) == Moxie_Max)
                    list.add(Ult.get(26));
                g.group = list.stream().collect(Collectors.toCollection(ArrayList::new));
                g.shuffle();
                if(g.group.size() > 0)
                    AbstractDungeon.gridSelectScreen.open(g, 1, DESCRIPTIONS[4], false, false, false, false);
                else {
                    AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, this.DESCRIPTIONS[34], true));
                }
            }
        }
        else
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, this.DESCRIPTIONS[5], true));
    }

    public void update() {
        super.update();
        if (!cardSelected) {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
                if (AbstractDungeon.player.hand.size() < 10) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(AbstractDungeon.gridSelectScreen.selectedCards.get(0)));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(AbstractDungeon.gridSelectScreen.selectedCards.get(0)));
                }
                for (int i = 0; i < Ult.size(); i++){
                    if (AbstractDungeon.gridSelectScreen.selectedCards.get(0) == Ult.get(i)){
                        Moxie.set(i, Moxie.get(i) - 5);
                        if (i == 26){
                            counter--;
                            Count_Melania++;
                        }
                        this.refreshDesc();
                    }
                }
            } else if (AbstractDungeon.screen != pre) {
                return;
            }
            cardSelected = true;
            AbstractDungeon.getCurrRoom().phase = phase;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    public void onVictory(){
        for(int i = 0; i < Moxie.size(); i++) {
            if (Moxie.get(i) >= 1)
                Moxie.set(i, 1);
        }
        this.refreshDesc();
    }

    @Override
    public ArrayList<Integer> onSave() {
        return Moxie;
    }

    @Override
    public void onLoad(ArrayList<Integer> L){
        Moxie = L;
        this.refreshDesc();
    }

    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        boolean hasArcanist = false;
        for (AbstractCard c: AbstractDungeon.player.masterDeck.group){
            if(c.hasTag(Arcanist)) {
                int index_C = -1;
                for(AbstractCard.CardTags t:c.tags){
                    if(Characters.contains(t)){
                        index_C = Characters.indexOf(t);
                        break;
                    }
                }
                if(Moxie.get(index_C) == 0) {
                    hasArcanist = true;
                    break;
                }
            }
        }
        options.add(new CampfireSummonButton(hasArcanist));
    }
}
