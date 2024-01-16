package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class RealityShow_Premiere extends CustomCard{
    public static final String ID = ModHelper.makePath(RealityShow_Premiere.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/RealityShow_Premiere.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public RealityShow_Premiere(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 20;
        this.damage = this.baseDamage;
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
        this.tags.add(CardTags.HEALING);
   }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new HealAction(p, p, 10));
        for(AbstractMonster mo: AbstractDungeon.getMonsters().monsters){
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber));
        }
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new RealityShow_Premiere();
    }
}
