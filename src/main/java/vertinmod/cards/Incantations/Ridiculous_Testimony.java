package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;
import static vertinmod.relics.The_Spinning_Wheel.Count_Melania;

public class Ridiculous_Testimony extends CustomCard {
    public static final String ID = ModHelper.makePath(Ridiculous_Testimony.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Ridiculous_Testimony.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Ridiculous_Testimony() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 0;
        this.damage = this.baseDamage = 0;
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = Count_Melania;
        this.damage = this.baseDamage;
        this.baseMagicNumber = Count_Melania;
        this.magicNumber = this.baseMagicNumber;
        for(int i = 0; i < this.magicNumber; i++)
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public void applyPowers() {
        super.applyPowers();
        this.baseMagicNumber = Count_Melania;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = Count_Melania;
        this.damage = this.baseDamage;
        if (this.baseMagicNumber > 0) {
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
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
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    public AbstractCard makeCopy() {
        return new Ridiculous_Testimony();
    }
}
