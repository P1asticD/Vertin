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
import static vertinmod.modcore.VertinMod.*;

public class Restless_Souls extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Restless_Souls.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Restless_Souls.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Restless_Souls(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 16;
        this.damage = this.baseDamage;
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardsToPreview = new Morning_Star_for_the_Night();
        this.tags.add(Arcanist);
        this.tags.add(Mercuria);
    }

    public void applyPowers() {
        super.applyPowers();
        this.baseDamage = this.upgraded ? 20:16;
        int Ddmg = 0;
        for(AbstractCard c: AbstractDungeon.player.hand.group)
            if(!c.upgraded && !c.equals(this))
                Ddmg++;
        baseDamage -= Ddmg;
        this.damage = this.baseDamage;
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.baseDamage = this.upgraded ? 20:16;
        int Ddmg = 0;
        for(AbstractCard c: AbstractDungeon.player.hand.group)
            if(!c.upgraded && !c.equals(this))
                Ddmg++;
        baseDamage -= Ddmg;
        this.damage = this.baseDamage;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            upgradeDamage(4);
        }
    }

    public AbstractCard makeCopy(){
        return new Restless_Souls();
    }
}
