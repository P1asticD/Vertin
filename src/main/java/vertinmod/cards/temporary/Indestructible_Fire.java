package vertinmod.cards.temporary;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.helpers.ModHelper;
import vertinmod.powers.BurnPower;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Indestructible_Fire extends CustomCard{
    public static final String ID = ModHelper.makePath(Indestructible_Fire.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Indestructible_Fire.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Indestructible_Fire(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        for (AbstractMonster monster:AbstractDungeon.getMonsters().monsters) {
            if (monster.hasPower("VertinMod:BurnPower")) {
                addToTop(new ApplyPowerAction(monster, p, new BurnPower(monster, p,
                        (monster.getPower("VertinMod:BurnPower")).amount * 2),
                        (monster.getPower("VertinMod:BurnPower")).amount * 2));
            }
        }
    }

    public void upgrade(){
        if (!upgraded) {
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Indestructible_Fire();
    }
}
