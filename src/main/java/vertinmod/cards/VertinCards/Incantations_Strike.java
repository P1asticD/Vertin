package vertinmod.cards.VertinCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Vertin;
import static vertinmod.relics.The_Suitcase.Count_Ascend;

public class Incantations_Strike extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Incantations_Strike.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Strike.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Incantations_Strike(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 0;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(Vertin);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        this.baseMagicNumber = Count_Ascend;
        this.magicNumber = this.baseMagicNumber;
        for (int i = 0; i < this.magicNumber; i++)
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void applyPowers() {
        super.applyPowers();
        this.baseMagicNumber = Count_Ascend;
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

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            upgradeDamage(3);
        }
    }

    public AbstractCard makeCopy(){
        return new Incantations_Strike();
    }
}
