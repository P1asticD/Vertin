package vertinmod.cards.Incantations;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Necrologist;

public class By_the_Coffin_1 extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(By_the_Coffin_1.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/By_the_Coffin.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public By_the_Coffin_1(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        GraveField.grave.set(this, Boolean.valueOf(true));
        this.cardsToPreview = new Whispers_of_Deceased_1();
        this.tags.add(Arcanist);
        this.tags.add(Necrologist);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterDiscardPileToHandAction(this.magicNumber, 0));
    }

    public void upgrade(){
        if (!this.upgraded){
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy(){
        return new By_the_Coffin_1();
    }
}
