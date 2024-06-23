package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.NewBabel;

public class New_Wave extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(New_Wave.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/New_Wave.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public New_Wave(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 9;
        this.block = this.baseBlock;
        this.cardsToPreview = new Future_Is_Near();
        this.tags.add(Arcanist);
        this.tags.add(NewBabel);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower("Strength")) {
            if(!upgraded)
                this.baseBlock = 9 + AbstractDungeon.player.getPower("Strength").amount;
            else
                this.baseBlock = 12 + AbstractDungeon.player.getPower("Strength").amount;
            calculateCardDamage(m);
            addToBot(new GainBlockAction(p, this.block));
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
            initializeDescription();
        }
        else
            addToBot(new GainBlockAction(p, this.block));
    }

    public void applyPowers() {
        if(AbstractDungeon.player.hasPower("Strength")) {
            if (!upgraded)
                this.baseBlock = 9 + AbstractDungeon.player.getPower("Strength").amount;
            else
                this.baseBlock = 12 + AbstractDungeon.player.getPower("Strength").amount;
            super.applyPowers();
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
            initializeDescription();
        }
    }

    public void onMoveToDiscard() {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public void upgrade(){
        if (!this.upgraded){
            upgradeName();
            upgradeBlock(3);
        }
    }

    public AbstractCard makeCopy(){
        return new New_Wave();
    }
}
