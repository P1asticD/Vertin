package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Future_Is_Near extends CustomCard{
    public static final String ID = ModHelper.makePath(Future_Is_Near.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Future_Is_Near.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Future_Is_Near(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        boolean powerExists = false;
        int thorns = 0;
        for (AbstractPower pow : p.powers) {
            if (pow.ID.equals("Barricade")) {
                powerExists = true;
                break;
            }
        }
        if (!powerExists)
            addToBot(new ApplyPowerAction(p, p, new BarricadePower(p)));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, this.magicNumber), this.magicNumber));
        if(AbstractDungeon.player.hasPower("Dexterity"))
            thorns += AbstractDungeon.player.getPower("Dexterity").amount;
        if(AbstractDungeon.player.hasPower("Strength"))
            thorns += AbstractDungeon.player.getPower("Strength").amount;
        if (thorns != 0)
            addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, thorns), thorns));
    }

    public void upgrade(){
        if (!upgraded)
            upgradeName();
    }

    public AbstractCard makeCopy(){
        return new Future_Is_Near();
    }
}
