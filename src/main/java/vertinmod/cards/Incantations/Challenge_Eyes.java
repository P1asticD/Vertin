package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Regulus;

public class Challenge_Eyes extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Challenge_Eyes.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Challenge_Eyes.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Challenge_Eyes(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 8;
        this.damage = this.baseDamage;
        this.cardsToPreview = new Sleepless_Rave();
        this.isMultiDamage = true;
        this.tags.add(Arcanist);
        this.tags.add(Regulus);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            upgradeDamage(8);
        }
    }

    public AbstractCard makeCopy(){
        return new Challenge_Eyes();
    }
}
