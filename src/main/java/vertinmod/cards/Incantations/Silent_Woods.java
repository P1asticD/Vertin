package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.MalleablePower;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Silent_Woods extends CustomCard {
    public static final String ID = ModHelper.makePath(Silent_Woods.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Silent_Woods.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL;

    public Silent_Woods(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        if (p.hasPower("Malleable")){
            int mal = p.getPower("Malleable").amount;
            addToTop(new ApplyPowerAction(p, p, new MalleablePower(p, mal), mal));
        }
        AbstractMonster stun = null;
        int max_con = 0;
        for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters){
            if (monster.hasPower("Constricted")){
                int con = monster.getPower("Constricted").amount;
                if(con > max_con){
                    stun = monster;
                    max_con = con;
                }
                addToTop(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, con), con));
            }
        }
        if (stun != null){
            addToBot(new StunMonsterAction(stun, p, 1));
        }
    }

    public void upgrade(){
        if (!this.upgraded) {
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Silent_Woods();
    }
}
