package vertinmod.cards.Incantations;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTags.HEALING;
import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Dikke;

public class Justice extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Justice.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Justice.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Justice(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.cardsToPreview = new Maverick_Judge();
        this.tags.add(HEALING);
        this.tags.add(Arcanist);
        this.tags.add(Dikke);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new AddTemporaryHPAction(p, p, this.magicNumber));
        if (p.currentHealth < p.maxHealth / 2.0F)
            addToBot(new HealAction(p, p, 5));
    }

    public void upgrade(){
        if (!this.upgraded) {
            upgradeBaseCost(0);
            upgradeName();
            upgradeMagicNumber(5);
        }
    }

    public AbstractCard makeCopy(){
        return new Justice();
    }
}
