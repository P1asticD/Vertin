package vertinmod.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import vertinmod.helpers.ModHelper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.relics.The_Spinning_Wheel.Characters;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;


public class CampfireSummonEffect extends AbstractGameEffect {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ModHelper.makePath("SummonEffect"));

    public static final String[] TEXT = uiStrings.TEXT;

    private static final float DUR = 1.5F;

    private boolean openedScreen = false;

    private Color screenColor = AbstractDungeon.fadeColor.cpy();

    public CampfireSummonEffect() {
        this.duration = 1.5F;
        this.screenColor.a = 0.0F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    public void update() {
        if (!AbstractDungeon.isScreenUp) {
            this.duration -= Gdx.graphics.getDeltaTime();
            updateBlackScreenColor();
        }
        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractCard.CardTags Summoning = null;
            for(AbstractCard.CardTags tag: Characters){
                if(c.hasTag(tag)){
                    Summoning = tag;
                    break;
                }
            }
            for(int i = 0; i < Characters.size(); i++){
                if(Characters.get(i).equals((Summoning))){
                    if(Moxie.get(i) == 0){
                        Moxie.set(i, 1);
                    }
                    break;
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        if (this.duration < 1.0F && !this.openedScreen) {
            this.openedScreen = true;
            CardGroup g = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            ArrayList<AbstractCard> list = new ArrayList<>();
            for(AbstractCard card:AbstractDungeon.player.masterDeck.group){
                if(card.hasTag(Arcanist)){
                    int index_C = -1;
                    for(AbstractCard.CardTags t:card.tags){
                        if(Characters.contains(t)){
                            index_C = Characters.indexOf(t);
                            break;
                        }
                    }
                    if(Moxie.get(index_C) == 0) {
                        list.add(card);
                    }
                }
            }
            g.group = list.stream().collect(Collectors.toCollection(ArrayList::new));
            g.shuffle();
            if(g.group.size() > 0)
                AbstractDungeon.gridSelectScreen.open(g, 1, TEXT[0], false, false, true, false);
        }
        if (this.duration < 0.0F) {
            this.isDone = true;
            if (CampfireUI.hidden) {
                AbstractRoom.waitTimer = 0.0F;
                (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
            }
        }
    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
        } else {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID)
            AbstractDungeon.gridSelectScreen.render(sb);
    }

    public void dispose() {}
}
