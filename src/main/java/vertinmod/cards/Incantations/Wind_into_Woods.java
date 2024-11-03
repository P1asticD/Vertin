package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.DruvisIII;

public class Wind_into_Woods extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Wind_into_Woods.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Wind_into_Woods.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Wind_into_Woods(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 7;
        this.damage = this.baseDamage;
        this.cardsToPreview = new Silent_Woods();
        this.tags.add(Arcanist);
        this.tags.add(DruvisIII);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage)));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        canUse = false;
        for (AbstractPower po : p.powers) {
            if (po.type == AbstractPower.PowerType.BUFF) {
                canUse = true;
            }
        }
        this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[1];
        return canUse;
    }

    public void upgrade(){
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(8);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new Wind_into_Woods();
    }
}
