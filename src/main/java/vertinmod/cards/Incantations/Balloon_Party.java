package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTags.HEALING;
import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Balloon_Party extends CustomCard{
    public static final String ID = ModHelper.makePath(Balloon_Party.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Balloon_Party.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL;

    public Balloon_Party(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 0;
        this.baseMagicNumber = 0;
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
        this.tags.add(HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower("Thorns")) {
            this.baseDamage = 3 * AbstractDungeon.player.getPower("Thorns").amount;
            this.baseMagicNumber = AbstractDungeon.player.getPower("Thorns").amount;
            calculateCardDamage(m);
            for (AbstractMonster monster:AbstractDungeon.getMonsters().monsters) {
                addToBot(new DamageAction(monster, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
            }
            addToBot(new HealAction(p, p, this.baseMagicNumber));
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
            initializeDescription();
        }
        else {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
            addToBot(new HealAction(p, p, this.baseMagicNumber));
        }
    }

    public void applyPowers() {
        if(AbstractDungeon.player.hasPower("Thorns")) {
            this.baseDamage = 3 * AbstractDungeon.player.getPower("Thorns").amount;
            this.baseMagicNumber = AbstractDungeon.player.getPower("Thorns").amount;
            super.applyPowers();
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
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
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upgrade(){
        if (!this.upgraded) {
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Balloon_Party();
    }
}
