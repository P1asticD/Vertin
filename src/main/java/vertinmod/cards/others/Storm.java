package vertinmod.cards.others;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import vertinmod.helpers.ModHelper;

import java.util.ArrayList;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;

public class Storm extends CustomCard {
    public static final String ID = ModHelper.makePath(Storm.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Storm.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.CURSE;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.CURSE;
    private static final CardTarget TARGET = CardTarget.NONE;

    public Storm(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        SoulboundField.soulbound.set(this, Boolean.valueOf(true));
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m){}

    public void upgrade(){}

    public void triggerWhenDrawn() {
        int count = 0;
        ArrayList <AbstractCard> cards = new ArrayList<>();
        for (AbstractCard card: AbstractDungeon.player.hand.group){
            if(card.upgraded) {
                cards.add(card.makeCopy());
                addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
                count++;
            }
        }
        for(AbstractCard c: cards){
            addToBot(new MakeTempCardInHandAction(c));
        }
        if(count > 0) {
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -count), -count));
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, -count), -count));
        }
    }

    public AbstractCard makeCopy(){
        return new Storm();
    }
}
