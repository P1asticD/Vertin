package vertinmod.deprecated;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Whispers_of_Deceased extends CustomCard{
    public static final String ID = ModHelper.makePath(Whispers_of_Deceased.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Whispers_of_Deceased.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = VERTIN_CARD;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

    public Whispers_of_Deceased(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 0;
        this.exhaust = true;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        int D_percentage = 0;
        for (AbstractPotion potion: AbstractDungeon.player.potions) {
            if (potion.ID.equals("Regen Potion")) {
                D_percentage += 20;
            }
            if (potion.ID.equals("FairyPotion")) {
                D_percentage += 30;
            }
        }
        this.baseMagicNumber = D_percentage;
        calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, baseMagicNumber * m.maxHealth / 100, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
        for (AbstractPotion potion: AbstractDungeon.player.potions) {
            if (potion.ID.equals("Regen Potion")) {
                AbstractDungeon.player.removePotion(potion);
            }
            if (potion.ID.equals("FairyPotion")) {
                AbstractDungeon.player.removePotion(potion);
            }
        }
    }

    public void applyPowers() {
        int D_percentage = 0;
        for (AbstractPotion potion: AbstractDungeon.player.potions) {
            if (potion.ID.equals("Regen Potion")) {
                D_percentage += 20;
            }
            if (potion.ID.equals("FairyPotion")) {
                D_percentage += 30;
            }
        }
        this.baseMagicNumber = D_percentage;
        super.applyPowers();
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upgrade(){
        if(!upgraded)
            upgradeName();
    }

    public AbstractCard makeCopy(){
        return new Whispers_of_Deceased();
    }
}
