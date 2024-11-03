package vertinmod.powers;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import java.util.ArrayList;

import static vertinmod.modcore.VertinMod.Vertin;

public class NihilismPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makePath("NihilismPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static ArrayList<Ver_CustomCard> RetainCard = new ArrayList<>();

    public NihilismPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        String path128 = "ModVertinResources/img/powers/Nihilism84.png";
        String path48 = "ModVertinResources/img/powers/Nihilism32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.description = DESCRIPTIONS[0];
        this.isTurnBased = true;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (!c.isEthereal && c instanceof Ver_CustomCard && !c.hasTag(Vertin)) {
                    c.retain = true;
                    RetainCard.add((Ver_CustomCard)c.makeStatEquivalentCopy());
                }
            }
        }
    }

    public void atEndOfRound() {
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "VertinMod:NihilismPower"));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, "VertinMod:NihilismPower", 1));
        }
    }

    public void atStartOfTurn(){
        for(Ver_CustomCard c : RetainCard){
            if(!c.upgraded) {
                CardModifierManager.addModifier(c, (AbstractCardModifier) new EtherealMod());
                addToTop((AbstractGameAction) new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
            }
        }
        RetainCard = new ArrayList<>();
    }
}
