package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Mix_All extends CustomCard{
    public static final String ID = ModHelper.makePath(Mix_All.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Mix_All.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL;

    public Mix_All(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        if (p.hasPower("Regeneration")){
            int regen = p.getPower("Regeneration").amount;
            if (regen < 65536)
                addToTop(new HealAction(p, p, (regen + 1) * regen / 2));
            else
                addToTop(new HealAction(p, p, Integer.MAX_VALUE));
            addToBot(new RemoveSpecificPowerAction(p, p, "Regeneration"));
        }
        for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters){
            if (monster.hasPower("Poison")){
                int poison = monster.getPower("Poison").amount;
                if (poison < 65536)
                    addToTop(new DamageAction(monster, new DamageInfo(p, (poison + 1) * poison / 2, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
                else
                    addToTop(new DamageAction(monster, new DamageInfo(p, Integer.MAX_VALUE, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
                addToBot(new RemoveSpecificPowerAction(monster, monster, "Poison"));
            }
        }
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Mix_All();
    }
}
