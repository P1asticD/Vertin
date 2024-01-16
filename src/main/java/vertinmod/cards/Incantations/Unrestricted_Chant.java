package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Unrestricted_Chant extends CustomCard{
    public static final String ID = ModHelper.makePath(Unrestricted_Chant.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Unrestricted_Chant.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Unrestricted_Chant(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 14;
        this.damage = this.baseDamage;
        this.isMultiDamage = true;
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        int st = p.getPower("Strength").amount;
        int de = p.getPower("Dexterity").amount;
        if (st > 0 && de > 0) {
            for(int i = 0; i < Math.min(st, de); i++)
                addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        else
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void upgrade(){
        if (!this.upgraded) {
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Unrestricted_Chant();
    }
}
