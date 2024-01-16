package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.NewBabel;

public class Old_Idea extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Old_Idea.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Old_Idea.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Old_Idea(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 8;
        this.damage = this.baseDamage;
        this.tags.add(Arcanist);
        this.tags.add(NewBabel);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower("Dexterity")) {
            if(!upgraded)
                this.baseDamage = 8 + AbstractDungeon.player.getPower("Dexterity").amount;
            else
                this.baseDamage = 12 + AbstractDungeon.player.getPower("Dexterity").amount;
            calculateCardDamage(m);
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
            initializeDescription();
        }
        else
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
    }

    public void applyPowers() {
        if(AbstractDungeon.player.hasPower("Dexterity")) {
            if(!upgraded)
                this.baseDamage = 8 + AbstractDungeon.player.getPower("Dexterity").amount;
            else
                this.baseDamage = 12 + AbstractDungeon.player.getPower("Dexterity").amount;
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
        if (!this.upgraded){
            upgradeName();
            upgradeDamage(4);
        }
    }

    public AbstractCard makeCopy(){
        return new Old_Idea();
    }
}
