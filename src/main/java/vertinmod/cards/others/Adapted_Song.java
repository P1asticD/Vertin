package vertinmod.cards.others;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;

public class Adapted_Song extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Adapted_Song.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Adapted_Song.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;

    public Adapted_Song(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        FleetingField.fleeting.set(this, Boolean.valueOf(true));
        this.cardsToPreview = new Jail_Break();
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        boolean canGet = true;
        for (AbstractCard c: p.masterDeck.group) {
            if (c.equals(new Jail_Break())){
                canGet = false;
                break;
            }
        }
        if (canGet){
            addToBot(new AddCardToDeckAction(new Jail_Break()));
            addToBot(new AddCardToDeckAction(new Injury()));
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
        return AbstractDungeon.getMonsters().monsters.size() >= 3;
    }

    public AbstractCard makeCopy(){
        return new Adapted_Song();
    }
}