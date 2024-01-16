package vertinmod.cards.temporary;

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
import static vertinmod.modcore.VertinMod.Mondlicht;

public class A_Silver_Bullet extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(A_Silver_Bullet.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/A_Silver_Bullet.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public A_Silver_Bullet(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 8;
        this.damage = this.baseDamage;
        this.tags.add(Mondlicht);
        this.tags.add(Arcanist);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        if (m.currentHealth >= m.maxHealth / 2.0F) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL)));
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL)));
        }
        else
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL)));
    }

    public void upgrade(){
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    public AbstractCard makeCopy() {
        return new A_Silver_Bullet();
    }
}
