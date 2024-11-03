package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
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

public class Crosswind_Takeoff extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Crosswind_Takeoff.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Crosswind_Takeoff.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = TOKEN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Crosswind_Takeoff(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 8;
        this.damage = this.baseDamage;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
        this.exhaust = true;
        this.tags.add(Arcanist);
        this.tags.add(Lilya);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL)));
    }

    public void applyPowers() {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null)
            strength.amount *= this.magicNumber;
        super.applyPowers();
        if (strength != null)
            strength.amount /= this.magicNumber;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null)
            strength.amount *= this.magicNumber;
        super.calculateCardDamage(mo);
        if (strength != null)
            strength.amount /= this.magicNumber;
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
        return new Crosswind_Takeoff();
    }
}
