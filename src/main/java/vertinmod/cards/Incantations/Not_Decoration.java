package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;
import vertinmod.modcore.VertinMod;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.relics.The_Suitcase.Count_Ascend;

public class Not_Decoration extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Not_Decoration.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Not_Decoration.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public Not_Decoration(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Bytes_65536();
        this.tags.add(VertinMod.JohnTitor);
        this.tags.add(Arcanist);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        if(this.upgraded)
            this.baseMagicNumber = 3;
        else
            this.baseMagicNumber = 1;
        this.baseMagicNumber += Count_Ascend;
        this.magicNumber = this.baseMagicNumber;
        addToBot(new DrawCardAction(p, this.magicNumber));
    }

    public void applyPowers() {
        super.applyPowers();
        if(this.upgraded)
            this.baseMagicNumber = 3;
        else
            this.baseMagicNumber = 1;
        this.baseMagicNumber += Count_Ascend;
        this.magicNumber = this.baseMagicNumber;
        if (this.baseMagicNumber > 0) {
            this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    public void onMoveToDiscard() {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (this.baseMagicNumber > 0)
            this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new Not_Decoration();
    }
}
