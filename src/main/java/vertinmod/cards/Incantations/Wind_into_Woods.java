package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Wind_into_Woods(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Silent_Woods();
        this.tags.add(Arcanist);
        this.tags.add(DruvisIII);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        if  (!upgraded) {
            addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, this.magicNumber)));
        }
        else{
            for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters){
                if (monster != null && !monster.isDeadOrEscaped()){
                    addToBot(new ApplyPowerAction(monster, p, new ConstrictedPower(monster, p, this.magicNumber), this.magicNumber));
                }
            }
        }
    }

    public void upgrade(){
        if (!this.upgraded) {
            this.upgradeName();
            this.target = AbstractCard.CardTarget.ALL_ENEMY;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new Wind_into_Woods();
    }
}
