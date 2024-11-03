package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Centurion;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;

public class Victorious_General extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Victorious_General.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Victorious_General.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Victorious_General(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 7;
        this.damage = this.baseDamage;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new RealityShow_Premiere();
        this.tags.add(Arcanist);
        this.tags.add(Centurion);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        int moxie = Moxie.get(18);
        if (moxie > 1) {
            this.baseDamage = 7 + (moxie - 1) * magicNumber;
            calculateCardDamage(m);
            addToBot(new DamageAction(m, new DamageInfo(p, damage   , DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
            initializeDescription();
        }
        else
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void applyPowers() {
        if(Moxie.get(18) > 1) {
            this.baseDamage = 7 + (Moxie.get(18) - 1) * magicNumber;
            super.applyPowers();
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
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(2);
        }
    }

    public AbstractCard makeCopy(){
        return new Victorious_General();
    }
}
