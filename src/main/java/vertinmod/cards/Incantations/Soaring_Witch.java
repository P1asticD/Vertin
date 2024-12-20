package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FlightPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import vertinmod.helpers.ModHelper;
import vertinmod.powers.LilyaPower;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Soaring_Witch extends CustomCard{
    public static final String ID = ModHelper.makePath(Soaring_Witch.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Soaring_Witch.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Soaring_Witch(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        int tmp = (p.currentHealth - 10) / 10;
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber * tmp), this.magicNumber * tmp));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber * tmp), this.magicNumber * tmp));
        addToBot(new ApplyPowerAction(p, p, new FlightPower(p, 99), 99));
        addToBot(new ApplyPowerAction(p, p, new LilyaPower(p, p.currentHealth), p.currentHealth));
        p.currentHealth = 10;
        p.healthBarUpdatedEvent();
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Soaring_Witch();
    }
}
