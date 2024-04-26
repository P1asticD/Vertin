package vertinmod.cards.others;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Writhe;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;

public class Paper_Slip extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Paper_Slip.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Paper_Slip.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;

    public Paper_Slip(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        FleetingField.fleeting.set(this, Boolean.valueOf(true));
        this.cardsToPreview = new Adapted_Song();
        this.isEthereal = true;
        this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        boolean canGet = true;
        for (AbstractCard c: p.masterDeck.group) {
            if (c.equals(new Adapted_Song())){
                canGet = false;
                break;
            }
        }
        if (canGet){
            addToBot(new AddCardToDeckAction(new Adapted_Song()));
            addToBot(new AddCardToDeckAction(new Writhe()));
        }
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        int count = 0;
        for(AbstractMonster m2: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m2.isDeadOrEscaped()){
                count++;
            }
        }
        return count == 1;
    }

    public AbstractCard makeCopy(){
        return new Paper_Slip();
    }
}
