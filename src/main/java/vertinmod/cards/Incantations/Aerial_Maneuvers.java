package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.TOKEN_CARD;
import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Lilya;

public class Aerial_Maneuvers extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Aerial_Maneuvers.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Aerial_Maneuvers.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TOKEN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Aerial_Maneuvers(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 8;
        this.block = this.baseBlock;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
        this.exhaust = true;
        this.tags.add(Arcanist);
        this.tags.add(Lilya);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
    }
    public void applyPowers() {
        AbstractPower dexterity = AbstractDungeon.player.getPower("Dexterity");
        if (dexterity != null)
            dexterity.amount *= this.magicNumber;
        super.applyPowers();
        if (dexterity != null)
            dexterity.amount /= this.magicNumber;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower dexterity = AbstractDungeon.player.getPower("Dexterity");
        if (dexterity != null)
            dexterity.amount *= this.magicNumber;
        super.calculateCardDamage(mo);
        if (dexterity != null)
            dexterity.amount /= this.magicNumber;
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy(){
        return new Aerial_Maneuvers();
    }
}
