package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.PoisonPotion;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;
import vertinmod.potions.SPoisonPotion;
import vertinmod.potions.TransformationPotion;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Sotheby;

public class Triple_Dose_1 extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Triple_Dose_1.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Triple_Dose.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Triple_Dose_1(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.cardsToPreview = new Mix_All();
        this.tags.add(Arcanist);
        this.tags.add(Sotheby);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        int chance = this.upgraded ? 50 : 60;
        if (AbstractDungeon.cardRandomRng.random(99) <= chance){
            if (!upgraded)
                addToBot(new ObtainPotionAction(new PoisonPotion()));
            else
                addToBot(new ObtainPotionAction(new SPoisonPotion()));
        }
        else
            addToBot(new ObtainPotionAction(new TransformationPotion()));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy(){
        return new Triple_Dose_1();
    }
}
