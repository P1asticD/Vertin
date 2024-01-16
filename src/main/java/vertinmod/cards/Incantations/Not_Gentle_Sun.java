package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import vertinmod.helpers.ModHelper;
import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Not_Gentle_Sun extends CustomCard {
    public static final String ID = ModHelper.makePath(Not_Gentle_Sun.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Not_Gentle_Sun.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Not_Gentle_Sun(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 0;
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        this.baseMagicNumber = AbstractDungeon.player.exhaustPile.size();
        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 2), 2));
        if (this.baseMagicNumber > 0)
            addToBot(new ApplyPowerAction(p, p, new RegenPower(p, this.baseMagicNumber), this.baseMagicNumber));
    }

    public void applyPowers() {
        this.baseMagicNumber = AbstractDungeon.player.exhaustPile.size();
        super.applyPowers();
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public void upgrade(){
        if (!this.upgraded) {
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Not_Gentle_Sun();
    }
}
